package presentation.vue.chef;

import java.awt.GridLayout;
import javax.swing.*;

public class OrderDetails extends JPanel{

    private String id;
    private JLabel status;
    private JLabel items;
    private JButton prepareButton;
    private JButton readyButton;
    
    public OrderDetails(int id,String status,String items){
        this.setLayout(new GridLayout(4,1));
        
        this.id = String.valueOf(id);
        this.status = new JLabel("Status : " + status);
        this.items = new JLabel("Items : " + items);
        this.prepareButton = new JButton("Mark as Preparing");
        this.readyButton = new JButton("Mark as Ready");

        this.setBorder(BorderFactory.createTitledBorder("Order #" + id));
        
        
        this.add(this.status);
        this.add(this.items);
        this.add(this.prepareButton);
        this.add(this.readyButton);
    }

    public JButton getPrepareButton(){
        return this.prepareButton;
    }

    public JButton getReadyButton(){
        return this.readyButton;        
    }

    public JLabel getStatusLabel(){
        return this.status;
    }
}
