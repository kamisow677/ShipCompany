package kamil.sowa.shipscompany.cruise;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cruises")
@Slf4j
public class CruiseController {

    private final CruiseService cruiseService;

    @GetMapping
    public ResponseEntity<Page<CruiseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(cruiseService.getAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CruiseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(cruiseService.get(id));
    }

    @PostMapping
    public ResponseEntity<CruiseDto> save(@RequestBody CruiseDto cruiseDto){
        return ResponseEntity.ok(cruiseService.save(cruiseDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Long id){
        cruiseService.delete(id);
        return ResponseEntity.noContent();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<CruiseDto> save(@PathVariable Long id, @RequestBody CruiseDto cruiseDto){
        return ResponseEntity.ok(cruiseService.put(id, cruiseDto));
    }

}
