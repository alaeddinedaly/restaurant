package presentation.vue.waiter;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AvailableItemPanel extends JPanel{
    public JLabel itemNameLabel;
    public JLabel itemPriceLabel;
    public JLabel itemDescriptionLabel;


    public AvailableItemPanel(JLabel itemNameLabel,JLabel itemPriceLabel, JLabel itemDescriptionLabel){

        this.setLayout(new GridLayout(3,1,5,5));
        this.itemNameLabel = itemNameLabel;
        this.itemPriceLabel = itemPriceLabel;
        this.itemDescriptionLabel = itemDescriptionLabel;


        this.setBorder(BorderFactory.createTitledBorder("Item Details"));

        this.add(this.itemNameLabel);
        this.add(this.itemPriceLabel);
        this.add(this.itemDescriptionLabel);

    }


}
