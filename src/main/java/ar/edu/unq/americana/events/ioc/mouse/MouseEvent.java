package ar.edu.unq.americana.events.ioc.mouse;

import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.ioc.IOEvent;

public class MouseEvent extends IOEvent {

	private final MouseButton button;

	public MouseEvent(final EventType type, final MouseButton button) {
		super(type);
		this.button = button;
	}

	protected MouseButton getButton() {
		return button;
	}

}
