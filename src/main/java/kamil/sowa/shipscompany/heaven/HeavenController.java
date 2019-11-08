package kamil.sowa.shipscompany.heaven;

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
@RequestMapping("/heavens")
@Slf4j
public class HeavenController {

    private final HeavenService heavenService;

    @GetMapping
    public ResponseEntity<Page<HeavenDto>> getAllPassenger(Pageable pageable) {
        return ResponseEntity.ok(heavenService.getAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HeavenDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(heavenService.get(id));
    }

    @PostMapping
    public ResponseEntity<HeavenDto> save(@RequestBody HeavenDto heavenDto){
        return ResponseEntity.ok(heavenService.save(heavenDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Long id){
        heavenService.delete(id);
        return ResponseEntity.noContent();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<HeavenDto> update(@PathVariable Long id, @RequestBody HeavenDto heavenDto){
        return ResponseEntity.ok(heavenService.put(id, heavenDto));
    }

}
