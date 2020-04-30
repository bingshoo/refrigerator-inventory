package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.model.GroceryList;
import ca.ubc.cs.cpsc210.model.RefrigeratorSystem;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroceryListFrame extends JFrame implements ActionListener {
    private JLabel groceryName;
    private JLabel groceryQuantity;

    private JTextField groceryNameField;
    private JTextField groceryQuantityField;

    private JButton addGroceryButton;
    private JButton removeLastItemButton;
    private JButton clearGroceryList;
    private JButton displayGroceryList;

    private JPanel groceryListPanel;
    private JPanel buttonPanel;

    private int frameHeight = 1000;
    private int frameWidth = 600;

    //
    public GroceryListFrame() {
        setTitle("Grocery List");
        setLayout(new GridLayout(2,1));
        setSize(frameWidth, frameHeight);

        setUpJPanels();
        setUpJButtons();
        add(buttonPanel);
        setUpLabelsAndTextFields();
        pack();
    }

    private void setUpLabelsAndTextFields() {
        groceryName = new JLabel("Enter Grocery Name: ");
        groceryQuantity = new JLabel("Enter Grocery Quantity: ");

        groceryNameField = new JTextField();
        groceryQuantityField = new JTextField();

        JPanel labelsPanel = new JPanel(new GridLayout(2,1));
        labelsPanel.add(groceryName);
        labelsPanel.add(groceryQuantity);

        JPanel fieldsPanel = new JPanel(new GridLayout(2,1));
        fieldsPanel.add(groceryNameField);
        fieldsPanel.add(groceryQuantityField);

        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.add(labelsPanel);
        panel.add(fieldsPanel);

        add(panel);
    }

    private void setUpJButtons() {
        addGroceryButton = new JButton("Add Grocery Item");
        addGroceryButton.addActionListener(this);

        removeLastItemButton = new JButton("Remove Last Item");
        removeLastItemButton.addActionListener(this);

        clearGroceryList = new JButton("Remove All Items");
        clearGroceryList.addActionListener(this);

        displayGroceryList = new JButton("Display Grocery List");
        displayGroceryList.addActionListener(this);

        buttonPanel.add(addGroceryButton);
        buttonPanel.add(removeLastItemButton);
        buttonPanel.add(clearGroceryList);
        buttonPanel.add(displayGroceryList);
    }

    private void setUpJPanels() {
        groceryListPanel = new JPanel();
        groceryListPanel.add(new JLabel("Your Grocery List"));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RefrigeratorSystem rfs = RefrigeratorSystem.getInstance();
        if (e.getSource() == addGroceryButton) {
            String itemName = groceryNameField.getText();
            addGroceryItemToFrame(rfs, itemName);
        } else if (e.getSource() == removeLastItemButton) {
            removeLastItemInFrame(rfs);
            System.out.println(rfs.getRefrigeratorGroceryList().groceryListToString());
        } else if (e.getSource() == clearGroceryList) {
            rfs.getRefrigeratorGroceryList().clearGroceryList();
        } else if (e.getSource() == displayGroceryList) {
            displayGroceryList(rfs);
        }
    }

    private void addGroceryItemToFrame(RefrigeratorSystem rfs, String itemName) {
        try {
            int quantity = Integer.parseInt(groceryQuantityField.getText());
            rfs.getRefrigeratorGroceryList().addToGroceryList(itemName, quantity);
            displayGroceryList(rfs);
        } catch (EmptyStringException exception) {
            JOptionPane.showMessageDialog(null, "Please enter a grocery name");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "That is not an integer");
        }
    }

    private void removeLastItemInFrame(RefrigeratorSystem rfs) {
        try {
            rfs.getRefrigeratorGroceryList().removeLastGroceryListItem();
            displayGroceryList(rfs);
        } catch (ArrayIndexOutOfBoundsException exception) {
            JOptionPane.showMessageDialog(null, "Your grocery list is empty");
        }
    }

    private void displayGroceryList(RefrigeratorSystem rfs) {
        String display = rfs.getRefrigeratorGroceryList().groceryListToString();
        JOptionPane.showMessageDialog(null, display);
    }

}
