package ca.ubc.cs.cpsc210.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Fridge extends Freezer {
    private ArrayList<Item> fridgeItems;

    // EFFECTS: creates a Fridge with fridgeItems and number of items
    public Fridge() {
        fridgeItems = new ArrayList<>();
        numItems = 0;
        checkNumItemInvariant();
    }

    // MODIFIES: this
    // EFFECTS: adds an item to the fridgeItems and updates the number of items
    public void addItem(Item item) {
        fridgeItems.add(item);
        numItems = fridgeItems.size();
    }

    // EFFECTS: removes an item and updates number of items given an index
    //          if the index is out of the index of fridgeItems then an error message is displayed
    public void removeItem(int index) {
        checkNumItemInvariant();
        try {
            fridgeItems.remove(index);
            numItems = fridgeItems.size();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your Fridge is empty!!!");
        }

        checkNumItemInvariant();
    }

    // MODIFIES: this
    // EFFECTS: removes the item at the end of fridgeItems
    public void removeLastItem() {
        removeItem(fridgeItems.size() - 1);
    }

    // MODIFIES: this
    // EFFECTS: removes all items from fridgeItems and updates the number of items
    public void removeAllItems() {
        fridgeItems.clear();
        numItems = 0;
    }

    // EFFECTS: returns the fridge items
    public ArrayList<Item> getFridgeItems() {
        return fridgeItems;
    }

    // REQUIRES: valid index for fridgeItems
    // EFFECTS: returns the fridge item at given index
    public Item getFridgeItem(int index) {
        return fridgeItems.get(index);
    }

    // EFFECTS: prints out a summary of items stored within fridge
    public void printFridgeItems() {
        int index = 0;
        System.out.println("Items in your Fridge: ");
        for (Item i: fridgeItems) {
            System.out.print(index + ". ");
            System.out.println(i.getItemName());
            index++;
        }
        if (numItems == 0) {
            System.out.println("Your fridge is empty!!!");
        } else {
            System.out.println("Look at all the food in your fridge!");
        }
    }

    // EFFECTS: prints out all expiration dates of items within fridge
    @Override
    public void printExpirationDates() {
        int index = 0;
        System.out.println("Expiration Dates for Items in your Fridge: ");
        for (Item i: fridgeItems) {
            int expirationYear = i.getExpirationDate().get(Calendar.YEAR);
            int expirationMonth = i.getExpirationDate().get(Calendar.MONTH);
            int expirationDate = i.getExpirationDate().get(Calendar.DATE);

            System.out.print(index + ". ");
            System.out.print(i.getItemName());
            System.out.println(" Expiration Date: " + expirationYear + "-" + ++expirationMonth + "-" + expirationDate);
            index++;
        }
        if (numItems == 0) {
            System.out.println("Your fridge is empty!!!");
        }
    }

    @Override
    // EFFECTS: returns a String that represents the item summary of all items stored in this
    public String contentsToString() {
        String contentString = "";
        for (Item i: fridgeItems) {
            contentString = contentString.concat(i.itemSummaryToString());
        }
        return  contentString;
    }

    // EFFECTS: returns fridgeItems iterator
    @Override
    public Iterator iterator() {
        return fridgeItems.iterator();
    }
}
