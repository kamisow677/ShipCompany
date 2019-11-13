package kamil.sowa.shipscompany.tickets

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import kamil.sowa.shipscompany.ShipscompanyApplication
import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.cruise.CruiseRepository
import kamil.sowa.shipscompany.heaven.Heaven
import kamil.sowa.shipscompany.heaven.HeavenRepository
import kamil.sowa.shipscompany.passanger.Passenger
import kamil.sowa.shipscompany.passanger.PassengerDto
import kamil.sowa.shipscompany.passanger.PassengerRepository
import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.ship.ShipRepository
import kamil.sowa.shipscompany.ticket.TicketDto
import kamil.sowa.shipscompany.ticket.TicketRepository
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.HeavenConstans
import kamil.sowa.shipscompany.utils.PassengerConstans
import kamil.sowa.shipscompany.utils.ShipConstans
import kamil.sowa.shipscompany.utils.TicketConstans
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kamil.sowa.shipscompany.ticket.Ticket

@SpringBootTest(classes = [ShipscompanyApplication.class])
@RequiredArgsConstructor
@AutoConfigureMockMvc
class TicketControllerTest extends Specification {
    private static Long[] IDS = [1L, 2L, 3L]
    //will be dumped -------------------------
    private Heaven heaven1 = HeavenConstans.createHeaven1(IDS[0]).build()
    private Ship ship1 = ShipConstans.createShip1(IDS[0]).build()
    private Cruise cruise1 = CruiseConstans.createCruise1(IDS[0], ship1, heaven1).build()
    //-----------------------
    private Passenger passenger = PassengerConstans.createPassenger1(IDS[0]).build()
    private Ticket ticket1 = TicketConstans.createTicket1(IDS[0], passenger, cruise1).build()
    private Ticket ticket2 = TicketConstans.createTicket2(IDS[1], passenger, cruise1).build()
    private TicketDto ticketDto = TicketConstans.createTicketDto1(
            IDS[2],passenger.getId(), cruise1.getId()).build()

    @Autowired
    private CruiseRepository cruiseRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private HeavenRepository heavenRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MockMvc mockMvc

    def jsonSlurper = new JsonSlurper()

    def cleanup() {
        ticketRepository.deleteAll()
        passengerRepository.deleteAll()
        cruiseRepository.deleteAll()
        shipRepository.deleteAll()
        heavenRepository.deleteAll()
    }

    def startDatabase() {
        heavenRepository.save(heaven1)
        shipRepository.save(ship1)
        cruiseRepository.save(cruise1)
        passengerRepository.save(passenger)
        ticketRepository.save(ticket1)
        ticketRepository.save(ticket2)

    }

    def "get all Ticket"() {
        given:
        startDatabase()
        def passengerDummies = [ticket1, ticket2]

        when:
        def results = mockMvc.perform(get('/tickets'))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results).content

        expect:
        result.eachWithIndex { def entry, int i ->
            entry.id == passengerDummies[i].id
            entry.price == passengerDummies[i].price
            entry.passengerId == passengerDummies[i].passenger.id
            entry.cruiseId == passengerDummies[i].cruise.id

        }
    }

    def "get specified Ticket"() {
        given:
        startDatabase()
        def dummy = ticket1

        when:
        def results = mockMvc.perform(get('/tickets/{id}', dummy.getId()))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == dummy.id
            result.price == dummy.price
            result.passengerId == dummy.passenger.id
            result.cruiseId == dummy.cruise.id
        }
    }

    def "save specified Ticket"() {
        given:
        startDatabase()
        def passengerDummy = ticketDto

        when:
        def results = mockMvc.perform(post('/tickets')
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonOutput.toJson(passengerDummy)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == passengerDummy.id
            result.price == passengerDummy.price
            result.passengerId == passengerDummy.passengerId
            result.cruiseId == passengerDummy.cruiseId
        }
    }

    def "update specified Ticket"() {
        given:
        startDatabase()
        def passengersDummy = ticketDto

        when:
        def results = mockMvc.perform(put('/tickets/{id}', ticket1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonOutput.toJson(passengersDummy)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == ticket1.getId()
            result.price == passengersDummy.price
            result.passengerId == passengersDummy.passengerId
            result.cruiseId == passengersDummy.cruiseId
        }
    }
}
