package kamil.sowa.shipscompany.ticket;

import kamil.sowa.shipscompany.ship.Ship;
import kamil.sowa.shipscompany.ship.ShipDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketDtoMapper {

    @Mapping(target = "passengerId", source = "ticket.passenger.id")
    @Mapping(target = "cruiseId", source = "ticket.cruise.id")
    TicketDto entityToDto(Ticket ticket);

    @IterableMapping(qualifiedByName = "entityToDto")
    List<TicketDto> entityToDto(List<Ticket> ticket);

    @Mapping(target = "passenger.id", source = "ticketDto.passengerId")
    @Mapping(target = "cruise.id", source = "ticketDto.cruiseId")
    Ticket dtoToEntity(TicketDto ticketDto);

    @Mapping(target = "target.passenger.id", source = "source.passengerId")
    @Mapping(target = "target.cruise.id", source = "source.cruiseId")
    void toTarget(TicketDto source, @MappingTarget Ticket target);
}
