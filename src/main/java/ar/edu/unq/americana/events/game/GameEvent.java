package ar.edu.unq.americana.events.game;

import java.util.List;

public abstract class GameEvent {

	public void fire(final List<EventHandler> events) {
		for (final EventHandler eventHandler : events) {
			if (eventHandler.manage(this)) {
				eventHandler.execute(this);
			}
		}

	}

}
