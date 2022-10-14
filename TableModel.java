import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel
{
    int rowCount;
    int columnCount;
    ArrayList<String []> dataArrayList;
    boolean dataFiltered;

    public TableModel()
    {
        this.columnCount = 7;
        this.rowCount = 0;

        this.dataArrayList = new ArrayList<String[]>();
        this.dataFiltered = false;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return switch (columnIndex)
                {
                    case 0 -> "ID";
                    case 1 -> "Name";
                    case 2 -> "Author";
                    case 3 -> "Album";
                    case 4 -> "YearsOfIssue";
                    case 5 -> "Genre";
                    case 6 -> "NumberInAlbum";
                    default -> "Error";
                };
    }

    @Override
    public int getRowCount()
    {
        return this.rowCount;
    }

    @Override
    public int getColumnCount()
    {
        return this.columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public void addDataRow(String[] row)
    {
        String[] dataRow = new String[getColumnCount()];
        dataRow = row;
        dataArrayList.add(dataRow);
        this.rowCount++;
    }

    public void clear()
    {
        dataArrayList = new ArrayList<String[]>();
        rowCount = 0;
    }
}
