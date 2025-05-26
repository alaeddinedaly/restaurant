package presentation.vue.waiter;

import dao.MenuDao;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class NewOrderPanel extends JPanel {

    private JTextField orderIdField;
    private JPanel tablePanel;
    private JLabel tableLabel;
    private JComboBox<String> tableComboBox;
    private JPanel guestsPanel;
    private JLabel guestsLabel;
    private JTextField guestsField;
    private JPanel statusPanel;
    private JLabel statusLabel ;
    private JComboBox<String> statusComboBox;
    private JPanel itemsPanel;
    private JLabel itemsLabel;
    private DefaultListModel<String> model;
    private JList<String> itemsList;
    private MenuDao menuDao;

    private JPanel spPanel ;
    private JScrollPane itemsScrollPane;
    private JPanel pricePanel;
    private JLabel priceLabel;
    private JTextField priceField;
    private JButton createOrderButton;
    private JPanel createOrderPanel;

    public  NewOrderPanel() {

         this.tablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         this.tableLabel = new JLabel("Table ID: ");
         this.tableComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4"});
         this.tablePanel.add(tableLabel);
         this.tablePanel.add(tableComboBox);
         this.guestsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         this.guestsLabel = new JLabel("Number of Guests: ");
         this.guestsField = new JTextField(10);
         this.guestsPanel.add(guestsLabel);
         this.guestsPanel.add(guestsField);
         this.statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         this.statusLabel = new JLabel("Status: ");
         this.statusComboBox = new JComboBox<>(new String[]{"Pending", "Preparing", "Ready"});
         this.statusComboBox.setSelectedItem("Pending");
         this.statusPanel.add(statusLabel);
         this.statusPanel.add(statusComboBox);
         this.itemsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         this.itemsLabel = new JLabel("Items: ");
         this.menuDao = new MenuDao();
         this.model = menuDao.loadItemNames();
         this.itemsList = new JList<>(model);
         this.itemsPanel.add(itemsLabel);
         this.itemsPanel.add(new JScrollPane(itemsList));
         this.spPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         this.itemsScrollPane = new JScrollPane(itemsList);
         this.spPanel.add(itemsScrollPane);
         this.pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         this.priceLabel = new JLabel("Price: ");
         this.priceField = new JTextField(10);
         this.pricePanel.add(priceLabel);
         this.pricePanel.add(priceField);
        


         createOrderButton = new JButton("Create Order");

         createOrderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        this.createOrderPanel.add(tablePanel);
        this.createOrderPanel.add(guestsPanel);
        this.createOrderPanel.add(statusPanel);
        this.createOrderPanel.add(itemsPanel);
        this.createOrderPanel.add(spPanel);
        this.createOrderPanel.add(pricePanel);
        this.createOrderPanel.add(createOrderButton);

        this.createOrderPanel.setBorder(BorderFactory.createTitledBorder("Create New Order"));

        this.add(createOrderPanel);

    } 
    public JButton getCreateOrderButton() {
        return createOrderButton;
    }
    public JTextField getOrderIdField() {
        return orderIdField;
    }
    public JComboBox<String> getTableComboBox() {
        return tableComboBox;
    }
    public JTextField getGuestsField() {
        return guestsField;
    }
    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }
    public JList<String> getItemsList() {
        return itemsList;
    }
    public JTextField getPriceField() {
        return priceField;
    }
    public DefaultListModel<String> getModel() {
        return model;
    }
    
}
