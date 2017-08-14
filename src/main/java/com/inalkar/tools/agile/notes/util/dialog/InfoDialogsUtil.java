package com.inalkar.tools.agile.notes.util.dialog;

import com.inalkar.tools.agile.notes.util.dialog.constant.ActionType;
import com.inalkar.tools.agile.notes.util.dialog.constant.MessageTitleType;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.springframework.stereotype.Component;

@Component
public class InfoDialogsUtil extends DialogsUtil {
    public void info(final String title, final String msg, final String btnLabel) {
        info(title, null, msg, btnLabel);
    }

    public void info(Node component) {
        info(MessageTitleType.Info.getValue(), null, ActionType.OK.getValue(), component);
    }

    public void info(String title, String headerText, String msg, String okBtnTitle) {
        Alert alert = createStyledAlert(Alert.AlertType.INFORMATION);
        styleDialog(title, headerText, msg, alert);
        ButtonType buttonTypeOk = new ButtonType(okBtnTitle, ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOk);
        alert.showAndWait();
    }

    public void info(String title, String headerText, String okBtnTitle, Node component) {
        Alert alert = createStyledAlert(Alert.AlertType.INFORMATION);
        styleDialog(title, headerText, null, alert);
        if (component != null) {
            setDisplayedInfo(alert, component);
        }
        ButtonType buttonTypeOk = new ButtonType(okBtnTitle, ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOk);
        alert.showAndWait();
    }
}
