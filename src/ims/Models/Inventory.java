/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Models;

import ims.Controllers.MainScreen;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 * Inventory data storage class
 * 
 * @author tmcqueen
 * @version 1.0.0 18 Dec 2018
 */
public class Inventory 
{
    
    private ObservableList<Product> productList = FXCollections.observableArrayList(
        product -> new Observable[] {
            product.ProductId(),
            product.Name(),
            product.Price(),
            product.InStock(),
            product.Min(),
            product.Max(),
            product.AssociatedParts()
        }
    );
    
    private ObjectProperty<Product> currentProduct = new SimpleObjectProperty<Product>(null);
    
    public ObjectProperty<Product> CurrentProduct () { return this.currentProduct; }
    
    private ObservableList<Part> partsList = FXCollections.observableArrayList(
        part -> new Observable[] {
            part.PartID(),
            part.Name(),
            part.Price(),
            part.InStock(),
            part.Min(),
            part.Max()
        }
    );
    
    private ObjectProperty<Part> currentPart = new SimpleObjectProperty<Part>(null);
    
    public ObjectProperty<Part> CurrentPart () { return this.currentPart; }
    
    /**
     *
     */
    public Inventory() 
    {
    }
    
    /**
     * Adds a product to the internal array
     * @param product the product to be added
     */
    public void addProduct(Product product) 
    {
        this.productList.add(product);
    }
    
    /**
     *
     * @param product
     * @return
     */
    public boolean removeProduct(Product product) 
    {
        return this.productList.remove(product);
    }
    
    /**
     *
     * @param ProductId
     * @return
     */
    public Product lookupProduct(int ProductId)
    {
        return this.productList.get(ProductId);
    }
    
    /**
     *
     * @param ProductId
     */
    public void updateProduct(int ProductId)
    {
//        What do?
//        this.currentProduct.
    }
    
    /**
     *
     * @param part
     */
    public void addPart(Part part)
    {
        this.partsList.add(part);
    }
    
    /**
     *
     * @param part
     * @return
     */
    public boolean deletePart(Part part)
    {
        return this.partsList.remove(part);
    }
    
    /**
     *
     * @param PartId
     * @return
     */
    public Part lookupPart(int PartId)
    {
        return this.partsList.get(PartId);
    }
    
    /**
     *
     * @param PartId
     */
    public void updatePart(int PartId)
    {
//        what do?
    }
    
    public static Inventory Load(String fileName) throws IOException
    {
        File f = new File(fileName);
        if (!f.exists())
        {
            return new Inventory();
        }
        
        FileInputStream inputStream = new FileInputStream(fileName);
        XMLDecoder decoder = new XMLDecoder(inputStream);
        
        decoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, "Somethig went wrong... {0}", e.getMessage());
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.initModality(Modality.NONE);
//                alert.setTitle("File Load Exception");
//                alert.setHeaderText("Please Confirm");
//                alert.setContentText(e.toString());
//                alert.show();
            }
        
        });
        Inventory inventory = (Inventory) decoder.readObject();
        Logger.getLogger(MainScreen.class.getName()).log(Level.INFO, "Inventory loaded");
        decoder.close();
        inputStream.close();
        return inventory;
    }
    
    public void Save(String fileName) throws IOException
    {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(outputStream);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, "Somethig went wrong... {0}", e.getMessage());
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.initModality(Modality.NONE);
//                alert.setTitle("File Save Exception");
//                alert.setHeaderText("Please Confirm");
//                alert.setContentText(e.toString());
//                alert.show();
            }
        
        });
        
        encoder.writeObject(this);
        
        
        Logger.getLogger(MainScreen.class.getName()).log(Level.INFO, "Inventory successfully saved");
        encoder.close();
        outputStream.close();
    }
}
