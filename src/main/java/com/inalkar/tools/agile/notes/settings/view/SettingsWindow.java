package com.inalkar.tools.agile.notes.settings.view;

import com.inalkar.tools.agile.notes.util.javafx.popup.AbstractPopupWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SettingsWindow extends AbstractPopupWindow {

    private static final Logger LOG = LogManager.getLogger(SettingsWindow.class);

    private SettingsWindowController controller;

    @Autowired
    public SettingsWindow(SettingsWindowController settingsWindowController) throws IOException {
        controller = settingsWindowController;
        controller.setWindow(this);
        setText("Settings");
        loadFromFxml("view/Settings.fxml", settingsWindowController);
    }

}
