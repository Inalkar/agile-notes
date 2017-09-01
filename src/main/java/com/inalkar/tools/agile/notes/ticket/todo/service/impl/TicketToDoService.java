package com.inalkar.tools.agile.notes.ticket.todo.service.impl;

import com.inalkar.tools.agile.notes.storage.DBStorage;
import com.inalkar.tools.agile.notes.ticket.todo.dto.TicketToDo;
import com.inalkar.tools.agile.notes.ticket.todo.service.ITicketToDoService;
import com.inalkar.tools.agile.notes.ticket.type.dto.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.inalkar.daf.storage.impl.WhereColumn.columnExpr;

@Service
public class TicketToDoService implements ITicketToDoService {
    
    @Autowired
    private DBStorage dbStorage;

    @Override
    public Stream<TicketToDo> getTicketToDosAsStream(TicketType ticketType) throws Exception {
        if (ticketType == null) throw new NullPointerException();
        
        return dbStorage.select(TicketToDo.class)
                .where(columnExpr("ticketTypeId").equal(ticketType.id))
                .all().stream();
    }

    @Override
    public List<TicketToDo> getTicketToDos(TicketType ticketType) throws Exception {
        try (Stream<TicketToDo> ticketToDoStream = getTicketToDosAsStream(ticketType)) {
            return ticketToDoStream.collect(Collectors.toList());
        }
    }

    @Override
    public void addTicketToDo(TicketType ticketType, TicketToDo ticketToDo) throws Exception {
        if (ticketType == null || ticketType.id == null || ticketToDo == null) throw new NullPointerException();
        
        ticketToDo.id = UUID.randomUUID();
        ticketToDo.ticketTypeId = ticketType.id;
        dbStorage.insert(ticketToDo).execute();
    }

    @Override
    public void removeTicketToDo(TicketToDo ticketToDo) throws Exception {
        if (ticketToDo == null || ticketToDo.id == null) throw new NullPointerException();
        
        dbStorage.delete(ticketToDo).execute();
    }

    @Override
    public void updateTicketToDo(TicketToDo ticketToDo) throws Exception {
        if (ticketToDo == null || ticketToDo.id == null) throw new NullPointerException();
        
        dbStorage.update(ticketToDo).execute();
    }
    
}
