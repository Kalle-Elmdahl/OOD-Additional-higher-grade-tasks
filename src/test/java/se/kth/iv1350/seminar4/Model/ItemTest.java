package se.kth.iv1350.seminar4.Model;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.seminar4.DTO.ItemDTO;
import se.kth.iv1350.seminar4.model.Item;

public class ItemTest {
    Item item;
    @Before
    public void setUp() {
        item = new Item(new ItemDTO("description", 10, 10, "name", "identifier"));
    }

    @After
    public void tearDown() {
        item = null;
    }

    @Test
    public void testIncreaseQuantity() {
        int startQuantity = item.getQuantity();
        item.increaseQuantity();
        assertEquals("Increase quantity did not give expected output", startQuantity + 1, item.getQuantity());
    }
}
