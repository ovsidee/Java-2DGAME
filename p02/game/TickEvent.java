package p02.game;

import java.util.ArrayList;
import java.util.List;

public class TickEvent {
    private static List<TickEventListener> listeners = new ArrayList<TickEventListener>();
    public TickEvent() {}

    public interface TickEventListener {
        void tickEventExecutes();
    }

    public static void addTickEventListener(TickEventListener listener) {
        listeners.add(listener);
    }

    public void notifyTickEventListeners() {
        for (TickEventListener listener : listeners)
            listener.tickEventExecutes();
    }
}