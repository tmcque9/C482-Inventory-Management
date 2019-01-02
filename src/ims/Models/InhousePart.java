/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author tmcqueen
 */
public class InhousePart extends Part
{
    private IntegerProperty _machineId = new SimpleIntegerProperty();
    
    public void setMachineID(int value)
    {
        this._machineId.set(value);
    }
    
    public int getMachineID()
    {
        return this._machineId.get();
    }

    @Override
    public PartSource getPartSource()
    {
        return PartSource.INHOUSE;
    }
}
