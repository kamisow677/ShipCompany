package kamil.sowa.shipscompany.passanger;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerDtoMapper {

    @Mapping(target = "cruiseId", source = "passenger.cruise.id")
    @Mapping(target = "shipId", source = "passenger.ship.id")
    PassengerDto entityToDto(Passenger passenger);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<PassengerDto> entityToDto(List<Passenger> passengerList);

    @Mapping(target = "cruise.id", source = "passengerDto.cruiseId")
    @Mapping(target = "ship.id", source = "passengerDto.shipId")
    Passenger dtoToEntity(PassengerDto passengerDto);

    @Mapping(target = "target.id", ignore = true)
    @Mapping(target = "target.cruise.id", source = "source.cruiseId")
    @Mapping(target = "target.ship.id", source = "source.shipId")
    void toTarget(PassengerDto source, @MappingTarget Passenger target);
}
