package kamil.sowa.shipscompany.passanger;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private PassengerRepository passengerRepository;
    private PassengerDtoMapper passengerDtoMapper;

    public List<PassengerDto> getAll(){
        return passengerDtoMapper.entityToDto(passengerRepository.findAll());
    }

    public PassengerDto save(PassengerDto passengerDto) {
        PassengerEntity save = passengerRepository.save(passengerDtoMapper.dtoToEntity(passengerDto));
        return passengerDtoMapper.entityToDto(save);
    }

    public void delete(Long id){
        passengerRepository.deleteById(id);
    }

    private PassengerEntity passengerEntity(Long id){
        return passengerRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found"));
    }


    public PassengerDto get(Long id)  {
        return passengerDtoMapper.entityToDto(passengerEntity(id));
    }


    public PassengerDto put(Long id, PassengerDto passengerDto)  {
        PassengerEntity passenger = PassengerEntity.builder().id(id).build();
        passengerDtoMapper.toTarget(passengerDto, passenger);
        return passengerDtoMapper.entityToDto(passenger);
    }
}
