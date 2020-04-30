package ca.ubc.cs.cpsc210.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Freezer implements Iterable<FreezerItem> {
    private ArrayList<FreezerItem> freezerItems;
    protected int numItems;

    /*
     * Creates a Freezer object that holds FreezerItem in an ArrayList
     *                          also keeps track of total number of items
     *                          as int numItems
     * Class Invariant: numItems >= 0
     */
    public Freezer() {
        freezerItems = new ArrayList<>();
        numItems = 0;
        checkNumItemInvariant();
    }

    public Freezer(ArrayList<FreezerItem> freezerItems) {
        this.freezerItems = freezerItems;
        numItems = freezerItems.size();
        checkNumItemInvariant();
    }

    // MODIFIES: this
    // EFFECTS: adds a given freezer item to freezer and updates the number of items
    public void addFreezerItem(FreezerItem freezerItem) {
        freezerItems.add(freezerItem);
        numItems = freezerItems.size();
    }

    // REQUIRES: a valid index
    // EFFECTS: returns freezer item at given index
    public FreezerItem getFreezerItem(int index) {
        return freezerItems.get(index);
    }

    // EFFECTS: removes a freezer item at a given index
    //          if invalid index is given an error message is displayed
    public void removeFreezerItem(int index) {
        checkNumItemInvariant();
        try {
            freezerItems.remove(index);
            numItems = freezerItems.size();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your Freezer is empty!!!");
        }
        checkNumItemInvariant();
    }

    // MODIFIES: this
    // EFFECTS: removes the last freezer item in freezer and updates number of items
    public void removeLastFreezerItem() {
        removeFreezerItem(freezerItems.size() - 1);
    }

    // MODIFIES: this
    // EFFECTS: removes all freezer items in freezer and updates number of items
    public void removeAllItems() {
        freezerItems = new ArrayList<>();
        numItems = 0;
    }

    // EFFECTS: prints a summary of all freezer items currently in freezer
    public void printFreezerItems() {
        int index = 0;
        System.out.println("Items in your freezer: ");
        for (FreezerItem i: freezerItems) {
            System.out.print(index + ". ");
            System.out.println(i.getItemName());
            index++;
        }
        System.out.println("Look at all your frozen foods!");
    }

    // EFFECTS: print expiration dates of items in freezer
    public void printExpirationDates() {
        int index = 0;
        System.out.println("Expiration Dates for Items in your Freezer: ");
        for (FreezerItem i: freezerItems) {
            int expirationYear = i.getExpirationDate().get(Calendar.YEAR);
            int expirationMonth = i.getExpirationDate().get(Calendar.MONTH);
            int expirationDate = i.getExpirationDate().get(Calendar.DATE);

            System.out.print(index + ". ");
            System.out.print(i.getItemName());
            System.out.println(" Expiration Date: " + expirationYear + "-" + ++expirationMonth + "-" + expirationDate);
            index++;
        }
    }

    // EFFECTS: returns a String that represents the item summary of all items stored in this
    public String contentsToString() {
        String contentString = "";
        for (FreezerItem i: freezerItems) {
            contentString = contentString.concat(i.itemSummaryToString());
        }

        return  contentString;
    }

    // EFFECTS: returns number of items in freezer
    public int getNumItems() {
        return numItems;
    }

    // EFFECTS: returns freezerItems
    public ArrayList getFreezerItems() {
        return freezerItems;
    }

    // EFFECTS: checks that the number of items is non negative
    protected void checkNumItemInvariant() {
        assert numItems >= 0;
    }

    // EFFECTS: returns iterator for freezerItems
    @Override
    public Iterator iterator() {
        return freezerItems.iterator();
    }
}
