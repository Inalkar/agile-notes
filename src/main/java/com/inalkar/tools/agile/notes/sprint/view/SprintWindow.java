package com.inalkar.tools.agile.notes.sprint.view;

import com.inalkar.tools.agile.notes.sprint.controller.SprintWindowController;
import com.inalkar.tools.agile.notes.sprint.dto.Sprint;
import com.inalkar.tools.agile.notes.util.javafx.popup.AbstractPopupWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class SprintWindow extends AbstractPopupWindow {
    
    private static final Logger LOG = LogManager.getLogger(SprintWindow.class);
    
    private SprintWindowController controller;

    @Autowired
    public SprintWindow(SprintWindowController sprintWindowController) throws IOException {
        controller = sprintWindowController;
        controller.setWindow(this);
        setText("Edit Sprint");
        loadFromFxml("view/EditSprint.fxml", sprintWindowController);
    }
    
    public void setSprint(Sprint sprint) {
        controller.setSprint(sprint);
    }
    
}
