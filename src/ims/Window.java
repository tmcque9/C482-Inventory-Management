/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import ims.Controllers.ControllerBase;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author timmc
 */
public class Window<T extends ControllerBase> 
{
    private Stage _stage;
    private Scene _scene;
    private T _controller;
    
    public Window(Stage stage, Scene scene, T controller)
    {
        _stage = stage;
        _scene = scene;
        _controller = controller;
    }
        
    public void Show()
    {
        _stage.show();
    }
    
    public void ShowAndWait()
    {
        _stage.showAndWait();
    }
    
    public T getController()
    {
        return _controller;
    }
}
