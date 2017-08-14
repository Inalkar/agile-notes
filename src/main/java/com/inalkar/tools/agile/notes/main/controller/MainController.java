package com.inalkar.tools.agile.notes.main.controller;

import com.inalkar.tools.agile.notes.sprint.dto.Sprint;
import com.inalkar.tools.agile.notes.sprint.window.SprintWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {
    
    private final static Logger LOG = LogManager.getLogger(MainController.class);
    
    @Autowired
    private SprintWindow sprintWindow;
    
    @FXML
    private VBox sprintLinesContainer;
    
    @FXML
    void addSprint() {
        sprintWindow.editSprint(new Sprint());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
