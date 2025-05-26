package presentation.vue.chef;

import java.awt.*;
import javax.swing.*;

public class SummaryPanel extends JPanel {

    public JLabel summaryLabel;
    public JLabel pendingLabel;
    public int pendingCount;
    public JLabel readyLabel;
    public int readyCount;

    public JPanel insidePanel;

    public JLabel actionsLabel;
    public JButton refreshOrdersBtn;
    public JPanel bottomPanel;

    public SummaryPanel() {

        this.setLayout(new BorderLayout());

        this.summaryLabel = new JLabel("Summary");
        this.summaryLabel.setFont(this.summaryLabel.getFont().deriveFont(24f));
        this.summaryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.pendingLabel = new JLabel("Pending");
        this.pendingLabel.setFont(this.pendingLabel.getFont().deriveFont(18f));
        this.readyLabel = new JLabel("Ready");
        this.readyLabel.setFont(this.readyLabel.getFont().deriveFont(18f));
        this.pendingCount = 8;
        this.readyCount = 3;
        this.pendingLabel.setText("  - Pending: " + this.pendingCount);
        this.readyLabel.setText("  - Ready: " + this.readyCount);

        this.insidePanel = new JPanel();
        this.insidePanel.setLayout(new FlowLayout());

        this.insidePanel.add(this.pendingLabel);
        this.insidePanel.add(this.readyLabel);

        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new FlowLayout());
        this.actionsLabel = new JLabel("Actions");
        this.actionsLabel.setFont(this.actionsLabel.getFont().deriveFont(18f));
        this.refreshOrdersBtn = new JButton("Refresh Orders");

        this.bottomPanel.add(this.actionsLabel);
        this.bottomPanel.add(this.refreshOrdersBtn);

        this.add(this.summaryLabel, BorderLayout.NORTH);
        this.add(this.insidePanel, BorderLayout.CENTER);
        this.add(this.bottomPanel, BorderLayout.SOUTH);
        
    }

    public int getPendingCount() {
        return this.pendingCount;
    }

    public int getReadyCount() {
        return this.readyCount;
    }

    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
        this.pendingLabel.setText("  - Pending: " + this.pendingCount);
    }

    public void setReadyCount(int readyCount) {
        this.readyCount = readyCount;
        this.readyLabel.setText("  - Ready: " + this.readyCount);
    }

    public JButton getRefreshOrdersBtn() {
        return this.refreshOrdersBtn;
    }
}
