/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameshop;

class Weapon
    {
        public String weaponName;
        public int range;
        public int damage;
        public double weight;
        public double cost;

        public Weapon(String n, int ran, int dam, double w, double c)
        {
            weaponName = n;
            range = ran;
            damage = dam;
            weight = w;
            cost = c;
        }
    }
