package tests;

import org.junit.Assert;
import org.junit.Test;

import jerra.item.Caffeine;
import jerra.item.HalfAndHalf;
import jerra.item.Item;

/**
 * Test Class for testing the Item class.
 */
public class ItemTest {

    @Test
    public void test_SameItemsAreEqual() {
        Item coffee = new Caffeine();
        Item coffee2 = new Caffeine();

        Assert.assertEquals("Comparing two Caffeine objects if equal, expecting true", true, coffee.equals(coffee2));
    }

    @Test
    public void test_DifferentItemsAreNotEqual() {
        Item coffee = new Caffeine();
        Item half = new HalfAndHalf();

        Assert.assertEquals("Comparing Caffeine and HalfAndHalf objects if equal, expecting true", false, coffee.equals(half));
    }

}
