package com.inalkar.tools.agile.notes.util.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AnchorLayout extends AnchorPane {

    protected Parent mainPane;

    protected AnchorLayout(JavaFXController controller, String fxmlName) {
        readFXML(controller, fxmlName);
    }

    private void readFXML(JavaFXController controller, String fxmlName) {
        FXMLLoader f;
        try {
            f = new FXMLLoader(getClass().getClassLoader().getResource(fxmlName));
            if (controller != null) f.setController(controller);
            mainPane = f.load();
            this.getChildren().setAll(mainPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
