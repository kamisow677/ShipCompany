package kamil.sowa.shipscompany.passanger;

import kamil.sowa.shipscompany.cruise.CruiseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder(toBuilder=true)
public class PassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private Integer lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cruise_id")
    CruiseEntity cruise;

}
