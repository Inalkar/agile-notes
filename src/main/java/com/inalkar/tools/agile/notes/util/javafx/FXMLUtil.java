package com.inalkar.tools.agile.notes.util.javafx;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    
}
