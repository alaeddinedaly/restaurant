package presentation.vue.waiter;

import java.awt.*;
import javax.swing.*;

public class WaiterTablePanel extends JPanel {
    
    public JPanel leftPanel;
    public JButton availableBtn, busyBtn;

    public JPanel rightPanel;
    
    WaiterTablePanel() {
        this.setLayout(new BorderLayout());
        
        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(new GridLayout(0, 1, 5, 5));
        
        this.availableBtn = new JButton("Available");
        this.busyBtn = new JButton("Busy");
        
        this.leftPanel.add(availableBtn);
        this.leftPanel.add(busyBtn);

        this.rightPanel = new JPanel(new GridLayout(0,1));
        JScrollPane scrollPane = new JScrollPane(rightPanel);
        
        
        this.add(leftPanel, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public JButton getAvailableBtn() {
        return availableBtn;
    }
    public JButton getBusyBtn() {
        return busyBtn;
    }
    public JPanel getRightPanel() {
        return rightPanel;
    }
    
}
