package presentation.vue.waiter;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CurrentOrderPanel extends JPanel{

    public JLabel orderIdLabel;
    public JLabel associatedTableAndGuestsLabel;
    public JLabel itemsLabel;
    public JLabel statusLabel;
    public JLabel totalPriceLabel;

    public JPanel buttonsPanel;
    public JButton markAsReadyBtn;
    public JButton deleteOrder;



    public CurrentOrderPanel(JLabel orderIdLabel, JLabel associatedTableAndGuestsLabel, JLabel itemsLabel, JLabel statusLabel, JLabel totalPrice, JButton markAsReadyBtn, JButton deleteOrder) {

        this.orderIdLabel = orderIdLabel;
        this.associatedTableAndGuestsLabel = associatedTableAndGuestsLabel;
        this.itemsLabel = itemsLabel;
        this.statusLabel = statusLabel;
        this.totalPriceLabel = totalPrice;

        this.buttonsPanel = new JPanel(new GridLayout(0,1));
        this.markAsReadyBtn = markAsReadyBtn;
        this.deleteOrder = deleteOrder;
        this.buttonsPanel.add(markAsReadyBtn);
        this.buttonsPanel.add(deleteOrder);

        this.setLayout(new GridLayout(3, 1, 5, 5));
        this.setBorder(BorderFactory.createTitledBorder("Order Details"));
        
        this.add(orderIdLabel);
        this.add(associatedTableAndGuestsLabel);
        this.add(itemsLabel);
        this.add(statusLabel);
        this.add(totalPriceLabel);
        this.add(buttonsPanel);

    }

    public JButton getCancelOrderBtn(){
        return deleteOrder;
    }

    public JButton getMarkAsReadyBtn() {
        return markAsReadyBtn;
    }

    public JLabel getStatusLabel(){
        return statusLabel;
    }
}
