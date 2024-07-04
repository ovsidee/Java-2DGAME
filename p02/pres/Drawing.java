package p02.pres;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Drawing extends JPanel implements TableCellRenderer {

    private BufferedImage obstacleImage;
    private BufferedImage carImage;

    private static final double[][] positions = {
            {697.6, 720.0, 745.6},  // first row
            {598.4, 656.0, 710.4},  // second
            {499.2, 579.2, 665.6},  // third
            {396.8, 508.8, 620.8},  // fourth
            {304.0, 444.8, 576.0},  // fifth
            {208.0, 380.8, 540.8},  // sixth
            {128.0, 320.0, 512.0}   // car (sevenths)
    };

    private int selectedValue = 0;
    private int selectedRow = 0;

    public Drawing() {
        setOpaque(false);
        loadImages();
    }

    private void loadImages() {
        try {
            obstacleImage = ImageIO.read(new File("./assets/obstacle2.jpg"));
            carImage = ImageIO.read(new File("./assets/car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.selectedValue = (int) value;
        this.selectedRow = row;
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (selectedRow != 6) {
            if (selectedValue > 0 && selectedValue < 4) {
                int xPos = (int) positions[selectedRow][selectedValue - 1];
                int yPos = (selectedRow == 0) ? 10 : 0;
                drawObstacle(g, obstacleImage, xPos, yPos);
            } else if (selectedValue > 3) {
                for (int j = 0; j < 3; j++) {
                    if (j != selectedValue - 4) {
                        int xPos = (int) positions[selectedRow][j];
                        drawObstacle(g, obstacleImage, xPos, 0);
                    }
                }
            }
        } else {
            int carXPos = (int) positions[selectedRow][selectedValue];
            drawCar(g, carImage, carXPos, 0);
        }
    }

    private void drawObstacle(Graphics g, BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, 45 * (selectedRow + 1) / 2, 15 * (selectedRow + 1) / 2, null);
    }

    private void drawCar(Graphics g, BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, 100, 75, this);
    }
}
