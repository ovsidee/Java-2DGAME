package p02.pres;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    public Image backgroundImage;

    public Background(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}