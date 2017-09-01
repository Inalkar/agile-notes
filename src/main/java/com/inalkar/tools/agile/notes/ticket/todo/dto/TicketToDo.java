package com.inalkar.tools.agile.notes.ticket.todo.dto;

import java.util.UUID;

public class TicketToDo {
    
    public UUID id;
    public UUID ticketTypeId;
    public String title;

    public TicketToDo() {
    }

    public TicketToDo(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TicketToDo{" +
                "id=" + id +
                ", ticketTypeId=" + ticketTypeId +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketToDo that = (TicketToDo) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
}
