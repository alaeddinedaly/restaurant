package presentation.vue.chef;

import java.awt.*;
import javax.swing.*;

public class KitchenOrdersPanel extends JPanel{
    public JLabel titleLabel;
    public JPanel leftPanel;

    public JPanel buttonsPanel;
    public JButton allButton,pendingButton,readyButton,preparingButton;

    public JPanel ordersPanel;

    public KitchenOrdersPanel() {

        this.setLayout(new BorderLayout());
        this.titleLabel = new JLabel("KITCHEN ORDERS");

        this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(24f));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(this.titleLabel, BorderLayout.NORTH);

        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(new GridLayout(0, 1));
        this.leftPanel.setBorder(BorderFactory.createTitledBorder("Filters"));

        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setLayout(new GridLayout(0, 1));

        this.allButton = new JButton("All");
        this.pendingButton = new JButton("Pending");
        this.readyButton = new JButton("Ready");
        this.preparingButton = new JButton("Preparing");

        this.buttonsPanel.add(this.allButton);
        this.buttonsPanel.add(this.pendingButton);
        this.buttonsPanel.add(this.preparingButton);
        this.buttonsPanel.add(this.readyButton);

        this.leftPanel.add(this.buttonsPanel);

        this.ordersPanel = new JPanel();
        this.ordersPanel.setLayout(new GridLayout(0, 1));

        JScrollPane scrollPane = new JScrollPane(this.ordersPanel);

        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


    }

    public JButton getAllButton() {
        return allButton;
    }

    public JButton getPendingButton() {
        return pendingButton;
    }

    public JButton getReadyButton() {
        return readyButton;
    }

    public JButton getPreparingButton() {
        return preparingButton;
    }

    public JPanel getOrdersPanel() {
        return ordersPanel;
    }
    
}
