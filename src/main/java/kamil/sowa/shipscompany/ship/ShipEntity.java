package kamil.sowa.shipscompany.ship;

import kamil.sowa.shipscompany.cruise.CruiseEntity;
import kamil.sowa.shipscompany.passanger.PassengerEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder
public class ShipEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String model;

    @NotNull
    private Integer serialNumber;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "ship_entity")
    private List<CruiseEntity> cruises;

}
