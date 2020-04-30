package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FreezerTest {

    private Freezer testFreezer;
    private FreezerItem testFreezerItem1;
    private FreezerItem testFreezerItem2;
    private FreezerItem testFreezerItem3;
    private FreezerItem testFreezerItem4;

    @BeforeEach
    void setupFreezerTest() throws EmptyStringException, NegativeInputException {
        testFreezerItem1 = new FreezerItem("Vanilla Ice Cream",
                new GregorianCalendar(2019, Calendar.AUGUST, 30),
                new GregorianCalendar(2019, Calendar.AUGUST, 20)
                ,1.0, 8.50);
        testFreezerItem2 = new FreezerItem("Burger Patties",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 30),
                5.0, 20);
        testFreezerItem3 = new FreezerItem("Frozen Mixed Berries",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 28),
                3.0, 10.50);
        testFreezerItem4 = new FreezerItem("Ground Beef",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 20),
                8.0, 8.50);
        testFreezer = new Freezer();

        testFreezer.addFreezerItem(testFreezerItem1);
        testFreezer.addFreezerItem(testFreezerItem2);
        testFreezer.addFreezerItem(testFreezerItem3);
        testFreezer.addFreezerItem(testFreezerItem4);
    }

    @Test
    void testConstructor() {
        assertEquals(testFreezer.getNumItems(), 4);
        assertEquals(testFreezer.getFreezerItem(0).getItemName(), "Vanilla Ice Cream");
        assertEquals(testFreezer.getFreezerItem(1).getItemName(), "Burger Patties");
        assertEquals(testFreezer.getFreezerItem(2).getItemName(), "Frozen Mixed Berries");
        assertEquals(testFreezer.getFreezerItem(3).getItemName(), "Ground Beef");
    }

    @Test
    void addOneNewFreezerItem() throws EmptyStringException, NegativeInputException {
        FreezerItem testFreezerItem5 = new FreezerItem("Frozen Lasagna",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 20),
                12.0, 25.50);
        testFreezer.addFreezerItem(testFreezerItem5);

        assertEquals(testFreezer.getNumItems(), 5);
        assertEquals(testFreezer.getFreezerItem(4).getItemName(), "Frozen Lasagna");
    }

    @Test
    void testRemoveFirstFreezerItem() {
        testFreezer.removeFreezerItem(0);

        assertEquals(testFreezer.getNumItems(), 3);
        assertEquals(testFreezer.getFreezerItem(0).getItemName(), "Burger Patties");
        assertEquals(testFreezer.getFreezerItem(1).getItemName(), "Frozen Mixed Berries");
        assertEquals(testFreezer.getFreezerItem(2).getItemName(), "Ground Beef");
    }

    @Test
    void testRemoveLastFreezerItem() {
        testFreezer.removeLastFreezerItem();

        assertEquals(testFreezer.getNumItems(), 3);
        assertEquals(testFreezer.getFreezerItem(0).getItemName(), "Vanilla Ice Cream");
        assertEquals(testFreezer.getFreezerItem(1).getItemName(), "Burger Patties");
        assertEquals(testFreezer.getFreezerItem(2).getItemName(), "Frozen Mixed Berries");

        testFreezer.removeLastFreezerItem();

        assertEquals(testFreezer.getNumItems(), 2);
        assertEquals(testFreezer.getFreezerItem(0).getItemName(), "Vanilla Ice Cream");
        assertEquals(testFreezer.getFreezerItem(1).getItemName(), "Burger Patties");
    }

    @Test
    void testPrintFreezerItems() {
        testFreezer.printFreezerItems();

        assertEquals(testFreezer.getNumItems(), 4);
        assertEquals(testFreezer.getFreezerItem(0).getItemName(), "Vanilla Ice Cream");
        assertEquals(testFreezer.getFreezerItem(1).getItemName(), "Burger Patties");
        assertEquals(testFreezer.getFreezerItem(2).getItemName(), "Frozen Mixed Berries");
        assertEquals(testFreezer.getFreezerItem(3).getItemName(), "Ground Beef");
    }

    @Test
    void testRemovalWithEmptyFreezer() {
        testFreezer.removeLastFreezerItem();
        testFreezer.removeLastFreezerItem();
        testFreezer.removeLastFreezerItem();
        testFreezer.removeLastFreezerItem();
        try {
            testFreezer.removeLastFreezerItem();
            System.out.println("Test Passed");
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("Test failed");
        }
    }

    @Test
    void testRemoveAllItems() throws EmptyStringException, NegativeInputException {
        FreezerItem testFreezerItem5 = new FreezerItem("Frozen Lasagna",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 20),
                12.0, 25.50);
        testFreezer.removeAllItems();
        assertEquals(testFreezer.getFreezerItems(), new ArrayList<FreezerItem>());
        assertEquals(testFreezer.getNumItems(), 0);

        testFreezer.addFreezerItem(testFreezerItem5);
        testFreezer.removeAllItems();
        assertEquals(testFreezer.getFreezerItems(), new ArrayList<FreezerItem>());
        assertEquals(testFreezer.getNumItems(), 0);
    }

    @Test
    void testPrintFreezerItemsAfterAddingOneItem() throws EmptyStringException, NegativeInputException {
        FreezerItem testFreezerItem5 = new FreezerItem("Frozen Lasagna",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 20),
                12.0, 25.50);
        testFreezer.addFreezerItem(testFreezerItem5);

        testFreezer.printFreezerItems();

        assertEquals(testFreezer.getNumItems(), 5);

        assertEquals(testFreezer.getFreezerItem(0).getItemName(), "Vanilla Ice Cream");
        assertEquals(testFreezer.getFreezerItem(1).getItemName(), "Burger Patties");
        assertEquals(testFreezer.getFreezerItem(2).getItemName(), "Frozen Mixed Berries");
        assertEquals(testFreezer.getFreezerItem(3).getItemName(), "Ground Beef");
        assertEquals(testFreezer.getFreezerItem(4).getItemName(), "Frozen Lasagna");
    }

    @Test
    void testPrintFreezerItemsAfterAddingTwoItems() throws EmptyStringException, NegativeInputException {
        FreezerItem testFreezerItem5 = new FreezerItem("Frozen Lasagna",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 20),
                12.0, 25.50);
        FreezerItem testFreezerItem6 = new FreezerItem("Frozen Broccoli",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 20),
                12.0, 25.50);
        testFreezer.addFreezerItem(testFreezerItem5);
        testFreezer.addFreezerItem(testFreezerItem6);

        testFreezer.printFreezerItems();
    }

    @Test
    void testPrintFreezerItemsAfterRemovingOneItem() {
        testFreezer.removeFreezerItem(0);
        testFreezer.printFreezerItems();

        assertEquals(testFreezer.getNumItems(), 3);
        assertEquals(testFreezer.getFreezerItem(0).getItemName(), "Burger Patties");
        assertEquals(testFreezer.getFreezerItem(1).getItemName(), "Frozen Mixed Berries");
        assertEquals(testFreezer.getFreezerItem(2).getItemName(), "Ground Beef");
    }

    @Test
    void testPrintExpirationDates() {
        testFreezer.printExpirationDates();
    }

    @Test
    void testPrintExpirationDatesThenAddingObject() throws EmptyStringException, NegativeInputException {
        testFreezer.printExpirationDates();

        FreezerItem testFreezerItem5 = new FreezerItem("Frozen Lasagna",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 20),
                12.0, 25.50);
        testFreezer.addFreezerItem(testFreezerItem5);

        System.out.println();
        testFreezer.printExpirationDates();
    }

    @Test
    void testPrintExpirationDatesThenRemovingObject() {
        testFreezer.printExpirationDates();

        testFreezer.removeLastFreezerItem();
        testFreezer.removeFreezerItem(0);

        System.out.println();
        testFreezer.printExpirationDates();
    }

    @Test
    void testContentsToString() {
        System.out.println(testFreezer.contentsToString());
    }

}
