package kamil.sowa.shipscompany.ticket;

import jline.internal.Nullable;
import kamil.sowa.shipscompany.cruise.Cruise;
import kamil.sowa.shipscompany.heaven.Heaven;
import kamil.sowa.shipscompany.passanger.Passenger;
import kamil.sowa.shipscompany.ship.Ship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private BigDecimal price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    Passenger passenger;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cruise_id")
    Cruise cruise;

}
