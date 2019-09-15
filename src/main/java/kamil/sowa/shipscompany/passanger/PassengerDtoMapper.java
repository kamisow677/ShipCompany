package kamil.sowa.shipscompany.passanger;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerDtoMapper {

    @Mapping(target = "cruiseId", source = "passenger.cruise.id")
    PassengerDto entityToDto(Passenger passenger);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<PassengerDto> entityToDto(List<Passenger> passengerList);

    @Mapping(target = "id", source = "passengerDto.cruiseId")
    Passenger dtoToEntity(PassengerDto passengerDto);

    void toTarget(PassengerDto source, @MappingTarget Passenger target);
}
