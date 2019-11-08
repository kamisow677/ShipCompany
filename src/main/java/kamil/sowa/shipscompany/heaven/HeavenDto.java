package kamil.sowa.shipscompany.heaven;

import kamil.sowa.shipscompany.ship.Ship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class HeavenDto {

    private Long id;

    private String name;
}
