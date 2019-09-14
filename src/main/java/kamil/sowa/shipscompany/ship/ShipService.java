package kamil.sowa.shipscompany.ship;

import kamil.sowa.shipscompany.passanger.PassengerDto;
import kamil.sowa.shipscompany.passanger.PassengerDtoMapper;
import kamil.sowa.shipscompany.passanger.PassengerEntity;
import kamil.sowa.shipscompany.passanger.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipService {
    private ShipRepository shipRepository;
    private ShipDtoMapper shipDtoMapper;

    public List<ShipDto> getAll(){
        return shipDtoMapper.entityToDto(shipRepository.findAll());
    }

    public ShipDto save(ShipDto shipDto) {
        ShipEntity save = shipRepository.save(shipDtoMapper.dtoToEntity(shipDto));
        return shipDtoMapper.entityToDto(save);
    }

    public void delete(Long id){
        shipRepository.deleteById(id);
    }

    private ShipEntity passengerEntity(Long id){
        return shipRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found"));
    }


    public ShipDto get(Long id)  {
        return shipDtoMapper.entityToDto(passengerEntity(id));
    }


    public ShipDto put(Long id, ShipDto shipDto)  {
        ShipEntity passenger = ShipEntity.builder().id(id).build();
        shipDtoMapper.toTarget(shipDto, passenger);
        return shipDtoMapper.entityToDto(passenger);
    }
}
