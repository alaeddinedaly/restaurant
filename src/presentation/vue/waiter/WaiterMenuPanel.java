package presentation.vue.waiter;

import java.awt.*;
import javax.swing.*;

public class WaiterMenuPanel extends JPanel{
    public JPanel leftPanel;

    public JLabel categoriesLabel;
    public JButton allFilter,mainsFilter,startersFilter,dessertsFilter;

    public JPanel rightPanel;

    public JPanel searchPanel;
    public JLabel searchLabel;
    public JTextField searchField;



    

    public JButton addToOrderBtn;

    public WaiterMenuPanel() {
        this.setLayout(new BorderLayout());
        this.leftPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        this.leftPanel.setBorder(BorderFactory.createTitledBorder("Categories"));

  
        this.rightPanel = new JPanel(new GridLayout(0,1));
        this.rightPanel.setBorder(BorderFactory.createTitledBorder("Menu Items"));

        this.searchPanel = new JPanel(new FlowLayout());
        this.searchLabel = new JLabel("Search: ");
        this.searchField = new JTextField(20);
        this.searchPanel.add(this.searchLabel);
        this.searchPanel.add(this.searchField);
  

        this.add(this.searchPanel, BorderLayout.NORTH);


        this.allFilter = new JButton("All");
        this.mainsFilter = new JButton("Mains");
        this.startersFilter = new JButton("Starters");
        this.dessertsFilter = new JButton("Desserts");

        this.leftPanel.add(this.allFilter);
        this.leftPanel.add(this.mainsFilter);
        this.leftPanel.add(this.startersFilter);
        this.leftPanel.add(this.dessertsFilter);

        JScrollPane scrollPane = new JScrollPane(this.rightPanel);
        
        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);

    }

    public JPanel getRightPanel(){
        return rightPanel;
    }
    public JPanel getLeftPanel(){
        return leftPanel;
    }

    public JButton getAllFilter() {
        return allFilter;
    }

    public JButton getMainsFilter() {
        return mainsFilter;
    }

    public JButton getStartersFilter() {
        return startersFilter;
    }

    public JButton getDessertsFilter() {
        return dessertsFilter;
    }
    public JTextField getSearchField() {
        return searchField;
    }
}