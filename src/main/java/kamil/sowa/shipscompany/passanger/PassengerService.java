package kamil.sowa.shipscompany.passanger;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerDtoMapper passengerDtoMapper;

    public Page<PassengerDto> getAll(Pageable pageable) {
        return passengerRepository.findAll(pageable)
                .map(passengerDtoMapper::entityToDto);
    }

    public PassengerDto save(PassengerDto CruiseDto) {
        Passenger save = passengerRepository.save(passengerDtoMapper.dtoToEntity(CruiseDto));
        return passengerDtoMapper.entityToDto(save);
    }

    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }

    private Passenger CruiserEntity(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }


    public PassengerDto get(Long id) {
        return passengerDtoMapper.entityToDto(CruiserEntity(id));
    }


    public PassengerDto put(Long id, PassengerDto passengerDto) {
        Passenger passenger = Passenger.builder().id(id).build();
        passengerDtoMapper.toTarget(passengerDto, passenger);
        return passengerDtoMapper.entityToDto(passenger);
    }
}
