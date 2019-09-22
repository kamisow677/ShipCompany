package kamil.sowa.shipscompany.ship;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipDtoMapper {

    ShipDto entityToDto(Ship ship);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<ShipDto> entityToDto(List<Ship> ship);

    @Mapping(target = "cruises", ignore = true)
    Ship dtoToEntity(ShipDto shipDto);

    @Mapping(target = "cruises", ignore = true)
    void toTarget(ShipDto source, @MappingTarget Ship target);
}
