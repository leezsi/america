package ar.edu.unq.americana.events.ioc;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.annotations.EventType;

public abstract class IOEvent extends Event<IOHandler<IOEvent>> {

	private final EventType type;

	@Override
	public void applyOn(final IOHandler<IOEvent> handler) {
		// dummy
	}

	@Override
	public void applyOn(final IOHandler<IOEvent> handler,
			final DeltaState deltaState) {
		if (type.equals(handler.getÈventType())) {
			handler.executeOn(deltaState);
		}
	}

	public IOEvent(final EventType type) {
		this.type = type;
	}

	public EventType getÈventType() {
		return type;
	}

}
