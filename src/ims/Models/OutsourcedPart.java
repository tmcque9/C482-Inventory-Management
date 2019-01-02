/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tmcqueen
 */
public class OutsourcedPart extends Part 
{
    private StringProperty _companyName = new SimpleStringProperty();
    
    public void setCompanyName(String value)
    {
        this._companyName.set(value);
    }
    
    public String getCompanyName()
    {
        return this._companyName.get();
    }

    @Override
    public PartSource getPartSource()
    {
        return PartSource.OUTSOURCED;
    }
}
