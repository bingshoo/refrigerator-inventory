### Phase 1 Feedback

#### General Comments
- looks like you've got off to a good start!

#### Checkstyle
- passes :-)

#### Code coverage
- 90.1% - very good!

#### Tests
- all tests pass!

#### Documentation
- very good

#### Implementation
- generally very good, just a few things to watch out for:

- for methods such as this:

```java
    // MODIFIES: this
    // EFFECTS: removes the last GroceryItem with within the groceryList
    //          if groceryList is empty then an error message will be displayed
    public void removeLastGroceryListItem() {
        try {
            groceryList.remove(groceryList.size() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your grocery list is empty!");
        }
    }
```      
rather than printing out an error message, have the method propagate the
exception.  Then the caller can decide how they want to handle the problem.
Keep in mind that you're going to be adding a graphical user interface to
your application in Phase 4.  So printing to System.out won't be an option.  
By propagating the exception, the calling code (presumably in the code
that controls the UI) will be able to display a pop-up error to the user, 
rather than printing to the console (which may go unnoticed).

- you have a number of methods that print to the console - these will not
be useful when you add a graphical user interface

                