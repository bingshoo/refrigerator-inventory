package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class GroceryListTest {
    private GroceryList testGroceryList;

    @BeforeEach
    void setup() throws EmptyStringException, NegativeInputException {
        testGroceryList = new GroceryList();
        testGroceryList.addToGroceryList("Carrots", 2);
        testGroceryList.addToGroceryList("Chicken Thighs", 4);
        testGroceryList.addToGroceryList("Ground Beef", 1);
    }

    @Test
    void testConstructor() {
        assertEquals(testGroceryList.getGroceryListSize(), 3);
        assertEquals(testGroceryList.getGroceryName(0), "Carrots");
        assertEquals(testGroceryList.getGroceryName(1), "Chicken Thighs");
        assertEquals(testGroceryList.getGroceryName(2), "Ground Beef");

        assertEquals(testGroceryList.getGroceryQuantity(0), 2);
        assertEquals(testGroceryList.getGroceryQuantity(1), 4);
        assertEquals(testGroceryList.getGroceryQuantity(2), 1);
    }
    @Test
    void testRemoveLastGroceryListItem() {
        testGroceryList.removeLastGroceryListItem();

        assertEquals(testGroceryList.getGroceryName(0), "Carrots");
        assertEquals(testGroceryList.getGroceryName(1), "Chicken Thighs");
        try {
            assertEquals(testGroceryList.getGroceryName(2), "Ground Beef");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Last Item Removed");
        }
        assertEquals(testGroceryList.getGroceryQuantity(0), 2);
        assertEquals(testGroceryList.getGroceryQuantity(1), 4);
        try {
            assertEquals(testGroceryList.getGroceryQuantity(2), 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Last Item Removed");
        }
    }

    @Test
    void testRemovingOnAnEmptyList() {
        testGroceryList.clearGroceryList();
        try {
            testGroceryList.removeLastGroceryListItem();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Got expected outcome");
        }

    }

    @Test
    void testPrintGroceryListThenClearAndPrintingAgain() {
        testGroceryList.printGroceryList();

        testGroceryList.clearGroceryList();
        testGroceryList.printGroceryList();
    }

    @Test
    void testGroceryListToString() {
        String expectedString = "Your Grocery List:\n" + "1. Carrots quantity: 2\n"
                + "2. Chicken Thighs quantity: 4\n" + "3. Ground Beef quantity: 1\n";
        assertEquals(testGroceryList.groceryListToString(), expectedString);
    }

    @Test
    void testGroceryListToStringOnEmptyGroceryList() {
        String expectedString = "Your Grocery List:\n" + "Is Empty...";
        testGroceryList.clearGroceryList();
        assertEquals(testGroceryList.groceryListToString(), expectedString);
    }

    @Test
    void testGroceryListToStringAfterRemovingLastItem() {
        String expectedString = "Your Grocery List:\n" + "1. Carrots quantity: 2\n"
                + "2. Chicken Thighs quantity: 4\n" + "3. Ground Beef quantity: 1\n";
        assertEquals(testGroceryList.groceryListToString(), expectedString);

        testGroceryList.removeLastGroceryListItem();
        String expectedStringAfterRemoval = "Your Grocery List:\n" + "1. Carrots quantity: 2\n"
                + "2. Chicken Thighs quantity: 4\n";
        assertEquals(testGroceryList.groceryListToString(), expectedStringAfterRemoval);

    }
}
