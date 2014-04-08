package ar.edu.unq.americana.events.mouse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.MouseEvents;

public class MouseReleaseEvent extends MouseEvent {

	@SuppressWarnings("rawtypes")
	static public List<MouseEvent> getEvents(final GameComponent<?> component) {
		final Class<? extends GameComponent> clazz = component.getClass();
		final List<MouseEvent> result = new ArrayList<MouseEvent>();
		final Method[] mehtods = ComponentUtils.filterMethodsByAnnotation(
				clazz, MouseEvents.Release.class);
		for (final Method method : mehtods) {
			final MouseButton button = method.getAnnotation(
					MouseEvents.Release.class).value();
			result.add(new MouseReleaseEvent(component, method, button));
		}
		return result;

	}

	public MouseReleaseEvent(final GameComponent<?> component,
			final Method method, final MouseButton button) {
		super(component, method, button);
	}

	@Override
	public void apply(final DeltaState state) {
		if (state.isMouseButtonReleased(button)) {
			if (button.equals(MouseButton.ANY)) {
				ComponentUtils.invoke(component, method, state, button);
			} else {
				ComponentUtils.invoke(component, method, state);
			}
		}
	}

}
