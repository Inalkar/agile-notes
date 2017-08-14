package com.inalkar.tools.agile.notes.util.dialog;

import com.inalkar.tools.agile.notes.util.dialog.constant.ActionType;
import com.inalkar.tools.agile.notes.util.dialog.constant.MessageTitleType;
import com.inalkar.tools.agile.notes.util.dialog.event.IDialogEvent;
import com.inalkar.tools.agile.notes.util.dialog.event.IDialogTextEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConfirmDialogsUtil extends DialogsUtil{

    public void confirm(String msg, IDialogEvent onOk, IDialogEvent onCancel) {
        confirm(MessageTitleType.Confirm.getValue(), 
                null, 
                msg, 
                onOk, 
                onCancel, 
                ActionType.OK.getValue(), 
                ActionType.Cancel.getValue()
        );
    }

    public void confirm(Node component, IDialogEvent onOk, IDialogEvent onCancel) {
        confirm(MessageTitleType.Confirm.getValue(), 
                null, 
                onOk, 
                onCancel, 
                ActionType.OK.getValue(), 
                ActionType.Cancel.getValue(), 
                component
        );
    }

    public void confirmWithTextArea(String msg, String defaultTxt, IDialogTextEvent onOk,
                                    IDialogEvent onCancel) 
    {
        confirmWithTextArea(
                MessageTitleType.Confirm.getValue(), 
                null, 
                msg, 
                defaultTxt, 
                onOk, 
                onCancel, 
                ActionType.Confirm.getValue(),
                ActionType.Cancel.getValue()
        );
    }

    public void confirmWithTextField(String msg, String defaultTxt, IDialogTextEvent onOk,
                                     IDialogEvent onCancel) 
    {
        confirmWithTextField(
                MessageTitleType.Confirm.getValue(), 
                null, 
                msg, 
                defaultTxt, 
                onOk, 
                onCancel, 
                ActionType.Confirm.getValue(),
                ActionType.Cancel.getValue()
        );
    }

    public void displayAndClose(String message, int timeDelay) {
        Alert alert = createStyledAlert(Alert.AlertType.INFORMATION);
        styleDialog(MessageTitleType.Confirm.getValue(), null, message, alert);
        ButtonType buttonTypeOk = new ButtonType(ActionType.OK.getValue(), ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOk);
        Timeline dialogTimeline = 
                new Timeline(
                        new KeyFrame(
                                Duration.seconds(timeDelay), 
                                event -> Platform.runLater(() -> { 
                                    if (alert.isShowing()) { 
                                        alert.close(); 
                                    } 
                                })));
        dialogTimeline.setCycleCount(Timeline.INDEFINITE);
        dialogTimeline.play();
        alert.showAndWait();
    }


    public void confirmWithTextArea(String title, String headerText, String msg, String defaultTxt, 
                                    IDialogTextEvent onOk, IDialogEvent onCancel, String okBtnTitle, 
                                    String cancelBtnTitle) 
    {
        TextArea textArea = new TextArea();
        confirmWithTextInput(title, headerText, msg, defaultTxt, onOk, onCancel, okBtnTitle, cancelBtnTitle,
                textArea);
    }

    public void confirmWithTextField(String title, String headerText, String msg, String defaultTxt,
                                     IDialogTextEvent onOk, IDialogEvent onCancel, String okBtnTitle, 
                                     String cancelBtnTitle) 
    {
        TextField textField = new TextField();
        textField.setPrefWidth(400);
        confirmWithTextInput(title, headerText, msg, defaultTxt, onOk, onCancel, okBtnTitle, cancelBtnTitle,
                textField);
    }

    public boolean confirmWithReturnBoolean(final String title, final String message) {
        Alert alert = createStyledAlert(Alert.AlertType.CONFIRMATION);
        styleDialog(title, title, message, alert);
        ButtonType buttonTypeOk = new ButtonType(ActionType.OK.getValue(), ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType(ActionType.Cancel.getValue(), ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == buttonTypeOk;
    }

    public void confirm(String msg, IDialogEvent onOk) {
        confirm(MessageTitleType.Confirm.getValue(), null, msg, onOk, ActionType.OK.getValue());
    }

    public void confirm(String title, String headerText, String msg, IDialogEvent onOk,
                        String okBtnTitle) 
    {
        Alert alert = createStyledAlert(Alert.AlertType.CONFIRMATION);
        styleDialog(title, headerText, msg, alert);
        ButtonType buttonTypeOk = new ButtonType(okBtnTitle, ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOk);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOk) {
            onOk.execute();
        }
    }

    public void confirm(String title, String headerText, String msg, IDialogEvent onOk,
                        IDialogEvent onCancel, String okBtnTitle, String cancelBtnTitle) 
    {
        Alert alert = createStyledAlert(Alert.AlertType.CONFIRMATION);
        styleDialog(title, headerText, msg, alert);
        ButtonType buttonTypeOk = new ButtonType(okBtnTitle, ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType(cancelBtnTitle, ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        handleResult(onOk, onCancel, buttonTypeOk, result);
    }

    public void confirm(String title, String headerText, IDialogEvent onOk,
                        IDialogEvent onCancel, String okBtnTitle, String cancelBtnTitle, Node component) 
    {
        Alert alert = createStyledAlert(Alert.AlertType.CONFIRMATION);
        styleDialog(title, headerText, null, alert);
        if (component != null) {
            setDisplayedInfo(alert, component);
        }
        ButtonType buttonTypeOk = new ButtonType(okBtnTitle, ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType(cancelBtnTitle, ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        handleResult(onOk, onCancel, buttonTypeOk, result);
    }

    public void confirmWithUITextComponent(String title, String headerText, String msg,
                                           IDialogTextEvent onOk, String okBtnTitle, String cancelBtnTitle, 
                                           TextInputControl component) 
    {
        confirmWithTextInputAndUIComponent(title, headerText, msg, onOk, okBtnTitle, cancelBtnTitle, component);
    }

    protected void confirmWithTextInput(final String title, final String headerText, final String msg,
                                        final String defaultTxt, final IDialogTextEvent onOk, final IDialogEvent onCancel,
                                        final String okBtnTitle, final String cancelBtnTitle, final TextInputControl textField) {
        Dialog textInputDialog = createStyledTextInputDialog();
        styleDialog(title, headerText, msg, textInputDialog);
        setButtons(okBtnTitle, cancelBtnTitle, textInputDialog);

        textField.setText(defaultTxt);
        setTextInput(msg, textInputDialog, textField);

        Optional<ButtonType> result = textInputDialog.showAndWait();

        handleResultWithText(onOk, onCancel, result, textField.getText());
    }

    protected void confirmWithTextInputAndUIComponent(final String title, final String headerText, final String msg, 
                                                      final IDialogTextEvent onOk, final String okBtnTitle, 
                                                      final String cancelBtnTitle, final TextInputControl textField) 
    {
        Dialog textInputDialog = createStyledTextInputDialog();
        styleDialog(title, headerText, msg, textInputDialog);
        setButtons(okBtnTitle, cancelBtnTitle, textInputDialog);

        setTextInput(textInputDialog, textField);

        Optional<ButtonType> result = textInputDialog.showAndWait();

        handleResultWithText(onOk, result, textField.getText());
    }
}
