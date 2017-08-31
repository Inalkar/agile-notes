package com.inalkar.tools.agile.notes.settings.extension.ticket;

import com.inalkar.tools.agile.notes.settings.extension.ISettingExtension;
import com.inalkar.tools.agile.notes.settings.extension.ticket.view.TicketSettingsLayout;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Ticket")
public class TicketSettingExtension implements ISettingExtension {

    @Autowired
    private TicketSettingsLayout ticketSettingsLayout;
    
    @Override
    public String getName() {
        return "Ticket";
    }

    @Override
    public AnchorPane getLayout() {
        return ticketSettingsLayout;
    }

}
