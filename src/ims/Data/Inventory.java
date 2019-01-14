/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Data;

import ims.Controllers.MainScreen;
import ims.Models.Part;
import ims.Models.Product;
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
import javafx.collections.ListChangeListener;
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
    
    private final String _dataFileName;
        
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
    
    public ObservableList<Part> GetPartsList () { return this.partsList; }
    
    
    public Inventory(String dataFileName)
    {
        _dataFileName = dataFileName;
        SetListeners();
    }
    
    /**
     *
     */
    public Inventory(InventoryData data, String dataFileName) 
    {
        _dataFileName = dataFileName;
        
        productList.addAll(data.Products);
        partsList.addAll(data.Parts);
        
        SetListeners();
    }

    private void SetListeners()
    {
        partsList.addListener(new ListChangeListener<Part>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Part> c)
            {
                while (c.next()){
                    if (c.wasPermutated()) {
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            Logger.getLogger(MainScreen.class.getName()).log(Level.INFO, "Permuted: {0} {1}", new Object[] {i, partsList.get(i)});
                        }
                    }
                    else if (c.wasUpdated()) {
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            Logger.getLogger(MainScreen.class.getName()).log(Level.INFO, "Updated: {0} {1}", new Object[] {i, partsList.get(i)});
                        }
                    }
                    else {
                        for (Part removedItem : c.getRemoved()) {
                            Logger.getLogger(MainScreen.class.getName()).log(Level.INFO, "Removed: {0}", removedItem);
                        }
                        
                        for (Part addedItem : c.getAddedSubList()) {
                            Logger.getLogger(MainScreen.class.getName()).log(Level.INFO, "Added: {0}", addedItem);
                        }
                    }
                }
            }   
            
        });
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
        Logger.getLogger(Inventory.class.getName()).log(Level.INFO, "adding part '{0}' to database", part.getName());
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
            Logger.getLogger(Inventory.class.getName()).log(Level.INFO, "no data file found, newing up an empty object");
            return new Inventory(fileName);
        }
        
        FileInputStream inputStream = new FileInputStream(fileName);
        XMLDecoder decoder = new XMLDecoder(inputStream);
        
        decoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, "Somethig went wrong... {0}", e.getMessage());
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.initModality(Modality.NONE);
//                alert.setTitle("File Load Exception");
//                alert.setHeaderText("Please Confirm");
//                alert.setContentText(e.toString());
//                alert.show();
            }
        
        });
//        Inventory inventory = (Inventory) decoder.readObject();
        InventoryData data = (InventoryData) decoder.readObject();
        Logger.getLogger(MainScreen.class.getName()).log(Level.INFO, "Inventory loaded");
        decoder.close();
        inputStream.close();
        
        return new Inventory(data, fileName);
    }
    
    public void Save() throws IOException
    {
        FileOutputStream outputStream = new FileOutputStream(_dataFileName);
        XMLEncoder encoder = new XMLEncoder(outputStream);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, "Somethig went wrong... {0}", e.getMessage());
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.initModality(Modality.NONE);
//                alert.setTitle("File Save Exception");
//                alert.setHeaderText("Please Confirm");
//                alert.setContentText(e.toString());
//                alert.show();
            }
        
        });
        
        InventoryData data = new InventoryData();
        data.Products.addAll(productList);
        data.Parts.addAll(partsList);
        
        encoder.writeObject(data);
        
        
        Logger.getLogger(Inventory.class.getName()).log(Level.INFO, "Inventory successfully saved");
        encoder.close();
        outputStream.close();
    }
}
