/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Controllers;

import ims.Data.Inventory;
import ims.MainApp;
import ims.Window;
import ims.WindowFactory;
import ims.Models.Part;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

/**
 *
 * @author timmc
 */
public class MainScreen extends ControllerBase {
    
    @FXML private TableColumn colPartID;
    @FXML private TableColumn colPartName;
    @FXML private TableColumn colPartInventory;
    @FXML private TableColumn colPartPrice;
    
    @FXML private TableView<Part> partsTable;
    
    public void initialize()
    {
        colPartID.setCellValueFactory(new PropertyValueFactory<Part, String>("PartID"));
        colPartName.setCellValueFactory(new PropertyValueFactory<Part, String>("Name"));
        colPartInventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("InStock"));
        colPartPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("Price"));
        
    }
    
    
    
    @FXML public void exitButtonPressed(ActionEvent event) throws IOException
    {
        _inventory.Save();
        Platform.exit();
        System.exit(0);
    }
    
    @FXML public void addPartButtonPressed(ActionEvent event) throws IOException
    {
//        this.addPartWindow.showAndWait();
        Window addPartWindow = WindowFactory.CreateWindow(MainApp.addPartUrl);
        AddPart controller = (AddPart) addPartWindow.getController();
        addPartWindow.ShowAndWait();
        Part part = controller.getPart();
        _inventory.addPart(part);
    }
    
    @FXML public void modifyPartButtonPressed(ActionEvent event) throws IOException
    {
        Window addPartWindow = WindowFactory.CreateWindow(MainApp.addPartUrl);
        AddPart controller = (AddPart) addPartWindow.getController();
        Part part = partsTable.getSelectionModel().getSelectedItem();
        
        if (part != null)
        {
            controller.UpdatePart(part);
            addPartWindow.ShowAndWait();
        }
    }
    
    @FXML public void deletePartButtonPressed(ActionEvent event)
    {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        
        if (part != null)
        {
            _inventory.deletePart(part);
        }
        
    }
    
    @FXML public void addProductButtonPressed(ActionEvent event)
    {
        
    }
    
    @FXML public void modifyProductButtonPressed(ActionEvent event)
    {
        
    }
    
    @FXML public void deleteProductButtonPressed(ActionEvent event)
    {
        
    }
    
    @FXML public void searchPartsButtonPressed(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText("Please Confirm");
        alert.setContentText("This is a message");
        alert.show();
    }
    
    @FXML public void searchProductsButtonPressed(ActionEvent event)
    {
        
    }

    @Override
    public void setInventory(Inventory value)
    {
        _inventory = value;
        partsTable.setItems(_inventory.GetPartsList());
    }

}
