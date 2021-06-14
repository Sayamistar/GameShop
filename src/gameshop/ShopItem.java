/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameshop;

/**
 *
 * @author Adder
 */
public class ShopItem {
    Weapon item;
    int numberInStock;
    ShopItem left;
    ShopItem right;
    
    public ShopItem(Weapon w, int nInStock){
        this.item = w;
        this.numberInStock = nInStock;
        left = right = null;
    }
}
