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

    @Mapping(target = "cruise.id", source = "passengerDto.cruiseId")
    Passenger dtoToEntity(PassengerDto passengerDto);

    @Mapping(target = "target.id", ignore = true)
    @Mapping(target = "target.cruise.id", source = "source.cruiseId")
    void toTarget(PassengerDto source, @MappingTarget Passenger target);
}
