package ar.edu.unq.americana.events.ioc.collision;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.ioc.Event;

public class CollisionCheckForGroupEvent extends
		Event<CollisionCheckForGroupEventHandler> {

	@Override
	public void applyOn(final CollisionCheckForGroupEventHandler handler) {
	}

	@Override
	public void applyOn(final CollisionCheckForGroupEventHandler handler,
			final DeltaState deltaState) {
		handler.executeOn(deltaState);
	}

}
