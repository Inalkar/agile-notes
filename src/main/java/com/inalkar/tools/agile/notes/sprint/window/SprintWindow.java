package com.inalkar.tools.agile.notes.sprint.window;

import com.inalkar.tools.agile.notes.sprint.controller.SprintWindowController;
import com.inalkar.tools.agile.notes.sprint.dto.Sprint;
import com.inalkar.tools.agile.notes.util.javafx.AbstractWindow;
import javafx.application.Platform;
import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
public class SprintWindow extends AbstractWindow {
    
    @Autowired
    private SprintWindowController sprintWindowController;
    
    private static final Logger logger = LogManager.getLogger(SprintWindow.class);
    protected Scene scene;

    public SprintWindow() throws IOException {
        setTitle("Edit Sprint");
        setHeight(600);
        setWidth(450);
        setFXMLTemplate("view/EditSprint.fxml");
        setModal(true);
    }

    public void editSprint(Sprint sprint) {
        sprintWindowController.setSprint(sprint);
//        ProductEditWindow wnd = new ProductEditWindow(p);
//        productController.getBtn_ok().setOnAction(actionEvent -> {
//            productController.showLoadMask();
//            CompletableFuture.runAsync(() -> {
//                try {
//                    productController.updateProduct();
//                    siteService.updateProduct(p);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }).thenAccept((Void) -> Platform.runLater(() -> {
//                productController.hideLoadMask();
//                wnd.close();
//            })).exceptionally((e) -> {
//                productController.hideLoadMask();
//                logger.catching(e);
//                error(i18n.getProperty("msg.error.product.cant.save"));
//                return null;
//            });
//        });
        this.showAndWait();
    }

    public void addSprint(Sprint sprint) throws Exception {
//        Product p = new Product();
//        p.custom = 1;
//        p.categoryID = c.categoryID;
//        ProductEditWindow wnd = new ProductEditWindow(p);
//        wnd.setTitle("Добавление продукта");
//
//        productController.getBtn_ok().setOnAction(actionEvent -> {
//            productController.showLoadMask();
//            CompletableFuture.runAsync(() -> {
//                try {
//                    productController.updateProduct();
//                    siteService.addProduct(p);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }).thenAccept((Void) -> Platform.runLater(() -> {
//                productController.hideLoadMask();
//                wnd.close();
//            })).exceptionally((e) -> {
//                productController.hideLoadMask();
//                logger.catching(e);
//                error(i18n.getProperty("msg.error.product.cant.save"));
//                return null;
//            });
//        });
//
//        wnd.showAndWait();
    }
    
}
