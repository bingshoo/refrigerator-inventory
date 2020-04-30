package ca.ubc.cs.cpsc210.model;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;

import java.util.Objects;

public class GroceryItem {
    private String groceryName;
    private int groceryQuantity;

    // EFFECTS: creates a GroceryItem with a name and a quantity
    //          throws an EmptyStringException when name is an empty String
    //          throws a NegativeInputException when quantity <= 0
    public GroceryItem(String groceryName, int groceryQuantity) throws EmptyStringException, NegativeInputException {
        if (groceryName.length() == 0) {
            throw new EmptyStringException("groceryName must be a non-empty String");
        } else if (groceryQuantity <= 0) {
            throw new NegativeInputException("groceryQuantity must be a positive non-zero integer");
        }
        this.groceryName = groceryName;
        this.groceryQuantity = groceryQuantity;
    }

    // EFFECTS: returns the groceryName
    public String getGroceryName() {
        return groceryName;
    }

    // EFFECTS: returns the groceryQuantity
    public int getGroceryQuantity() {
        return groceryQuantity;
    }
}
