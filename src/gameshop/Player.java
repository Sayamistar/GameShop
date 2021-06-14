/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameshop;

class Player {
    public String name;
    public Backpack bp;
    public int numItems;
    public double money;

    public Player(String n, double m, double wt) {
        this.name = n;
        this.money = m;
        this.numItems = 0;
        this.bp = new Backpack(wt);
    }

    public void buy(Weapon w) {
        if((w.cost <= money) && bp.currentWeight + w.weight <= bp.maxWeight) {
        System.out.println(w.weaponName + " purchased!");
        bp.insert(w);
        numItems++;
        }
        else {
            System.out.println("\nYour backpack capacity: " + bp.maxWeight
                + "\nCurrent Weight: " + bp.currentWeight
                + "\nWeight of selected weapon: " + w.weight
                + "\n Sorry! Backpack capacity exceeded. New weapon couldn't be purchased.\n");
        }
    }

    public void withdraw(double amt) {
        money = money - amt;
    }

    public boolean inventoryFull() {
        return (numItems == 10) ;
    }

    public void printCharacter() {
        System.out.println("\nName: " + name + "\nAvailable Money: " + money);
        printBackpack();
    }

    public void printBackpack()
    {
        System.out.println(name + ", you own "+numItems+" Weapons:");
        System.out.println(bp.toString());
        System.out.println();
    }
}
