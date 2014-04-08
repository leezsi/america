package ar.edu.unq.americana.events.keyboard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.constants.Key;

abstract public class KeybordEvent {

	protected final GameComponent<?> component;
	protected Method method;
	protected Key key;

	static public List<KeybordEvent> getAll(final GameComponent<?> component) {
		final List<KeybordEvent> events = new ArrayList<KeybordEvent>();
		events.addAll(KeyPressEvent.getEvents(component));
		events.addAll(KeyReleaseEvent.getEvents(component));
		events.addAll(KeyHoldEvent.getEvents(component));
		return events;
	}

	protected KeybordEvent(final GameComponent<?> component,
			final Method method, final Key key) {
		this.component = component;
		this.method = method;
		this.key = key;
	}

	public abstract void apply(DeltaState state);

	public GameComponent<?> getComponent() {
		return component;
	}

}
