package kamil.sowa.shipscompany.ticket;

import kamil.sowa.shipscompany.passanger.Passenger;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketDto {

    @Id
    private Long id;

    @NotNull
    private BigDecimal price;

    @NotNull
    Long passengerId;

    @NotNull
    Long cruiseId;
}
