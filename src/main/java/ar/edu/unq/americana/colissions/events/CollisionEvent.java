package ar.edu.unq.americana.colissions.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;

public abstract class CollisionEvent {
	protected GameComponent<?> component;
	protected Method method;
	protected Class<? extends GameComponent<?>> target;

	public static List<? extends CollisionEvent> getEvents(
			final GameComponent<?> component) {
		final List<CollisionEvent> result = new ArrayList<CollisionEvent>();
		result.addAll(CollisionAgaintsCheckerEvent.getEvents(component));
		return result;
	}

	protected CollisionEvent(final GameComponent<?> component,
			final Method method, final Class<? extends GameComponent<?>> target) {
		this.component = component;
		this.method = method;
		this.target = target;
	}

	public GameComponent<?> getComponent() {
		return component;
	}

	public abstract void apply(List<GameComponent<?>> allComponents);
}
