package kamil.sowa.shipscompany.ship;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ShipDto {

    @Id
    private Long id;

    @NotNull
    private String model;

    @NotNull
    private Integer serialNumber;
}
