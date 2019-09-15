package kamil.sowa.shipscompany.cruise;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class CruiseDto {

    private Long id;

    @NotNull
    private LocalDateTime departure;

    @NotNull
    private LocalDateTime arrival;

    @NotNull
    private Long shipId;

}
