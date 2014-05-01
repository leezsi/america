package ar.edu.unq.americana.events.ioc.keyboard;

import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.ioc.IOEvent;

public class KeyboarEvent extends IOEvent {

	private final Key key;

	public KeyboarEvent(final EventType type, final Key key) {
		super(type);
		this.key = key;
	}

	protected Key getKey() {
		return key;
	}

}
