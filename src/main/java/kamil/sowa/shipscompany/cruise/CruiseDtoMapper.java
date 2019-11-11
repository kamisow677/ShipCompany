package kamil.sowa.shipscompany.cruise;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CruiseDtoMapper {

    @Mapping(target = "shipId", source = "cruise.ship.id")
    @Mapping(target = "heavenAimId", source = "cruise.heavenAim.id")
    @Mapping(target = "heavenDepartureId", source = "cruise.heavenDeparture.id")
    CruiseDto entityToDto(Cruise cruise);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<CruiseDto> entityToDto(List<Cruise> cruise);

    @Mapping(target = "ship.id", source = "cruiseDto.shipId")
    @Mapping(target = "heavenAim", expression = "java( (cruiseDto.getHeavenAimId()==null)? null" +
            ": Heaven.builder().id(cruiseDto.getHeavenAimId()).build())")
    @Mapping(target = "heavenDeparture", expression = "java( (cruiseDto.getHeavenDepartureId()==null)? null" +
            ": Heaven.builder().id(cruiseDto.getHeavenDepartureId()).build())")
    Cruise dtoToEntity(CruiseDto cruiseDto);

    @Mapping(target = "target.id", ignore = true)
    @Mapping(target = "target.ship.id", source = "source.shipId")
    @Mapping(target = "heavenAim", expression = "java( (source.getHeavenAimId()==null)? null" +
            ": Heaven.builder().id(source.getHeavenAimId()).build())")
    @Mapping(target = "heavenDeparture", expression = "java( (source.getHeavenDepartureId()==null)? null" +
            ": Heaven.builder().id(source.getHeavenDepartureId()).build())")
    void toTarget(CruiseDto source, @MappingTarget Cruise target);
}
