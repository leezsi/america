package ar.edu.unq.americana.events.keyboard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.events.annotations.Events.Keyboard.Release;

public class KeyReleaseEvent extends KeyboardEvent {

	public KeyReleaseEvent(final GameComponent<?> component,
			final Method method, final Key key) {
		super(component, method, key);
	}

	@SuppressWarnings("rawtypes")
	public static List<? extends KeyboardEvent> getEvents(
			final GameComponent<?> component) {
		final Class<? extends GameComponent> clazz = component.getClass();
		final List<KeyboardEvent> result = new ArrayList<KeyboardEvent>();
		final Method[] mehtods = ComponentUtils.filterMethodsByAnnotation(
				clazz, Release.class);
		for (final Method method : mehtods) {
			final Key key = method.getAnnotation(Release.class).value();
			result.add(new KeyReleaseEvent(component, method, key));
		}
		return result;
	}

	@Override
	protected boolean validate(final DeltaState state) {
		return state.isKeyReleased(expected);
	}

}
