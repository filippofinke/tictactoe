package ch.supsi.tictactoe.gamelogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private final Map<GameEventType, List<EventListener<?>>> listeners = new HashMap<>();

    public void subscribe(GameEventType type, EventListener<?> listener) {
        List<EventListener<?>> listeners = this.listeners.computeIfAbsent(type, k -> new ArrayList<>());
        listeners.add(listener);
    }

    public <T> void notify(GameEventType type,  T data) {
        List<EventListener<?>> listeners = this.listeners.get(type);
        if (listeners == null) {
            return;
        }

        for (EventListener<?> listener : listeners) {
            ((EventListener<T>)(listener)).update(data);
        }
    }

}
