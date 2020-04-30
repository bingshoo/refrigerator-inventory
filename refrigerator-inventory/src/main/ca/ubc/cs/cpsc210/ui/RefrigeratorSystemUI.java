package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.model.FreezerItem;
import ca.ubc.cs.cpsc210.model.Item;
import ca.ubc.cs.cpsc210.model.RefrigeratorSystem;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import ca.ubc.cs.cpsc210.persistence.FileIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static ca.ubc.cs.cpsc210.ui.AddItemFrame.generateItemButtonOnPanel;

public class RefrigeratorSystemUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    protected static ItemPanel fridgeItemPanel;
    protected static ItemPanel freezerItemPanel;
    private static JFrame frame;
    private static JButton addItemButton;
    private static JButton printSummaryButton;
    private static JButton recipeListButton;
    private static JButton groceryListButton;

    /*
     *  Starts up the application and sets up the initial JFrame
     */
    public static void main(String[] args) {
        RefrigeratorSystem rfs = RefrigeratorSystem.getInstance();

//        rfs.getFridgeUnit().addItem(new Item("Milk",
//                new GregorianCalendar(2019, Calendar.JULY, 1),
//                new GregorianCalendar(2019, Calendar.JUNE, 25),
//                0.4, 3.50, false));
//
//        try {
//            rfs.getFreezerUnit().addFreezerItem(new FreezerItem("Mixed Berries",
//                    new GregorianCalendar(2019, Calendar.JANUARY, 1),
//                    new GregorianCalendar(2018, Calendar.NOVEMBER, 29),
//                    1.0, 5.00));
//        } catch (EmptyStringException e) {
//            e.printStackTrace();
//        } catch (NegativeInputException e) {
//            e.printStackTrace();
//        }
        setupFridgePanel();
        setupFreezerPanel();
        renderItemButtons(rfs);
        setupJFrame();
        setUpWindowListener();

    }

    // REQUIRES: RefrigeratorSystem
    // MODIFIES: freezerItemPanel, fridgeItemPanel
    // EFFECTS: creates JButtons that represents items from the RefrigeratorSystem freezer and fridge
    //          and adds the buttons to freezerItemPanel and fridgeItemPanel respectively
    private static void renderItemButtons(RefrigeratorSystem rfs) {
        for (FreezerItem i: rfs.getFreezerUnit()) {
            AddItemFrame.generateItemButtonOnPanel(i, freezerItemPanel);
        }
        for (Item i: rfs.getFridgeUnit()) {
            generateItemButtonOnPanel(i, fridgeItemPanel);
        }
    }

    // EFFECTS: sets up the behaviour for when the JFrame is started and when it is closed
    private static void setUpWindowListener() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                super.windowOpened(windowEvent);
                FileIO.read();
                System.out.println("Window opened");
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                FileIO.write();
                System.out.println("window closing");
            }
        });
    }

    // MODIFIES: frame
    // EFFECTS: initializes JFrame and adds the components to it
    private static void setupJFrame() {
        frame = new JFrame("My Refrigerator");
        frame.setSize(WIDTH, HEIGHT);

        setUpAddItemButton();
        setUpShowSummaryButton();
        setUpGroceryListButton();
        setUpRecipeButton();

        JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        frame.getContentPane().add(fridgeItemPanel.getItemPanel());
        frame.getContentPane().add(freezerItemPanel.getItemPanel());
        frame.add(buttonPanel);

        buttonPanel.add(addItemButton);
        buttonPanel.add(printSummaryButton);
        buttonPanel.add(groceryListButton);
        buttonPanel.add(recipeListButton);

        frame.setLayout(new GridLayout(1,3));

        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // MODIFIES: fridgeItemPanel
    // EFFECTS: initializes fridgeItemPanel and sets the title
    private static void setupFridgePanel() {
        fridgeItemPanel = new ItemPanel("Fridge Items");
    }

    // MODIFIES: freezerItemPanel
    // EFFECTS: initializes freezerItemPanel and sets the title
    private static void setupFreezerPanel() {
        freezerItemPanel = new ItemPanel("Freezer Items");
    }

    // MODIFIES: addItemButton
    // EFFECTS: initializes addItemButton and adds an action listener to it
    //          if the button is pressed a new AddItemFrame is created and set visible
    private static void setUpAddItemButton() {
        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == addItemButton) {
                    AddItemFrame i = new AddItemFrame();
                    i.setVisible(true);
                }
            }
        });
    }

    // MODIFIES: printSummaryButton
    // EFFECTS: initializes printSummaryButton and adds an action listener to it
    //          if the button is pressed a new SummaryFrame is created and set visible
    private static void setUpShowSummaryButton() {
        printSummaryButton = new JButton("Show Summary");
        printSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == printSummaryButton) {
                    SummaryFrame sf = new SummaryFrame();
                    sf.setVisible(true);
                }
            }
        });
    }

    // MODIFIES: recipeListButton
    // EFFECTS: initializes recipeListButton and adds an action listener to it
    //          if the button is pressed a new RecipesFrame is created and set visible
    private static void setUpRecipeButton() {
        recipeListButton = new JButton("Saved Recipes");
        recipeListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == recipeListButton) {
                    RecipesFrame rf = new RecipesFrame();
                    rf.setVisible(true);
                }
            }
        });
    }

    // MODIFIES: groceryListButton
    // EFFECTS: initializes groceryListButton and adds an action listener to it
    //          if the button is pressed a new GroceryListFrame is created and set visible
    private static void setUpGroceryListButton() {
        groceryListButton = new JButton("Saved Grocery List");
        groceryListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == groceryListButton) {
                    GroceryListFrame gf = new GroceryListFrame();
                    gf.setVisible(true);
                }
            }
        });
    }
}
