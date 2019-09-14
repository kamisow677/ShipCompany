package kamil.sowa.shipscompany.passanger;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerDtoMapper {

    PassengerDto entityToDto(PassengerEntity passengerEntity);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<PassengerDto> entityToDto(List<PassengerEntity> passengerEntity);

    PassengerEntity dtoToEntity(PassengerDto passengerDto);

    void toTarget(PassengerDto source, @MappingTarget PassengerEntity target );
}
