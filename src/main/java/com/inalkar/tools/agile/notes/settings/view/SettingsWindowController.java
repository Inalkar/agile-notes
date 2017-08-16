package com.inalkar.tools.agile.notes.settings.view;

import com.inalkar.tools.agile.notes.settings.extension.ISettingExtension;
import com.inalkar.tools.agile.notes.util.javafx.popup.AbstractPopupWindow;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

import static javafx.scene.layout.AnchorPane.*;

@Component
public class SettingsWindowController implements Initializable {

    private AbstractPopupWindow window;

    @Autowired
    private Map<String, ISettingExtension> settingExtensions;

    @FXML
    private ListView<String> settingsList;

    @FXML
    private AnchorPane extensionAnchor;

    @FXML
    void cancel(ActionEvent event) {
        extensionAnchor.getChildren().clear();
        window.close();
    }

    @FXML
    void save(ActionEvent event) {
        window.close();
    }

    public void setWindow(AbstractPopupWindow window) {
        this.window = window;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> extensionNames = new ArrayList<>(settingExtensions.keySet());
        Collections.sort(extensionNames);
        settingsList.setItems(new ObservableListWrapper<>(extensionNames));

        settingsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            extensionAnchor.getChildren().clear();

            AnchorPane extendsionLayout = settingExtensions.get(newValue).getLayout();
            setLeftAnchor(extendsionLayout, 0.0);
            setRightAnchor(extendsionLayout, 0.0);
            setTopAnchor(extendsionLayout, 0.0);
            setBottomAnchor(extendsionLayout, 0.0);
            extensionAnchor.getChildren().add(extendsionLayout);
        });
    }

}
