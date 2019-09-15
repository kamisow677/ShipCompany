package kamil.sowa.shipscompany.cruise;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CruiseDtoMapper {

    @Mapping(target = "shipId", source = "cruise.ship.id")
    CruiseDto entityToDto(Cruise cruise);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<CruiseDto> entityToDto(List<Cruise> cruise);

    @Mapping(target = "id", source = "cruiseDto.shipId")
    Cruise dtoToEntity(CruiseDto cruiseDto);

    void toTarget(CruiseDto source, @MappingTarget Cruise target);
}
