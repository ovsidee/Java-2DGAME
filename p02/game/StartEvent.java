package p02.game;

import java.util.ArrayList;
import java.util.List;

public class StartEvent{
    private static List<StartEventListener> listeners = new ArrayList<StartEventListener>();

    public StartEvent() {
        Timer.getInstance().startTimer();
    }

    public interface StartEventListener {
        void startEventExecutes();
    }

    public static void addStartEventListener(StartEventListener listener) {
        listeners.add(listener);
    }

    public void notifyStartEventListeners() {
        for (StartEventListener listener : listeners) {
            listener.startEventExecutes();
        }
    }
}