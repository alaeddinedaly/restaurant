package presentation.vue.admin;


import dao.UserDao;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class UsersPanel extends JPanel {
    
    public JPanel usersPanel;
    public JPanel userPanel;
    public JPanel leftPanel;
    public JButton addUserBtn, editUserBtn, deleteUserBtn,allFilter,waiterFilter,chefsFilter;
    public JLabel filter;

    public JPanel centerPanel;

    public JPanel filtersPanel;
    public JPanel sortByPanel;

    public JLabel sortLabel;

    public JComboBox<String> sortByRoleComboBox;

    public JLabel searchLabel;
    public JTextField searchField;
    public JPanel searchPanel;

    private UserDao userDao;


    public UsersPanel() {
        
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(""));
        this.usersPanel = new JPanel(new GridLayout(0,1));
        JScrollPane scrollPane = new JScrollPane(this.usersPanel);

        this.leftPanel = new JPanel(new GridLayout(2, 1, 9, 9));
        this.leftPanel.setBorder(BorderFactory.createTitledBorder(""));
        this.addUserBtn = new JButton("Add User");
        this.editUserBtn = new JButton("Edit User");
        this.deleteUserBtn = new JButton("Delete User");

        

        this.filtersPanel = new JPanel(new GridLayout(4, 1, 9, 9));
        this.allFilter = new JButton("ALL");
        this.waiterFilter = new JButton("Waiters");
        this.chefsFilter = new JButton("Chefs");

        this.filter = new JLabel("Filters : ");
        this.sortLabel = new JLabel("Filter By : ");
        
        this.filtersPanel.add(this.filter);
        this.filtersPanel.add(this.allFilter);
        this.filtersPanel.add(this.waiterFilter);
        this.filtersPanel.add(this.chefsFilter);

        this.sortByRoleComboBox = new JComboBox<>(new String[]{"admin","all", "waiter", "chef"});
        this.sortByRoleComboBox.setSelectedIndex(1);

        this.sortByPanel = new JPanel(new GridLayout(3, 1, 9, 9));

        this.sortByPanel.add(this.sortLabel);
        this.sortByPanel.add(this.sortByRoleComboBox);

        this.centerPanel = new JPanel(new BorderLayout());
        this.searchPanel = new JPanel(new FlowLayout());
        this.searchLabel = new JLabel("Search:");
        this.searchField = new JTextField(15);
        this.searchPanel.add(this.searchLabel);
        this.searchPanel.add(this.searchField);

        
        

        this.centerPanel.add(this.searchPanel, BorderLayout.NORTH);
        this.centerPanel.add(scrollPane, BorderLayout.CENTER);
        this.centerPanel.add(this.addUserBtn, BorderLayout.SOUTH);



        this.leftPanel.add(this.filtersPanel);
        this.leftPanel.add(this.sortByPanel);
        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(this.centerPanel, BorderLayout.CENTER);
        
    }
    public JComboBox<String> getSortByRoleComboBox() {
        return sortByRoleComboBox;
    }
    public JButton getAddUserButton() {
        return addUserBtn;
    }

    public JButton getEditUserBtn() {
        return editUserBtn;
    }
    public JButton getDeleteUserBtn() {
        return deleteUserBtn;
    }

    
    
}