package kamil.sowa.shipscompany.ship;

import kamil.sowa.shipscompany.cruise.Cruise;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String model;

    @NotNull
    private Integer serialNumber;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "ship")
    private List<Cruise> cruises;

}
