/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.restaurant.italianApp;

import co.unicauca.restaurant.domain.Dish;
import co.unicauca.restaurant.domain.Product;
import co.unicauca.restaurant.domain.Size;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Jefferson Eduardo Campo
 */
public class ItalianDish extends Dish{
    
    private Product base;
    private List<Product> myOptions;
    private Size size;
    
    ItalianDish(double p) {
        this.price = p;
        myOptions = new Vector<>();
    }
    
    void setBase(Product base) {
        this.base = base; 
    }
    void addOption(Product option) {
        getMyOptions().add(option);
    }

    /**
     * @return the base
     */
    public Product getBase() {
        return base;
    }

    /**
     * @return the myOptions
     */
    public List<Product> getMyOptions() {
        return myOptions;
    }

    /**
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }
    
    public double getPrice(){
        price = base.getPrice();
        for(Product each: myOptions){
            price = price + each.getPrice();
        }
        if(size==Size.DOUBLE) price = 2*price;
        if(size==Size.FAMILY) price = 4*price;
        return price;
    }
}
