package com.inalkar.tools.agile.notes.sprint.view;

import com.inalkar.tools.agile.notes.sprint.dto.Sprint;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;

import java.io.IOException;

public class SprintRegion extends Region {

    private Sprint sprint;
    private TitledPane mainPane;
    private Label sprintStartLabel;
    private Label sprintEndLabel;
    private Label rrfDueLabel;
    private Label stageReleaseLabel;
    private Label prodReleaseLabel;

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
        sprintStartLabel = (Label) lookup("#sprintStartLabel");
        sprintEndLabel = (Label) lookup("#sprintEndLabel");
        rrfDueLabel = (Label) lookup("#rrfDueLabel");
        stageReleaseLabel = (Label) lookup("#stageReleaseLabel");
        prodReleaseLabel = (Label) lookup("#prodReleaseLabel");
    }

    private void setData() {
        mainPane.setText(sprint.title);
        sprintStartLabel.setText(sprint.startTime.toString());
    }

    public Sprint getSprint() {
        return sprint;
    }

}
