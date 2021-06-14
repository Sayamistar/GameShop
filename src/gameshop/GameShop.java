package gameshop;
import java.util.Scanner;

public class GameShop {
    public static BSTree bst = new BSTree();

    public static int getInteger(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            sc.next(); //clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextInt();
    }
    
    public static double getDouble(Scanner sc,String message) {
        System.out.print(message);
        while (!sc.hasNextDouble()) {
            sc.next(); //clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextDouble();
    }
    
    public static void addWeapons(BSTree bst,Scanner sc) {
        System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
        String weaponName;
        int weaponRange, weaponDamage, quantity;
        double weaponWeight, weaponCost;
        System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
        weaponName=sc.next();
        while (weaponName.compareTo("end") != 0) {
            weaponRange = -1;
            while(weaponRange < 0 || weaponRange > 10){
                weaponRange = getInteger(sc,"Please enter the Range of the Weapon (0-10):");
            }
            weaponDamage = getInteger(sc,"Please enter the Damage of the Weapon:"); 
            weaponWeight = getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
            weaponCost = getDouble(sc,"Please enter the Cost of the Weapon:");
            Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
            quantity = getInteger(sc,"Please enter the quantity in stock:"); 
            bst.put(w,quantity);
            System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
            weaponName = sc.next();
        }
    }

    public static void deleteShopItem(BSTree bst, Scanner sc) {
        String wName;
        System.out.print("Please enter the Weapon to DELETE ('end' to go back):");
        wName = sc.next();
        ShopItem si = bst.getItem(wName);
        if(wName.compareTo("end") != 0 && si != null && si.item.weaponName.compareTo(wName) == 0){
            bst.removeItem(wName);
            return;
        }
        System.out.println("\n" + wName + " is not available in the shop. \nCould not delete!\n");
    }

    public static void showRoomMenu(BSTree bst,Player p) {
        System.out.println("WELCOME TO THE SHOWROOM!!!!");
        bst.printTable();
        System.out.println("You have "+p.money+" money.");
        System.out.print("Please select a weapon to buy('end' to quit): ");
    }

    public static void showRoom(BSTree bst, Player p, Scanner sc) {
        String choice;
        showRoomMenu(bst, p);
        choice=sc.next();
        while (choice.compareTo("end") != 0 && !p.inventoryFull()) {
            ShopItem si = bst.getItem(choice);
            if (si != null) {
                if (si.item.cost > p.money)
                    System.out.println("Insufficient funds to buy "
                        + si.item.weaponName);
                else {
                    p.buy(si.item);
                    p.withdraw(si.item.cost);
                    si.numberInStock--;
                }
            }
            else System.out.println("** " + choice + " not found!! **" );
            showRoomMenu(bst, p);
            choice = sc.next();
        }
        System.out.println("");
    }
    
    public static void getWeapon(String wName){
        Weapon newWeapon = bst.get(wName);
        if(newWeapon != null){
            System.out.print("\nWeapon Name: " + newWeapon.weaponName
                + "\tRange: " + newWeapon.range
                + "\tDamage: " + newWeapon.damage
                + "\tWeight: " + newWeapon.weight
                + "\tCost: " + newWeapon.cost + "\n");
        }
    }

    public static void buyWeapon(Player p, String w){
        Weapon newWeapon = bst.get(w);
        if(newWeapon == null){
            System.out.println("The weapon you are looking for, "
                + newWeapon + " is currently available!");
            return;
        }
        p.buy(newWeapon);    
        p.printCharacter();                       
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter Player name: ");
        String pname=sc.next();
        Player pl= new Player(pname,45,20);
        System.out.println("Welcome, " + pl.name
            + "!\nMoney Available: 45.00\nBackpack Capacity: 20 lbs\n");

        int choice=0;
        do{
            System.out.println("\n$$$$$ WELCOME TO THE GAME SHOP $$$$$");
            System.out.println("\n\t\tMENU\n");
            System.out.println("1. Add Item to shop\n2. Delete Item from Shop\n3. Buy Item from shop\n4. View backpack\n5. View Player\n6. Exit");
            choice=getInteger(sc, "Please make a selection: ");
            switch (choice){
                case 1:
                    addWeapons(bst, sc);
                    break;
                case 2:
                    deleteShopItem(bst, sc);
                    break;
                case 3:
                    showRoom(bst, pl, sc);
                    break;
                case 4:
                    pl.printBackpack();
                    break;
                case 5:
                    pl.printCharacter();
                    break;                    
            }
        }while(choice!=6);
        System.out.println("\n THANK YOU \n");
    }
}
