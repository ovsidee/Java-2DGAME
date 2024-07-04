package p02.game;

public class Timer extends Thread implements Runnable {
    private static Timer instance;
    private Thread thread;
    private boolean running;
    private int interval;
    private static final int gameinterval = 1000;

    private Timer() {
        this.running = false;
        this.interval = gameinterval;
    }

    public static synchronized Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(interval);
                new TickEvent().notifyTickEventListeners();
                decreaseInterval();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void decreaseInterval() {
        if (interval > 500) {
            interval -= 5;
        }
    }

    public void startTimer() {
        if (!running) {
            interval = gameinterval;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stopTimer() {
        running = false;
        thread = null;
    }

    public void resetTimer() {
        stopTimer();
        interval = gameinterval;
    }
}