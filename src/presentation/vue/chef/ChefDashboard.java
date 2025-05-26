package presentation.vue.chef;

import java.awt.*;
import javax.swing.*;

public class ChefDashboard extends JFrame {

    public JLabel titleLabel;
    public JTabbedPane tabbedPane;

    public KitchenOrdersPanel kitchenOrdersPanel;
    public SummaryPanel summaryPanel;

    public ChefDashboard() {
        this.setTitle("Chef Dashboard");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.titleLabel = new JLabel("Chef Dashboard");
        this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(24f));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.add(titleLabel, BorderLayout.NORTH);
        this.tabbedPane = new JTabbedPane();
        this.summaryPanel = new SummaryPanel();
        this.tabbedPane.addTab("Summary", summaryPanel);
        this.kitchenOrdersPanel = new KitchenOrdersPanel();
        this.tabbedPane.addTab("Kitchen Orders", kitchenOrdersPanel);
        this.add(tabbedPane);
        this.setVisible(true);
  
    }

    public JButton getRefreshButton(){
        return summaryPanel.getRefreshOrdersBtn();
    }

    public void setReadyOrdersOnSummaryPanel(int readyCount) {
        summaryPanel.setReadyCount(readyCount);
    }
    public void setPendingOrdersOnSummaryPanel(int PendingCount) {
        summaryPanel.setPendingCount(PendingCount);
    }

    public JPanel getKitchenOrdersPanel() {
        return kitchenOrdersPanel;
    }

    public JPanel getOrdersPanel(){
        return kitchenOrdersPanel.getOrdersPanel();
    }
    
    public JButton getAllButton() {
        return kitchenOrdersPanel.getAllButton();
    }

    public JButton getPendingButton() {
        return kitchenOrdersPanel.getPendingButton();
    }

    public JButton getReadyButton() {
        return kitchenOrdersPanel.getReadyButton();
    }

    public JButton getPreparingButton() {
        return kitchenOrdersPanel.getPreparingButton();
    }

}
