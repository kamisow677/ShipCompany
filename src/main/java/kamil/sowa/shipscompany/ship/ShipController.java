package kamil.sowa.shipscompany.ship;

import kamil.sowa.shipscompany.passanger.PassengerDto;
import kamil.sowa.shipscompany.passanger.PassengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ships")
@Slf4j
public class ShipController {

    private final ShipService shipService;

    @GetMapping
    public ResponseEntity<Page<ShipDto>> getAllShips(Pageable pageable) {
        return ResponseEntity.ok(shipService.getAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ShipDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(shipService.get(id));
    }

    @PostMapping
    public ResponseEntity<ShipDto> save(@RequestBody ShipDto shipDto){
        return ResponseEntity.ok(shipService.save(shipDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Long id){
        shipService.delete(id);
        return ResponseEntity.noContent();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<ShipDto> save(@PathVariable Long id, @RequestBody ShipDto shipDto){
        return ResponseEntity.ok(shipService.put(id, shipDto));
    }

}
