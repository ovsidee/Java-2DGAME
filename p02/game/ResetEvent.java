package p02.game;

import java.util.ArrayList;
import java.util.List;

public class ResetEvent {
    public ResetEvent() {}

    public interface ResetEventListener {
        void resetEventExecutes();
    }

    private static List<ResetEventListener> listeners = new ArrayList<ResetEventListener>();

    public static void addResetEventListener(ResetEventListener listener) {
        listeners.add(listener);
    }

    public void notifyResetListeners() {
        for (ResetEventListener listener : listeners) {
            listener.resetEventExecutes();
        }
    }
}