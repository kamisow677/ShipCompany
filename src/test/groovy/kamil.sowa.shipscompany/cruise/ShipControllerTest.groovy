package kamil.sowa.shipscompany.cruise


import groovy.json.JsonSlurper
import kamil.sowa.shipscompany.ShipscompanyApplication
import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.ship.ShipDto
import kamil.sowa.shipscompany.ship.ShipRepository
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.ShipConstans
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [ShipscompanyApplication.class])
@RequiredArgsConstructor
@AutoConfigureMockMvc
class ShipControllerTest extends Specification {
    private static Long[] IDS = [1L, 2L]
    private Ship ship1 = ShipConstans.createShip1(IDS[0]).build()
    private Ship ship2 = ShipConstans.createShip2(IDS[1]).build()
    private ShipDto shipDto = ShipConstans.createShipDto1(IDS[0]).build()

    @Autowired
    private ShipRepository shipRepository

    @Autowired
    private MockMvc mockMvc

    def jsonSlurper = new JsonSlurper()

    def cleanup() {
        shipRepository.deleteAll()
    }

    private void startDatabase() {
        shipRepository.save(ship1)
        shipRepository.save(ship2)
    }

    def "get all Ship"() {
        given:
        startDatabase()
        def shipDummies = [ship1, ship2]

        when:
        def results = mockMvc.perform(get('/ships'))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results).content

        result.eachWithIndex { def entry, int i ->
            entry.id == shipDummies[i].id
            entry.model == shipDummies[i].model
            entry.serialNumber == shipDummies[i].serialNumber
        }
    }

    def "get specified Ship"() {
        given:
        startDatabase()
        def dummy = ship1

        when:
        def results = mockMvc.perform(get('/ships/{id}', dummy.getId()))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        verifyAll {
            result.id == dummy.id
            result.model == dummy.model
            result.serialNumber == dummy.serialNumber
        }
    }

//    def "save specified Cruise"() {
//        given:
//        startDatabase()
//        def cruiseDummies = [cruiseDto1]
//
//        when:
//        def results = mockMvc.perform(post('/cruises')
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(generator.toJson(cruiseDummies[0])))
//                .andExpect(status().isOk())
//                .andReturn().response.contentAsString
//
//        then:
//        def result = jsonSlurper.parseText(results)
//
//        result.eachWithIndex { def entry, int i ->
//            entry.id == cruiseDummies[i].id
//            entry.arrival == cruiseDummies[i].arrival
//            entry.departure == cruiseDummies[i].departure
//            entry.shipId == cruiseDummies[i].shipId
//        }
//    }

}
