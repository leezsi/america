package ar.edu.unq.americana.colissions;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.colissions.events.CollisionEvent;

public class CollisionManager {

	static private final CollisionManager INSTANCE = new CollisionManager();
	private List<CollisionEvent> events = new ArrayList<CollisionEvent>();

	private CollisionManager() {
	}

	static public CollisionManager get() {
		return INSTANCE;
	}

	public void registerComponentEvents(final GameComponent<?> component) {
		events.addAll(CollisionEvent.getEvents(component));

	}

	public void deresgister(final GameComponent<?> component) {
		for (final CollisionEvent event : events) {
			if (event.getComponent() == component) {
				events.remove(component);
			}
		}
	}

	public void privateUpdate(final List<GameComponent<?>> allComponents) {
		for (final CollisionEvent event : events) {
			final List<GameComponent<?>> currents = new ArrayList<GameComponent<?>>();
			currents.addAll(allComponents);
			currents.remove(event.getComponent());
			event.apply(currents);
		}
	}

	public void reset() {
		events = new ArrayList<CollisionEvent>();
	}
}
