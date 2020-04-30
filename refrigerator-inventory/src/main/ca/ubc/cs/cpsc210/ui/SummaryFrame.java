package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.model.Item;
import ca.ubc.cs.cpsc210.model.RefrigeratorSystem;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SummaryFrame extends JFrame implements ActionListener {
    private int frameHeight = 1000;
    private int frameWidth = 600;

    private JButton fridgeSummary;
    private JButton freezerSummary;
    private JButton generalSummary;

    private JPanel summaryButtonPanel;
    private JPanel summaryPanel;

    // EFFECTS: initializes the summary panel and adds the components to it
    public SummaryFrame() {
        setTitle("Summary");
        setLayout(new GridLayout(2,1));
        setSize(frameWidth, frameHeight);

        setUpJPanels();
        setUpJButtons();
        pack();
    }

    // MODIFIES: this
    // EFFECTS: initializes JPanels and JLabel and adds them to the JFrame
    private void setUpJPanels() {
        summaryButtonPanel = new JPanel();
        summaryPanel = new JPanel();

        summaryPanel.add(new JLabel("Item Summary"));

        this.add(summaryPanel);
        this.add(summaryButtonPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes JButtons and adds action listeners to them and adds them to summaryButtonPanel
    private void setUpJButtons() {
        fridgeSummary = new JButton("Display Fridge Summary");
        fridgeSummary.addActionListener(this);

        freezerSummary = new JButton("Display Freezer Summary");
        freezerSummary.addActionListener(this);

        generalSummary = new JButton("Display Entire Refrigerator Summary");
        generalSummary.addActionListener(this);

        summaryButtonPanel.add(fridgeSummary);
        summaryButtonPanel.add(freezerSummary);
        summaryButtonPanel.add(generalSummary);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RefrigeratorSystem rfs = RefrigeratorSystem.getInstance();
        if (e.getSource() == fridgeSummary) {
            displayFridgeSummary(rfs);
        } else if (e.getSource() == freezerSummary) {
            displayFreezerSummary(rfs);
        } else if (e.getSource() == generalSummary) {
            displayFridgeAndFreezer(rfs);
        }
    }

    // EFFECTS: creates pop up window that gives information of all the items in the fridge
    private void displayFridgeSummary(RefrigeratorSystem rfs) {
        String fridgeSummaryString = rfs.getFridgeUnit().contentsToString();
        JOptionPane.showMessageDialog(null, "Fridge Contents: \n" + fridgeSummaryString);
    }

    // EFFECTS: creates pop up window that gives information of all the items in the freezer
    private void displayFreezerSummary(RefrigeratorSystem rfs) {
        String freezerSummaryString = rfs.getFreezerUnit().contentsToString();
        JOptionPane.showMessageDialog(null, "Freezer Contents: \n" + freezerSummaryString);
    }

    // EFFECTS: creates pop up window that gives information of all the items in the fridge and freezer
    private void displayFridgeAndFreezer(RefrigeratorSystem rfs) {
        String fridgeSummaryString = rfs.getFridgeUnit().contentsToString();
        String freezerSummaryString = rfs.getFreezerUnit().contentsToString();
        JOptionPane.showMessageDialog(null, "Fridge and Freezer Contents: \n"
                + fridgeSummaryString + freezerSummaryString);
    }
}
