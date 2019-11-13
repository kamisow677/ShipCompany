package kamil.sowa.shipscompany.ticket;

import kamil.sowa.shipscompany.ship.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
