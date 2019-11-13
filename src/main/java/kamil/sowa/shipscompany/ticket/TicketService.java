package kamil.sowa.shipscompany.ticket;

import kamil.sowa.shipscompany.ship.Ship;
import kamil.sowa.shipscompany.ship.ShipDto;
import kamil.sowa.shipscompany.ship.ShipDtoMapper;
import kamil.sowa.shipscompany.ship.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketDtoMapper ticketDtoMapper;

    public Page<TicketDto> getAll(Pageable pageable) {
        return ticketRepository.findAll(pageable)
                .map(ticketDtoMapper::entityToDto);
    }

    public TicketDto save(TicketDto ticketDto) {
        Ticket save = ticketRepository.save(ticketDtoMapper.dtoToEntity(ticketDto));
        return ticketDtoMapper.entityToDto(save);
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    private Ticket TicketEntity(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
    }


    public TicketDto get(Long id) {
        return ticketDtoMapper.entityToDto(TicketEntity(id));
    }


    public TicketDto put(Long id, TicketDto ticketDto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
        ticketDtoMapper.toTarget(ticketDto, ticket);
        ticket.setId(id);
        return ticketDtoMapper.entityToDto(ticket);
    }
}
