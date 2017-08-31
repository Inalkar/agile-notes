package com.inalkar.tools.agile.notes.settings.extension.ticket.view;

import com.inalkar.tools.agile.notes.util.javafx.AnchorLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketSettingsLayout extends AnchorLayout {
    
    private TicketSettingsController controller;
    
    @Autowired
    public TicketSettingsLayout(TicketSettingsController controller) {
        super(controller, "view/SettingsTicketExtension.fxml");
        this.controller = controller;
    }

}
