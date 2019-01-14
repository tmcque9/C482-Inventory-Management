/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import ims.Data.Inventory;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author timmc
 */
public class MainApp extends Application {
    
    public static URL mainScreenUrl;
    public static URL addPartUrl;
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Inventory inventory;
        MainApp.mainScreenUrl = getClass().getResource("Views/MainScreen.fxml");
        MainApp.addPartUrl = getClass().getResource("Views/AddPart.fxml");
    
        try 
        {
            inventory = Inventory.Load("inventory.xml");
            Window mainWindow = WindowFactory.CreateWindow(mainScreenUrl, stage);
            mainWindow.getController().setInventory(inventory);
            mainWindow.Show();
        }
        catch (IOException e){}
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
