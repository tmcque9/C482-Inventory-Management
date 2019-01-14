/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Controllers;

import ims.Data.Inventory;
import ims.Models.Part;
import ims.Models.InhousePart;
import ims.Models.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.annotation.Resources;

/**
 *
 * @author timmc
 */
public class AddPart extends ControllerBase {
    
    private Part _part;
    private Stage _stage;
 
    public Part getPart()
    {
        return _part;
    }
    
    @FXML private ToggleGroup inHouseOrOutsourced;
    
//    @FXML private RadioButton rbInHouse;
//    @FXML private RadioButton rbOutsourced;
    
    
    @FXML private TextField partID;
    @FXML private TextField partName;
    @FXML private TextField partInv;
    @FXML private TextField partPrice;
    @FXML private TextField partMax;
    @FXML private TextField partMin;
    @FXML private TextField partSpecificValue;
    @FXML private Label partSpecificValueLabel;
    @FXML private Label functionLabel;
    
    @FXML private Button btnSave;
    
    @FXML private Button btnCancel;
    
    @FXML private AnchorPane anchorPane;
    
    @FXML private void saveButtonAction() {
        
        RadioButton rb = (RadioButton) this.inHouseOrOutsourced.getSelectedToggle();

        if ( rb.getId().equalsIgnoreCase("INHOUSE"))
        {
            _part = new InhousePart();
            ((InhousePart)_part).setMachineID(Integer.parseInt(partSpecificValue.getText()));
        }
        else {
            _part = new OutsourcedPart();
        }
        
        Random rand = new Random();
        int n = rand.nextInt(999999)+100000;
        _part.setPartID(n);
//        _part.setPartID(Integer.parseInt(partID.getText()));
        _part.setName(partName.getText());
        _part.setInStock(Integer.parseInt(partInv.getText()));
        _part.setPrice(Integer.parseInt(partPrice.getText()));
        _part.setMax(Integer.parseInt(partMax.getText()));
        _part.setMin(Integer.parseInt(partMin.getText()));
        
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    @FXML private void cancelButtonAction() {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
    
    @FXML public void initialize()
    {
        this.inHouseOrOutsourced
                .selectedToggleProperty()
                .addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String value = ((RadioButton)newValue).getText();
                        if (value.equalsIgnoreCase("In-House"))
                        {
                            partSpecificValueLabel.setText("Machine ID");
                            partSpecificValue.setPromptText("Machine ID");
                        }
                        else
                        {
                            partSpecificValueLabel.setText("Company Name");
                            partSpecificValue.setPromptText("Company Name");
                        }
                        
                    }
                });
            }
            
        });
    }

    @Override
    public void setInventory(Inventory value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void UpdatePart(Part part)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
                functionLabel.setText("Modify Part");
                _part = part;
                Logger.getLogger(AddPart.class.getName()).log(Level.INFO, "Class: {0}", part.getClass());
            }
        });   
    }
}
