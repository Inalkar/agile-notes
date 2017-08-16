package com.inalkar.tools.agile.notes.sprint.view;

import com.inalkar.tools.agile.notes.sprint.service.dto.Sprint;
import com.inalkar.tools.agile.notes.util.javafx.FXMLUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static com.inalkar.tools.agile.notes.util.DateUtil.formatDate;

public class SprintRegion extends AnchorPane {

    private Sprint sprint;
    private TitledPane mainPane;
    private Label sprintStartLabel;
    private Label sprintEndLabel;
    private Label rrfDueLabel;
    private Label stageReleaseLabel;
    private Label prodReleaseLabel;
    private Button addTicketBtn;

    public SprintRegion(Sprint sprint) {
        if (sprint == null) throw new NullPointerException();

        this.sprint = sprint;
        readFXML();
        setData();
    }

    private void readFXML() {
        FXMLLoader f;
        try {
            f = new FXMLLoader();
            final Parent fxmlRoot = f.load(getClass().getClassLoader().getResource("view/SprintView.fxml"));
            mainPane = (TitledPane) fxmlRoot;
            this.getChildren().setAll(fxmlRoot);
            lookupComponents();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            f = null;
        }
    }

    private void lookupComponents() {
        sprintStartLabel = (Label) FXMLUtil.lookup(this, "sprintStartLabel");
        sprintEndLabel = (Label) FXMLUtil.lookup(this, "sprintEndLabel");
        rrfDueLabel = (Label) FXMLUtil.lookup(this, "rrfDueLabel");
        stageReleaseLabel = (Label) FXMLUtil.lookup(this, "stageReleaseLabel");
        prodReleaseLabel = (Label) FXMLUtil.lookup(this, "prodReleaseLabel");
//        addTicketBtn = (Button) FXMLUtil.lookup(this, "addTicketBtn");
//        addTicketBtn.setOnAction(this::addTicket);
    }

    private void setData() {
        mainPane.setText(sprint.title);
        sprintStartLabel.setText(formatDate(sprint.startTime));
        sprintEndLabel.setText(formatDate(sprint.endTime));
        rrfDueLabel.setText(formatDate(sprint.rrfDue));
        stageReleaseLabel.setText(formatDate(sprint.stageRelease));
        prodReleaseLabel.setText(formatDate(sprint.prodRelease));
    }
    
    private void addTicket(ActionEvent event) {
        
    }

    public Sprint getSprint() {
        return sprint;
    }

}
