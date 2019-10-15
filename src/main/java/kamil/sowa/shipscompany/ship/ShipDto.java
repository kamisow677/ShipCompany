package kamil.sowa.shipscompany.ship;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ShipDto {

    @Id
    private Long id;

    @NotBlank
    private String model;

    @NotNull
    private Integer serialNumber;
}
