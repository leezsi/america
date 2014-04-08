package ar.edu.unq.americana.events.game;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.events.game.GameEvents.Fired;

public class EventManager {

	private static EventManager INSTANCE = new EventManager();
	private List<EventHandler> events = new ArrayList<EventHandler>();

	private EventManager() {
	}

	public static EventManager get() {
		return INSTANCE;
	}

	public void fireEvent(final GameEvent event) {
		event.fire(events);
	}

	public void registerComponentEvents(final GameComponent<?> component) {
		final Method[] methods = ComponentUtils.filterMethodsByAnnotation(
				component.getClass(), GameEvents.Fired.class);
		for (final Method method : methods) {
			final Fired annotation = method
					.getAnnotation(GameEvents.Fired.class);
			final Class<? extends GameEvent> type = annotation.type();
			events.add(new EventHandler(component, type, method));
		}
	}

	public void deresgister(final GameComponent<?> component) {
		for (final EventHandler handler : events) {
			if (handler.component.equals(component)) {
				events.remove(handler);
			}
		}
	}

	public void reset() {
		events = new ArrayList<EventHandler>();
	}

}
