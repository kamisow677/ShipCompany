package kamil.sowa.shipscompany.cruise;

import kamil.sowa.shipscompany.passanger.PassengerEntity;
import kamil.sowa.shipscompany.ship.ShipEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class CruiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime departure;

    @NotNull
    private LocalDateTime arrival;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY,
    mappedBy = "cruise_entity")
    private List<PassengerEntity> passengerEntities;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id")
    private ShipEntity ship;

}
