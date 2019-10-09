package kamil.sowa.shipscompany.cruise

import groovy.json.JsonSlurper
import kamil.sowa.shipscompany.ShipscompanyApplication
import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.ship.ShipRepository
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.ShipConstans
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [ShipscompanyApplication.class])
@RequiredArgsConstructor
@AutoConfigureMockMvc
class CruiseControllerTest extends Specification {
    private static Long[] IDS = [1L, 2L, 3L]
    private Ship ship1 = ShipConstans.createShip1(IDS[0]).build()
    private Ship ship2 = ShipConstans.createShip2(IDS[1]).build()
    private Cruise cruise1 = CruiseConstans.createCruise1(IDS[0], ship1).build()
    private Cruise cruise2 = CruiseConstans.createCruise2(IDS[1], ship2).build()
    private CruiseDto cruiseDto1 = CruiseConstans.createCruiseDto1(IDS[2], ship1.getId()).build()

    @Autowired
    private CruiseRepository cruiseRepository

    @Autowired
    private ShipRepository shipRepository

    @Autowired
    private MockMvc mockMvc

    def jsonSlurper = new JsonSlurper()

    def cleanup() {
        cruiseRepository.deleteAll()
        shipRepository.deleteAll()
    }

    def generator = new groovy.json.JsonGenerator.Options()
            .addConverter(LocalDateTime) {
                it.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }
            .build()

    private void startDatabase() {
        shipRepository.save(ship1)
        shipRepository.save(ship2)
        cruiseRepository.save(cruise1)
        cruiseRepository.save(cruise2)
    }

    def "get all Cruise"() {
        given:
        startDatabase()
        def cruiseDummies = [cruise1, cruise2]

        when:
        def results = mockMvc.perform(get('/cruises'))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results).content

        result.eachWithIndex { def entry, int i ->
            entry.id == cruiseDummies[i].id
            entry.arrival == cruiseDummies[i].arrival
            entry.departure == cruiseDummies[i].departure
            entry.shipId == cruiseDummies[i].ship.id
        }
    }

    def "get specified Cruise"() {
        given:
        startDatabase()
        def cruiseDummy = cruise1

        when:
        def results = mockMvc.perform(get('/cruises/{id}', cruise1.getId()))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        verifyAll {
            result.id == cruiseDummy.id
            result.arrival == cruiseDummy.arrival.toString()
            result.departure == cruiseDummy.departure.toString()
            result.shipId == cruiseDummy.ship.id
        }
    }

    def "save specified Cruise"() {
        given:
        startDatabase()
        def cruiseDummies = cruiseDto1

        when:
        def results = mockMvc.perform(post('/cruises')
                .contentType(MediaType.APPLICATION_JSON)
                .content(generator.toJson(cruiseDummies)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        verifyAll {
            result.id == cruiseDummies.id
            result.arrival == cruiseDummies.arrival.toString()
            result.departure == cruiseDummies.departure.toString()
            result.shipId == cruiseDummies.shipId
        }
    }

    def "update specified Cruise"() {
        given:
        startDatabase()
        def cruiseDummies = cruiseDto1

        when:
        def results = mockMvc.perform(put('/cruises/{id}', cruise2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(generator.toJson(cruiseDummies)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        verifyAll {
            result.id == cruise2.getId()
            result.arrival == cruiseDummies.arrival.toString()
            result.departure == cruiseDummies.departure.toString()
            result.shipId == cruiseDummies.shipId
        }
    }
}
