/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.Data;

import ims.Models.Part;
import ims.Models.Product;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tmcqueen
 */
public class InventoryData implements Serializable
{
    public ArrayList<Product> Products = new ArrayList<Product>();
    public ArrayList<Part> Parts = new ArrayList<Part>();
}
