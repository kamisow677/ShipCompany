package kamil.sowa.shipscompany.cruise;

import kamil.sowa.shipscompany.heaven.Heaven;
import kamil.sowa.shipscompany.passanger.Passenger;
import kamil.sowa.shipscompany.ship.Ship;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cruise {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private LocalDateTime departure;

    @NotNull
    private LocalDateTime arrival;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "heavenAim_id")
//    private Heaven heavenAim;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "heavenDeparture_id")
//    private Heaven heavenDeparture;

    @OneToMany(fetch = FetchType.LAZY,
    mappedBy = "cruise")
    private List<Passenger> passengerEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id")
    private Ship ship;

}
