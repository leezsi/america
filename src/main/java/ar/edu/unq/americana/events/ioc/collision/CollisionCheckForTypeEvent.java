package ar.edu.unq.americana.events.ioc.collision;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.ioc.Event;

public class CollisionCheckForTypeEvent extends Event<CollisionCheckForTypeEventHandler> {

	@Override
	public void applyOn(final CollisionCheckForTypeEventHandler handler) {
	}

	@Override
	public void applyOn(final CollisionCheckForTypeEventHandler handler,
			final DeltaState deltaState) {
		handler.executeOn(deltaState);
	}

}
