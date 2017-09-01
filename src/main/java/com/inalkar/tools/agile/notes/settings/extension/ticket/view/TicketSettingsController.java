package com.inalkar.tools.agile.notes.settings.extension.ticket.view;

import com.inalkar.tools.agile.notes.ticket.todo.dto.TicketToDo;
import com.inalkar.tools.agile.notes.ticket.todo.service.ITicketToDoService;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import static com.inalkar.tools.agile.notes.util.javafx.FXMLUtil.hideLoadMask;
import static com.inalkar.tools.agile.notes.util.javafx.FXMLUtil.showLoadMask;


@Component
public class TicketSettingsController implements JavaFXController, Initializable {
    
    @Autowired private ITicketTypeService ticketTypeService;
    @Autowired private ITicketToDoService ticketToDoService;
    @Autowired private ConfirmDialogsUtil confirmDialogsUtil;
    @Autowired private ErrorDialogsUtil errorDialogsUtil;
    
    @FXML private ListView<TicketType> ticketTypesList;
    @FXML private ListView<TicketToDo> ticketToDosList;
    @FXML private AnchorPane ticketTypesListViewPane;
    @FXML private AnchorPane ticketToDosListViewPane;
    
    @FXML
    private void addTicketType(ActionEvent event) {
        confirmDialogsUtil.confirmWithTextField(
                "Please input the Ticket Type name", 
                "", 
                (text) -> {
                    try {
                        ticketTypeService.addTicketType(new TicketType(text));
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
        TicketType selectedItem = ticketTypesList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;
        
        confirmDialogsUtil.confirm(
                "Are you sure you want to remove the item?", 
                () -> {
                    try {
                        ticketTypeService.removeTicketType(selectedItem);
                        loadTicketTypes();
                    } catch (Exception e) {
                        errorDialogsUtil.exceptionDialog("Can't remove ticket type.", e);
                    }
                }
        );
    } 
    
    @FXML
    private void editTicketType(ActionEvent event) {
        TicketType selectedItem = ticketTypesList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;
        
        confirmDialogsUtil.confirmWithTextField(
                "Please input the Ticket Type name",
                null,
                "Please input the Ticket Type name",
                selectedItem.title,
                (text) -> {
                    try {
                        selectedItem.title = text;
                        ticketTypeService.updateTicketType(selectedItem);
                        loadTicketTypes();
                    } catch (Exception e) {
                        errorDialogsUtil.exceptionDialog("Can't remove ticket type.", e);
                    }
                },
                null,
                "Update",
                "Cancel"
        );
    }
    
    @FXML
    private void addToDo(ActionEvent event) {
        TicketType selectedItem = ticketTypesList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;
        
        confirmDialogsUtil.confirmWithTextField(
                "Please input the ToDo name", 
                "", 
                (text) -> {
                    try {
                        ticketToDoService.addTicketToDo(selectedItem, new TicketToDo(text));
                        loadTicketToDos();
                    } catch (Exception e) {
                        errorDialogsUtil.exceptionDialog("Can't add new ticket ToDo.", e);
                    }
                },
                null
        );
    }
    
    @FXML
    private void removeToDo(ActionEvent event) {
        TicketToDo selectedItem = ticketToDosList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;
        
        confirmDialogsUtil.confirm(
                "Are you sure you want to remove the item?", 
                () -> {
                    try {
                        ticketToDoService.removeTicketToDo(selectedItem);
                        loadTicketToDos();
                    } catch (Exception e) {
                        errorDialogsUtil.exceptionDialog("Can't remove ticket ToDo.", e);
                    }
                }
        );
    }
    
    @FXML
    private void editToDo(ActionEvent event) {
        TicketToDo selectedItem = ticketToDosList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;
        
        confirmDialogsUtil.confirmWithTextField(
                "Please input the ticket ToDo name",
                null,
                "Please input the ticket ToDo name",
                selectedItem.title,
                (text) -> {
                    try {
                        selectedItem.title = text;
                        ticketToDoService.updateTicketToDo(selectedItem);
                        loadTicketToDos();
                    } catch (Exception e) {
                        errorDialogsUtil.exceptionDialog("Can't remove ticket ToDo.", e);
                    }
                },
                null,
                "Update",
                "Cancel"
        );
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
        ticketToDosList.setCellFactory(param -> new ListCell<TicketToDo>() {
            @Override
            protected void updateItem(TicketToDo item, boolean empty) {
                super.updateItem(item, empty);
                setText(!empty && item != null ? item.title : null);
            }
        });
        loadTicketTypes();
    }

    private void onTicketTypeSelected(ObservableValue<? extends TicketType> value, TicketType oldValue, 
                                      TicketType newValue) 
    {
        if (newValue == null) return;
        
        loadTicketToDos();
    }
    
    private void loadTicketTypes() {
        showLoadMask(ticketTypesListViewPane);
        ticketTypesList.getItems().clear();
        CompletableFuture.<List<TicketType>>supplyAsync(() -> {
            try {
                return ticketTypeService.getTicketTypes();
            } catch (Exception e) {
                errorDialogsUtil.exceptionDialog("Can't load ticket types", e);
            }
            return Collections.emptyList();
        }).thenAccept((ticketTypes) -> {
            Platform.runLater(() -> ticketTypesList.setItems(new ObservableListWrapper<>(ticketTypes)));
            hideLoadMask(ticketTypesListViewPane);
        });
    }
    
    private void loadTicketToDos() {
        TicketType ticketType = ticketTypesList.getSelectionModel().getSelectedItem();
        if (ticketType == null) return;
        
        showLoadMask(ticketToDosListViewPane);
        ticketToDosList.getItems().clear();
        CompletableFuture.<List<TicketToDo>>supplyAsync(() -> {
            try {
                return ticketToDoService.getTicketToDos(ticketType);
            } catch (Exception e) {
                errorDialogsUtil.exceptionDialog("Can't load ticket ToDo's", e);
            }
            return Collections.emptyList();
        }).thenAccept((ticketToDos) -> {
            Platform.runLater(() -> ticketToDosList.setItems(new ObservableListWrapper<>(ticketToDos)));
            hideLoadMask(ticketToDosListViewPane);
        });
    }
    
}
