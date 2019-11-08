package kamil.sowa.shipscompany.ship;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipDtoMapper {

    @Mapping(target = "heavenId", source = "ship.heaven.id")
    ShipDto entityToDto(Ship ship);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<ShipDto> entityToDto(List<Ship> ship);

    @Mapping(target = "cruises", ignore = true)
    @Mapping(target = "heaven", expression = "java( (shipDto.getHeavenId()==null)? null" +
            ": Heaven.builder().id(shipDto.getHeavenId()).build())")
    Ship dtoToEntity(ShipDto shipDto);

    @Mapping(target = "target.heaven.id", source = "source.heavenId")
    void toTarget(ShipDto source, @MappingTarget Ship target);
}
