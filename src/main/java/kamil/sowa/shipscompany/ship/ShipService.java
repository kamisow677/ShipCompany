package kamil.sowa.shipscompany.ship;

import kamil.sowa.shipscompany.cruise.Cruise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipService {
    private final ShipRepository shipRepository;
    private final ShipDtoMapper shipDtoMapper;

    public Page<ShipDto> getAll(Pageable pageable) {
        return shipRepository.findAll(pageable)
                .map(shipDtoMapper::entityToDto);
    }

    public ShipDto save(ShipDto shipDto) {
        Ship save = shipRepository.save(shipDtoMapper.dtoToEntity(shipDto));
        return shipDtoMapper.entityToDto(save);
    }

    public void delete(Long id) {
        shipRepository.deleteById(id);
    }

    private Ship CruiserEntity(Long id) {
        return shipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }


    public ShipDto get(Long id) {
        return shipDtoMapper.entityToDto(CruiserEntity(id));
    }


    public ShipDto put(Long id, ShipDto shipDto) {
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
        shipDtoMapper.toTarget(shipDto, ship);
        ship.setId(id);
        return shipDtoMapper.entityToDto(ship);
    }
}
