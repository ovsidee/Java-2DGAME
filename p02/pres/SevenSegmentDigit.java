package p02.pres;

import p02.game.PlusOneEvent;

import javax.swing.*;
import java.awt.*;

public class SevenSegmentDigit extends JPanel {
    private int value;
    private PlusOneEvent.PlusOneEventListener plusOneEventListener = this::increase;
    private PlusOneEvent.PlusOneEventListener next;

    public SevenSegmentDigit(Dimension size, PlusOneEvent.PlusOneEventListener next) {
        this.value = 0;
        setOpaque(true);
        this.setPreferredSize(size);
        this.next = next;
    }

    public void increase() {
        if (value == 9) {
            value = 0;
            next.plusOneEventExecutes();
        } else {
            value++;
        }
        repaint();
    }

    public PlusOneEvent.PlusOneEventListener getListener(){
        return this.plusOneEventListener;
    }

    @Override
     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawDigits(g, value, getWidth(), getHeight());
    }

    private void drawDigits(Graphics g, int value, int width, int height) {
        boolean[][] allSegments = {
                {true, true, true, true, true, true, false},      // 0
                {false, true, true, false, false, false, false},  // 1
                {true, true, false, true, true, false, true},     // 2
                {true, true, true, true, false, false, true},     // 3
                {false, true, true, false, false, true, true},    // 4
                {true, false, true, true, false, true, true},     // 5
                {true, false, true, true, true, true, true},      // 6
                {true, true, true, false, false, false, false},   // 7
                {true, true, true, true, true, true, true},       // 8
                {true, true, true, true, false, true, true}       // 9
        };

        int segmentWidth = width / 5;
        int segmentHeight = height / 5;
        int gap = segmentWidth / 5;

        int[][] positions = {
                // top
                {
                    segmentWidth + gap,
                        0,
                        width - 2 * (segmentWidth + gap),
                        segmentHeight
                },

                // top-right
                {
                    width - segmentWidth - gap,
                        segmentHeight + gap,
                        segmentWidth,
                        height / 2 - segmentHeight - 2 * gap
                },

                // bottom-right
                {
                    width - segmentWidth - gap,
                        height / 2 + segmentHeight / 2 + gap,
                        segmentWidth,
                        height / 2 - segmentHeight - 2 * gap
                },

                // bottom
                {
                    segmentWidth + gap,
                        height - segmentHeight,
                        width - 2 * (segmentWidth + gap),
                        segmentHeight
                },

                // bottom-left
                {
                    0,
                        height / 2 + segmentHeight / 2 + gap,
                        segmentWidth,
                        height / 2 - segmentHeight - 2 * gap
                },

                // top-left
                {
                    0,
                        segmentHeight + gap,
                        segmentWidth,
                        height / 2 - segmentHeight - 2 * gap
                },

                // middle
                {
                    segmentWidth + gap,
                        height / 2 - segmentHeight / 2,
                        width - 2 * (segmentWidth + gap),
                        segmentHeight
                }
        };

        g.setColor(Color.BLACK);
        for (int i = 0; i < allSegments[value].length; i++) {
            if (allSegments[value][i]) {
                int[] pos = positions[i];
                g.fillRect(pos[0], pos[1], pos[2], pos[3]);
            }
        }
    }

    public int getSegment() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        repaint();
    }
}
