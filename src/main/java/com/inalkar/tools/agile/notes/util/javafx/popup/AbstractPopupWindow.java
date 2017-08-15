package com.inalkar.tools.agile.notes.util.javafx.popup;

import com.inalkar.tools.agile.notes.util.javafx.popup.event.OnCloseEvent;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static com.inalkar.tools.agile.notes.util.javafx.AnimationUtil.scaleInFromNodeToNodeCenter;
import static com.inalkar.tools.agile.notes.util.javafx.AnimationUtil.scaleOutToNode;

public class AbstractPopupWindow extends TitledPane {
    
    protected Parent contentNode;
    protected OnCloseEvent onCloseEvent;

    public AbstractPopupWindow() {
        setCollapsible(false);
    }

    @SuppressWarnings("unchecked")
    public void loadFromFxml(String fxmlName, Object controller) throws IOException {
        FXMLLoader f;
        try {
            f = new FXMLLoader(getClass().getClassLoader().getResource(fxmlName));
            if (controller != null) f.setController(controller);
            final Parent fxmlRoot = f.load();
            contentNode = fxmlRoot;
            setContent(fxmlRoot);
        } finally {
            f = null;
        }
    }
    
    public void showFromNode(Node fromNode, AnchorPane toNode) {
        setVisible(true);
        scaleInFromNodeToNodeCenter(this, fromNode, toNode).play();
    }
    
    public void hideToNode(Node toNode, AnchorPane testNode) {
        ParallelTransition animation = scaleOutToNode(this, toNode);
        animation.setOnFinished((e) -> setVisible(false));
        animation.play();
    }

    public void setOnCloseEvent(OnCloseEvent onCloseEvent) {
        this.onCloseEvent = onCloseEvent;
    }
    
    public void close() {
        if (onCloseEvent != null) {
            onCloseEvent.close();
        }
    }
    
}
