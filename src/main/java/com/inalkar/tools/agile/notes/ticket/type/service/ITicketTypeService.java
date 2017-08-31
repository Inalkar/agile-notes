package com.inalkar.tools.agile.notes.ticket.type.service;

import com.inalkar.tools.agile.notes.ticket.type.dto.TicketType;

import java.util.List;
import java.util.stream.Stream;

public interface ITicketTypeService {
    
    Stream<TicketType> getTicketTypesAsStream() throws Exception;
    
    List<TicketType> getTicketTypes() throws Exception;
    
    void addTicketType(TicketType ticketType) throws Exception;
    
    void removeTicketType(TicketType ticketType) throws Exception;
    
    void updateTicketType(TicketType ticketType) throws Exception;
    
}
