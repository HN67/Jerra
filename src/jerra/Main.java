package jerra;

import jerra.item.Caffeine;
import jerra.item.HalfAndHalf;
import jerra.item.Inventory;
import jerra.item.Item;

/**
 * Entrypoint for Jerra
 */
public class Main {

    public static void main(String[] args) {

        //App.launch(App.class, args);
        Inventory inventory = new Inventory();

        inventory.add(new Caffeine());
        inventory.add(new Caffeine());
        inventory.add(new HalfAndHalf());
        System.out.println(inventory);
        inventory.remove(new Caffeine());
        System.out.println(inventory);
        inventory.remove(new HalfAndHalf());
        System.out.println(inventory);
        inventory.remove(new HalfAndHalf());
        inventory.remove(new HalfAndHalf());
        System.out.println(inventory);

    }
    
}