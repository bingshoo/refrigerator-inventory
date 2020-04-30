package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NotInListException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class RecipeListTest {
    private RecipeList testRecipeList;

    @BeforeEach
    void setup() throws EmptyStringException {
        testRecipeList = new RecipeList();
        testRecipeList.addRecipe("Chicken Pot Pie");
        testRecipeList.addRecipe("BBQ Chicken Thighs");
    }

    @Test
    void testConstructor() {
        assertEquals(testRecipeList.getRecipeListSize(), 2);
        assertEquals(testRecipeList.getRecipe(0), "Chicken Pot Pie");
        assertEquals(testRecipeList.getRecipe(1), "BBQ Chicken Thighs");
    }

    @Test
    void testAddingEmptyStringAsRecipe() {
        try {
            testRecipeList.addRecipe("");
            fail("Test failed");
        } catch (EmptyStringException e) {
            System.out.println("Test Passed");
        }
    }

    @Test
    void testRemovingRecipeThatIsInRecipeList() {
        testRecipeList.printRecipes();

        System.out.println("Removing Chicken Pot Pie\n");
        try {
            testRecipeList.removeRecipe("Chicken Pot Pie");
        } catch (NotInListException e) {
            e.printStackTrace();
        }
        testRecipeList.printRecipes();

        testRecipeList.clearRecipeList();
        testRecipeList.printRecipes();
    }

    @Test
    void testRemovingRecipeFromEmptyList() {
        testRecipeList.clearRecipeList();

        try {
            testRecipeList.removeRecipe("Chicken Pot Pie");
        } catch (NotInListException e) {
            System.out.println("Expected Outcome");
        }
    }

    @Test
    void testRecipesToString() {
        String expectedString = "Your Recipes: \n" + "Chicken Pot Pie\n" + "BBQ Chicken Thighs\n";
        assertEquals(testRecipeList.recipesToString(), expectedString);
    }

    @Test
    void testRecipesToStringOnAnEmptyRecipeList() {
        testRecipeList.clearRecipeList();
        String expectedString = "Your Recipes: \n" + "You have no saved recipes";
        assertEquals(testRecipeList.recipesToString(), expectedString);
    }

    @Test
    void testRecipesToStringAfterRemovingFirstRecipe() {
        String expectedString = "Your Recipes: \n" + "Chicken Pot Pie\n" + "BBQ Chicken Thighs\n";
        assertEquals(testRecipeList.recipesToString(), expectedString);

        String expectedStringAfterRemoval = "Your Recipes: \n" + "BBQ Chicken Thighs\n";
        try {
            testRecipeList.removeRecipe("Chicken Pot Pie");
        } catch (NotInListException e) {
            e.printStackTrace();
        }
        assertEquals(testRecipeList.recipesToString(), expectedStringAfterRemoval);
    }

    @Test
    void testRemoveLastRecipeOnNonEmptyList() {
        String expectedString = "Your Recipes: \n" + "Chicken Pot Pie\n" + "BBQ Chicken Thighs\n";
        assertEquals(testRecipeList.getRecipeListSize(), 2);
        assertEquals(testRecipeList.recipesToString(), expectedString);

        testRecipeList.removeLastRecipe();
        String expectedStringAfterRemoval = "Your Recipes: \n" + "Chicken Pot Pie\n";
        assertEquals(testRecipeList.getRecipeListSize(), 1);
        assertEquals(testRecipeList.recipesToString(), expectedStringAfterRemoval);
    }

    @Test
    void testRemoveLastRecipeUntilRecipeListIsEmpty() {
        assertEquals(testRecipeList.getRecipeListSize(), 2);
        testRecipeList.removeLastRecipe();

        assertEquals(testRecipeList.getRecipeListSize(), 1);
        testRecipeList.removeLastRecipe();

        assertEquals(testRecipeList.getRecipeListSize(), 0);
    }

    @Test
    void testRemoveLastRecipeOnAnEmptyList() {
        testRecipeList.clearRecipeList();
        assertEquals(testRecipeList.getRecipeListSize(), 0);

        try {
            testRecipeList.removeLastRecipe();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Got expected Result");
        }
    }
}
