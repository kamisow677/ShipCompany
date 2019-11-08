package kamil.sowa.shipscompany.ship;

import jline.internal.Nullable;
import kamil.sowa.shipscompany.cruise.Cruise;
import kamil.sowa.shipscompany.heaven.Heaven;
import kamil.sowa.shipscompany.passanger.Passenger;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ship {

    @Id
    @NotNull
    private Long id;

    @NotBlank
    private String model;

    @NotNull
    private Integer serialNumber;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "ship")
    private List<Cruise> cruises;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "ship")
    private List<Passenger> passengers;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heaven_id")
    Heaven heaven;

}
