package kamil.sowa.shipscompany.cruise;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CruiseService {
    private final CruiseRepository repository;
    private final CruiseDtoMapper cruiseDtoMapper;

    public Page<CruiseDto> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(cruiseDtoMapper::entityToDto);
    }

    public CruiseDto save(CruiseDto CruiseDto) {
        Cruise save = repository.save(cruiseDtoMapper.dtoToEntity(CruiseDto));
        return cruiseDtoMapper.entityToDto(save);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Cruise CruiserEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }


    public CruiseDto get(Long id) {
        return cruiseDtoMapper.entityToDto(CruiserEntity(id));
    }


    public CruiseDto put(Long id, CruiseDto cruiseDto) {
        Cruise cruise = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
        cruiseDtoMapper.toTarget(cruiseDto, cruise);
        return cruiseDtoMapper.entityToDto(cruise);
    }
}
