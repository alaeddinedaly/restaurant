package presentation.vue.admin;


import dao.MenuDao;
import java.awt.*;
import javax.swing.*;


public class MenuPanel extends JPanel {
    
    public JPanel leftPanel;

    public JPanel categoriesPanel;
    public JLabel categoriesLabel;
    public JButton startersButton,mainsButton,dessertsButton;

    public JPanel itemsPanel;
    public JLabel itemsLabel;
    public JList<String> itemsList;
    public DefaultListModel<String> itemsListModel;
    public DefaultListModel<String> itemsListCategorieModel;

    public MenuDao menuDao;
    public JPanel centerPanel;

    public JLabel editItemLabel;

    public JPanel insideCenterPanel;
    public JLabel itemName;
    public JComboBox<String> itemNameBox;
    public JLabel itemPrice;
    public JTextField itemPriceField;
    public JLabel itemDescription;
    public JTextField itemDescriptionField;
    public JLabel itemCategoryLabel;
    public JComboBox<String> itemCategories;



    public JButton saveBtn,deleteBtn,addNewItemBtn;
    public JPanel buttonPanel;



    public MenuPanel() {

        this.setLayout(new BorderLayout());

        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(new GridLayout(2, 1, 5, 5));

        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new BorderLayout());

        this.insideCenterPanel = new JPanel();
        this.insideCenterPanel.setLayout(new GridLayout(5, 2,5,5));

        this.editItemLabel = new JLabel("Edit Item");
        this.editItemLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.editItemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.centerPanel.add(this.editItemLabel, BorderLayout.NORTH);

        this.menuDao = new MenuDao();
        this.itemsListModel = menuDao.loadItemNames();
        this.itemsListCategorieModel = menuDao.loadItemCategories();
        

        
        this.itemName = new JLabel("Item Name:");
        String[] listStringNames = new String[this.itemsListModel.getSize()];
        for (int i = 0; i < this.itemsListModel.getSize(); i++) {
            listStringNames[i] = this.itemsListModel.getElementAt(i);
        }
        this.itemNameBox = new JComboBox<>(listStringNames);
        this.itemNameBox.setSelectedIndex(0);
        this.insideCenterPanel.add(this.itemName);
        this.insideCenterPanel.add(this.itemNameBox);

        this.itemPrice = new JLabel("Item Price:");
        this.itemPriceField = new JTextField(20);
        this.insideCenterPanel.add(this.itemPrice);
        this.insideCenterPanel.add(this.itemPriceField);

        this.itemDescription = new JLabel("Item Description:");
        this.itemDescriptionField = new JTextField(20);
        this.insideCenterPanel.add(this.itemDescription);
        this.insideCenterPanel.add(this.itemDescriptionField);

        this.itemCategoryLabel = new JLabel("Item Category:");
        String[] listStringCategorie = new String[this.itemsListCategorieModel.getSize()];

        for (int i = 0; i < this.itemsListCategorieModel.getSize(); i++) {
            listStringCategorie[i] = this.itemsListCategorieModel.getElementAt(i);
        }
        this.itemCategories = new JComboBox<>(listStringCategorie);
        this.itemCategories.setSelectedIndex(0);
        this.itemCategories.setEditable(false);
        this.insideCenterPanel.add(this.itemCategoryLabel);
        this.insideCenterPanel.add(this.itemCategories);

        this.saveBtn = new JButton("Save");
        this.deleteBtn = new JButton("Delete");
        this.addNewItemBtn = new JButton("Add New Item");

        this.buttonPanel = new JPanel();
        this.buttonPanel.add(this.saveBtn);
        this.buttonPanel.add(this.deleteBtn);

        this.categoriesPanel = new JPanel();
        this.categoriesPanel.setLayout(new GridLayout(4, 1,5,5));

        this.categoriesLabel = new JLabel("Categories : ");
        this.categoriesLabel.setFont(new Font("Arial", Font.BOLD, 15));

        this.startersButton = new JButton("Starters");
        this.mainsButton = new JButton("Mains");
        this.dessertsButton = new JButton("Desserts");

        this.categoriesPanel.add(this.categoriesLabel);
        this.categoriesPanel.add(this.startersButton);
        this.categoriesPanel.add(this.mainsButton);
        this.categoriesPanel.add(this.dessertsButton);

        this.leftPanel.add(this.categoriesPanel);
        
        this.itemsPanel = new JPanel();
        this.itemsLabel = new JLabel("Items : ");
        this.itemsLabel.setFont(new Font("Arial", Font.BOLD, 15));

        this.itemsList = new JList<>();
        add(new JScrollPane(this.itemsList));
        
        


        this.itemsList.setModel(this.itemsListModel);
        


        this.itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.itemsList.setLayoutOrientation(JList.VERTICAL);


        this.itemsPanel.setLayout(new GridLayout(2,1,5,5));
        this.itemsPanel.add(this.itemsLabel);
        this.itemsPanel.add(this.itemsList);

        

        this.leftPanel.add(this.itemsPanel);
        
        this.centerPanel.add(this.buttonPanel, BorderLayout.SOUTH);
        this.centerPanel.add(this.insideCenterPanel, BorderLayout.CENTER);
        

        this.insideCenterPanel.setBorder(BorderFactory.createTitledBorder("Edit Item"));

        this.leftPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(this.centerPanel, BorderLayout.CENTER);
        this.add(this.addNewItemBtn, BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }
    public JPanel getLeftPanel() {
        return leftPanel;
    }
    public JPanel getCategoriesPanel() {
        return categoriesPanel;
    }
    public JLabel getCategoriesLabel() {
        return categoriesLabel;
    }
    public JButton getStartersButton() {
        return startersButton;
    }
    public JButton getMainsButton() {
        return mainsButton;
    }
    public JButton getDessertsButton() {
        return dessertsButton;
    }
    public JPanel getItemsPanel() {
        return itemsPanel;
    }
    public JLabel getItemsLabel() {
        return itemsLabel;
    }
    public JList<String> getItemsList() {
        return itemsList;
    }

    public DefaultListModel<String> getItemsListModel() {
        return itemsListModel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public JLabel getEditItemLabel() {
        return editItemLabel;
    }

    public JPanel getInsideCenterPanel() {
        return insideCenterPanel;
    }

    public JLabel getItemName() {
        return itemName;
    }

    public JComboBox<String> getItemNameBox() {
        return itemNameBox;
    }
    public JLabel getItemPrice() {
        return itemPrice;
    }
    public JTextField getItemPriceField() {
        return itemPriceField;
    }
    public JLabel getItemDescription() {
        return itemDescription;
    }
    public JTextField getItemDescriptionField() {
        return itemDescriptionField;
    }
    public JLabel getItemCategoryLabel() {
        return itemCategoryLabel;
    }
    public JComboBox<String> getItemCategories() {
        return itemCategories;
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }
    public JButton getDeleteBtn() {
        return deleteBtn;
    }
    public JButton getAddNewItemBtn() {
        return addNewItemBtn;
    }
    public JPanel getButtonPanel() {
        return buttonPanel;
    }
    
}