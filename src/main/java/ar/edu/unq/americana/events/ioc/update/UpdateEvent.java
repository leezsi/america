package ar.edu.unq.americana.events.ioc.update;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.ioc.Event;
import ar.edu.unq.americana.events.ioc.Handler;

public class UpdateEvent extends Event<Handler<? extends UpdateEvent>> {

	@Override
	public void applyOn(final Handler<? extends UpdateEvent> handler) {
		// dummy
	}

	@Override
	public void applyOn(final Handler<? extends UpdateEvent> handler,
			final DeltaState deltaState) {
		handler.executeOn(deltaState);
	}

}
