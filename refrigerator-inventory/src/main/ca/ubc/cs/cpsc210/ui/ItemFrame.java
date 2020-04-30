package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.model.Item;
import ca.ubc.cs.cpsc210.model.RefrigeratorSystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ca.ubc.cs.cpsc210.ui.RefrigeratorSystemUI.freezerItemPanel;
import static ca.ubc.cs.cpsc210.ui.RefrigeratorSystemUI.fridgeItemPanel;

public class ItemFrame extends JPanel implements ActionListener {
    private JButton itemButton;
    private JFrame frame;
    private JButton removeItemButton;
    private Item item;

    private int frameHeight = 400;
    private int frameWidth = 300;

    // REQUIRES: non-null Item and JButton
    // MODIFIES: this
    // EFFECTS: initializes ItemFrame and adds components to it
    public ItemFrame(Item item, JButton button) {
        this.item = item;
        itemButton = button;
        frame = new JFrame();
        frame.setLayout(new GridLayout(2,1));
        frame.setSize(frameWidth, frameHeight);

        setUpRemoveItemButton();

        frame.add(setUpItemInfoPanel());
        frame.add(removeItemButton);
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: Renders the item information and places it onto a JPanel and returns the JPanel
    private JPanel setUpItemInfoPanel() {
        String s = "<html>" + item.itemSummaryToString().replaceAll("\n", "<br/>") + "</html>";
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.black));
        JLabel infoLabel = new JLabel(s);
        infoLabel.setFont(new Font("Verdana",Font.BOLD,15));
        infoLabel.setSize(500,600);
        panel.add(infoLabel);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: initializes removeItemButton and adds an action listener to it
    private void setUpRemoveItemButton() {
        removeItemButton = new JButton("Remove Item");
        removeItemButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        RefrigeratorSystem rfs = RefrigeratorSystem.getInstance();
        if (actionEvent.getSource() == removeItemButton && !item.isGoesInFreezer()) {
            removeFridgeItemFromFrame(rfs);
        } else if (actionEvent.getSource() == removeItemButton && item.isGoesInFreezer()) {
            removeFreezerItemFromFrame(rfs);
        }
    }

    // REQUIRES: a RefrigeratorSystem
    // MODIFIES: fridgeItemPanel, RefrigeratorSystem, Fridge
    // EFFECTS: removes item that the frame represents from Refrigerator system fridge and removes the button
    //          corresponding to the item from the fridge panel then closes the frame
    private void removeFridgeItemFromFrame(RefrigeratorSystem rfs) {
        int i = rfs.getFridgeUnit().getFridgeItems().indexOf(item);
        rfs.getFridgeUnit().removeItem(i);
        fridgeItemPanel.getItemPanel().remove(itemButton);
        fridgeItemPanel.removeButtonFromList(itemButton);
        fridgeItemPanel.getItemPanel().updateUI();
        frame.dispose();
    }

    // REQUIRES: a RefrigeratorSystem
    // MODIFIES: freezerItemPanel, RefrigeratorSystem, Freezer
    // EFFECTS: removes item that the frame represents from Refrigerator system freezer and removes the button
    //          corresponding to the item from the freezer panel then closes the frame
    private void removeFreezerItemFromFrame(RefrigeratorSystem rfs) {
        int i = rfs.getFreezerUnit().getFreezerItems().indexOf(item);
        rfs.getFreezerUnit().removeFreezerItem(i);
        freezerItemPanel.getItemPanel().remove(itemButton);
        freezerItemPanel.removeButtonFromList(itemButton);
        freezerItemPanel.getItemPanel().updateUI();
        frame.dispose();
    }
}
