package com.inalkar.tools.agile.notes.util.javafx;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import static javafx.scene.layout.AnchorPane.setBottomAnchor;
import static javafx.scene.layout.AnchorPane.setLeftAnchor;
import static javafx.scene.layout.AnchorPane.setRightAnchor;
import static javafx.scene.layout.AnchorPane.setTopAnchor;

public class FXMLUtil {

    public static Node lookup(Parent parent, String id) {
        Node res = null;
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (id.equals(node.getId())) {
                res = node;
                break;
            } else {
                res = lookup(node, id);
                if (res != null) {
                    break;
                }
            }
        }
        return res;
    }

    private static Node lookup(Node node, String id) {
        if (node.getClass().isAssignableFrom(VBox.class)) {
            return lookupVBox((VBox) node, id);
        }
        if (node.getClass().isAssignableFrom(SplitPane.class)) {
            return lookupSplitPane((SplitPane) node, id);
        }
        if (node.getClass().isAssignableFrom(AnchorPane.class)) {
            return lookupAnchorPane((AnchorPane) node, id);
        }
        if (node.getClass().isAssignableFrom(GridPane.class)) {
            return lookupGridPane((GridPane) node, id);
        }
        if (node.getClass().isAssignableFrom(HBox.class)) {
            return lookupHBox((HBox) node, id);
        }
        if (node.getClass().isAssignableFrom(ScrollPane.class)) {
            return lookupScrollPane((ScrollPane) node, id);
        }
        if (node.getClass().isAssignableFrom(TitledPane.class)) {
            return lookupTitledPane((TitledPane) node, id);
        }
        return null;
    }

    private static Node lookupAnchorPane(AnchorPane pane, String id) {
        Node res = null;
        for (Node node : pane.getChildren()) {
            if (id.equals(node.getId())) {
                res = node;
                break;
            } else {
                res = lookup(node, id);
                if (res != null) {
                    break;
                }
            }
        }
        return res;
    }

    private static Node lookupSplitPane(SplitPane pane, String id) {
        Node res = null;
        ObservableList<Node> list = pane.getItems();
        for (Node node : list) {
            if (id.equals(node.getId())) {
                res = node;
                break;
            } else {
                res = lookup(node, id);
                if (res != null) {
                    break;
                }
            }
        }
        return res;
    }

    private static Node lookupVBox(VBox vbox, String id) {
        Node res = null;
        for (Node node : vbox.getChildren()) {
            if (id.equals(node.getId())) {
                res = node;
                break;
            } else {
                res = lookup(node, id);
                if (res != null) {
                    break;
                }
            }
        }
        return res;
    }

    private static Node lookupHBox(HBox hbox, String id) {
        Node res = null;
        for (Node node : hbox.getChildren()) {
            if (id.equals(node.getId())) {
                res = node;
                break;
            } else {
                res = lookup(node, id);
                if (res != null) {
                    break;
                }
            }
        }
        return res;
    }

    private static Node lookupGridPane(GridPane grid, String id) {
        Node res = null;
        for (Node node : grid.getChildren()) {
            if (id.equals(node.getId())) {
                res = node;
                break;
            } else {
                res = lookup(node, id);
                if (res != null) {
                    break;
                }
            }
        }
        return res;
    }

    private static Node lookupScrollPane(ScrollPane pane, String id) {
        return lookup(pane.getContent(), id);
    }
    
    private static Node lookupTitledPane(TitledPane pane, String id) {
        Node res = lookup(pane.getGraphic(), id);
        return res != null ? res : lookup(pane.getContent(), id);
    }
    
    public static void showLoadMask(AnchorPane node) {
        Platform.runLater(() -> {
            ProgressIndicator progressIndicator = new ProgressIndicator();
            VBox.setVgrow(progressIndicator, Priority.NEVER);
            
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            setLeftAnchor(vBox, 0.0);
            setRightAnchor(vBox, 0.0);
            setBottomAnchor(vBox, 0.0);
            setTopAnchor(vBox, 0.0);
            vBox.getChildren().add(progressIndicator);
            
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId("load-mask");
            anchorPane.getStyleClass().add("load-mask");
            setLeftAnchor(anchorPane, 0.0);
            setRightAnchor(anchorPane, 0.0);
            setBottomAnchor(anchorPane, 0.0);
            setTopAnchor(anchorPane, 0.0);
            anchorPane.getChildren().add(vBox);
            
            node.getChildren().add(anchorPane); 
        });
    }
    
    public static void hideLoadMask(AnchorPane parent) {
        Platform.runLater(() -> {
            Node loadMask = lookupAnchorPane(parent, "load-mask");
            loadMask.setVisible(false);
            parent.getChildren().remove(loadMask);
        });
    }
    
}
