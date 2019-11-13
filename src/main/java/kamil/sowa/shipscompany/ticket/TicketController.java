package kamil.sowa.shipscompany.ticket;

import kamil.sowa.shipscompany.ship.ShipDto;
import kamil.sowa.shipscompany.ship.ShipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
@Slf4j
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<Page<TicketDto>> getAllShips(Pageable pageable) {
        return ResponseEntity.ok(ticketService.getAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.get(id));
    }

    @PostMapping
    public ResponseEntity<TicketDto> save(@RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.save(ticketDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Long id){
        ticketService.delete(id);
        return ResponseEntity.noContent();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<TicketDto> update(@PathVariable Long id, @RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.put(id, ticketDto));
    }

}
