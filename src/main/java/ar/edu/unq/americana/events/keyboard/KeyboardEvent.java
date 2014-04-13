package ar.edu.unq.americana.events.keyboard;

import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.events.game.IOEvent;

public abstract class KeyboardEvent extends IOEvent<Key> {

	protected KeyboardEvent(final GameComponent<?> component,
			final Method method, final Key expected) {
		super(component, method, expected);
	}

	@Override
	protected abstract boolean validate(final DeltaState state);

	@Override
	protected boolean checkANY() {
		return expected.equals(Key.ANY);
	}
}
