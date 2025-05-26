package presentation.vue.admin;

import java.awt.*;
import javax.swing.*;

public class ReportsPanel extends JPanel {

    private JPanel infoPanel;
    private JButton refreshButton,exportButton,printButton;
    public ReportsPanel() {
        this.setLayout(new BorderLayout(10, 10)); 

        JLabel titleLabel = new JLabel("REPORTS DASHBOARD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        this.refreshButton = new JButton("Refresh");
        this.exportButton = new JButton("Export to CSV");
        this.printButton = new JButton("Print");



        this.infoPanel = new JPanel(new BorderLayout());
        

        buttonPanel.add(refreshButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(printButton);

        this.add(this.infoPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }
    
    public JButton getRefreshButton() {
        return refreshButton;
    }

}