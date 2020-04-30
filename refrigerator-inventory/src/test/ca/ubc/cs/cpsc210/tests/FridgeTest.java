package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FridgeTest {
    private Fridge testFridge;
    private Item testFridgeItem1;
    private Item testFridgeItem2;
    private Item testFridgeItem3;
    private Item testFridgeItem4;
    private Item testFridgeItem5;

    @BeforeEach
    void setUpFridge() throws EmptyStringException, NegativeInputException {
        testFridgeItem1 = new Item("Milk",
                new GregorianCalendar(2019, Calendar.JULY, 1),
                new GregorianCalendar(2019, Calendar.JUNE, 25),
                0.4, 3.50, false);
        testFridgeItem2 = new Item("Kraft Singles",
                new GregorianCalendar(2019, Calendar.AUGUST, 1),
                new GregorianCalendar(2019, Calendar.JUNE, 25),
                2.0, 5.00, false);
        testFridgeItem3 = new Item("Strawberry Jam",
                new GregorianCalendar(2019, Calendar.JULY, 31),
                new GregorianCalendar(2019, Calendar.JUNE, 25),
                0.4, 4.00, false);
        testFridgeItem4 = new Item("Apples",
                new GregorianCalendar(2019, Calendar.JULY, 10),
                new GregorianCalendar(2019, Calendar.JUNE, 25),
                2, 8, false);
        testFridgeItem5 = new Item("Ketchup",
                new GregorianCalendar(2019, Calendar.JULY, 1),
                new GregorianCalendar(2018, Calendar.JUNE, 25),
                0.6, 6.00, false);

        testFridge = new Fridge();
        testFridge.addItem(testFridgeItem1);
        testFridge.addItem(testFridgeItem2);
        testFridge.addItem(testFridgeItem3);
        testFridge.addItem(testFridgeItem4);
    }

    @Test
    void testConstructor() {
        assertEquals(testFridge.getNumItems(), 4);
        assertEquals(testFridge.getFridgeItem(0).getItemName(), "Milk");
        assertEquals(testFridge.getFridgeItem(1).getItemName(), "Kraft Singles");
        assertEquals(testFridge.getFridgeItem(2).getItemName(), "Strawberry Jam");
        assertEquals(testFridge.getFridgeItem(3).getItemName(), "Apples");
    }

    @Test
    void testRemoveItemsThenPrinting() {
        testFridge.printFridgeItems();

        testFridge.removeItem(0);
        testFridge.printFridgeItems();

        testFridge.removeLastItem();
        testFridge.printFridgeItems();
    }

    @Test
    void testRemoveAllItemsThenPrinting() {
        testFridge.printFridgeItems();

        testFridge.removeAllItems();
        testFridge.printFridgeItems();
    }

    @Test
    void testPrintExpirationDates() {
        testFridge.printExpirationDates();

        testFridge.removeLastItem();
        testFridge.printExpirationDates();

        testFridge.removeAllItems();
        testFridge.printExpirationDates();
    }

    @Test
    void testTryToRemoveItemFromEmptyList() {
        testFridge.removeAllItems();
        try {
            testFridge.removeLastItem();
            System.out.println("Test Passed");
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("We went out of bounds");
        }
    }

    @Test
    void testContentsToString() {
        System.out.println(testFridge.contentsToString());
    }
}
