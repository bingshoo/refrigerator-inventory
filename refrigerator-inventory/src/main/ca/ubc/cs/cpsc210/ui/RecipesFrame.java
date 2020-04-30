package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.model.RefrigeratorSystem;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NotInListException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipesFrame extends JPanel implements ActionListener {
    private int frameHeight = 800;
    private int frameWidth = 500;

    private JButton addRecipe;
    private JButton removeLastRecipe;
    private JButton removeRecipe;
    private JButton showRecipes;
    private JTextField recipeTextField;

    // MODIFIES: this
    // EFFECTS: initializes JFrame and adds components to it
    public RecipesFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Your Saved Recipes");
        frame.setLayout(new GridLayout(2,1));
        frame.setSize(frameWidth,frameHeight);

        JPanel buttonsPanel = new JPanel();
        recipeTextField = new JTextField("Enter Recipe");

        setUpJButtons();
        buttonsPanel.add(addRecipe);
        buttonsPanel.add(removeRecipe);
        buttonsPanel.add(recipeTextField);
        buttonsPanel.add(removeLastRecipe);
        buttonsPanel.add(removeRecipe);
        buttonsPanel.setLayout(new GridLayout(2,2));

        frame.add(buttonsPanel);
        frame.add(showRecipes);
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes JButton fields and adds action listeners to them
    private void setUpJButtons() {
        addRecipe = new JButton("Add Recipe");
        addRecipe.addActionListener(this);

        removeLastRecipe = new JButton("Remove Last Recipe");
        removeLastRecipe.addActionListener(this);

        removeRecipe = new JButton("Remove Recipe");
        removeRecipe.addActionListener(this);

        showRecipes = new JButton("Display Recipes");
        showRecipes.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RefrigeratorSystem rfs = RefrigeratorSystem.getInstance();
        String recipe = recipeTextField.getText();
        if (e.getSource() == addRecipe) {
            addingInRecipeFrame(rfs, recipe);
        } else if (e.getSource() == removeLastRecipe) {
            removeLastInRecipeFrame(rfs);
        } else if (e.getSource() == removeRecipe) {
            removeInRecipeFrame(rfs, recipe);
        } else if (e.getSource() == showRecipes) {
            displayRecipes(rfs);
        }
    }

    // EFFECTS: adds recipe to refrigerator system recipe list and displays the recipes in the recipe list on a pop up
    //          window if the user attempts to add an enter recipe then an error message will pop up
    private void addingInRecipeFrame(RefrigeratorSystem rfs, String recipe) {
        try {
            rfs.getRefrigeratorRecipeList().addRecipe(recipe);
            String display = rfs.getRefrigeratorRecipeList().recipesToString();
            JOptionPane.showMessageDialog(null, display);
            System.out.println(rfs.getRefrigeratorRecipeList().recipesToString());
        } catch (EmptyStringException e1) {
            JOptionPane.showMessageDialog(null, "Please enter a recipe", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: removes last recipe in refrigerator system recipe list and displays the recipes in the recipe list to be
    //          on a pop up window if the user attempts to remove from an empty list hen an error message will pop up
    private void removeLastInRecipeFrame(RefrigeratorSystem rfs) {
        try {
            rfs.getRefrigeratorRecipeList().removeLastRecipe();
            String display = rfs.getRefrigeratorRecipeList().recipesToString();
            JOptionPane.showMessageDialog(null, display);
        } catch (ArrayIndexOutOfBoundsException exception) {
            JOptionPane.showMessageDialog(null, "Your recipe list is empty", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: removes given recipe from the refrigerator system recipe list and displays the remaining recipes within
    //          the recipe list on a pop up window, if the user attempts to remove a recipe that is not in the list
    //          then an error message will pop up
    private void removeInRecipeFrame(RefrigeratorSystem rfs, String recipe) {
        try {
            rfs.getRefrigeratorRecipeList().removeRecipe(recipe);
            String display = rfs.getRefrigeratorRecipeList().recipesToString();
            JOptionPane.showMessageDialog(null, display);
        } catch (NotInListException e1) {
            JOptionPane.showMessageDialog(null, recipe + " is not in the recipe list",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: creates a pop up message with the recipes within the refrigerator system recipe list
    private void displayRecipes(RefrigeratorSystem rfs) {
        String display = rfs.getRefrigeratorRecipeList().recipesToString();
        JOptionPane.showMessageDialog(null, display);
    }

}
