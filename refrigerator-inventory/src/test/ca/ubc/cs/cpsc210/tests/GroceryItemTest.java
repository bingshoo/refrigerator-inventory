package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class GroceryItemTest {
    private GroceryItem testGroceryItem;
    private int numberOfGroceryItems;

    @BeforeEach
    void setup() throws EmptyStringException, NegativeInputException {
        testGroceryItem = new GroceryItem("Carrots", 2);
    }

    @Test
    void testConstructor() {
        assertEquals(testGroceryItem.getGroceryName(), "Carrots");
        assertEquals(testGroceryItem.getGroceryQuantity(), 2);
    }

    @Test
    void testConstructorWithEmptyGroceryName() {
        try {
            testGroceryItem = new GroceryItem("", 2);
            fail("Empty String not caught");
        } catch (EmptyStringException e) {
            System.out.println("Empty String Caught!");
        } catch (NegativeInputException e) {
            fail("Empty String not caught");
        }
    }

    @Test
    void testConstructorWithQuantityOfZero() {
        try {
            testGroceryItem = new GroceryItem("Spam", 0);
            fail("Negative Num not caught");
        } catch (EmptyStringException e) {
            fail("Negative Num not caught");
        } catch (NegativeInputException e) {
            System.out.println("Negative Number Caught");
        }
    }

    @Test
    void testConstructorWithNegativeQuantity() {
        try {
            testGroceryItem = new GroceryItem("Spam", -2);
            fail("Negative Num not caught");
        } catch (EmptyStringException e) {
            fail("Negative Num not caught");
        } catch (NegativeInputException e) {
            System.out.println("Negative Number Caught");
        }
    }
}
