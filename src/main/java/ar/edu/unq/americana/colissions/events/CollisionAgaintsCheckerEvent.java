package ar.edu.unq.americana.colissions.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.colissions.annotations.CollitionChecker;
import ar.edu.unq.americana.components.utils.ComponentUtils;

public class CollisionAgaintsCheckerEvent extends CollisionEvent {
	protected CollisionAgaintsCheckerEvent(final GameComponent<?> component,
			final Method method, final Class<? extends GameComponent<?>> target) {
		super(component, method, target);
	}

	@SuppressWarnings("rawtypes")
	public static List<? extends CollisionEvent> getEvents(
			final GameComponent<?> component) {
		final Class<? extends GameComponent> clazz = component.getClass();
		final List<CollisionEvent> result = new ArrayList<CollisionEvent>();
		final Method[] mehtods = ComponentUtils.filterMethodsByAnnotation(
				clazz, CollitionChecker.Against.class);
		for (final Method method : mehtods) {
			final Class<? extends GameComponent<?>> target = method
					.getAnnotation(CollitionChecker.Against.class).target();
			result.add(new CollisionAgaintsCheckerEvent(component, method,
					target));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void apply(final List<GameComponent<?>> allComponents) {
		for (final GameComponent<?> current : allComponents) {
			if (target.isAssignableFrom(current.getClass())) {
				if (ComponentUtils.invoke(component, method, current)) {
					final String methodName = method.getAnnotation(
							CollitionChecker.Against.class).fire();
					final Method fireMethod = ComponentUtils.getMethod(
							component, methodName, current.getClass());
					ComponentUtils.invoke(component, fireMethod, current);
				}
				;
			}
		}
	}
}
