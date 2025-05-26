package presentation.vue.waiter;

import java.awt.*;
import javax.swing.*;

public class ActiveOrdersPanel extends JPanel {
    public JPanel leftPanel;
    public JButton allFilter, pendingFilter, readyFilter, preparingFilter;

    public JPanel rightPanel;

    
    

    public ActiveOrdersPanel() {
        this.setLayout(new BorderLayout());
        this.leftPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        this.leftPanel.setSize(200, 200);
        this.allFilter = new JButton("All");
        this.pendingFilter = new JButton("Pending");
        this.readyFilter = new JButton("Ready");
        this.preparingFilter = new JButton("Preparing");

        this.leftPanel.add(this.allFilter);
        this.leftPanel.add(this.pendingFilter);
        this.leftPanel.add(this.preparingFilter);
        this.leftPanel.add(this.readyFilter);
        this.leftPanel.setBorder(BorderFactory.createTitledBorder("Filters"));

        this.rightPanel = new JPanel(new GridLayout(0,1));
        this.rightPanel.setBorder(BorderFactory.createTitledBorder("Active Orders"));

        JScrollPane scrollPane = new JScrollPane(this.rightPanel);
        
        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);
    }
    public JButton getAllFilter() {
        return allFilter;
    }

    public JButton getPendingFilter() {
        return pendingFilter;
    }

    public JButton getReadyFilter() {
        return readyFilter;
    }

    public JButton getPreparingFilter() {
        return preparingFilter;
    }
    
    public void addOrderPanel(CurrentOrderPanel orderPanel) {
        this.rightPanel.add(orderPanel);
        this.revalidate();
        this.repaint();
    }
    public void removeOrderPanel(CurrentOrderPanel orderPanel) {
        this.rightPanel.remove(orderPanel);
        this.revalidate();
        this.repaint();
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }
    public JPanel getLeftPanel() {
        return leftPanel;
    }
    
}
