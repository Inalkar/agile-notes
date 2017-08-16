package com.inalkar.tools.agile.notes.sprint.view;

import com.inalkar.tools.agile.notes.sprint.service.dto.Sprint;
import com.inalkar.tools.agile.notes.sprint.service.ISprintService;
import com.inalkar.tools.agile.notes.util.dialog.ErrorDialogsUtil;
import com.inalkar.tools.agile.notes.util.javafx.popup.AbstractPopupWindow;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.inalkar.tools.agile.notes.util.DateUtil.dateToLocalDate;
import static com.inalkar.tools.agile.notes.util.DateUtil.localDateToDate;

@Component
public class SprintWindowController {

    private static final Logger LOG = LogManager.getLogger(SprintWindowController.class);

    private AbstractPopupWindow window;
    private Sprint sprint;

    @Autowired
    private ISprintService sprintService;

    @Autowired
    private ErrorDialogsUtil errorDialogsUtil;
    
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
    void save(ActionEvent event) throws Exception {
        if (validate()) {
            sprint.title = sprintTitle.textProperty().getValue();
            sprint.startTime = localDateToDate(sprintStartDate.getValue());
            sprint.endTime = localDateToDate(sprintEndDate.getValue());
            sprint.rrfDue = localDateToDate(sprintRRFDue.getValue());
            sprint.stageRelease = localDateToDate(sprintStageDate.getValue());
            sprint.prodRelease = localDateToDate(sprintProdDate.getValue());

            CompletableFuture
            .runAsync(() -> {
                try {
                    sprintService.addSprint(sprint);
                    Platform.runLater(() -> window.close());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .thenAccept((Void) -> Platform.runLater(() -> window.close()))
            .exceptionally((e) -> {
                LOG.catching(e);
                errorDialogsUtil.error("Can't save Sprint.");
                return null;
            });
        }
    }

    boolean validate() {
        if (sprintTitle.textProperty().getValue().isEmpty()) {
            errorDialogsUtil.error("Please fill Sprint title");
            return false;
        }
        if (sprintStartDate.getValue() == null) {
            errorDialogsUtil.error("Please fill Sprint start date");
            return false;
        }
        if (sprintEndDate.getValue() == null) {
            errorDialogsUtil.error("Please fill Sprint end date");
            return false;
        }
        if (sprintRRFDue.getValue() == null) {
            errorDialogsUtil.error("Please fill Sprint RRF Due date");
            return false;
        }
        if (sprintStageDate.getValue() == null) {
            errorDialogsUtil.error("Please fill Sprint Stage Release date");
            return false;
        }
        if (sprintProdDate.getValue() == null) {
            errorDialogsUtil.error("Please fill Sprint PROD Release date");
            return false;
        }
        return true;
    }

    @FXML
    void cancel(ActionEvent event) {
        sprint = null;
        window.close();
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
        if (sprint.id != null) {
            sprintTitle.textProperty().setValue(sprint.title);
            sprintStartDate.setValue(dateToLocalDate(sprint.startTime));
            sprintEndDate.setValue(dateToLocalDate(sprint.endTime));
            sprintRRFDue.setValue(dateToLocalDate(sprint.rrfDue));
            sprintStageDate.setValue(dateToLocalDate(sprint.stageRelease));
            sprintProdDate.setValue(dateToLocalDate(sprint.prodRelease));
        }
    }

    public void setWindow(AbstractPopupWindow abstractWindow) {
        this.window = abstractWindow;
    }
}
