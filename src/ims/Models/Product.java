/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Models;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;

/**
 * Product Class
 * @author tmcqueen
 * @version 1.0.0 18 Dec 2018
 */
public class Product 
{

//    private ArrayList<Part> associatedParts;
//    private int productID;
//    private StringProperty name;
//    private double price;
//    private int inStock;
//    private int min;
//    private int max;
    
    private ListProperty<Part> _associatedParts = new SimpleListProperty<Part>();
    private StringProperty _name = new SimpleStringProperty();
    private IntegerProperty _productId = new SimpleIntegerProperty();
    private IntegerProperty _inStock = new SimpleIntegerProperty();
    private IntegerProperty _min = new SimpleIntegerProperty();
    private IntegerProperty _max = new SimpleIntegerProperty();
    private DoubleProperty _price = new SimpleDoubleProperty();
    
    /**
     * 
     */
    public Product()
    {
//        this.associatedParts = new ArrayList<Part>();
    }
    
    public ListProperty<Part> AssociatedParts () { return this._associatedParts; }
    public StringProperty Name () { return this._name; }
    public IntegerProperty ProductId () { return this._productId; }
    public IntegerProperty InStock () { return this._inStock; }
    public IntegerProperty Min () { return this._min; }
    public IntegerProperty Max () { return this._max; }
    public DoubleProperty Price () { return this._price; }
    
    
    /**
     * Sets the name of the product
     * @param name
     */
    public void setName(String name)
    {
        this._name.set(name);
    }
    
    /**
     *
     * @return the name of the product
     */
    public String getName()
    {
        return this._name.get();
    }
    
    /**
     *
     * @param price
     */
    public void setPrice(double value)
    {
        this._price.set(value);
    }
    
    /**
     *
     * @return
     */
    public double getPrice()
    {
        return this._price.get();
    }
    
    /**
     *
     * @param inStock
     */
    public void setInStock(int value)
    {
        this._inStock.set(value);
    }
    
    /**
     *
     * @return
     */
    public int getInStock()
    {
        return this._inStock.get();
    }
    
    /**
     *
     * @param min
     */
    public void setMin(int value)
    {
        this._min.set(value);
    }
    
    /**
     *
     * @return
     */
    public int getMin()
    {
        return this._min.get();
    }
    
    /**
     *
     * @param max
     */
    public void setMax(int value)
    {
        this._max.set(value);
    }
    
    /**
     *
     * @return
     */
    public int getMax()
    {
        return this._max.get();
    }
    
    /**
     *
     * @param part
     */
    public void addAssociatedPart(Part part)
    {
        this._associatedParts.add(part);
    }
    
    /**
     *
     * @param partId
     */
    public void removeAssociatedPart(int id)
    {
        this._associatedParts.remove(id);
    }
    
    /**
     *
     * @param partId
     * @return
     */
    public Part lookupAssociatedPart(int partId)
    {
        return this._associatedParts.get(partId);
    }
    
    /**
     *
     * @param productId
     */
    public void setProductID(int productId)
    {
        this._productId.set(productId);
    }
    /**
     *
     * @return
     */
    public int getProductID()
    {
        return this._productId.get();
    }
}
