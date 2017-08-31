package com.inalkar.tools.agile.notes.settings.extension.ticket.view;

import com.inalkar.tools.agile.notes.ticket.type.dto.TicketType;
import com.inalkar.tools.agile.notes.ticket.type.service.ITicketTypeService;
import com.inalkar.tools.agile.notes.util.dialog.ConfirmDialogsUtil;
import com.inalkar.tools.agile.notes.util.dialog.ErrorDialogsUtil;
import com.inalkar.tools.agile.notes.util.javafx.JavaFXController;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import static com.inalkar.tools.agile.notes.util.javafx.FXMLUtil.hideLoadMask;
import static com.inalkar.tools.agile.notes.util.javafx.FXMLUtil.showLoadMask;


@Component
public class TicketSettingsController implements JavaFXController, Initializable {
    
    @Autowired
    private ITicketTypeService ticketTypeService;
    
    @Autowired
    private ConfirmDialogsUtil confirmDialogsUtil;
    
    @Autowired
    private ErrorDialogsUtil errorDialogsUtil;
    
    @FXML
    private ListView<TicketType> ticketTypesList;
    
    @FXML
    private AnchorPane ticketTypesListViewPane;
    
    @FXML
    private void addTicketType(ActionEvent event) {
        confirmDialogsUtil.confirmWithTextField(
                "Please input the Ticket Type name", 
                "", 
                (type) -> {
                    try {
                        ticketTypeService.addTicketType(new TicketType(type));
                        loadTicketTypes();
                    } catch (Exception e) {
                        errorDialogsUtil.exceptionDialog("Can't add new ticket type.", e);
                    }
                },
                null
        );
    } 
    
    @FXML
    private void removeTicketType(ActionEvent event) {
        confirmDialogsUtil.confirm(
                "Are you sure you want to remove the item?", 
                () -> {
                    try {
                        ticketTypeService.removeTicketType(ticketTypesList.getSelectionModel().getSelectedItem());
                        loadTicketTypes();
                    } catch (Exception e) {
                        errorDialogsUtil.exceptionDialog("Can't remove ticket type.", e);
                    }
                }
        );
    } 
    
    @FXML
    private void editTicketType(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketTypesList.getSelectionModel().selectedItemProperty().addListener(this::onTicketTypeSelected);
        ticketTypesList.setCellFactory(param -> new ListCell<TicketType>() {
            @Override
            protected void updateItem(TicketType item, boolean empty) {
                super.updateItem(item, empty);
                setText(!empty && item != null ? item.title : null);
            }
        });
        loadTicketTypes();
    }

    private void onTicketTypeSelected(ObservableValue<? extends TicketType> observableValue, TicketType ticketType, 
                                      TicketType ticketType1) 
    {
        System.out.println("old: " + ticketType + " new: " + ticketType1);
    }
    
    private void loadTicketTypes() {
        showLoadMask(ticketTypesListViewPane);
        ticketTypesList.getItems().clear();
        CompletableFuture.supplyAsync(() -> {
            try {
                return ticketTypeService.getTicketTypes();
            } catch (Exception e) {
                errorDialogsUtil.exceptionDialog("Can't load ticket types", e);
            }
            return Collections.emptyList();
        }).thenAccept((ticketTypes) -> {
            Platform.runLater(() -> ticketTypesList.setItems(new ObservableListWrapper(ticketTypes)));
            hideLoadMask(ticketTypesListViewPane);
        });
    }
    
}
