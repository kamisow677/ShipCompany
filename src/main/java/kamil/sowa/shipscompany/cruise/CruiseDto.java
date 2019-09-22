package kamil.sowa.shipscompany.cruise;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CruiseDto {

    private Long id;

    @NotNull
    private LocalDateTime departure;

    @NotNull
    private LocalDateTime arrival;

    @NotNull
    private Long shipId;

}
