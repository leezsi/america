package ar.edu.unq.americana.events.mouse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.Events.Mouse.Release;

public class MouseReleaseEvent extends MouseEvent {

	@SuppressWarnings("rawtypes")
	static public Collection<? extends MouseEvent> getEvents(
			final GameComponent<?> component) {
		final Class<? extends GameComponent> clazz = component.getClass();
		final List<MouseEvent> result = new ArrayList<MouseEvent>();
		final Method[] mehtods = ComponentUtils.filterMethodsByAnnotation(
				clazz, Release.class);
		for (final Method method : mehtods) {
			final MouseButton button = method.getAnnotation(Release.class)
					.value();
			result.add(new MouseReleaseEvent(component, method, button));
		}
		return result;

	}

	public MouseReleaseEvent(final GameComponent<?> component,
			final Method method, final MouseButton button) {
		super(component, method, button);
	}

	@Override
	protected boolean validate(final DeltaState state) {
		return state.isMouseButtonReleased(expected);
	}

}
