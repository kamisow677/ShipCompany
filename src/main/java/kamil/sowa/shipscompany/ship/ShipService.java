package kamil.sowa.shipscompany.ship;

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
        Ship passenger = Ship.builder().id(id).build();
        shipDtoMapper.toTarget(shipDto, passenger);
        return shipDtoMapper.entityToDto(passenger);
    }
}
