package presentation.controller;

import dao.OrderDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presentation.model.Order;
import presentation.vue.chef.ChefDashboard;
import presentation.vue.chef.OrderDetails;

public class ChefDashboardController implements ActionListener {

    private ChefDashboard view;
    private OrderDao orderDao;

    public ChefDashboardController(ChefDashboard view) {
        view.setVisible(true);

        this.view = view;
        this.orderDao = new OrderDao();

        view.getAllButton().addActionListener(this);
        view.getReadyButton().addActionListener(this);
        view.getPendingButton().addActionListener(this);
        view.getPreparingButton().addActionListener(this);
        view.getRefreshButton().addActionListener(this);


        displayOrders();
        loadSummary();
    }

    public void loadSummary(){
        view.setReadyOrdersOnSummaryPanel(orderDao.getReadyOrdersNumber());
        view.setPendingOrdersOnSummaryPanel(orderDao.getPendingOrdersNumber());
    }
    public void getAndDisplayOrder(ChefDashboard view, Order order){
        OrderDetails addOrder = new OrderDetails(order.getId(),order.getStatus(),order.getItems());
                addOrder.getPrepareButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        orderDao.updateOrderStatus(order.getId(), "Preparing");
                        addOrder.getStatusLabel().setText("Status : Preparing");
                }
            });
                addOrder.getReadyButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        orderDao.updateOrderStatus(order.getId(), "Ready");
                        addOrder.getStatusLabel().setText("Status : Ready");
                    }
                });
            view.getOrdersPanel().add(addOrder);
            view.getOrdersPanel().revalidate();
            view.getOrdersPanel().repaint();
    }
    public void displayOrders(){
        view.getOrdersPanel().removeAll();

        for(Order order : orderDao.getChefOrders()){
            getAndDisplayOrder(view, order);
        }
    }

    public void displayReadyOrders(){
        view.getOrdersPanel().removeAll();

        for(Order order : orderDao.getChefOrders()){
            if(order.getStatus().equals("Ready")){
                getAndDisplayOrder(view,order);
            }
        }
    }

    public void displayPreparingOrders(){
        view.getOrdersPanel().removeAll();

        for(Order order : orderDao.getChefOrders()){
            if(order.getStatus().equals("Preparing")){
                getAndDisplayOrder(view,order);
            }
        }
    }
    public void displayPendingOrders(){
        view.getOrdersPanel().removeAll();

        for(Order order : orderDao.getChefOrders()){
            if(order.getStatus().equals("Pending")){
                getAndDisplayOrder(view,order);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getAllButton()){
            displayOrders();
        }
        else if(e.getSource() == view.getReadyButton()){
            displayReadyOrders();
        }
        else if(e.getSource() == view.getPendingButton()){
            displayPendingOrders();
        }
        else if(e.getSource() == view.getPreparingButton()){
            displayPreparingOrders();
        }
        else if(e.getSource() == view.getRefreshButton()){
            view.getOrdersPanel().revalidate();
            view.getOrdersPanel().repaint();
        }
    }
}
