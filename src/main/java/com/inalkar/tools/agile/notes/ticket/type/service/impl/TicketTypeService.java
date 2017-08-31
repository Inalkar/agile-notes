package com.inalkar.tools.agile.notes.ticket.type.service.impl;

import com.inalkar.tools.agile.notes.storage.DBStorage;
import com.inalkar.tools.agile.notes.ticket.type.dto.TicketType;
import com.inalkar.tools.agile.notes.ticket.type.service.ITicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TicketTypeService implements ITicketTypeService {
    
    @Autowired
    private DBStorage dbStorage;
    
    @Override
    public Stream<TicketType> getTicketTypesAsStream() throws Exception {
        return dbStorage.select(TicketType.class).all().stream();
    }

    @Override
    public List<TicketType> getTicketTypes() throws Exception {
        try (Stream<TicketType> ticketTypeStream = getTicketTypesAsStream()) {
            return ticketTypeStream.collect(Collectors.toList());
        }
    }

    @Override
    public void addTicketType(TicketType ticketType) throws Exception {
        ticketType.id = UUID.randomUUID();
        dbStorage.insert(ticketType).execute();
    }

    @Override
    public void removeTicketType(TicketType ticketType) throws Exception {
        dbStorage.delete(ticketType).execute();
    }

    @Override
    public void updateTicketType(TicketType ticketType) throws Exception {
        dbStorage.update(ticketType).execute();
    }

}
