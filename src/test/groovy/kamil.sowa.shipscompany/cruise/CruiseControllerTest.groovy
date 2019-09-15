package kamil.sowa.shipscompany.cruise

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.ship.ShipRepository
import kamil.sowa.shipscompany.utils.CruiseConstans
import kamil.sowa.shipscompany.utils.ShipConstans
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@RequiredArgsConstructor
class CruiseControllerTest extends Specification {
    private static Long[] IDS = [1L,2L]
    private  Ship ship1 = ShipConstans.createShip1(IDS[0]).build()
    private  Ship ship2 = ShipConstans.createShip2(IDS[1]).build()
    private  Cruise cruise1 = CruiseConstans.createCruise1(IDS[0],ship1).build()
    private  Cruise cruise2 = CruiseConstans.createCruise2(IDS[1],ship2).build()

    @Autowired
    private CruiseRepository cruiseRepository
    @Autowired
    private ShipRepository shipRepository

    private final MockMvc mockMvc
    private final JsonOutput jsonOutput
    private final JsonSlurper jsonSlurper

    def cleanup(){
        shipRepository.deleteAll()
        cruiseRepository.deleteAll()
    }

    private void startDatabase(){
        shipRepository.save(ship1)
        shipRepository.save(ship2)
        cruiseRepository.save(cruise1)
        cruiseRepository.save(cruise2)
    }

    def "get all Cruise" (){
        given:
        startDatabase()
        def cruiseDummies = [cruise1,cruise2]

        when:
        def results = mockMvc.perform(get('/cruises'))
                .andExpect(status().isOk())
                .andReturn().response

        then:
        def result = jsonSlurper.parse(results)

        result.eachWithIndex{ def entry, int i ->
           entry.id == cruiseDummies[i].id
           entry.arrival == cruiseDummies[i].arrival
           entry.departure == cruiseDummies[i].departure
           entry.ship.id == cruiseDummies[i].ship.id
        }
    }
}
