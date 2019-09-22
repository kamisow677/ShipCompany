package kamil.sowa.shipscompany.passanger;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PassengerDto {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    Long cruiseId;

}
