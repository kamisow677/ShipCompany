package kamil.sowa.shipscompany.ships

import groovy.json.JsonOutput
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
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [ShipscompanyApplication.class])
@RequiredArgsConstructor
@AutoConfigureMockMvc
class ShipControllerTest extends Specification {
    private static Long[] IDS = [1L, 2L, 3L]
    private Ship ship1 = ShipConstans.createShip1(IDS[0]).build()
    private Ship ship2 = ShipConstans.createShip2(IDS[1]).build()
    private ShipDto shipDto = ShipConstans.createShipDto1(IDS[2]).build()

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

        expect:
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

        expect:
        verifyAll {
            result.id == dummy.id
            result.model == dummy.model
            result.serialNumber == dummy.serialNumber
        }
    }

    def "save specified Ship"() {
        given:
        startDatabase()
        def shipDummy = shipDto

        when:
        def results = mockMvc.perform(post('/ships')
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonOutput.toJson(shipDummy)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == shipDummy.id
            result.model == shipDummy.model
            result.serialNumber == shipDummy.serialNumber
        }
    }

    def "update specified Ship"() {
        given:
        startDatabase()
        def shipDummy = shipDto

        when:
        def results = mockMvc.perform(put('/ships/{id}', ship2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonOutput.toJson(shipDummy)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == ship2.getId()
            result.model == shipDummy.model
            result.serialNumber == shipDummy.serialNumber
        }
    }
}
