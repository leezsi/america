package ar.edu.unq.americana.events.game;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.events.annotations.Events.Update;

public class UpdateEvent {

	protected GameComponent<?> component;
	protected Method method;

	@SuppressWarnings("unchecked")
	public static List<? extends UpdateEvent> getEvents(
			final GameComponent<?> component) {
		final List<UpdateEvent> result = new ArrayList<UpdateEvent>();
		final Class<? extends GameComponent<?>> clazz = (Class<? extends GameComponent<?>>) component
				.getClass();
		final Method[] methods = ComponentUtils.filterMethodsByAnnotation(
				clazz, Update.class);
		for (final Method method : methods) {
			result.add(new UpdateEvent(component, method));
		}
		return result;
	}

	public UpdateEvent(final GameComponent<?> component, final Method method) {
		this.component = component;
		this.method = method;
	}

	public void execute(final DeltaState state) {
		ComponentUtils.invoke(component, method, state.getDelta());
	}

}
