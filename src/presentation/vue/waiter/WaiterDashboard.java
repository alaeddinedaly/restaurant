package presentation.vue.waiter;    

import java.awt.*;
import javax.swing.*;

public class WaiterDashboard extends JFrame {
    public JLabel titleLabel;
    public JTabbedPane tabbedPane;
    public SummaryPanel summaryPanel;
    public WaiterMenuPanel menuPanel;
    public ActiveOrdersPanel activeOrdersPanel;
    public WaiterTablePanel tablePanel;

    public WaiterDashboard() {
        this.setTitle("Waiter Dashboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500); 
        this.setLocationRelativeTo(null); 
        this.setResizable(true);



        this.titleLabel = new JLabel("Waiter Dashboard");
        this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(24f)); 
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        this.titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); 
        
        this.add(titleLabel, BorderLayout.NORTH);
        this.tabbedPane = new JTabbedPane(); 
        this.summaryPanel = new SummaryPanel(); 
        this.menuPanel = new WaiterMenuPanel(); 
        this.activeOrdersPanel = new ActiveOrdersPanel();
        this.tablePanel = new WaiterTablePanel();

        this.tabbedPane.addTab("Summary", this.summaryPanel); 
        this.tabbedPane.addTab("Active Orders", this.activeOrdersPanel);
        this.tabbedPane.addTab("Menu", this.menuPanel);  
        this.tabbedPane.addTab("Tables", this.tablePanel);

        this.add(tabbedPane); 
        this.setVisible(true);


    }

    public JButton getAvailableBtn() {
        return tablePanel.availableBtn;
    }
    public JButton getBusyBtn() {
        return tablePanel.busyBtn;
    }
    public JPanel getRightPanel() {
        return tablePanel.rightPanel;
    }
    

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
    public SummaryPanel getSummaryPanel() {
        return summaryPanel;
    }
    public WaiterMenuPanel getMenuPanel() {
        return menuPanel;
    }
    public ActiveOrdersPanel getActiveOrdersPanel() {
        return activeOrdersPanel;
    }

    public JButton getCreateNewOrderBtn() {
        return summaryPanel.createNewOrderBtn;
    }
    public JButton getViewTablesBtn() {
        return summaryPanel.viewTablesBtn;
    }

    
}
