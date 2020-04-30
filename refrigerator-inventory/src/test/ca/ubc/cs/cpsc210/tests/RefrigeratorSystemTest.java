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

public class RefrigeratorSystemTest {
    private Freezer testFreezerUnit;
    private Fridge testFridgeUnit;
    private GroceryList testRefrigeratorGroceryList;
    private RecipeList testRefrigeratorRecipeList;
    private RefrigeratorSystem testRefrigeratorSystem;
    private FreezerItem testFreezerItem1 = new FreezerItem("Vanilla Ice Cream",
                                               new GregorianCalendar(2019, Calendar.AUGUST, 30),
                new GregorianCalendar(2019, Calendar.AUGUST, 20)
                ,1.0, 8.50);

    private RefrigeratorSystemTest() throws EmptyStringException, NegativeInputException {
    }

    @BeforeEach
    void setup() {
        testRefrigeratorSystem = RefrigeratorSystem.getInstance();
        testFreezerUnit = testRefrigeratorSystem.getFreezerUnit();
        testFridgeUnit = testRefrigeratorSystem.getFridgeUnit();
        testRefrigeratorGroceryList = testRefrigeratorSystem.getRefrigeratorGroceryList();
        testRefrigeratorRecipeList = testRefrigeratorSystem.getRefrigeratorRecipeList();
    }

    @Test
    void testConstructor() {
        assertEquals(testRefrigeratorSystem.getFreezerUnit(), testFreezerUnit);
        assertEquals(testRefrigeratorSystem.getFridgeUnit(), testFridgeUnit);
        assertEquals(testRefrigeratorSystem.getRefrigeratorGroceryList(), testRefrigeratorGroceryList);
        assertEquals(testRefrigeratorSystem.getRefrigeratorRecipeList(), testRefrigeratorRecipeList);
    }

    @Test
    void testAddItemToFreezer() {
        testRefrigeratorSystem.getFreezerUnit().addFreezerItem(testFreezerItem1);

        assertEquals(testRefrigeratorSystem.getFreezerUnit().getNumItems(), 1);
        assertEquals(testRefrigeratorSystem.getFreezerUnit().getFreezerItem(0).getItemName(),
                "Vanilla Ice Cream");
    }
}
