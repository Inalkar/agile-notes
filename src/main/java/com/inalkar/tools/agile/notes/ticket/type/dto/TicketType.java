package com.inalkar.tools.agile.notes.ticket.type.dto;

import java.util.UUID;

public class TicketType {
    
    public UUID id;
    public String title;

    public TicketType() {
    }

    public TicketType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketType that = (TicketType) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
}
