package p02.game;

import p02.pres.Counter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Board extends JPanel implements KeyListener {
    private static final int boardSize = 3;
    private static final int nothing = 0b000;

    private int[] board = new int[7];
    private Random random = new Random();
    private Counter scoreCounter;
    private boolean canMove;
    private int tickCounter;
    private boolean running;
    private int forCollision;
    private int forIncrement;

    private final TickEvent.TickEventListener tickEvent = this::tickEvent;

    private static final int[] obstacleMarks = {
            0b000, // nothing
            0b001, //  1
            0b010, //  2
            0b100, //  3
            0b110, //  4
            0b101, //  5
            0b011  //  6
    };

    public Board(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
        this.tickCounter = -1;
        this.running = false;
        this.forCollision = 0;
        this.forIncrement = 0;

        setOpaque(false);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new FlowLayout());
        addKeyListener(this);
        setFocusable(true);
        TickEvent.addTickEventListener(tickEvent);
    }

    private void moveLeft() {
        System.out.println("Attempting to move left");
        if (board[0] > 0 && canMove) {
            board[0]--;
            System.out.println("Moved left");
            canMove = false;
        }
    }

    private void moveRight() {
        System.out.println("Attempting to move right");
        if (board[0] < boardSize - 1 && canMove) {
            board[0]++;
            System.out.println("Moved right");
            canMove = false;
        }
    }

    private void generateObstacles() {
        System.out.println("Generating Obstacles");
        for (int i = 1; i < 6; i++) {
            board[i] = board[i + 1];
        }
        int previous = findPreviousObstacle();
        if (previous <= 3 && previous > 0) {
            generateNextObstacle(previous);
        } else if (previous > 3) {
            board[6] = previous - 3;
        } else {
            board[6] = random.nextInt(6) + 1;
        }
    }

    private int findPreviousObstacle() {
        for (int i = 6; i > 0; i--) {
            if (board[i] != 0) {
                return board[i];
            }
        }
        return 0;
    }

    private void generateNextObstacle(int previous) {
        for (int j = 1; j < 4; j++) {
            if (j != previous && random.nextBoolean()) {
                board[6] = j;
                return;
            }
        }
        board[6] = previous + 3;
    }

    public void tickEvent() {
        System.out.println("Tick : " + tickCounter);
        forIncrement = board[1];
        forCollision = board[1];
        canMove = true;

        if (scoreCounter.isMaxScore()) {
            new ResetEvent().notifyResetListeners();
            running = false;
        }

        tickCounter++;

        if (tickCounter == 9) tickCounter++;
//        System.out.println(tickCounter);
        if (tickCounter % 2 == 0) {
            generateObstacles();
        } else {
            generateBlank();
        }

        checkCollision();
        increment();
    }

    private void checkCollision() {
        if (((1 << board[0]) & getObstacleBits(forCollision)) != 0) {
            new ResetEvent().notifyResetListeners();
            Timer.getInstance().resetTimer();
            forCollision = 0;
            running = false;
            System.out.println("Collision occurred, start again");
        }
    }

    private int getObstacleBits(int obstacle) {
        if (obstacle >= 0 && obstacle < obstacleMarks.length) {
            return obstacleMarks[obstacle];
        } else {
            return nothing;
        }
    }


    private void increment() {
        if (forIncrement != 0) {
            new PlusOneEvent().notifyPlusOneListeners();
            forIncrement = 0;
        }
    }

    private void generateBlank() {
        for (int i = 1; i < 6; i++) {
            board[i] = board[i + 1];
        }
        board[6] = 0;
    }

    public void start() {
        System.out.println("Game started");
        board = new int[7];
        board[0] = 1;
        tickCounter = -1;
    }

    public int[] getArr() {
        return board;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            System.out.println("A pressed");
            moveLeft();
        } else if (key == KeyEvent.VK_D) {
            System.out.println("D pressed");
            moveRight();
        } else if (key == KeyEvent.VK_S && !running) {
            System.out.println("S pressed");
            new StartEvent().notifyStartEventListeners();
            start();
            running = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // no need to use
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // no need to use
    }
}