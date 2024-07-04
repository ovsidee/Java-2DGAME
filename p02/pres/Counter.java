package p02.pres;

import p02.game.PlusOneEvent;
import p02.game.ResetEvent;
import p02.game.StartEvent;

import javax.swing.*;
import java.awt.*;

public class Counter extends JPanel {
    private SevenSegmentDigit hundreds;
    private SevenSegmentDigit tens;
    private SevenSegmentDigit ones;

    public Counter() {
        setupUI();
        setupEventListeners();
    }

    private void setupUI() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        Dimension digitSize = new Dimension(28, 74);
        initNumbers(digitSize);
        add(hundreds);
        add(tens);
        add(ones);
        setOpaque(false);
    }

    private void setupEventListeners() {
        StartEvent.addStartEventListener(this::start);
        PlusOneEvent.addPlusOneEventListener(() -> ones.getListener().plusOneEventExecutes());
        ResetEvent.addResetEventListener(this::reset);
    }

    private void initNumbers(Dimension digitSize) {
        hundreds = new SevenSegmentDigit(digitSize, null);
        tens = new SevenSegmentDigit(digitSize, hundreds.getListener());
        ones = new SevenSegmentDigit(digitSize, tens.getListener());
    }

    public boolean isMaxScore() {
        boolean isScoreMax = hundreds.getSegment() == 9 &&
                    tens.getSegment() == 9 &&
                    ones.getSegment() == 9;
        return isScoreMax;
    }

    public void reset() {
        setVisible(false);
    }

    public void start() {
        setVisible(true);
        ones.setValue(0);
        tens.setValue(0);
        hundreds.setValue(0);
    }
}
