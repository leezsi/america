package ar.edu.unq.americana.events.ioc.timer;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.ioc.Event;

public class TimerEvent extends Event<TimerEventHandler> {

	@Override
	public void applyOn(final TimerEventHandler handler) {
	}

	@Override
	public void applyOn(final TimerEventHandler handler,
			final DeltaState deltaState) {
		handler.executeOn(deltaState);
	}
}
