package kamil.sowa.shipscompany.heaven;

import kamil.sowa.shipscompany.passanger.Passenger;
import kamil.sowa.shipscompany.passanger.PassengerDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HeavenDtoMapper {

    HeavenDto entityToDto(Heaven heaven);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<HeavenDto> entityToDto(List<Heaven> heavenList);

    Heaven dtoToEntity(HeavenDto heavenDto);

    @Mapping(target = "target.id", ignore = true)
    void toTarget(HeavenDto source, @MappingTarget Heaven target);
}
