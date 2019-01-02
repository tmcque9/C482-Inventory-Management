/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import ims.Controllers.ControllerBase;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author timmc
 */
public class WindowFactory {
    
    public static Window CreateWindow(URL url, Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(url);   
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        ControllerBase controller = loader.getController();
        return new Window(stage, scene, controller);
    }
    
    public static Window CreateWindow(URL url) throws IOException
    {
        Stage stage = new Stage();
        return CreateWindow(url, stage);
    }
    
}
