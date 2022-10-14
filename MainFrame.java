import javax.swing.*;
import java.awt.*;

public class MainFrame extends MyFrame{
    JButton btnDisconnect;
    JButton btnEditData;
    JButton btnDeleteData;
    JButton btnFilteredData;
    JButton btnAddData;

    TableModel tableModel;
    JTable table;
    JScrollPane scrollPane;

    public MainFrame(){
        super("Working with the database", new Dimension(800, 475));

        this.btnAddData = new JButton();
        this.btnDeleteData = new JButton();
        this.btnFilteredData = new JButton();
        this.btnEditData = new JButton();
        this.btnDisconnect = new JButton();

        this.btnAddData.setBounds(15, 375, 120, 35);
        this.btnDeleteData.setBounds(145, 375, 120, 35);
        this.btnFilteredData.setBounds(275, 375, 120, 35);
        this.btnEditData.setBounds(405, 375, 120, 35);
        this.btnDisconnect.setBounds(635, 375, 120, 35);

        this.tableModel = new TableModel();
        this.table = new JTable(tableModel);
        this.table.getTableHeader().setReorderingAllowed(false);
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(15, 15, 760, 350);

        this.myPanel.add(btnAddData);
        this.myPanel.add(btnDeleteData);
        this.myPanel.add(btnFilteredData);
        this.myPanel.add(btnEditData);
        this.myPanel.add(btnDisconnect);
        this.myPanel.add(scrollPane);

    }
}
