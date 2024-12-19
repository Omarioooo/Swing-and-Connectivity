package GUI;

import DBConnectivity.DBOperations;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class TablePnl extends JPanel {
    ResultSet resultSet;
    DBOperations operations;
    JTable tbl;


    public TablePnl() throws SQLException {
        operations = new DBOperations();
        this.resultSet = operations.select();
        setLayout(new BorderLayout());

        tbl = InfoTable();
        JScrollPane scrollPane = new JScrollPane(tbl);
        add(scrollPane, BorderLayout.CENTER);

    }


    // A method to return the view(Table)
    public JTable InfoTable() throws SQLException {

        if (resultSet == null) {
            JOptionPane.showMessageDialog(this, "No data found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return new JTable();
        }

        DefaultTableModel tableModel = buildTableModel(resultSet);

        JTable table = new JTable(tableModel);
        table.setRowHeight(40);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 15));
        header.setBackground(Color.GRAY);
        header.setForeground(Color.WHITE);

        // Close the ResultSet when done
        resultSet.close();

        return table;
    }


    // A method to convert the ResultSet to DefaultTableModel
    private DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        // Collect column names
        Vector<String> header = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            header.add(metaData.getColumnName(columnIndex));
        }

        // Collect rows body
        Vector<Vector<Object>> body = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(resultSet.getObject(columnIndex));
            }
            body.add(row);
        }

        return new DefaultTableModel(body, header);
    }
}
