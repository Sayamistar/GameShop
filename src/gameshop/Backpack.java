
package gameshop;
// This class should implement a backpack as a linked list
    // The backpack can hold any number of weapons as long as maxWeight is not crossed.
    // If an attempt to add a weapon to backpack is rejected due to weight
    class Backpack
    {
        double maxWeight;
        double currentWeight;
        WeaponNode head;
        
        public Backpack(double maxWt) {
            this.head = null;
            this.currentWeight = 0;
            this.maxWeight = maxWt;
        }

        public void insert(Weapon w) {
            WeaponNode nw = new WeaponNode(w);
            System.out.println("Weight Added: " + nw.newWeapon.weight);

            if(currentWeight + nw.newWeapon.weight <= maxWeight) {
                if(head == null) {
                    head = nw;
                    currentWeight += nw.newWeapon.weight;
                    return;
                }
                WeaponNode current = head;
                while(current.next != null) {
                    current = current.next;
                }
                currentWeight += nw.newWeapon.weight;
                current.next = nw;
            }
        }

        public String toString() {
            String bp = "";
            WeaponNode current = head;
            while(current != null) {
                bp += "Weapon Name: " + current.newWeapon.weaponName;
                bp += "\tRange: " + current.newWeapon.range;
                bp += "\tDamage: " + current.newWeapon.damage;
                bp += "\tWeight:" +current.newWeapon.weight;
                bp += "\tCost: " + current.newWeapon.cost;
                bp += "\n";
                current = current.next;
            }
            return bp;
        }
    }
