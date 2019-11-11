package kamil.sowa.shipscompany.heaven;
import kamil.sowa.shipscompany.cruise.Cruise;
import kamil.sowa.shipscompany.passanger.Passenger;
import kamil.sowa.shipscompany.ship.Ship;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Heaven {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "heavenAim")
    private List<Cruise> heavenAimEntities;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "heavenDeparture")
    private List<Cruise> heavenDepartureEntities;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "heaven")
    private List<Ship> shipsEntities;

}
