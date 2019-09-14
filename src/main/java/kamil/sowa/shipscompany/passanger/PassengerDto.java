package kamil.sowa.shipscompany.passanger;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class PassengerDto {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private Integer lastName;

}
