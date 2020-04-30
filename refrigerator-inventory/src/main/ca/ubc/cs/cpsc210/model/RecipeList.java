package ca.ubc.cs.cpsc210.model;

import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NotInListException;

import javax.lang.model.UnknownEntityException;
import java.util.ArrayList;
import java.util.Objects;

public class RecipeList {
    private ArrayList<String> recipeList;

    // EFFECTS: constructs a new recipeList
    public RecipeList() {
        recipeList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a recipe as a String to the recipeList
    //          throws an EmptyStringException if recipe is an empty String
    public void addRecipe(String recipe) throws EmptyStringException {
        if (recipe.isEmpty()) {
            throw new EmptyStringException("Recipe must be a non-empty string");
        }
        recipeList.add(recipe);
    }

    // MODIFIES: this
    // EFFECTS: removes a recipe from the recipeList if the recipe exists in the recipeList
    //          else will print out a message
    public void removeRecipe(String recipe) throws NotInListException {
        if (recipeList.contains(recipe))  {
            recipeList.remove(recipeList.indexOf(recipe));
        } else {
            throw new NotInListException();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the recipe that is at the end of the recipe list
    public void removeLastRecipe() {
        recipeList.remove(recipeList.size() - 1);
    }

    // EFFECTS: prints out all the recipes within recipeList in order they were added
    //          prints a different message if recipeList has no recipes
    public void printRecipes() {
        System.out.println(recipesToString());
    }

    // EFFECTS: converts all recipes within recipeList in to a single string that has a
    //          a certain format
    public String recipesToString() {
        String recipeListString = "Your Recipes: " + "\n";
        if (recipeList.size() == 0) {
            recipeListString = recipeListString.concat("You have no saved recipes");
        } else {
            for (String r : recipeList) {
                recipeListString = recipeListString.concat(r + "\n");
            }
        }
        return recipeListString;
    }

    // MODIFIES: this
    // EFFECTS: removes all recipes from recipeList
    public void clearRecipeList() {
        recipeList.clear();
    }

    // REQUIRES: a valid index
    // EFFECTS: return a recipe from recipeList given an index
    public String getRecipe(int index) {
        return recipeList.get(index);
    }

    // EFFECTS: returns the size of the recipeList
    public int getRecipeListSize() {
        return recipeList.size();
    }

    public ArrayList<String> getRecipeList() {
        return recipeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecipeList that = (RecipeList) o;
        return Objects.equals(recipeList, that.recipeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeList);
    }
}
