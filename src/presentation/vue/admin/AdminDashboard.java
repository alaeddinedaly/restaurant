package presentation.vue.admin;

import java.awt.BorderLayout;
import javax.swing.*;

public class AdminDashboard extends JFrame {

    public JTabbedPane tabbedPane;
    public JLabel titleLabel;
    public JPanel usersPanel;
    public JPanel menuPanel;
    public JPanel tablesPanel;
    public JPanel reportsPanel;
    



    public AdminDashboard() {

        this.setTitle("Restaurant App - Admin Dashboard");
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500); 
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(true);

        this.titleLabel = new JLabel("Admin Dashboard");
        this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(24f));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        
        this.tabbedPane = new JTabbedPane();

        this.usersPanel = new UsersPanel();
        add(new JScrollPane(usersPanel));

        this.menuPanel = new MenuPanel();

        this.tablesPanel = new TablesPanel();

        this.reportsPanel = new ReportsPanel();

        this.tabbedPane.addTab("Users", this.usersPanel);
        this.tabbedPane.addTab("Menu", this.menuPanel);
        this.tabbedPane.addTab("Tables", this.tablesPanel);
        this.tabbedPane.addTab("Reports",this.reportsPanel);
        

        this.add(this.titleLabel, BorderLayout.NORTH);
        this.add(this.tabbedPane, BorderLayout.CENTER);


    }

    public JList getItemsList() {
        return ((MenuPanel) tabbedPane.getComponentAt(1)).itemsList;
    }
    public DefaultListModel getItemsListModel() {
        return ((MenuPanel) tabbedPane.getComponentAt(1)).itemsListModel;
    }
    public JButton getMainsButton() {
        return ((MenuPanel) tabbedPane.getComponentAt(1)).mainsButton;
    }

    public JButton getStartersButton() {
        return ((MenuPanel) tabbedPane.getComponentAt(1)).startersButton;
    }

    public JButton getDessertsButton() {
        return ((MenuPanel) tabbedPane.getComponentAt(1)).dessertsButton;
    }

    public JTextField getTableId() {
        return ((TablesPanel) tabbedPane.getComponentAt(2)).getTableField();
    }

    public JTextField getTableCapacity() {
        return ((TablesPanel) tabbedPane.getComponentAt(2)).getGuestsField();
    }

    public JComboBox<String> getStatusComboBox() {
        return ((TablesPanel) tabbedPane.getComponentAt(2)).getStatusComboBox();
    }

    

    public JButton getCreateTableButton() {
        return ((TablesPanel) tabbedPane.getComponentAt(2)).getCreateTableButton();
    }

    public JPanel getReportsInfoPanel(){
        return ((ReportsPanel) tabbedPane.getComponentAt(3)).getInfoPanel();
    }
    
    public JButton getRefreshButton() {
        return ((ReportsPanel) tabbedPane.getComponentAt(3)).getRefreshButton();
    }
    
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JButton getAddUserBtn() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).addUserBtn;
    }
    
    public JPanel getUsersPanel() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).usersPanel;
    }

    public JButton getAllFilterBtn() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).allFilter;
    }
    public JButton getWaiterFilterBtn() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).waiterFilter;
    }
    public JButton getChefFilterBtn() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).chefsFilter;
    }

    public JComboBox<String> getSortByRoleComboBox() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).sortByRoleComboBox;
    }

    public JPanel getFiltersPanel() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).filtersPanel;
    }
    public JTextField getSearchField() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).searchField;
    }
    public JButton getEditUserBtn() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).editUserBtn;
    }
    public JButton getDeleteUserBtn() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).deleteUserBtn;
    }

    public JComboBox<String> getRoleComboBox() {
        return ((UsersPanel) tabbedPane.getComponentAt(0)).sortByRoleComboBox;
    }
    
    public JComboBox<String> menuItemName(){
        return ((MenuPanel) tabbedPane.getComponentAt(1)).itemNameBox;
    }

    public JTextField menuItemPrice(){
        return ((MenuPanel) tabbedPane.getComponentAt(1)).itemPriceField;
    }
    public JTextField menuItemDescription(){
        return ((MenuPanel) tabbedPane.getComponentAt(1)).itemDescriptionField;
    }
    public JComboBox<String> menuItemCategories(){
        return ((MenuPanel) tabbedPane.getComponentAt(1)).itemCategories;
    }
    public JButton menuSaveBtn(){
        return ((MenuPanel) tabbedPane.getComponentAt(1)).saveBtn;
    }

    public JButton menuDeleteBtn(){
        return ((MenuPanel) tabbedPane.getComponentAt(1)).deleteBtn;
    }

    public JList<String> menuItemsList(){
        return ((MenuPanel) tabbedPane.getComponentAt(1)).itemsList;
    }

    public JButton menuAddBtn(){
        return ((MenuPanel) tabbedPane.getComponentAt(1)).addNewItemBtn;
    }
    
    
}