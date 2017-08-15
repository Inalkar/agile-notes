package com.inalkar.tools.agile.notes.util.javafx;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.CubicCurve;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class AnimationUtil {
    public static final int ANIMATION_DURATION = 300;
    public static final int TRANSLATE_SHIFT = 15;
    public static final int SEQUENTIAL_DELAY = 120;

    public static void addAnimatedTabChange(final TabPane pane) {
        pane.getSelectionModel().selectedItemProperty().addListener((observableValue, tab, tab2) -> {
            if (tab2 != null) {
                int prev = pane.getTabs().indexOf(tab);
                int curr = pane.getTabs().indexOf(tab2);
                if (prev < curr) {
                    animateParallel(
                            translateIn(tab2.getContent()),
                            fadeIn(tab2.getContent())
                    ).play();
                } else {
                    animateParallel(
                            translateInRight(tab2.getContent()),
                            fadeIn(tab2.getContent())
                    ).play();
                }
            }
        });
    }

    public static SequentialTransition animateImageChange(final ImageView view, final Image image) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        FadeTransition fadeOut = fadeOut(view);
        fadeOut.setOnFinished(actionEvent -> view.setImage(image));
        sequentialTransition.getChildren().addAll(
                fadeOut,
                fadeIn(view)
        );
        return sequentialTransition;
    }

    public static ScaleTransition animateScaleIn(Node node) {
        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(ANIMATION_DURATION), node);
        scaleTransition.setFromX(0);
        scaleTransition.setToX(1);
        scaleTransition.setFromY(0);
        scaleTransition.setToY(1);

        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);
        return scaleTransition;
    }

    public static ScaleTransition animateScaleOut(Node node) {
        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(ANIMATION_DURATION), node);
        scaleTransition.setFromX(1);
        scaleTransition.setToX(0);
        scaleTransition.setFromY(1);
        scaleTransition.setToY(0);

        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);
        return scaleTransition;
    }
    
    public static ParallelTransition scaleInFromNodeToNodeCenter(Node node, Node fromNode, AnchorPane toNode) {
        Point2D fromCenter = getNodeCenter(fromNode);
        Point2D toCenter = getNodeCenter(toNode);
        
        PathTransition pathTransition = 
                new PathTransition(
                        Duration.millis(ANIMATION_DURATION), 
                        buildCubicTrajectory(fromCenter, toCenter)
                );
        pathTransition.setNode(node);
        pathTransition.setCycleCount(1);
        pathTransition.setInterpolator(Interpolator.EASE_BOTH);
        pathTransition.setAutoReverse(false);
        
        return animateParallel(pathTransition, animateScaleIn(node));
    }
    
    public static ParallelTransition scaleOutToNode(Node node, Node toNode) {
        Point2D fromCenter = getNodeCenter(node);
        Point2D toCenter = getNodeCenter(toNode);

        CubicCurve cubicCurve = buildCubicTrajectory(fromCenter, toCenter);
        
        PathTransition pathTransition = 
                new PathTransition(
                        Duration.millis(ANIMATION_DURATION), 
                        cubicCurve
                );
        pathTransition.setNode(node);
        pathTransition.setCycleCount(1);
        pathTransition.setInterpolator(Interpolator.EASE_BOTH);
        pathTransition.setAutoReverse(false);
        
        return animateParallel(pathTransition, animateScaleOut(node));
    }

    public static SequentialTransition animateSequential(Animation... animations) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(animations);
        sequentialTransition.setCycleCount(1);
        return sequentialTransition;
    }

    public static ParallelTransition animateDelay(Animation... animations) {
        return animateDelay(SEQUENTIAL_DELAY, animations);
    }

    public static ParallelTransition animateDelay(int delay, Animation... animations) {
        ParallelTransition parallelTransition = new ParallelTransition();
        for (int i = 0; i < animations.length; i++) {
            animations[i].setDelay(Duration.millis(i * delay));
        }
        parallelTransition.getChildren().addAll(animations);
        parallelTransition.setCycleCount(1);
        return parallelTransition;
    }

    public static ParallelTransition animateParallel(int delay, Animation... animations) {
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(animations);
        parallelTransition.setCycleCount(1);
        parallelTransition.setDelay(Duration.millis(delay));
        return parallelTransition;
    }

    public static ParallelTransition animateParallel(Animation... animations) {
        return animateParallel(0, animations);
    }

    public static TranslateTransition translateOut(Node node) {
        return translateOut(node.getTranslateX(), node);
    }

    public static TranslateTransition translateOut(double fromX, Node node) {
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(ANIMATION_DURATION), node);
        translateTransition.setFromX(fromX);
        translateTransition.setToX(fromX - TRANSLATE_SHIFT);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        return translateTransition;
    }

    public static FadeTransition fadeOut(Node node) {
        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(ANIMATION_DURATION), node);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.0f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        return fadeTransition;
    }

    public static TranslateTransition translateIn(double toX, Node node) {
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(ANIMATION_DURATION), node);
        translateTransition.setFromX(toX + TRANSLATE_SHIFT);
        translateTransition.setToX(toX);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        return translateTransition;
    }

    public static TranslateTransition translateIn(Node node) {
        return translateIn(node.getTranslateX(), node);
    }

    public static TranslateTransition translateInRight(Node node) {
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(ANIMATION_DURATION), node);
        translateTransition.setFromX(node.getTranslateX()-TRANSLATE_SHIFT);
        translateTransition.setToX(node.getTranslateX());
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        return translateTransition;
    }

    public static FadeTransition fadeIn(Node node) {
        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(ANIMATION_DURATION), node);
        fadeTransition.setFromValue(0.0f);
        fadeTransition.setToValue(1.0f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        return fadeTransition;
    }
    
    private static Point2D getNodeCenter(Node node) {
        Bounds sceneBounds = node.localToScene(node.getBoundsInLocal());

        double fromStartX = Math.round(sceneBounds.getMinX() + (sceneBounds.getWidth() / 2));
        double fromStartY = Math.round(sceneBounds.getMinY() + (sceneBounds.getHeight() / 2));
        return new Point2D(fromStartX, fromStartY);
    }
    
    private static CubicCurve buildCubicTrajectory(Point2D from, Point2D to) {
        CubicCurve cubicCurve = new CubicCurve();
        
        cubicCurve.setStartX(from.getX());
        cubicCurve.setStartY(from.getY());
        
        double yCenter = from.getY() - to.getY();
        
        cubicCurve.setControlX1(Math.min(from.getX(), to.getX()));
        if (to.getY() > from.getY()) {
            cubicCurve.setControlY1(yCenter * 0.2);
        } else {
            cubicCurve.setControlY1(from.getY() - (from.getY() * 0.2));
        }
        
        cubicCurve.setControlX2(Math.min(from.getX(), to.getX()));
        if (to.getY() > from.getY()) {
            cubicCurve.setControlY2(to.getY() - (to.getY() * 0.2));
        } else {
            cubicCurve.setControlY2(yCenter * 0.2);
        }
        
        cubicCurve.setEndX(to.getX());
        cubicCurve.setEndY(to.getY());
        
        return cubicCurve;
    }

}
