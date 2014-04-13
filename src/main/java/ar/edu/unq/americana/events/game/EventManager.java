package ar.edu.unq.americana.events.game;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.events.annotations.Events.Fired;
import ar.edu.unq.americana.events.keyboard.KeyHoldEvent;
import ar.edu.unq.americana.events.keyboard.KeyPressEvent;
import ar.edu.unq.americana.events.keyboard.KeyReleaseEvent;
import ar.edu.unq.americana.events.keyboard.KeyboardEvent;
import ar.edu.unq.americana.events.mouse.MouseEvent;
import ar.edu.unq.americana.events.mouse.MouseHoldEvent;
import ar.edu.unq.americana.events.mouse.MousePressEvent;
import ar.edu.unq.americana.events.mouse.MouseReleaseEvent;

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
				component.getClass(), Fired.class);
		for (final Method method : methods) {
			final Fired annotation = method.getAnnotation(Fired.class);
			final Class<? extends GameEvent> type = annotation.value();
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

	public static List<? extends KeyboardEvent> getAllKeyboardEvents(
			final GameComponent<?> component) {
		final List<KeyboardEvent> all = KeyPressEvent.getEvents(component);
		all.addAll(KeyReleaseEvent.getEvents(component));
		all.addAll(KeyHoldEvent.getEvents(component));
		return all;
	}

	public static Collection<? extends MouseEvent> getAllMouseEvents(
			final GameComponent<?> component) {
		final List<MouseEvent> all = MousePressEvent.getEvents(component);
		all.addAll(MouseReleaseEvent.getEvents(component));
		all.addAll(MouseHoldEvent.getEvents(component));
		return all;
	}
}
