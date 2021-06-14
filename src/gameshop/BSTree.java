/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameshop;

/**
 *
 * @author Admin
 */
public class BSTree {
    public static ShopItem root;

    public BSTree() {
        BSTree.root = null;
    }

    public void put(Weapon w, int nInStock){
        ShopItem newItem = new ShopItem(w, nInStock);
        if (root == null){
            root = newItem;
            return;
        }

        ShopItem parent, current;
        parent = current = root;
        while(current != null){
            parent = current;
            if(parent.item.weaponName.compareTo(w.weaponName) >= 1) current = current.left;
            else current = current.right;
        }

        if(parent.item.weaponName.compareTo(w.weaponName) >= 1) parent.left = newItem;
        else parent.right = newItem;
    }

    public void inOrderTrav(ShopItem current) {
    	if(current != null) {
            inOrderTrav(current.left);
            if(current.numberInStock > 0) {
                System.out.print("\nName: " + current.item.weaponName 
                    + "\tRange: " + current.item.range
                    + "\tDamage: " + current.item.damage 
                    + "\tWeight: " + current.item.weight
                    + "\tCost: " + current.item.cost
                    + "\tQty in Stock: " + current.numberInStock);
            }
            inOrderTrav(current.right);
    	}
    }

    public Weapon get(String wName){
        if (root == null) return null;

        ShopItem current = root;
        while(current != null && current.item.weaponName.compareTo(wName) != 0){
            if(current.item.weaponName.compareTo(wName) > 1) current = current.left;
            else current = current.right;
        }
        if (current == null || current.numberInStock > 0) return null;
        else return current.item;
    }

    public ShopItem getItem(String wName) {
    	if(root == null) return null;

        ShopItem current = root;
    	while (current != null && current.item.weaponName.compareTo(wName) != 0) {
            if(current.item.weaponName.compareTo(wName) >= 1) current = current.left;
            else current = current.right;
    	}
    	if(current == null || current.numberInStock <= 0) return null;
    	return current;
    }

    //Delete node
    void removeItem(String wName)
    {
        root = removeItem(root, wName);
        if (root!=null){
            System.out.println("\n Deletion successful!! \n" + wName
                + " was deleted from the shop list \n");
        }
    }

    ShopItem removeItem(ShopItem root, String wName)
    {
        if(root==null) return root;

        if (wName.compareTo(root.item.weaponName) < 0) root.left = removeItem(root.left, wName);
        else if (wName.compareTo(root.item.weaponName) > 0) root.right = removeItem(root.right, wName);
        else
        {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.item.weaponName = minValue(root.right);
            root.right = removeItem(root.right, root.item.weaponName);
        }
        return root;
    }

    String minValue(ShopItem root)
    {
        String minv = root.item.weaponName;
        while (root.left != null)
        {
            minv = root.left.item.weaponName;
            root = root.left;
        }
        return minv;
    }

    public void printTable() {
    	System.out.println("");
    	inOrderTrav(root);
    	System.out.println("");
    }
}
