package com.inalkar.tools.agile.notes.main.controller;

import com.inalkar.tools.agile.notes.sprint.dto.Sprint;
import com.inalkar.tools.agile.notes.sprint.service.ISprintService;
import com.inalkar.tools.agile.notes.sprint.view.SprintRegion;
import com.inalkar.tools.agile.notes.sprint.window.SprintWindow;
import com.inalkar.tools.agile.notes.util.dialog.ErrorDialogsUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MainController implements Initializable {
    
    private final static Logger LOG = LogManager.getLogger(MainController.class);
    
    @Autowired
    private SprintWindow sprintWindow;

    @Autowired
    private ISprintService sprintService;

    @Autowired
    private ErrorDialogsUtil errorDialogsUtil;
    
    @FXML
    private VBox sprintLinesContainer;
    
    @FXML
    void addSprint() {
        sprintWindow.addSprint();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CompletableFuture.runAsync(() -> {
            try {
                loadSprints();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        })
//        .thenAccept((Void) -> Platform.runLater(() -> window.close()))
        .exceptionally((e) -> {
            LOG.catching(e);
            errorDialogsUtil.exceptionDialog("Can't load Sprints.", e);
            return null;
        });

    }

    private void loadSprints() throws Exception {
        try (Stream<Sprint> sprintStream = sprintService.getSprintsAsStream()) {
            sprintStream.map(SprintRegion::new)
                    .forEach(region -> Platform.runLater(() -> sprintLinesContainer.getChildren().add(region)));
        }
    }

}
