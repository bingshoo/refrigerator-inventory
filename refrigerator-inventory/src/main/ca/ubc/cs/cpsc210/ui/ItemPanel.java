package ca.ubc.cs.cpsc210.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPanel extends JPanel {
    private JPanel itemPanel;
    private List<JButton> buttons;

    // REQUIRES: non-null String
    // MODIFIES: this
    // EFFECTS: initializes item panel and adds components to it
    ItemPanel(String title) {
        itemPanel = new JPanel();
        buttons = new ArrayList<>();
        JLabel panelTitle = new JLabel(title);

        itemPanel.add(panelTitle);
        itemPanel.setBorder(new LineBorder(Color.black));
    }

    public JPanel getItemPanel() {
        return itemPanel;
    }

    // REQUIRES: JButton
    // MODIFIES: this
    // EFFECTS: adds a JButton to buttons list
    public void addToListOfButtons(JButton b) {
        buttons.add(b);
    }

    // REQUIRES: JButton
    // MODIFIES: this
    // EFFECTS: removes a JButton to buttons list
    public void removeButtonFromList(JButton b) {
        if (buttons.contains(b)) {
            buttons.remove(b);
        }
    }
}
