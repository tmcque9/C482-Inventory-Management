/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Controllers;

import ims.Data.Inventory;

/**
 *
 * @author timmc
 */
public abstract class ControllerBase {
    
    protected Inventory _inventory;
    
    public abstract void setInventory(Inventory value);
}
