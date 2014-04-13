package ar.edu.unq.americana.events.game;

import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;

public abstract class IOEvent<T> {
	protected GameComponent<?> component;
	protected Method method;
	protected T expected;

	protected IOEvent(final GameComponent<?> component, final Method method,
			final T expected) {
		this.component = component;
		this.method = method;
		this.expected = expected;
	}

	public void apply(final DeltaState state) {
		if (validate(state)) {
			if (checkANY()) {
				ComponentUtils.invoke(component, method, state, expected);
			} else {
				ComponentUtils.invoke(component, method, state);
			}
		}
	}

	protected abstract boolean checkANY();

	protected abstract boolean validate(final DeltaState state);

	public GameComponent<?> getComponent() {
		return component;
	}

}
