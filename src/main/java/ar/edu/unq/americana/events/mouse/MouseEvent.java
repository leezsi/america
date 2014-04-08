package ar.edu.unq.americana.events.mouse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.constants.MouseButton;

abstract public class MouseEvent {

	protected final GameComponent<?> component;
	protected MouseButton button;
	protected Method method;

	static public List<MouseEvent> getAll(final GameComponent<?> component) {
		final List<MouseEvent> events = new ArrayList<MouseEvent>();
		events.addAll(MousePressEvent.getEvents(component));
		events.addAll(MouseReleaseEvent.getEvents(component));
		events.addAll(MouseHoldEvent.getEvents(component));
		return events;
	}

	public MouseEvent(final GameComponent<?> component, final Method method,
			final MouseButton button) {
		this.component = component;
		this.method = method;
		this.button = button;
	}

	public abstract void apply(DeltaState state);

	public GameComponent<?> getComponent() {
		return component;
	}

}
