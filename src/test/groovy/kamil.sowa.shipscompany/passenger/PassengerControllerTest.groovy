package kamil.sowa.shipscompany.passenger

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
import kamil.sowa.shipscompany.ship.ShipDto
import kamil.sowa.shipscompany.ship.ShipRepository
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.HeavenConstans
import kamil.sowa.shipscompany.utils.PassengerConstans
import kamil.sowa.shipscompany.utils.ShipConstans
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [ShipscompanyApplication.class])
@RequiredArgsConstructor
@AutoConfigureMockMvc
class PassengerControllerTest extends Specification {
    private static Long[] IDS = [1L, 2L, 3L]
    private Heaven heaven1 = HeavenConstans.createHeaven1(IDS[0]).build()
    private Cruise cruise1 = CruiseConstans.createCruise1(IDS[0], null, heaven1).build()
    private Ship ship1 = ShipConstans.createShip1(IDS[0]).build()
    private Passenger passenger1 = PassengerConstans.createPassenger1(IDS[0],cruise1, ship1).build()
    private Passenger passenger2 = PassengerConstans.createPassenger1(IDS[1],cruise1, ship1).build()
    private PassengerDto passengerDto = PassengerConstans.createPassengerDto1(
            IDS[2],cruise1.getId(), ship1.getId()).build()

    @Autowired
    private CruiseRepository cruiseRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private HeavenRepository heavenRepository;

    @Autowired
    private MockMvc mockMvc

    def jsonSlurper = new JsonSlurper()

    def cleanup() {
        passengerRepository.deleteAll()
        cruiseRepository.deleteAll()
        shipRepository.deleteAll()
        heavenRepository.deleteAll()
    }

    def startDatabase() {
        heavenRepository.save(heaven1)
        cruiseRepository.save(cruise1)
        shipRepository.save(ship1)
        passengerRepository.save(passenger1)
        passengerRepository.save(passenger2)
    }

    def "get all Passenger"() {
        given:
        startDatabase()
        def passengerDummies = [passenger1, passenger2]

        when:
        def results = mockMvc.perform(get('/passengers'))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results).content

        expect:
        result.eachWithIndex { def entry, int i ->
            entry.id == passengerDummies[i].id
            entry.firstName == passengerDummies[i].firstName
            entry.lastName == passengerDummies[i].lastName
            entry.cruiseId == passengerDummies[i].cruise.id
            entry.shipId == passengerDummies[i].ship.id

        }
    }

    def "get specified Passenger"() {
        given:
        startDatabase()
        def dummy = passenger1

        when:
        def results = mockMvc.perform(get('/passengers/{id}', dummy.getId()))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == dummy.id
            result.firstName == dummy.firstName
            result.lastName == dummy.lastName
            result.cruiseId == dummy.cruise.id
            result.shipId == dummy.ship.id
        }
    }

    def "save specified Passenger"() {
        given:
        startDatabase()
        def passengerDummy = passengerDto

        when:
        def results = mockMvc.perform(post('/passengers')
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonOutput.toJson(passengerDummy)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == passengerDummy.id
            result.firstName == passengerDummy.firstName
            result.lastName == passengerDummy.lastName
            result.cruiseId == passengerDummy.cruiseId
            result.shipId == passengerDummy.shipId
        }
    }

    def "update specified Passenger"() {
        given:
        startDatabase()
        def passengersDummy = passengerDto

        when:
        def results = mockMvc.perform(put('/passengers/{id}', passenger1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonOutput.toJson(passengersDummy)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == cruise1.getId()
            result.firstName == passengersDummy.firstName
            result.lastName == passengersDummy.lastName
            result.cruiseId == passengersDummy.cruiseId
            result.shipId == passengersDummy.shipId
        }
    }
}
