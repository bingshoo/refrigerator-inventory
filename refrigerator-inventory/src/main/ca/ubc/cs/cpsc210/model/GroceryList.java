package ca.ubc.cs.cpsc210.model;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;

import java.util.ArrayList;
import java.util.Objects;

public class GroceryList {
    private ArrayList<GroceryItem> groceryList;

    // Constructor for GroceryList
    public GroceryList() {
        groceryList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new GroceryItem with a String for the groceryName and an int for groceryQuantity
    //          throws an EmptyStringException with an error message if groceryName is an empty String
    //          throws a NegativeInputException with an error message if groceryQuantity <= 0
    public void addToGroceryList(String groceryName, int groceryQuantity) throws EmptyStringException,
            NegativeInputException {
        groceryList.add(new GroceryItem(groceryName, groceryQuantity));
    }

    // MODIFIES: this
    // EFFECTS: removes the last GroceryItem with within the groceryList
    //          if groceryList is empty then an error message will be displayed
    public void removeLastGroceryListItem() {
        groceryList.remove(groceryList.size() - 1);
    }

    // MODIFIES: this
    // EFFECTS: removes all elements within groceryList
    public void clearGroceryList() {
        groceryList.clear();
    }

    // EFFECTS: prints a summary of all groceryName and groceryQunatity within groceryList
    public void printGroceryList() {
        System.out.println(groceryListToString());
    }

    // EFFECTS: returns a grocery list as a string with formatting
    public String groceryListToString() {
        int index = 1;
        String yourGroceryList = "Your Grocery List:\n";
        for (GroceryItem i: groceryList) {
            yourGroceryList = yourGroceryList.concat(index + ". " + i.getGroceryName() + " quantity: "
                    + i.getGroceryQuantity() + "\n");
            index++;
        }
        if (getGroceryListSize() == 0) {
            yourGroceryList = yourGroceryList.concat("Is Empty...");
        }
        return yourGroceryList;
    }

    // EFFECTS: returns the size of groceryList
    public int getGroceryListSize() {
        return groceryList.size();
    }

    // REQUIRES: an index of the groceryList
    // EFFECTS: returns the groceryName of a groceryItem in the list given an index
    public String getGroceryName(int index) {
        return groceryList.get(index).getGroceryName();
    }

    // REQUIRES: an index of the groceryList
    // EFFECTS: returns the groceryQuantity of a groceryItem in the list given an index
    public int getGroceryQuantity(int index) {
        return groceryList.get(index).getGroceryQuantity();
    }

    // EFFECTS: returns the groceryList as an ArrayList
    public ArrayList<GroceryItem> getGroceryList() {
        return groceryList;
    }
}
