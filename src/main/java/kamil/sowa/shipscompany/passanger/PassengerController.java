package kamil.sowa.shipscompany.passanger;

import kamil.sowa.shipscompany.cruise.CruiseDto;
import kamil.sowa.shipscompany.cruise.CruiseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passengers")
@Slf4j
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    public ResponseEntity<Page<PassengerDto>> getAllPassenger(Pageable pageable) {
        return ResponseEntity.ok(passengerService.getAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PassengerDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.get(id));
    }

    @PostMapping
    public ResponseEntity<PassengerDto> save(@RequestBody PassengerDto passengerDto){
        return ResponseEntity.ok(passengerService.save(passengerDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Long id){
        passengerService.delete(id);
        return ResponseEntity.noContent();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<PassengerDto> save(@PathVariable Long id, @RequestBody PassengerDto passengerDto){
        return ResponseEntity.ok(passengerService.put(id, passengerDto));
    }

}
