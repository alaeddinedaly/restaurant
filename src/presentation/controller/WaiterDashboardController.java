package presentation.controller;


import dao.MenuDao;
import dao.OrderDao;
import dao.TableDao;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.*;
import presentation.model.MenuItem;
import presentation.model.Order;
import presentation.vue.waiter.AvailableItemPanel;
import presentation.vue.waiter.CurrentOrderPanel;
import presentation.vue.waiter.NewOrderPanel;
import presentation.vue.waiter.WaiterDashboard;

public class WaiterDashboardController implements ActionListener {

    private WaiterDashboard view;
    private MenuDao menuDao;
    private OrderDao orderDao;
    

    public WaiterDashboardController (WaiterDashboard view) {
        view.setVisible(true);
        orderDao = new OrderDao();
        menuDao = new MenuDao();

        this.view = view;
        
        view.getCreateNewOrderBtn().addActionListener(this);
        view.getViewTablesBtn().addActionListener(this);
        

        view.getSummaryPanel().getActiveOrdersValue().setText(String.valueOf(orderDao.getOrdersNumber()));

        displayAllOrders();
        displayAllItems();
        searchItemsByName();
        displayAllTables();
      
        view.getActiveOrdersPanel().getAllFilter().addActionListener(this);
        view.getActiveOrdersPanel().getReadyFilter().addActionListener(this);
        view.getActiveOrdersPanel().getPendingFilter().addActionListener(this);
        view.getActiveOrdersPanel().getPreparingFilter().addActionListener(this);
        view.getMenuPanel().getAllFilter().addActionListener(this);
        view.getMenuPanel().getMainsFilter().addActionListener(this);
        view.getMenuPanel().getStartersFilter().addActionListener(this);
        view.getMenuPanel().getDessertsFilter().addActionListener(this);
        view.getAvailableBtn().addActionListener(this);
        view.getBusyBtn().addActionListener(this);

    }



    public void getAndDisplayTable(WaiterDashboard view, int i) {
        JPanel tablePanel = new JPanel(new GridLayout(4,2, 5, 5));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Table Details"));

        String[] tableInfo = TableDao.getTable(i + 1);

        tablePanel.add(new JLabel("Table ID: " ));
        tablePanel.add(new JLabel(tableInfo[0]));

        tablePanel.add(new JLabel("Capacity: "));
        tablePanel.add(new JLabel(tableInfo[1]));

        tablePanel.add(new JLabel("Status: "));
        tablePanel.add(new JLabel(tableInfo[2]));

        JButton makeAvailableBtn = new JButton("Make Available");
        JButton makeBusyBtn = new JButton("Make Busy");

        makeBusyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableDao.updateTableStatus(Integer.parseInt(tableInfo[0]), "occupied");
                displayAllTables();
            }
        });
        makeAvailableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableDao.updateTableStatus(Integer.parseInt(tableInfo[0]), "available");
                displayAllTables();
            }
        });
        
        tablePanel.add(makeAvailableBtn);
        tablePanel.add(makeBusyBtn);


        view.getRightPanel().add(tablePanel);   
        view.getRightPanel().revalidate();
        view.getRightPanel().repaint();
    }

    public void displayAllTables(){
        view.getRightPanel().removeAll();
        
        for (int i = 0; i < TableDao.getTablesNumber(); i++) {
            getAndDisplayTable(view, i);
        }
    }

    public void searchItemsByName(){
        JTextField searchField = view.getMenuPanel().getSearchField();
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase();
                filterItemsByName(searchText);
            }
        });
    }
    public void filterItemsByName(String searchText) {
        view.getMenuPanel().getRightPanel().removeAll();

        for (MenuItem item : menuDao.getAllMenuItems()) {
            if (item.getName().toLowerCase().contains(searchText)) {
                getAndDisplayItem(view, item);
            }
        }
    }

    public void getAndDisplayOrder(WaiterDashboard view,Order order) {
        CurrentOrderPanel currentOrderPanel = new CurrentOrderPanel(
                new JLabel("Order ID: " + order.getId()),
                new JLabel("Table " + order.getTableNumber() + " - " + order.getNumberOfGuests() + " guests"),
                new JLabel("Items: " + order.getItems()),
                new JLabel("Status: " + order.getStatus()),
                new JLabel("Total Price: DT " + order.getTotalPrice()),
                new JButton("Mark as Ready"),
                new JButton("Cancel Order")
            );

            currentOrderPanel.getMarkAsReadyBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    orderDao.updateOrderStatus(order.getId(), "Ready");
                    currentOrderPanel.getStatusLabel().setText("Status: Ready");
                    view.getActiveOrdersPanel().getRightPanel().revalidate();
                    view.getActiveOrdersPanel().getRightPanel().repaint();
                }
            });

            currentOrderPanel.getCancelOrderBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    orderDao.deleteOrder(String.valueOf(order.getId()));
                    
                    view.getActiveOrdersPanel().getRightPanel().remove(currentOrderPanel);
                    view.getActiveOrdersPanel().getRightPanel().revalidate();
                    view.getActiveOrdersPanel().getRightPanel().repaint();
                }
            });

            view.getActiveOrdersPanel().getRightPanel().add(currentOrderPanel);
            view.getActiveOrdersPanel().getRightPanel().revalidate();
            view.getActiveOrdersPanel().getRightPanel().repaint();
    }
    public void getAndDisplayItem(WaiterDashboard view,MenuItem item){
        AvailableItemPanel availableItemPanel = new AvailableItemPanel(
            new JLabel(item.getName()),
            new JLabel("DT " + item.getPrice()),
            new JLabel(item.getDescription())
        );
        view.getMenuPanel().getRightPanel().add(availableItemPanel);
        view.getMenuPanel().getRightPanel().revalidate();
        view.getMenuPanel().getRightPanel().repaint();
    }

    public void displayMainItems(){
        view.getMenuPanel().getRightPanel().removeAll();

        for (MenuItem item : menuDao.getAllMenuItems()) {
            if(item.getCategory().equals("Mains")){
                getAndDisplayItem(view, item);
            }
        }
    }
    public void displayStarterItems(){
        view.getMenuPanel().getRightPanel().removeAll();

        for (MenuItem item : menuDao.getAllMenuItems()) {
            if(item.getCategory().equals("Starters")){
                getAndDisplayItem(view, item);
            }
        }
    }
    public void displayDessertItems(){
        view.getMenuPanel().getRightPanel().removeAll();

        for (MenuItem item : menuDao.getAllMenuItems()) {
            if(item.getCategory().equals("Desserts")){
                getAndDisplayItem(view, item);
            }
        }
    }

    public void displayAllItems(){
        view.getMenuPanel().getRightPanel().removeAll();

        for (MenuItem item : menuDao.getAllMenuItems()) {
            getAndDisplayItem(view, item);
        }
    }

    public void displayReadyOrders(){
        view.getActiveOrdersPanel().getRightPanel().removeAll();

        for (Order order : orderDao.getAllOrders()){
            if(order.getStatus().equals("Ready")){
                getAndDisplayOrder(view, order);
            }
        }
    }
    public void displayPendingOrders(){
        view.getActiveOrdersPanel().getRightPanel().removeAll();

        for (Order order : orderDao.getAllOrders()){
            if(order.getStatus().equals("Pending")){
                getAndDisplayOrder(view, order);
            }
        }
    }
    public void displayPreparingOrders(){
        view.getActiveOrdersPanel().getRightPanel().removeAll();

        for (Order order : orderDao.getAllOrders()){
            if(order.getStatus().equals("Preparing")){
                getAndDisplayOrder(view, order);
            }
        }
    }

    public void displayAllOrders(){
        view.getActiveOrdersPanel().getRightPanel().removeAll();

        if(orderDao.getOrdersNumber() == 0){
            view.getActiveOrdersPanel().getRightPanel().add(new JLabel("No Orders Found"));
            return;
        }

        for (Order order : orderDao.getAllOrders()) {
            getAndDisplayOrder(view, order);
        }
        
    }

    private void createNewOrder() {
        NewOrderPanel newOrderPanel = new NewOrderPanel();
        newOrderPanel.getCreateOrderButton().addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = new Random().nextInt(1000);
                String tableNumber = newOrderPanel.getTableComboBox().getSelectedItem().toString();
                String numberOfGuests = newOrderPanel.getGuestsField().getText();
                String status = (String) newOrderPanel.getStatusComboBox().getSelectedItem();
                String items = newOrderPanel.getItemsList().getSelectedValue();
                double totalPrice = newOrderPanel.getPriceField().getText().isEmpty() ? 0.0 : Double.parseDouble(newOrderPanel.getPriceField().getText());
                String createdDate = LocalDate.now().toString();
                
                
                if ( tableNumber.isEmpty() || numberOfGuests.isEmpty()) {
                    JOptionPane.showMessageDialog(newOrderPanel, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    Order order = new Order(id, tableNumber, Integer.parseInt(numberOfGuests), status, items, totalPrice,createdDate);
                    orderDao = new OrderDao();
                    if (orderDao.addOrder(order)) {
                        JOptionPane.showMessageDialog(newOrderPanel, "Order created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        displayAllOrders();
                    } else {
                        JOptionPane.showMessageDialog(newOrderPanel, "Failed to create order", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JOptionPane.showMessageDialog(view, newOrderPanel, "Create New Order", JOptionPane.PLAIN_MESSAGE);
        
    }
    public void viewTablesPane(){
        view.getTabbedPane().setSelectedIndex(3);
        view.revalidate();
        view.repaint();
    }

    public void displayAvailableTables(){
        view.getRightPanel().removeAll();
        
        for (int i = 0; i < TableDao.getTablesNumber(); i++) {
            String[] tableInfo = TableDao.getTable(i + 1);
            if(tableInfo[2].equals("available")){
                getAndDisplayTable(view, i);
            }
        }
    }

    public void displayBusyTables(){
        view.getRightPanel().removeAll();
        
        for (int i = 0; i < TableDao.getTablesNumber(); i++) {
            String[] tableInfo = TableDao.getTable(i + 1);
            if(tableInfo[2].equals("occupied")){
                getAndDisplayTable(view, i);
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getCreateNewOrderBtn()) {
            createNewOrder();
        }
        else if (e.getSource() == view.getViewTablesBtn()){
            viewTablesPane();
        }
        else if (e.getSource() == view.getActiveOrdersPanel().getAllFilter()) {
            displayAllOrders();
        }
        else if (e.getSource() == view.getActiveOrdersPanel().getReadyFilter()) {
            displayReadyOrders();
        }
        else if (e.getSource() == view.getActiveOrdersPanel().getPendingFilter()) {
            displayPendingOrders();
        }
        else if (e.getSource() == view.getActiveOrdersPanel().getPreparingFilter()) {
            displayPreparingOrders();
        }
        else if (e.getSource() == view.getMenuPanel().getAllFilter()) {
            displayAllItems();
        }
        else if (e.getSource() == view.getMenuPanel().getMainsFilter()){
            displayMainItems();
        }
        else if (e.getSource() == view.getMenuPanel().getStartersFilter()){
            displayStarterItems();
        }
        else if (e.getSource() == view.getMenuPanel().getDessertsFilter()){
            displayDessertItems();
        }
        else if (e.getSource() == view.getAvailableBtn()){
            displayAvailableTables();
        }
        else if (e.getSource() == view.getBusyBtn()){
            displayBusyTables();
        }
    }
}
