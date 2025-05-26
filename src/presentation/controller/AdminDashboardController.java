
package presentation.controller;

import dao.MenuDao;
import dao.OrderDao;
import dao.TableDao;
import dao.UserDao;
import presentation.model.MenuItem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import presentation.model.User;
import presentation.vue.admin.AdminDashboard;
import presentation.vue.admin.TableModel;

public class AdminDashboardController implements ActionListener{
    private AdminDashboard view;
    private UserDao userDao;
    private MenuDao menuDao;
    private OrderDao orderDao;

    public AdminDashboardController(AdminDashboard view, UserDao userDao) {
        view.setVisible(true);
        
        this.view = view;
        this.userDao = userDao;

        this.orderDao = new OrderDao();
        this.menuDao = new MenuDao();
        this.view.getAddUserBtn().addActionListener(this);
        this.view.getAllFilterBtn().addActionListener(this);
        this.view.getWaiterFilterBtn().addActionListener(this);
        this.view.getChefFilterBtn().addActionListener(this);

        this.view.getMainsButton().addActionListener(this);
        this.view.getStartersButton().addActionListener(this);
        this.view.getDessertsButton().addActionListener(this);

        this.view.menuSaveBtn().addActionListener(this);
        this.view.menuAddBtn().addActionListener(this);
        this.view.menuDeleteBtn().addActionListener(this);
        this.view.getRefreshButton().addActionListener(this);

        this.view.getCreateTableButton().addActionListener(this);

        filterByRole();
        searchByName(); 
        loadUsers();
        loadReport();

    }

    public void loadReport(){

        Object[][] reportData = orderDao.getReportData();
        TableModel tableModel = new TableModel(reportData);

        view.getReportsInfoPanel().add(tableModel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.add(new JLabel("Revenue : " + String.valueOf(orderDao.getRevenue())));
        infoPanel.add(new JLabel("Total Orders : " + String.valueOf(orderDao.getOrdersNumber())));
        infoPanel.add(new JLabel("Total Users : " + String.valueOf(userDao.getUsersNumber())));

        view.getReportsInfoPanel().add(infoPanel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getAddUserBtn()) {
            showAddUserDialog();
        }
        else if (e.getSource() == view.getAllFilterBtn()) {
            filterUsers("all");
        }
        else if (e.getSource() == view.getWaiterFilterBtn()) {
            filterUsers("waiter");
        }
        else if (e.getSource() == view.getChefFilterBtn()) {
            filterUsers("chef");
        }
        else if(e.getSource() == view.menuSaveBtn()){
            saveItemData();
        }
        else if(e.getSource() == view.menuAddBtn()){
            addNewItem();
        }
        else if(e.getSource() == view.menuDeleteBtn()){
            deleteItem();
        }
        else if(e.getSource() == view.getRefreshButton()){
            view.getReportsInfoPanel().revalidate();
            view.getReportsInfoPanel().repaint();
        }
        else if(e.getSource() == view.getCreateTableButton()){
            createTable();
        }
        else if(e.getSource() == view.getMainsButton()){
            view.getItemsList().setModel(menuDao.loadMains());
            view.getItemsList().repaint();
            view.getItemsList().revalidate();
        }
        else if(e.getSource() == view.getStartersButton()){
            view.getItemsList().setModel(menuDao.loadStarters());
            view.getItemsList().repaint();
            view.getItemsList().revalidate();
        }
        else if(e.getSource() == view.getDessertsButton()){
            view.getItemsList().setModel(menuDao.loadDesserts());
            view.getItemsList().repaint();
            view.getItemsList().revalidate();
        }
    }

    public void createTable(){
        int tableId = Integer.parseInt(view.getTableId().getText());
        int capacity = Integer.parseInt(view.getTableCapacity().getText());
        String status = view.getStatusComboBox().getSelectedItem().toString();

        if(TableDao.tableExists(tableId)){
            JOptionPane.showMessageDialog(view, "Table already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            TableDao.saveTable(tableId, capacity, status);
            view.getTableId().setText(String.valueOf(TableDao.getTablesNumber() + 1));
            JOptionPane.showMessageDialog(view, "Table created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void deleteItem() {
        String name = view.menuItemName().getSelectedItem().toString();
        if (menuDao.itemExists(name)) {
            menuDao.deleteMenuItem(name);
            view.menuItemsList().setModel(menuDao.loadItemNames());
            view.menuItemsList().repaint();
            view.menuItemsList().revalidate();
            view.menuItemName().setSelectedIndex(0);
            view.menuItemName().repaint();
            view.menuItemName().revalidate();
            
            JOptionPane.showMessageDialog(view, "Item deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Item does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addNewItem() {
       

        JLabel itemNameLabel = new JLabel("Item Name:");
        JTextField itemNameBox = new JTextField(20);
        
        JLabel itemPriceLabel = new JLabel("Item Price:");
        JTextField itemPriceField = new JTextField(20);
        JLabel itemDescriptionLabel = new JLabel("Item Description:");
        JTextField itemDescriptionField = new JTextField(20);
        JLabel itemCategoryLabel = new JLabel("Item Category:");
        JComboBox<String> itemCategories = new JComboBox<>(new String[] {"Starters", "Mains", "Desserts"});
        itemCategories.setSelectedIndex(0);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = itemNameBox.getText().toString();
                String price = itemPriceField.getText();
                String description = itemDescriptionField.getText();
                String category = itemCategories.getSelectedItem().toString();

                MenuItem item = new MenuItem(name, description, Double.parseDouble(price), category);
                boolean success = menuDao.saveMenuItem(item);
                if (success) {
                    JOptionPane.showMessageDialog(view, "Item created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    itemNameBox.setText("");
                    itemPriceField.setText("");
                    itemDescriptionField.setText("");
                    itemCategories.setSelectedIndex(0);

                    view.menuItemsList().setModel(menuDao.loadItemNames());
                    view.menuItemsList().repaint();
                    view.menuItemsList().revalidate();
                    
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to save item!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Add New Item"));
        panel.add(itemNameLabel);
        panel.add(itemNameBox);
        panel.add(itemPriceLabel);
        panel.add(itemPriceField);
        panel.add(itemDescriptionLabel);
        panel.add(itemDescriptionField);
        panel.add(itemCategoryLabel);
        panel.add(itemCategories);
        panel.add(saveBtn);
        JOptionPane.showMessageDialog(view, panel, "New Item", JOptionPane.PLAIN_MESSAGE);

    }

    private void saveItemData() {
        String name = view.menuItemName().getSelectedItem().toString();
        String price = view.menuItemPrice().getText();
        String description = view.menuItemDescription().getText();
        String category = view.menuItemCategories().getSelectedItem().toString();

        if (menuDao.itemExists(name)) {
            menuDao.updateMenuItem(new MenuItem(name, description, Double.parseDouble(price), category));
            JOptionPane.showMessageDialog(view, "Item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        else {
            MenuItem item = new MenuItem(name, description, Double.parseDouble(price), category);
            boolean success = menuDao.saveMenuItem(item);
            if (success) {
                JOptionPane.showMessageDialog(view, "Item created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Failed to save item!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void loadUsers() {

        view.getUsersPanel().removeAll();
        for (User user : userDao.getAllUsers()) {
            JPanel userCard = new JPanel(new GridLayout(3, 2));
            userCard.setBorder(BorderFactory.createTitledBorder("User Details"));
            
            userCard.add(new JLabel("Email: "));
            userCard.add(new JLabel(user.getEmail()));
            
            userCard.add(new JLabel("Role: " ));
            userCard.add(new JLabel(user.getRole()));

            userCard.add(new JLabel("Created on : "));
            userCard.add(new JLabel(user.getCreatedAt()));
            
            view.getUsersPanel().add(userCard);
        }
        
        view.getUsersPanel().revalidate();
        view.getUsersPanel().repaint();
    }

    void searchByName() {
        JTextField searchField = view.getSearchField();
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase();
                filterUsersByName(searchText);
            }
        });
    }

    void filterUsersByName(String searchText) {
        view.getUsersPanel().removeAll();
        
        for(User user : userDao.getAllUsers()) {
            if (user.getEmail().toLowerCase().contains(searchText)) {
                addAndDisplayUser(user);
            }
        }
        view.getUsersPanel().revalidate();
        view.getUsersPanel().repaint();
    }

    void filterByRole() {
        JComboBox<String> roleCombo = view.getRoleComboBox();
        roleCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleCombo.getSelectedItem();
                filterUsers(selectedRole);
            }
        });
    }

    private void filterUsers(String role) {
        view.getUsersPanel().removeAll();
        
        for(User user : userDao.getAllUsers()) {
            if (role.equals("all") || user.getRole().equals(role)) {
                addAndDisplayUser(user);
            }

            else if (role.equals("waiter") && user.getRole().equals("waiter")) {
                addAndDisplayUser(user);
            }

            else if (role.equals("chef") && user.getRole().equals("chef")) {
                addAndDisplayUser(user);
            }
            else if (role.equals("admin") && user.getRole().equals("admin")) {
                addAndDisplayUser(user);
            }
            
        }
        view.getUsersPanel().revalidate();
        view.getUsersPanel().repaint();
    }

    public void addAndDisplayUser(User user) {
        JPanel userCard = new JPanel(new GridLayout(3, 2));
            userCard.setBorder(BorderFactory.createTitledBorder("User Details"));
                
            userCard.add(new JLabel("Email: "));
            userCard.add(new JLabel(user.getEmail()));
                
            userCard.add(new JLabel("Role: " ));
            userCard.add(new JLabel(user.getRole()));

            userCard.add(new JLabel("Last logged in : "));
            userCard.add(new JLabel(user.getCreatedAt()));

            view.getUsersPanel().add(userCard);
    }

    private void showAddUserDialog() {

        JTextField emailField = new JTextField();
        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"admin", "waiter", "chef"});
    
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleCombo);
    
        int result = JOptionPane.showConfirmDialog(view, panel, "Add New User", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            String email = emailField.getText();

             if (userDao.emailExists(email)) {
            JOptionPane.showMessageDialog(view, 
                "Email already exists!\nPlease use a different email address.",
                "Duplicate Email", 
                JOptionPane.WARNING_MESSAGE);
            return; 
        }
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleCombo.getSelectedItem();
    
            boolean success = userDao.addUser(email, name, password, role);
            if (success) {
 
                view.getUsersPanel().removeAll();
                
                for(User user : userDao.getAllUsers()) {
                   JPanel userCard = new JPanel(new GridLayout(3, 2));
                    userCard.setBorder(BorderFactory.createTitledBorder("user Details"));
                    
                    userCard.add(new JLabel("Email: "));
                    userCard.add(new JLabel(user.getEmail()));
                    
                    userCard.add(new JLabel("Role: " ));
                    userCard.add(new JLabel(user.getRole()));

                    userCard.add(new JLabel("Last logged in : "));
                    userCard.add(new JLabel(user.getCreatedAt()));

                    view.getUsersPanel().add(userCard);
                }
                view.getUsersPanel().revalidate();
                view.getUsersPanel().repaint();
                
                JOptionPane.showMessageDialog(view, "User added successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add user", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}