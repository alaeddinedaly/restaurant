package presentation.vue.waiter;

import java.awt.*;
import javax.swing.*;

public class SummaryPanel extends JPanel {
    public JPanel insideLayoutPanel;

    public JPanel summaryStatsPanel;
    public JPanel quickActionsPanel;


    public JLabel summaryStatsLabel;
    
    public JPanel activeOrdersPanel;
    public JLabel activeOrdersLabel;
    public JLabel activeOrdersValue;


    public JLabel quickActionsLabel;
    public JButton createNewOrderBtn;
    public JButton viewTablesBtn;

    public SummaryPanel() {
        this.setLayout(new BorderLayout());
        this.summaryStatsLabel = new JLabel("Summary Statistics : ");
        this.activeOrdersLabel = new JLabel("  - Active Orders : ");
        this.quickActionsLabel = new JLabel("Quick Actions : ");

        this.createNewOrderBtn = new JButton("Create New Order");
        this.viewTablesBtn = new JButton("View Tables");

        this.summaryStatsPanel = new JPanel();
        this.quickActionsPanel = new JPanel();
        this.insideLayoutPanel = new JPanel(new GridLayout(0, 1));

        this.activeOrdersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        this.insideLayoutPanel.setBorder(BorderFactory.createTitledBorder(""));
        this.summaryStatsPanel.setBorder(BorderFactory.createTitledBorder(""));

        this.quickActionsPanel.setBorder(BorderFactory.createTitledBorder(""));
        this.summaryStatsPanel.setLayout(new GridLayout(2, 1,5,5));
        this.quickActionsPanel.setLayout(new GridLayout(3, 1,5,5));

        this.summaryStatsPanel.add(this.summaryStatsLabel);
        
        this.activeOrdersPanel.add(this.activeOrdersLabel);

        this.activeOrdersValue = new JLabel("0");
        this.activeOrdersPanel.add(this.activeOrdersValue);

        this.summaryStatsPanel.add(this.activeOrdersPanel);
        

        this.quickActionsPanel.add(this.quickActionsLabel);
        this.quickActionsPanel.add(this.createNewOrderBtn);
        this.quickActionsPanel.add(this.viewTablesBtn);

        this.insideLayoutPanel.add(this.summaryStatsPanel);
        this.insideLayoutPanel.add(this.quickActionsPanel);

        this.add(this.insideLayoutPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createTitledBorder("Orders Panel"));


    }

    public JLabel getActiveOrdersValue(){
        return activeOrdersValue;
    }
    public JPanel getQuickActionsPanel(){
        return quickActionsPanel;
    }

    public JButton getCreateNewOrderBtn() {
        return createNewOrderBtn;
    }
    public JButton getViewTablesBtn() {
        return viewTablesBtn;
    }

    
    
}
