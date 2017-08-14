package com.inalkar.tools.agile.notes.util.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.inalkar.tools.agile.notes.Application.MAIN_STYLE;

public class AbstractWindow extends Stage {

    protected Scene scene;

    @SuppressWarnings("unchecked")
    public void setFXMLTemplate(String fxmlName, Object controller) throws IOException {
        FXMLLoader f;
        try {
            f = new FXMLLoader(getClass().getClassLoader().getResource(fxmlName));
            if (controller != null) f.setController(controller);
            final Parent fxmlRoot = f.load();
            scene = new Scene(fxmlRoot);
            scene.getStylesheets().add(MAIN_STYLE);
            setScene(scene);
        } finally {
            f = null;
        }
    }

    protected void lookupComponents(final Parent fxmlRoot) {

    }

    public void setPositionX(double x) {
        this.setX(x);
    }

    public void setPositionY(double y) {
        this.setY(y);
    }

    public void setModal(Boolean flagModal) {
        if (flagModal) {
            initModality(Modality.APPLICATION_MODAL);
        } else {
            initModality(Modality.NONE);
        }
    }

    public void setOnTop(Boolean flagOnTop) {
        if (flagOnTop) {
            initModality(Modality.WINDOW_MODAL);
            initOwner(scene.getWindow());
        } else {
            initModality(Modality.NONE);
        }
    }

}
