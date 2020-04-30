package ca.ubc.cs.cpsc210.model;

public class RefrigeratorSystem {
    private static RefrigeratorSystem refrigerator = new RefrigeratorSystem();
    private Freezer freezerUnit;
    private Fridge fridgeUnit;
    private GroceryList refrigeratorGroceryList;
    private RecipeList refrigeratorRecipeList;

    // EFFECTS: constructors a refrigerator that has a freezer, fridge grocery list and recipe list
    private RefrigeratorSystem() {
        freezerUnit = new Freezer();
        fridgeUnit = new Fridge();
        refrigeratorGroceryList = new GroceryList();
        refrigeratorRecipeList = new RecipeList();
    }

    // EFFECTS: sets freezer of refrigerator system
    public void setFreezerUnit(Freezer freezerUnit) {
        this.freezerUnit = freezerUnit;
    }

    // EFFECTS: sets fridge of refrigerator system
    public void setFridgeUnit(Fridge fridgeUnit) {
        this.fridgeUnit = fridgeUnit;
    }

    // EFFECTS: sets grocery list of refrigerator system
    public void setRefrigeratorGroceryList(GroceryList refrigeratorGroceryList) {
        this.refrigeratorGroceryList = refrigeratorGroceryList;
    }

    // EFFECTS: sets recipe list of refrigerator system
    public void setRefrigeratorRecipeList(RecipeList refrigeratorRecipeList) {
        this.refrigeratorRecipeList = refrigeratorRecipeList;
    }

    // EFFECTS: returns freezer
    public Freezer getFreezerUnit() {
        return freezerUnit;
    }

    // EFFECTS: returns fridge
    public Fridge getFridgeUnit() {
        return fridgeUnit;
    }

    // EFFECTS: return grocery list
    public GroceryList getRefrigeratorGroceryList() {
        return refrigeratorGroceryList;
    }

    // EFFECTS: return recipe list
    public RecipeList getRefrigeratorRecipeList() {
        return refrigeratorRecipeList;
    }

    // EFFECTS: returns an instance of RefrigeratorSystem
    public static RefrigeratorSystem getInstance() {
        return refrigerator;
    }
}
