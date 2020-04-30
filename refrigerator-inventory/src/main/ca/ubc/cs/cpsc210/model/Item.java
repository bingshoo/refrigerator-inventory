package ca.ubc.cs.cpsc210.model;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class Item {
    private String itemName;
    private GregorianCalendar expirationDate;
    private GregorianCalendar dateBought;
    private double weight;
    private double cost;
    private boolean goesInFreezer;

    // EFFECTS: Constructs an Item with given name, expiration date, date item is bought, weight,
    //          cost, and whether it goes in the freezer or not
    //          throws EmptyStringException when item name is empty
    //          throws NegativeInputException when weight and or cost is <= 0
    public Item(String itemName, GregorianCalendar expirationDate, GregorianCalendar dateBought,
                double weight, double cost, boolean goesInFreezer)
                    throws EmptyStringException, NegativeInputException {
        if (itemName.length() == 0) {
            throw new EmptyStringException("itemName must be a non-empty String");
        } else if (weight <= 0) {
            throw new NegativeInputException("weight must be a non-negative non-zero number");
        } else if (cost <= 0) {
            throw new NegativeInputException("cost must be a non-negative non-zero number");
        }
        this.itemName = itemName;
        this.expirationDate = expirationDate;
        this.dateBought = dateBought;
        this.weight = weight;
        this.cost = cost;
        this.goesInFreezer = goesInFreezer;
    }

    public String getItemName() {
        return itemName;
    }

    public GregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    public GregorianCalendar getDateBought() {
        return dateBought;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }

    public boolean isGoesInFreezer() {
        return goesInFreezer;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setExpirationDate(GregorianCalendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDateBought(GregorianCalendar dateBought) {
        this.dateBought = dateBought;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void moveToFreezer() {
        goesInFreezer = true;
    }

    public void moveToFridge() {
        goesInFreezer = false;
    }

    // EFFECTS: prints a summary of the fields of the item
    public void printItemSummary() {
        System.out.println(itemSummaryToString());
    }

    // EFFECTS: returns a string with the item's name, expiration date,
    //          date bought, weight and cost in dollars
    public String itemSummaryToString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String itemSummaryString = getItemName() + "\n"
                + "Expiration Date: " + printDate(getExpirationDate()) + "\n"
                + "Date Bought: " + printDate(getDateBought()) + "\n"
                + getWeight() + " lbs\n"
                + formatter.format(getCost()) + "\n";
        if (isGoesInFreezer()) {
            itemSummaryString = itemSummaryString.concat("In freezer.\n");
        } else {
            itemSummaryString = itemSummaryString.concat("In fridge.\n");
        }
        return itemSummaryString;
    }

    // EFFECTS: prints the date of an item
    private String printDate(GregorianCalendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setCalendar(date);

        return formatter.format(date.getTime());
    }
}
