package ar.edu.unq.americana.events.keyboard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.events.annotations.Events.Keyboard.Press;

public class KeyPressEvent extends KeyboardEvent {

	public KeyPressEvent(final GameComponent<?> component, final Method method,
			final Key key) {
		super(component, method, key);
	}

	@SuppressWarnings("rawtypes")
	public static List<KeyboardEvent> getEvents(final GameComponent<?> component) {
		final Class<? extends GameComponent> clazz = component.getClass();
		final List<KeyboardEvent> result = new ArrayList<KeyboardEvent>();
		final Method[] mehtods = ComponentUtils.filterMethodsByAnnotation(
				clazz, Press.class);
		for (final Method method : mehtods) {
			final Key key = method.getAnnotation(Press.class).value();
			result.add(new KeyPressEvent(component, method, key));
		}
		return result;
	}

	@Override
	protected boolean validate(final DeltaState state) {
		return state.isKeyPressed(expected);
	}

}
