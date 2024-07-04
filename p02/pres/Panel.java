package p02.pres;

import p02.game.Board;
import java.awt.*;
import javax.swing.*;
import p02.game.TickEvent;

public class Panel extends JPanel {
    private final JTable jTable;

    private final Board board;
    private final Data data;
    private final TickEvent.TickEventListener tickEventListener;

    public Panel(Board board) {
        this.board = board;
        this.tickEventListener = this::updatePanel;
        this.data = new Data();
        this.jTable = createJTableWithDataModel(data);

        setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(800, 600));

        add(jTable);
        TickEvent.addTickEventListener(tickEventListener);
    }

    private JTable createJTableWithDataModel(Data data) {
        JTable customJTable = new JTable();
        customJTable.setOpaque(false);
        customJTable.setModel(data);
        customJTable.setDefaultRenderer(Integer.class, new Drawing());
        customJTable.setRowMargin(0);
        customJTable.setRowHeight(80);
        customJTable.setPreferredSize(new Dimension(800, 600));
        customJTable.setGridColor(new Color(255, 0, 0, 0));
        return customJTable;
    }

    private void updatePanel() {
        data.setData(board.getArr());
        jTable.setModel(data);
        repaint();
    }
}