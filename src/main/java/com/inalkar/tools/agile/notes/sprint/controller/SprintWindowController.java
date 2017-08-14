package com.inalkar.tools.agile.notes.sprint.controller;

import com.inalkar.tools.agile.notes.sprint.dto.Sprint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class SprintWindowController {
    
    @FXML
    private DatePicker sprintEndDate;

    @FXML
    private DatePicker sprintStageDate;

    @FXML
    private DatePicker sprintProdDate;

    @FXML
    private TextField sprintTitle;

    @FXML
    private DatePicker sprintStartDate;

    @FXML
    private DatePicker sprintRRFDue;

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }


    public void setSprint(Sprint sprint) {
        sprintTitle.textProperty().setValue("Test");
        System.out.println(sprint);
    }
}
