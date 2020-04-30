package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import ca.ubc.cs.cpsc210.ui.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ItemTest {
    private Item testItem1;
    private String testItemName = "Steak";
    private GregorianCalendar testExpirationDate = new GregorianCalendar(2019, Calendar.JANUARY, 20);
    private GregorianCalendar testDateBought = new GregorianCalendar(2019, Calendar.JANUARY, 15);
    private double testWeight = 1.2;
    private double testCost = 20.50;
    private boolean testGoesInFreezer = false;

    @BeforeEach
    void setUpTestItem() throws EmptyStringException, NegativeInputException {
        testItem1 = new Item(testItemName, testExpirationDate, testDateBought, testWeight, testCost, testGoesInFreezer);
    }

    @Test
    void testConstructor() {
        assertEquals("Steak", testItemName);
        assertEquals(new GregorianCalendar(2019, Calendar.JANUARY, 20), testExpirationDate);
        assertEquals(new GregorianCalendar(2019, Calendar.JANUARY, 15), testDateBought);
        assertEquals(1.2, testWeight);
        assertEquals(20.50, testCost);
        assertFalse(testGoesInFreezer);
    }

    @Test
    void testConstructorWithEmptyItemName() {
        try {
            testItem1 = new Item("", testExpirationDate, testDateBought, testWeight, testCost,
                    testGoesInFreezer);
            fail("Test Failed: EmptyStringException expected");
        } catch (EmptyStringException e) {
            System.out.println("Test Passed");
        } catch (NegativeInputException e) {
            fail("Test Failed: EmptyStringException expected");
        }
    }

    @Test
    void testConstructorWithNegativeWeight() {
        try {
            testItem1 = new Item(testItemName, testExpirationDate, testDateBought, -testWeight, testCost,
                    testGoesInFreezer);
            fail("Test Failed: NegativeInputException expected");
        } catch (EmptyStringException e) {
            fail("Test Failed: NegativeInputException expected");
        } catch (NegativeInputException e) {
            System.out.println("Test Passed");
        }
    }

    @Test
    void testConstructorWithNegativeCost() {
        try {
            testItem1 = new Item(testItemName, testExpirationDate, testDateBought, testWeight, -testCost,
                    testGoesInFreezer);
            fail("Test Failed: NegativeInputException expected");
        } catch (EmptyStringException e) {
            fail("Test Failed: NegativeInputException expected");
        } catch (NegativeInputException e) {
            System.out.println("Test Passed");
        }
    }

    @Test
    void testPrintItemSummary() {
        testItem1.printItemSummary();

        System.out.println();
        System.out.println("Changing date bought to: 2018-1-16");
        testItem1.setDateBought(new GregorianCalendar(2018, Calendar.JANUARY, 16));
        testItem1.printItemSummary();
    }

    @Test
    void testPrintIemSummaryOfFreezerItem() {
        testItem1.moveToFreezer();
        testItem1.printItemSummary();
    }

    @Test
    void testItemSummaryToString() {
        String itemSummary = "Steak\n"
                + "Expiration Date: " + "2019-01-20\n"
                + "Date Bought: " + "2019-01-15\n"
                + "1.2 lbs\n"
                + "$20.50\n"
                + "In fridge.\n";
        assertEquals(testItem1.itemSummaryToString(), itemSummary);
    }
}
