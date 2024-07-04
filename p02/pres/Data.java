package p02.pres;

import javax.swing.table.AbstractTableModel;

public class Data extends AbstractTableModel {
    private int[] data = new int[7];
    private int rowCount = 0;

    public void setData(int[] data) {
        this.data = data;
        rowCount++;
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0 && rowIndex >= 0 && rowIndex < data.length) {
            return data[data.length - 1 - rowIndex];
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return 1; //for a single-column table
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
