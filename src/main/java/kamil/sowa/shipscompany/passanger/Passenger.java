package kamil.sowa.shipscompany.passanger;

import kamil.sowa.shipscompany.cruise.Cruise;
import kamil.sowa.shipscompany.ship.Ship;
import kamil.sowa.shipscompany.ticket.Ticket;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id")
    Ship ship;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "passenger")
    private List<Ticket> tickets;

}
