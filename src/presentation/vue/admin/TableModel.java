package presentation.vue.admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableModel extends JPanel{

    private JTable reportTable;
    private DefaultTableModel tableModel;

    public TableModel(Object[][] reportData) {

        this.setLayout(new BorderLayout());


        String[] columns = {"Today's orders", "Price", "Date"};

        tableModel = new DefaultTableModel(reportData, columns);
        this.reportTable = new JTable(tableModel);

        this.reportTable.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(reportTable);

        this.add(scrollPane);
    }
}
