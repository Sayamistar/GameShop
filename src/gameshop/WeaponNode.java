package gameshop;

public class WeaponNode {
    public Weapon newWeapon;
    public WeaponNode next;

    public WeaponNode(Weapon w) {
    	this.newWeapon = w;
    	this.next = null;
    }
}
