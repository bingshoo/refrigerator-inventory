package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.Freezer;
import ca.ubc.cs.cpsc210.model.FreezerItem;
import ca.ubc.cs.cpsc210.model.Fridge;
import ca.ubc.cs.cpsc210.model.Item;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FridgeFreezerParser {

    public static Freezer parseFreezer(JSONObject freezerObject) throws EmptyStringException, NegativeInputException {
        Freezer freezer = new Freezer();
        JSONArray itemsArray = freezerObject.getJSONArray("freezerItems");

        for (Object i: itemsArray) {
            JSONObject item = (JSONObject) i;

            FreezerItem freezerItem = new FreezerItem(item.getString("itemName"),
                    (GregorianCalendar) item.get("expirationDate"),
                    (GregorianCalendar) item.get("dateBought"),
                    item.getDouble("weight"),
                    item.getDouble("cost"));

            freezer.addFreezerItem(freezerItem);
        }
        return freezer;
    }

    public static Fridge parseFridge(JSONObject fridgeObject) throws EmptyStringException, NegativeInputException {
        Fridge fridge = new Fridge();
        JSONArray itemsArray = fridgeObject.getJSONArray("fridgeItems");

        for (Object i: itemsArray) {
            JSONObject item = (JSONObject) i;

            Item fridgeItem = new Item(item.getString("itemName"),
                    (GregorianCalendar) item.get("expirationDate"),
                    (GregorianCalendar) item.get("dateBought"),
                    item.getDouble("weight"),
                    item.getDouble("cost"),
                    item.getBoolean("goesInFreezer"));

            fridge.addItem(fridgeItem);
        }
        return fridge;
    }
}
