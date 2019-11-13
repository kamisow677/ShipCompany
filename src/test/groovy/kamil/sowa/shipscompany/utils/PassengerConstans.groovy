package kamil.sowa.shipscompany.utils

import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.passanger.Passenger
import kamil.sowa.shipscompany.passanger.PassengerDto
import kamil.sowa.shipscompany.ship.Ship
import kamil.sowa.shipscompany.ship.ShipDto

class PassengerConstans {
    private static String[] FIRSTNAME = ["Adam", "John","Tom"]
    private static String[] LASTNAME = ["Smith", "Newmann","Ford"]

    static Passenger.PassengerBuilder createPassenger1(Long id) {
        return new Passenger.PassengerBuilder()
                .id(id)
                .firstName(FIRSTNAME[0])
                .lastName(LASTNAME[0])

    }

    static Passenger.PassengerBuilder createPassenger1(Long id, Ship ship) {
        return new Passenger.PassengerBuilder()
                .id(id)
                .firstName(FIRSTNAME[0])
                .lastName(LASTNAME[0])
                .ship(ship)

    }

    static Passenger.PassengerBuilder createPassenger2(Long id, Ship ship) {
        return new Passenger.PassengerBuilder()
                .id(id)
                .firstName(FIRSTNAME[1])
                .lastName(LASTNAME[1])
                .ship(ship)

    }

    static Passenger.PassengerBuilder createPassenger2(Long id) {
        return new Passenger.PassengerBuilder()
                .id(id)
                .firstName(FIRSTNAME[1])
                .lastName(LASTNAME[1])
    }

    static PassengerDto.PassengerDtoBuilder createPassengerDto1(
            Long id, Long shipId) {
        return new PassengerDto.PassengerDtoBuilder()
                .id(id)
                .firstName(FIRSTNAME[2])
                .lastName(LASTNAME[2])
                .shipId(shipId)
    }

    static PassengerDto.PassengerDtoBuilder createPassengerDto1(
            Long id) {
        return new PassengerDto.PassengerDtoBuilder()
                .id(id)
                .firstName(FIRSTNAME[2])
                .lastName(LASTNAME[2])
    }
}
