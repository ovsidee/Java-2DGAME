package p02.game;

import p02.pres.Background;
import p02.pres.Counter;
import p02.pres.Panel;

import javax.swing.*;
import java.awt.*;

public class InitGame extends JFrame {

    private static final int widthWindow = 800;
    private static final int heightWindow = 600;
    private static final String titleWindow = "AutoSlalom Game";
    private static final String backGroundPath = "./assets/board.jpg";

    private Background background;
    private Board board;

    public InitGame() {
        setTitle(titleWindow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        background = loadBackgroundImage();
        JLayeredPane layeredPane = setupLayeredPane();

        add(layeredPane, BorderLayout.CENTER);
        setSize(widthWindow, heightWindow);
        setVisible(true);
        addKeyListener(board);
    }

    private Background loadBackgroundImage() {
        Image image = new ImageIcon(backGroundPath).getImage();
        return new Background(image);
    }

    private JLayeredPane setupLayeredPane() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(widthWindow, heightWindow));

        background.setBounds(0, 0, widthWindow, heightWindow);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        Counter counter = new Counter();
        counter.setBounds(50, 30, 100, 80);
        layeredPane.add(counter, JLayeredPane.MODAL_LAYER);

        board = initBoard(counter);
        layeredPane.add(board, JLayeredPane.MODAL_LAYER);

        Panel panel = new Panel(board);
        panel.setBounds(0, 0, widthWindow, heightWindow);
        layeredPane.add(panel, JLayeredPane.MODAL_LAYER);

        return layeredPane;
    }

    private Board initBoard(Counter counter) {
        Board newBoard = new Board(counter);
        newBoard.setBounds(500, -50, 1, 1);
        return newBoard;
    }
}