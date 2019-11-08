package kamil.sowa.shipscompany.utils

import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.heaven.Heaven
import kamil.sowa.shipscompany.heaven.HeavenDto
import kamil.sowa.shipscompany.passanger.Passenger
import kamil.sowa.shipscompany.passanger.PassengerDto
import kamil.sowa.shipscompany.ship.Ship

class HeavenConstans {
    private static String[] NAME = ["Breslau", "Danzig","Gleiwitz"]

    static Heaven.HeavenBuilder createHeaven1(Long id) {
        return new Heaven.HeavenBuilder()
                .id(id)
                .name(NAME[0])
    }

    static Heaven.HeavenBuilder createHeaven2(Long id) {
        return new Heaven.HeavenBuilder()
                .id(id)
                .name(NAME[1])
    }

    static HeavenDto.HeavenDtoBuilder  createHeavenDto1(
            Long id) {
        return new HeavenDto.HeavenDtoBuilder ()
                .id(id)
                .name(NAME[2])
    }
}
