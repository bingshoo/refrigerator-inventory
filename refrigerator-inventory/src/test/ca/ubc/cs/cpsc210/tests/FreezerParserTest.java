package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import ca.ubc.cs.cpsc210.ui.*;

import org.junit.jupiter.api.Test;

import static ca.ubc.cs.cpsc210.parsers.FridgeFreezerParser.parseFreezer;
import static ca.ubc.cs.cpsc210.persistence.Jsonifier.freezerToJson;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FreezerParserTest {
    private Freezer testFreezer;
    private JSONObject testFreezerJson;

    @BeforeEach
    void setup() throws EmptyStringException, NegativeInputException {
        testFreezer = new Freezer();
        FreezerItem testFreezerItem1 = new FreezerItem("Vanilla Ice Cream",
                new GregorianCalendar(2019, Calendar.AUGUST, 30),
                new GregorianCalendar(2019, Calendar.AUGUST, 20)
                ,1.0, 8.50);
        FreezerItem testFreezerItem2 = new FreezerItem("Burger Patties",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 30),
                5.0, 20);
        FreezerItem testFreezerItem3 = new FreezerItem("Frozen Mixed Berries",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 28),
                3.0, 10.50);
        FreezerItem testFreezerItem4 = new FreezerItem("Ground Beef",
                new GregorianCalendar(2019, Calendar.JULY, 20),
                new GregorianCalendar(2019, Calendar.JUNE, 20),
                8.0, 8.50);

        testFreezer.addFreezerItem(testFreezerItem1);
        testFreezer.addFreezerItem(testFreezerItem2);
        testFreezer.addFreezerItem(testFreezerItem3);
        testFreezer.addFreezerItem(testFreezerItem4);
    }

    @Test
    void testParseFreezerWorking() {
        testFreezerJson = freezerToJson(testFreezer);

        try {
            Freezer parsedFreezer = parseFreezer(testFreezerJson);
            assertEquals(parsedFreezer.contentsToString(), testFreezer.contentsToString());
        } catch (EmptyStringException e) {
            fail();
        } catch (NegativeInputException e) {
            fail();
        }
    }


}
