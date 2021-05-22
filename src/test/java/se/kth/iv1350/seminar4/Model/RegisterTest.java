package se.kth.iv1350.seminar4.Model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.seminar4.model.Register;

import static org.junit.Assert.assertEquals;

public class RegisterTest {
    private Register instance;

    @Before
    public void setUp() {
        instance = Register.getInstance();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testUpdateRegisterAmount() {
        double startAmount = instance.getAmount();
        instance.updateAmount(50);
        assertEquals("UpdateAmount not working for positive numbers", startAmount + 50, instance.getAmount(), .01);
        instance.updateAmount(-30);
        assertEquals("UpdateAmount not working for negative numbers", startAmount + 50 - 30, instance.getAmount(), .01);
    }
}