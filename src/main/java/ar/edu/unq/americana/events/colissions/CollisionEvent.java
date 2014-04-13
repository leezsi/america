package ar.edu.unq.americana.events.colissions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;

public abstract class CollisionEvent {
	protected GameComponent<?> component;
	protected Method method;

	public static List<? extends CollisionEvent> getEvents(
			final GameComponent<?> component) {
		final List<CollisionEvent> result = new ArrayList<CollisionEvent>();
		result.addAll(ByRuleEvent.getEvents(component));
		return result;
	}

	protected CollisionEvent(final GameComponent<?> component,
			final Method method) {
		this.component = component;
		this.method = method;
	}

	public void apply(final GameComponent<?> component,
			final List<GameComponent<?>> all) {
		this.doApply(component, this.filter(component, all));
	}

	protected abstract void doApply(GameComponent<?> component,
			List<GameComponent<?>> others);

	public GameComponent<?> getComponent() {
		return component;
	}

	public abstract List<GameComponent<?>> filter(GameComponent<?> self,
			List<GameComponent<?>> all);

}
