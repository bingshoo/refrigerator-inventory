package ca.ubc.cs.cpsc210.model;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;

import java.util.GregorianCalendar;

public class FreezerItem extends Item {


    /*
     * Creates a FreezerItem with String itemName, String expirationDate, String dateBought,
     *                       double weight, double cost, and boolean goesInFreezer.
     *                       throws an EmptyStringException if item name is an empty String
     *                       throws a NegativeInputException if weight and or cost <= 0
     * All FreezerItems will have a class invariant that goesInFreezer
     *                       is true.
     */
    public FreezerItem(String itemName, GregorianCalendar expirationDate, GregorianCalendar dateBought,
                       double weight, double cost)
            throws EmptyStringException, NegativeInputException {
        super(itemName, expirationDate, dateBought, weight, cost, true);
        assert isGoesInFreezer();
    }
}
