/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tmcqueen
 */
public abstract class Part {
    
    private IntegerProperty _partId = new SimpleIntegerProperty();
    private StringProperty _name = new SimpleStringProperty();
    private DoubleProperty _price = new SimpleDoubleProperty();
    private IntegerProperty _inStock = new SimpleIntegerProperty();
    private IntegerProperty _min = new SimpleIntegerProperty();
    private IntegerProperty _max = new SimpleIntegerProperty();
    
    public abstract PartSource getPartSource();
    public IntegerProperty PartID () { return this._partId; }
    public StringProperty Name () { return this._name; }
    public DoubleProperty Price () { return this._price; }
    public IntegerProperty InStock () { return this._inStock; }
    public IntegerProperty Min () { return this._min; }
    public IntegerProperty Max () { return this._max; }
    
    /**
     *
     * @param value
     */
    public void setName(String value)
    {
        this._name.set(value);
    }
    
    /**
     *
     * @return
     */
    public String getName()
    {
        return this._name.get();
    }
    
    /**
     *
     * @param value
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
     * @param value
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
     * @param value
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
     * @param value
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
     * @param value
     */
    public void setPartID(int value)
    {
        this._partId.set(value);
    }
    
    /**
     *
     * @return
     */
    public int getPartID()
    {
        return this._partId.get();
    }
}
