package kamil.sowa.shipscompany.heaven

import groovy.json.JsonSlurper
import kamil.sowa.shipscompany.ShipscompanyApplication
import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.cruise.CruiseDto
import kamil.sowa.shipscompany.cruise.CruiseRepository
import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.ship.ShipRepository
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.HeavenConstans
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [ShipscompanyApplication.class])
@RequiredArgsConstructor
@AutoConfigureMockMvc
class HeavenControllerTest extends Specification {
    private static Long[] IDS = [1L, 2L, 3L]
    private Heaven heaven1 = HeavenConstans.createHeaven1(IDS[0]).build()
    private Heaven heaven2 = HeavenConstans.createHeaven2(IDS[1]).build()
    private HeavenDto heavenDto1 = HeavenConstans.createHeavenDto1(IDS[2]).build()

    @Autowired
    private HeavenRepository heavenRepository

    @Autowired
    private ShipRepository shipRepository

    @Autowired
    private MockMvc mockMvc

    def jsonSlurper = new JsonSlurper()

    def cleanup() {
        heavenRepository.deleteAll()
    }

    def setup(){
        shipRepository.deleteAll()
        heavenRepository.deleteAll()
    }

    def generator = new groovy.json.JsonGenerator.Options()
            .addConverter(LocalDateTime) {
                it.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }
            .build()

    private void startDatabase() {
        heavenRepository.save(heaven1)
        heavenRepository.save(heaven2)
    }

    def "get all Heaven"() {
        given:
        startDatabase()
        def heavenDummies = [heaven1, heaven2]

        when:
        def results = mockMvc.perform(get('/heavens'))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results).content

        expect:
        result.eachWithIndex { def entry, int i ->
            entry.id == heavenDummies[i].id
            entry.name == heavenDummies[i].name
        }
    }

    def "get specified Heaven"() {
        given:
        startDatabase()
        def cruiseDummy = heaven1

        when:
        def results = mockMvc.perform(get('/heavens/{id}', heaven1.getId()))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == cruiseDummy.id
            result.name == cruiseDummy.name.toString()
        }
    }

    def "save specified Heaven"() {
        given:
        startDatabase()
        def cruiseDummies = heavenDto1

        when:
        def results = mockMvc.perform(post('/heavens')
                .contentType(MediaType.APPLICATION_JSON)
                .content(generator.toJson(cruiseDummies)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == cruiseDummies.id
            result.name == cruiseDummies.name.toString()
        }
    }

    def "update specified Heaven"() {
        given:
        startDatabase()
        def cruiseDummies = heavenDto1

        when:
        def results = mockMvc.perform(put('/heavens/{id}', heaven2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(generator.toJson(cruiseDummies)))
                .andExpect(status().isOk())
                .andReturn().response.contentAsString

        then:
        def result = jsonSlurper.parseText(results)

        expect:
        verifyAll {
            result.id == heaven2.getId()
            result.name == cruiseDummies.name.toString()
        }
    }
}
