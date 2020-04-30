My project models a refrigerator. It contains a class that create Freezer objects to model the freezer of a refrigerator
and a class that models the fridge.

The Freezer stores a list of freezer items and keeps track of the number of items stored within it. The Fridge class
extends Freezer and stores a list of items and also keeps track of the number of items stored within it. These two
classes can add additional items, remove items, provide the expiration dates of all items and provide a summary of all
items stored in them.

Freezer items and non freezer items are modeled with the FreezerItem and Item classes. These classes create objects that
store the name of the item, expiration date, date purchased, weight, cost and whether they belong in the freezer or not.
These objects can also provide a printed summary of their fields.

This project is able to generate grocery lists and recipe lists with the GroceryList and RecipeList classes. The 
GroceryList objects are able to store GroceryItems and remove items. They can also print out a summary of groceryItems
stored. GroceryItems are objects that have an item name and a quantity.

The RecipeList object stores the names of recipes and can print out all the recipes stored. 

A RefrigeratorSystem class contains Freezer, Fridge, GroceryList and RecipeList classes as fields.

![image](https://github.ugrad.cs.ubc.ca/CPSC210-2018W-T2/project_x1f2b/blob/master/pictures/class_hierarchy.png)


**PHASE 2**

Overwritten equals and hashcode within RecipeList so that they compare each recipe string in each RecipeList

Added several toString methods in recipes, groceryItem and Item classes to prepare for the addition of a UI.
