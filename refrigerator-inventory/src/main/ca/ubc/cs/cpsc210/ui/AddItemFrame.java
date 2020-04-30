package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.model.Freezer;
import ca.ubc.cs.cpsc210.model.FreezerItem;
import ca.ubc.cs.cpsc210.model.Item;
import ca.ubc.cs.cpsc210.model.RefrigeratorSystem;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import static ca.ubc.cs.cpsc210.ui.RefrigeratorSystemUI.freezerItemPanel;
import static ca.ubc.cs.cpsc210.ui.RefrigeratorSystemUI.fridgeItemPanel;

public class AddItemFrame extends JFrame implements ActionListener {
    private JLabel enterItemName;
    private JLabel enterExpirationDate;
    private JLabel enterDateBought;
    private JLabel enterWeight;
    private JLabel enterCost;
    private JLabel enterGoesInFreezer;

    private JTextField itemNameTextField;
    private JTextField expirationDateYearTextField;
    private JTextField expirationDateMonthTextField;
    private JTextField expirationDateDayTextField;
    private JTextField dateBoughtYearTextField;
    private JTextField dateBoughtMonthTextField;
    private JTextField dateBoughtDayTextField;
    private JTextField weightTextField;
    private JTextField costTextField;
    private JCheckBox goesInFreezerBox;

    private JButton addItem;

    private JPanel labelPanel;
    private JPanel textFieldPanel;

    private int frameHeight = 400;
    private int frameWidth = 500;

    // EFFECTS: Creates a JFrame that allows the user to enter a item name, expiration date year, month and date,
    //          date bought year, month and date, item weight, item cost and whether it goes in the freezer or not,
    //          and creates an item in the fridge or freezer then creates a button that corresponds to that item onto
    //          the RefrigeratorSystemUI
    public AddItemFrame() {
        setTitle("Add Item");
        setLayout(new GridLayout(1,2));
        setSize(frameWidth, frameHeight);
        setUpJLabels();
        setUpJTextFields();

        addItem = new JButton("Add Item");
        addItem.addActionListener(this);

        addComponentsToJFrame();
    }

    // MODIFIES: enterItemName, enterExpirationDate, enterDateBought, enterWeight, enterCost, enterGoesInFreezer,
    //           labelPanel
    // EFFECTS: instantiates the JLabels and JPanel that will hold these labels
    private void setUpJLabels() {
        enterItemName = new JLabel("Enter Item Name: ");
        enterExpirationDate = new JLabel("Enter Expiration Date(YYYY-MM-DD): ");
        enterDateBought = new JLabel("Enter Date Bought(YYYY-MM-DD): ");
        enterWeight = new JLabel("Enter Weight(lbs): ");
        enterCost = new JLabel("Enter Cost($): ");
        enterGoesInFreezer = new JLabel("Goes in freezer?: ");

        labelPanel = new JPanel(new GridLayout(6,1));
    }

    // MODIFIES: itemNameTextField, expirationDateYearTextField, expirationDateMonthTextField,
    //           expirationDateDayTextField, dateBoughtYearTextField, dateBoughtMonthTextField, dateBoughtDayTextField,
    //           weightTextField, costTextField, goesInFreezerBox, textFieldPanel
    // EFFECTS: instantiates the JTextFields, JCheckBox and JPanel that will hold them
    private void setUpJTextFields() {
        itemNameTextField = new JTextField();
        expirationDateYearTextField = new JTextField("YYYY");
        expirationDateMonthTextField = new JTextField("MM");
        expirationDateDayTextField = new JTextField("DD");
        dateBoughtYearTextField = new JTextField("YYYY");
        dateBoughtMonthTextField = new JTextField("MM");
        dateBoughtDayTextField = new JTextField("DD");
        weightTextField = new JTextField("0.0");
        costTextField = new JTextField("0.00");
        goesInFreezerBox = new JCheckBox();

        textFieldPanel = new JPanel(new GridLayout(6,1));
    }

    // MODIFIES: labelPanel, textFieldPanel, this
    // EFFECTS: adds components to the labelPanel and the textFieldPanel, then adds labelPanel and textFieldPanel to
    //          this
    private void addComponentsToJFrame() {
        labelPanel.add(enterItemName);
        textFieldPanel.add(itemNameTextField);

        labelPanel.add(enterExpirationDate);
        textFieldPanel.add(setUpExpDatePanel());

        labelPanel.add(enterDateBought);
        textFieldPanel.add(setUpBoughtDatePanel());

        labelPanel.add(enterWeight);
        textFieldPanel.add(weightTextField);

        labelPanel.add(enterCost);
        textFieldPanel.add(costTextField);

        labelPanel.add(enterGoesInFreezer);
        textFieldPanel.add(goesInFreezerBox);

        this.add(labelPanel);
        this.add(textFieldPanel);
        this.add(addItem);
    }

    // EFFECTS: returns a JPanel with expirationDateYearTextField, expirationDateMonthTextField and
    //          expirationDateDayTextField
    private JPanel setUpExpDatePanel() {
        JPanel expDatePanel = new JPanel(new GridLayout(1,3));
        expDatePanel.add(expirationDateYearTextField);
        expDatePanel.add(expirationDateMonthTextField);
        expDatePanel.add(expirationDateDayTextField);
        return expDatePanel;
    }

    // EFFECTS: returns a JPanel with dateBoughtYearTextField, dateBoughtMonthTextField, and
    //          dateBoughtDayTextField
    private JPanel setUpBoughtDatePanel() {
        JPanel boughtDatePanel = new JPanel(new GridLayout(1,3));
        boughtDatePanel.add(dateBoughtYearTextField);
        boughtDatePanel.add(dateBoughtMonthTextField);
        boughtDatePanel.add(dateBoughtDayTextField);
        return boughtDatePanel;
    }

    // MODIFIES: RefrigeratorSystem, Fridge, Freezer
    // EFFECTS: if actionEvent.getSource() == addItem and goesInFreezerBox is not selected then a item will be
    //          generated and added to RefrigeratorSystem else if actionEvent.getSource() == addItem and
    //          goesInFreezerBox is selected then a freezer item will be generated and added to RefrigeratorSystem
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        RefrigeratorSystem rfs = RefrigeratorSystem.getInstance();
        if (actionEvent.getSource() == addItem && !goesInFreezerBox.isSelected()) {
            addItemEvent(rfs);
        } else if (actionEvent.getSource() == addItem && goesInFreezerBox.isSelected()) {
            addFreezerItemEvent(rfs);
        }
    }

    // MODIFIES: RefrigeratorSystem, Freezer
    // EFFECTS: will create a freezer item from the data entered in the text fields, adds the created freezer item to
    //          the freezer and creates a button that represents the freezer item on the RefrigeratorSystemUI if a text
    //          field is incorrect then a error message will pop up
    private void addFreezerItemEvent(RefrigeratorSystem rfs) {
        FreezerItem itemToAdd;
        try {
            itemToAdd = createFreezerItem();
            rfs.getFreezerUnit().addFreezerItem(itemToAdd);
            JOptionPane.showMessageDialog(null, rfs.getFreezerUnit().contentsToString());

            generateItemButtonOnPanel(itemToAdd, freezerItemPanel);
            dispose();
        } catch (EmptyStringException e) {
            JOptionPane.showMessageDialog(null, "Enter a non empty item name");
        } catch (NegativeInputException e) {
            JOptionPane.showMessageDialog(null, "Enter a non negative number");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!");
        }
    }

    // MODIFIES: RefrigeratorSystem, Fridge
    // EFFECTS: will create a item from the data entered in the text fields, adds the created item to
    //          the fridge and creates a button that represents the item on the RefrigeratorSystemUI if a text
    //          field is incorrect then a error message will pop up
    private void addItemEvent(RefrigeratorSystem rfs) {
        Item itemToAdd;
        try {
            itemToAdd = createItem();

            rfs.getFridgeUnit().addItem(itemToAdd);
            JOptionPane.showMessageDialog(null, rfs.getFridgeUnit().contentsToString());

            generateItemButtonOnPanel(itemToAdd, fridgeItemPanel);
            dispose();
        } catch (EmptyStringException e) {
            JOptionPane.showMessageDialog(null, "Enter a non empty item name");
        } catch (NegativeInputException e) {
            JOptionPane.showMessageDialog(null, "Enter a non negative number");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong!");
        }
    }

    // REQUIRES: a non-null Item and ItemPanel
    // MODIFIES: panel
    // EFFECTS: creates a button with information from the item and adds that button to the item panel
    protected static void generateItemButtonOnPanel(Item itemToAdd, ItemPanel panel) {
        JButton button = getJButton(itemToAdd);
        panel.getItemPanel().add(button);
        panel.addToListOfButtons(button);
        panel.getItemPanel().updateUI();
    }

    // REQUIRES: non-null Item
    // EFFECTS: creates a button which creates an ItemFrame when pressed and returns that button
    private static JButton getJButton(Item itemToAdd) {
        JButton button = new JButton(itemToAdd.getItemName());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ItemFrame itf = new ItemFrame(itemToAdd, button);
                itf.setVisible(true);
            }
        });
        return button;
    }

    // EFFECTS: creates an item from the data entered into the text fields and returns that item
    private Item createItem() throws EmptyStringException, NegativeInputException {
        String itemName = itemNameTextField.getText();
        int expDateYear = textFieldToInt(expirationDateYearTextField);
        int expDateMonth = textFieldToInt(expirationDateMonthTextField);
        int expDateDay = textFieldToInt(expirationDateDayTextField);
        int buyDateYear = textFieldToInt(dateBoughtYearTextField);
        int buyDateMonth = textFieldToInt(dateBoughtMonthTextField);
        int buyDateDay = textFieldToInt(dateBoughtDayTextField);
        double weight = textFieldToDouble(weightTextField);
        double cost = textFieldToDouble(costTextField);
        boolean goesInFreezer = goesInFreezerBox.isSelected();

        return new Item(itemName, new GregorianCalendar(expDateYear, expDateMonth, expDateDay),
                new GregorianCalendar(buyDateYear, buyDateMonth, buyDateDay), weight, cost, goesInFreezer);
    }

    // EFFECTS: creates a FreezerItem from the data entered into the text fields and returns that item
    private FreezerItem createFreezerItem() throws EmptyStringException, NegativeInputException {
        String itemName = itemNameTextField.getText();
        int expDateYear = textFieldToInt(expirationDateYearTextField);
        int expDateMonth = textFieldToInt(expirationDateMonthTextField);
        int expDateDay = textFieldToInt(expirationDateDayTextField);
        int buyDateYear = textFieldToInt(dateBoughtYearTextField);
        int buyDateMonth = textFieldToInt(dateBoughtMonthTextField);
        int buyDateDay = textFieldToInt(dateBoughtDayTextField);
        double weight = textFieldToDouble(weightTextField);
        double cost = textFieldToDouble(costTextField);

        return new FreezerItem(itemName, new GregorianCalendar(expDateYear, expDateMonth, expDateDay),
                new GregorianCalendar(buyDateYear, buyDateMonth, buyDateDay), weight, cost);
    }

    // REQUIRES: JTextField with a valid input
    // EFFECTS: converts text from a text field and converts it into an int and returns that int
    private int textFieldToInt(JTextField textField) {
        return Integer.parseInt(textField.getText());
    }

    // REQUIRES: JTextField with a valid input
    // EFFECTS: converts text from a text field and converts it into an double and returns that double
    private double textFieldToDouble(JTextField textField) {
        return Double.parseDouble(textField.getText());
    }
}
