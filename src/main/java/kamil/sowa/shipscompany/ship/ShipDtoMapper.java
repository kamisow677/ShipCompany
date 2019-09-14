package kamil.sowa.shipscompany.ship;

import kamil.sowa.shipscompany.passanger.PassengerDto;
import kamil.sowa.shipscompany.passanger.PassengerEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipDtoMapper {

    ShipDto entityToDto(ShipEntity shipEntity);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<ShipDto> entityToDto(List<ShipEntity> shipEntity);

    ShipEntity dtoToEntity(ShipDto shipDto);

    void toTarget(ShipDto source, @MappingTarget ShipEntity target);
}
