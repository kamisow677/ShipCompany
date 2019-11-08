package kamil.sowa.shipscompany.heaven;

import kamil.sowa.shipscompany.passanger.Passenger;
import kamil.sowa.shipscompany.passanger.PassengerDto;
import kamil.sowa.shipscompany.passanger.PassengerDtoMapper;
import kamil.sowa.shipscompany.passanger.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class HeavenService {
    private final HeavenRepository heavenRepository;
    private final HeavenDtoMapper heavenDtoMapper;

    public Page<HeavenDto> getAll(Pageable pageable) {
        return heavenRepository.findAll(pageable)
                .map(heavenDtoMapper::entityToDto);
    }

    public HeavenDto save(HeavenDto heavenDto) {
        Heaven save = heavenRepository.save(heavenDtoMapper.dtoToEntity(heavenDto));
        return heavenDtoMapper.entityToDto(save);
    }

    public void delete(Long id) {
        heavenRepository.deleteById(id);
    }

    private Heaven HeavenEntity(Long id) {
        return heavenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }


    public HeavenDto get(Long id) {
        return heavenDtoMapper.entityToDto(HeavenEntity(id));
    }


    public HeavenDto put(Long id, HeavenDto heavenDto) {
        Heaven heaven = Heaven.builder().id(id).build();
        heavenDtoMapper.toTarget(heavenDto, heaven);
        heaven.setId(id);
        return heavenDtoMapper.entityToDto(heaven);
    }
}
