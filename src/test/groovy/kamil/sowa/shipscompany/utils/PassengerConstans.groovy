package kamil.sowa.shipscompany.utils

import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.passanger.Passenger
import kamil.sowa.shipscompany.passanger.PassengerDto
import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.ship.ShipDto

class PassengerConstans {
    private static String[] FIRSTNAME = ["Adam", "John"]
    private static String[] LASTNAME = ["Smith", "Newmann"]

    static Passenger.PassengerBuilder createPassenger1(Long id, Cruise cruise) {
        return new Passenger.PassengerBuilder()
                .id(id)
                .firstName(FIRSTNAME[0])
                .lastName(LASTNAME[0])
                .cruise(cruise)

    }

    static Passenger.PassengerBuilder createPassenger2(Long id, Cruise cruise) {
        return new Passenger.PassengerBuilder()
                .id(id)
                .firstName(FIRSTNAME[1])
                .lastName(LASTNAME[1])
                .cruise(cruise)

    }

    static PassengerDto.PassengerDtoBuilder createPassengerDto1(Long id, Integer cruiseId) {
        return new PassengerDto.PassengerDtoBuilder()
                .id(id)
                .firstName(FIRSTNAME[1])
                .lastName(LASTNAME[1])
                .cruiseId(cruiseId)
    }
}
