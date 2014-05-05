package ar.edu.unq.americana.events.ioc.fired;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.ioc.Event;

public class FiredEvent extends Event<FiredEventHandler<FiredEvent>> {

	@Override
	public void applyOn(final FiredEventHandler<FiredEvent> handler) {
		handler.execute(this);
	}

	@Override
	public void applyOn(final FiredEventHandler<FiredEvent> handler,
			final DeltaState deltaState) {
		// dummy

	}

}
