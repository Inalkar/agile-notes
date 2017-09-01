package com.inalkar.tools.agile.notes.ticket.todo.service;

import com.inalkar.tools.agile.notes.ticket.todo.dto.TicketToDo;
import com.inalkar.tools.agile.notes.ticket.type.dto.TicketType;

import java.util.List;
import java.util.stream.Stream;

public interface ITicketToDoService {
    
    Stream<TicketToDo> getTicketToDosAsStream(TicketType ticketType) throws Exception;
    
    List<TicketToDo> getTicketToDos(TicketType ticketType) throws Exception;
    
    void addTicketToDo(TicketType ticketType, TicketToDo ticketToDo) throws Exception;
    
    void removeTicketToDo(TicketToDo ticketToDo) throws Exception;
    
    void updateTicketToDo(TicketToDo ticketToDo) throws Exception;
    
}
