package ar.edu.unq.americana.events.mouse;

import java.lang.reflect.Method;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.game.IOEvent;

public abstract class MouseEvent extends IOEvent<MouseButton> {

	protected MouseEvent(final GameComponent<?> component, final Method method,
			final MouseButton expected) {
		super(component, method, expected);
	}

	@Override
	protected boolean checkANY() {
		return expected.equals(MouseButton.ANY);
	}

}
