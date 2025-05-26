package presentation.vue.admin;

import dao.TableDao;
import java.awt.*;
import javax.swing.*;

public class TablesPanel extends JPanel{

    private JLabel tableLabel;
    private JTextField tableField;
    private JLabel guestsLabel;
    private JTextField guestsField;
    private JLabel statusLabel;
    private JComboBox<String> statusComboBox;
    private JButton createTableButton;

    public TablesPanel() {
        this.setLayout(new GridLayout(0, 1, 5, 5));
        this.setBorder(BorderFactory.createTitledBorder("Table Information"));

        tableLabel = new JLabel("Table ID: ");
        tableField = new JTextField(10);
        tableField.setText(String.valueOf(TableDao.getTablesNumber() + 1));

        guestsLabel = new JLabel("Capacity: ");
        guestsField = new JTextField(10);
        statusLabel = new JLabel("Status: ");
        statusComboBox = new JComboBox<>(new String[]{"Available", "Occupied"});
        createTableButton = new JButton("Create Table");

        add(tableLabel);
        add(tableField);
        add(guestsLabel);
        add(guestsField);
        add(statusLabel);
        add(statusComboBox);
        add(createTableButton);

        setVisible(true);
    }

    public JButton getCreateTableButton() {
        return createTableButton;
    }

    public JTextField getTableField() {
        return tableField;
    }
    public JTextField getGuestsField() {
        return guestsField;
    }
    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }
}
