package com.inalkar.tools.agile.notes.util.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AnchorLayout extends AnchorPane {

    protected Parent mainPane;

    protected AnchorLayout(String fxmlName) {
        readFXML(fxmlName);
    }

    private void readFXML(String fxmlName) {
        FXMLLoader f;
        try {
            f = new FXMLLoader();
            final Parent fxmlRoot = f.load(getClass().getClassLoader().getResource(fxmlName));
            mainPane = (Parent) fxmlRoot;
            this.getChildren().setAll(fxmlRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
