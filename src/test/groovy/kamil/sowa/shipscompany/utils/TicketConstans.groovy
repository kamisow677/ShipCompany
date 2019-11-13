package kamil.sowa.shipscompany.utils

import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.passanger.Passenger
import kamil.sowa.shipscompany.ticket.Ticket
import kamil.sowa.shipscompany.ticket.TicketDto
import kamil.sowa.shipscompany.heaven.Heaven
import kamil.sowa.shipscompany.ship.Ship

import java.time.LocalDateTime

class TicketConstans {
    private static BigDecimal[] PRICES = [new BigDecimal(2), new BigDecimal(6), new BigDecimal(4)]

    static Ticket.TicketBuilder createTicket1(Long id, Passenger passenger, Cruise cruise) {
        return new Ticket.TicketBuilder()
                .id(id)
                .price(PRICES[0])
                .passenger(passenger)
                .cruise(cruise)
    }

    static Ticket.TicketBuilder createTicket2(Long id, Passenger passenger, Cruise cruise) {
        return new Ticket.TicketBuilder()
                .id(id)
                .price(PRICES[1])
                .passenger(passenger)
                .cruise(cruise)
    }

    static TicketDto.TicketDtoBuilder createTicketDto1(Long id, Long passengerId, Long cruiseId) {
        return new TicketDto.TicketDtoBuilder()
                .id(id)
                .passengerId(passengerId)
                .price(PRICES[2])
                .cruiseId(cruiseId)
    }
}
