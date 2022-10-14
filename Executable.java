import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class Executable {
    LoginDialog loginDialog;
    MainFrame mainFrame;
    WorkWithData addString;
    WorkWithData editString;
    Connection connection;
    Statement statement;

    public Executable(){
        this.mainFrame = new MainFrame();
        mainFrame.hide();
        this.loginDialog = new LoginDialog();
        this.loginDialog.btnLog.setText("Login");
        ActionListener actionListener = new ActionSetConnection();
        this.loginDialog.btnLog.addActionListener(actionListener);
        this.loginDialog.setVisible(true);

        this.mainFrame.btnAddData.setAction(new ActionDisplayFrameAddData());
        this.mainFrame.btnAddData.setText("Add data");
        this.mainFrame.btnDeleteData.setAction(new ActionDeleteData());
        this.mainFrame.btnDeleteData.setText("Delete data");
        this.mainFrame.btnFilteredData.setAction(new ActionFilterData());
        this.mainFrame.btnFilteredData.setText("Filter data");
        this.mainFrame.btnEditData.setAction(new ActionDisplayEditDataFrame());
        this.mainFrame.btnEditData.setText("Edit data");
        this.mainFrame.btnDisconnect.setAction(new ActionDisconnection());
        this.mainFrame.btnDisconnect.setText("Disconnect");

    }

    public void updateData(ResultSet resultSet){
        try{
            mainFrame.tableModel.clear();
            while (resultSet.next())
            {
                String[] dataRow = new String[7];
                dataRow[0] = String.valueOf(resultSet.getInt("id"));
                dataRow[1] = resultSet.getString("Name");
                dataRow[2] = resultSet.getString("Author");
                dataRow[3] = resultSet.getString("Album");
                dataRow[4] = String.valueOf(resultSet.getInt("YearsOfIssue"));
                dataRow[5] = resultSet.getString("Genre");
                dataRow[6] = String.valueOf(resultSet.getInt("NumberInAlbum"));
                mainFrame.tableModel.addDataRow(dataRow);
            }
            mainFrame.tableModel.fireTableDataChanged();
        }catch(SQLException ex){}
    }
    //Вывод окна добавления данных
    public class ActionDisplayFrameAddData extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(mainFrame.tableModel.dataFiltered)
            {
                new ActionFilterData();
                mainFrame.tableModel.dataFiltered = false;
            }
            addString = new WorkWithData();
            addString.btConfirm.setAction(new ActionAddData());
            addString.btConfirm.setText("Confirm");
            addString.display();
        }
    }

    //Добавление новых данных
    public class ActionAddData extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int $id = mainFrame.tableModel.dataArrayList.size() + 1;
                String $Name = addString.tfName.getText();
                String $Author = addString.tfAuthor.getText();
                String $Album = addString.tfAlbum.getText();
                String $Years = addString.tfYearsOfIssue.getText();
                String $Genre = addString.tfGenre.getText();
                String $Number = addString.tfNumberInAlbum.getText();

                int rows = statement.executeUpdate("INSERT music(id,Name,Author,Album,YearsOfIssue,Genre,NumberInAlbum) VALUES " +
                        "('"+$id+"', '"+$Name+"', '"+$Author+"', '"+$Album+"', '"+$Years+"', '"+$Genre+"', '"+$Number+"')");

                ResultSet resultSet = statement.executeQuery("SELECT * FROM music");
                updateData(resultSet);
                addString.hide();

            }catch (SQLException ex){
                System.err.println(ex);
            }
        }
    }

    public class ActionDisplayEditDataFrame extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {

                editString = new WorkWithData();
                editString.frame.setTitle("Row edit");

                editString.btConfirm.setAction(new ActionEditData());
                editString.btConfirm.setText("Save");

                int selectedRowId = Integer.parseInt((String) mainFrame.table.getModel().getValueAt(mainFrame.table.getSelectedRow(), 0));
                ResultSet resultSet = statement.executeQuery("SELECT Name,Author,Album,YearsOfIssue,Genre,NumberInAlbum FROM music WHERE id = '"+selectedRowId+"' LIMIT 1");
                resultSet.next();

                editString.tfName.setText(resultSet.getString("Name"));
                editString.tfAuthor.setText(resultSet.getString("Author"));
                editString.tfAlbum.setText(resultSet.getString("Album"));
                editString.tfYearsOfIssue.setText(resultSet.getString("YearsOfIssue"));
                editString.tfGenre.setText(resultSet.getString("Genre"));
                editString.tfNumberInAlbum.setText((resultSet.getString("NumberInAlbum")));

                editString.display();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    //Изменение выбранной строки выбранных даных
    public class ActionEditData extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int selectedRowId = Integer.parseInt((String)mainFrame.table.getModel().getValueAt(mainFrame.table.getSelectedRow(), 0));
                String $Name = editString.tfName.getText();
                String $Author = editString.tfAuthor.getText();
                String $Album = editString.tfAlbum.getText();
                String $Years = editString.tfYearsOfIssue.getText();
                String $Genre = editString.tfGenre.getText();
                String $Number = editString.tfNumberInAlbum.getText();

                statement.executeUpdate("UPDATE music SET Name = '"+$Name+"', Author = '"+$Author+"', Album = '"+$Album+"', YearsOfIssue = '"+$Years+"', Genre = '"+$Genre+"', NumberInAlbum = '"+$Number+"' WHERE id = '"+selectedRowId+"' LIMIT 1");

                ResultSet resultSet = statement.executeQuery("SELECT id,Name,Author,Album,YearsOfIssue,Genre,NumberInAlbum FROM music");
                updateData(resultSet);

                editString.hide();
            }
            catch (SQLException | NumberFormatException exception)
            {
                System.out.println("SQLException: " + exception);
            }
        }
    }

    public class ActionSetConnection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String url = loginDialog.getURL();
                String login = loginDialog.getLog();
                String password = loginDialog.getPass();
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url,login,password);
                statement = connection.createStatement();
                System.out.println("Connection complete");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM music");
                updateData(resultSet);
                loginDialog.dispose();
                mainFrame.display();

            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Error! Please try again!","Error"
                        ,JOptionPane.ERROR_MESSAGE);
            }
            catch(ClassNotFoundException ex){}
        }
    }

    //Удаления выбранной строки
    public class ActionDeleteData extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int selectedRowId = Integer.parseInt((String) mainFrame.table.getModel().getValueAt(mainFrame.table.getSelectedRow(), 0));

            System.out.println("Selected row: " + selectedRowId);
            try
            {
                statement.executeUpdate("DELETE FROM music WHERE id = '"+selectedRowId+"' LIMIT 1");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM music");
                updateData(resultSet);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    //Фильтрация данных по полю reaction
    public class ActionFilterData extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                ResultSet resultSet;
                mainFrame.tableModel.clear();

                if (mainFrame.tableModel.dataFiltered)
                {
                    resultSet = statement.executeQuery("SELECT * FROM music");
                    mainFrame.tableModel.dataFiltered = false;
                }
                else
                {
                    resultSet = statement.executeQuery("SELECT * FROM music WHERE Genre = 'Rock'");
                    mainFrame.tableModel.dataFiltered = true;
                    JOptionPane.showMessageDialog(null,"Data filtered by genre 'Rock'");
                }
                mainFrame.tableModel.fireTableDataChanged();
                mainFrame.tableModel.clear();

                updateData(resultSet);
                mainFrame.tableModel.fireTableDataChanged();
            }
            catch (SQLException exception)
            {
                System.out.println("SQLException: " + exception);
            }
        }
    }


    //Отлючение от базы данных
    public class ActionDisconnection extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Disconnection");
            loginDialog.setVisible(true);
            mainFrame.hide();
        }
    }

    public static void main(String[] args) {
        Executable executable = new Executable();
    }
}