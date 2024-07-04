package p02.game;

import java.util.ArrayList;
import java.util.List;

public class PlusOneEvent {
    private static List<PlusOneEventListener> listeners = new ArrayList<PlusOneEventListener>();

    public PlusOneEvent() {}

    public interface PlusOneEventListener {
        void plusOneEventExecutes();
    }

    public static void addPlusOneEventListener(PlusOneEventListener listener) {
        listeners.add(listener);
    }

    public void notifyPlusOneListeners() {
        for (PlusOneEventListener listener : listeners) {
            listener.plusOneEventExecutes();
        }
    }
}