package jerra.item;
import java.util.HashMap;


public class Inventory{

    private HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
    


    public String toString() {
        return this.inventory.toString();
    }

    public void add(Item item) {
        if (inventory.size() > 0) {  
            for (Item i : inventory.keySet()) {
                if (i.equals(item)) {
                    inventory.replace(i, inventory.get(i)+1);
                    return;
                }
                
            }
            inventory.put(item, 1);
            return;
        }
        else {
            inventory.put(item, 1);
        }
    }

    public void remove(Item item) {  
            for (Item i : inventory.keySet()) {
                if (i.equals(item)) {
                    if (inventory.get(i)>0) {
                        inventory.replace(i, inventory.get(i)-1);
                        return;
                    }
                }   
            }
    }

}