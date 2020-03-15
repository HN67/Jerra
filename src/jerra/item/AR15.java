package jerra.item;

// import jerra.entity.Gun;

/**
 * AR15
 * Increase your stopping power.
 */

 public class AR15 extends Item {

    // private Gun gun = new Gun(bullet, 50);

    @Override
    public String name() {
        return "AR15";
    }

    @Override
    public String description() {
        return "Increase your stopping power.";
    }

    // public Gun getGun() {
    //     return Gun;
    // }

}
