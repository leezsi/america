package ar.edu.unq.americana.events.game;

import java.lang.reflect.Method;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;

public class EventHandler {

	protected GameComponent<?> component;
	protected Class<? extends GameEvent> type;
	protected Method method;

	public EventHandler(final GameComponent<?> component,
			final Class<? extends GameEvent> type, final Method method) {
		this.component = component;
		this.type = type;
		this.method = method;
	}

	public boolean manage(final GameEvent gameEvent) {
		return type.equals(gameEvent.getClass());
	}

	public void execute(final GameEvent event) {
		ComponentUtils.invoke(component, method, event);
	}

}
