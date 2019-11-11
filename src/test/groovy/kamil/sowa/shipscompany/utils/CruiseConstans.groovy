package kamil.sowa.shipscompany.utils

import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.cruise.CruiseDto
import kamil.sowa.shipscompany.heaven.Heaven;
import kamil.sowa.shipscompany.passanger.Passenger
import kamil.sowa.shipscompany.ship.Ship

import java.time.LocalDateTime

class CruiseConstans {
    private static LocalDateTime[] DEPARTURES = [
            LocalDateTime.of(2014, 2, 1, 10, 10, 30)
            , LocalDateTime.of(2018, 2, 1, 10, 10, 30)
            , LocalDateTime.of(2019, 3, 2, 12, 10, 30)
    ]
    private static LocalDateTime[] ARRIVALS = [
            LocalDateTime.of(2015, 2, 1, 10, 10, 30)
            , LocalDateTime.of(2018, 2, 1, 10, 10, 30)
            , LocalDateTime.of(2019, 2, 1, 10, 10, 30)
    ]

    static Cruise.CruiseBuilder createCruise1(Long id, Ship ship, Heaven departureHeaven) {
        return new Cruise.CruiseBuilder()
                .id(id)
                .ship(ship)
                .heavenDeparture(departureHeaven)
                .arrival(ARRIVALS[0])
                .departure(DEPARTURES[0])
    }

    static Cruise.CruiseBuilder createCruise2(Long id, Ship ship, Heaven departureHeaven) {
        return new Cruise.CruiseBuilder()
                .id(id)
                .ship(ship)
                .heavenDeparture(departureHeaven)
                .arrival(ARRIVALS[1])
                .departure(DEPARTURES[1])
    }

    static CruiseDto.CruiseDtoBuilder createCruiseDto1(Long id, Long shipId, Long departureHeavenId) {
        return new CruiseDto.CruiseDtoBuilder()
                .id(id)
                .shipId(shipId)
                .heavenDepartureId(departureHeavenId)
                .arrival(ARRIVALS[2])
                .departure(DEPARTURES[2])
    }
}
