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

public class FreezerItemTest {
    private FreezerItem testFreezerItem;

    @BeforeEach
    void setup() throws EmptyStringException, NegativeInputException {
        testFreezerItem = new FreezerItem("Mixed Berries",
                new GregorianCalendar(2019, Calendar.JANUARY, 1),
                new GregorianCalendar(2018, Calendar.NOVEMBER, 29),
                1.0, 5.00);
    }

    @Test
    void testConstructor() {
        assertEquals(testFreezerItem.getItemName(), "Mixed Berries");
        assertEquals(testFreezerItem.getExpirationDate(),
                new GregorianCalendar(2019, Calendar.JANUARY, 1));
        assertEquals(testFreezerItem.getDateBought(),
                new GregorianCalendar(2018, Calendar.NOVEMBER, 29));
        assertEquals(testFreezerItem.getWeight(), 1.0);
        assertEquals(testFreezerItem.getCost(), 5.00);
    }

    @Test
    void testConstructorWithEmptyItemName() {
        try {
            testFreezerItem = new FreezerItem("",
                    new GregorianCalendar(2019, Calendar.JANUARY, 1),
                    new GregorianCalendar(2018, Calendar.NOVEMBER, 29),
                    1.0, 5.00);
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
            testFreezerItem = new FreezerItem("Mixed Berries",
                    new GregorianCalendar(2019, Calendar.JANUARY, 1),
                    new GregorianCalendar(2018, Calendar.NOVEMBER, 29),
                    -1.0, 5.00);
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
            testFreezerItem = new FreezerItem("Mixed Berries",
                    new GregorianCalendar(2019, Calendar.JANUARY, 1),
                    new GregorianCalendar(2018, Calendar.NOVEMBER, 29),
                    1.0, -5.00);
            fail("Test Failed: NegativeInputException expected");
        } catch (EmptyStringException e) {
            fail("Test Failed: NegativeInputException expected");
        } catch (NegativeInputException e) {
            System.out.println("Test Passed");
        }
    }

    @Test
    void testPrintItemSummary() {
        testFreezerItem.printItemSummary();
    }
}
