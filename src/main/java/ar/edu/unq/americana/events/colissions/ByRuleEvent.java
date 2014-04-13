package ar.edu.unq.americana.events.colissions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.colissions.ICollisionRule;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.events.annotations.Events.Collision.ByRule;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class ByRuleEvent extends CollisionEvent {

	private final ICollisionRule<GameComponent<?>, GameComponent<?>> rule;

	@SuppressWarnings("unchecked")
	protected ByRuleEvent(final GameComponent<?> component,
			final Method method, final ICollisionRule<?, ?> rule) {
		super(component, method);
		this.rule = (ICollisionRule<GameComponent<?>, GameComponent<?>>) rule;
	}

	@SuppressWarnings("unchecked")
	public static List<? extends CollisionEvent> getEvents(
			final GameComponent<?> component) {
		final List<CollisionEvent> result = new ArrayList<CollisionEvent>();
		final Class<? extends GameComponent<?>> clazz = (Class<? extends GameComponent<?>>) component
				.getClass();
		final Method[] methods = ComponentUtils.filterMethodsByAnnotation(
				clazz, ByRule.class);
		for (final Method method : methods) {
			final ByRule annotation = method.getAnnotation(ByRule.class);
			final ICollisionRule<?, ?> rule = ReflectionUtils
					.<ICollisionRule<?, ?>> newInstance(annotation.value());
			result.add(new ByRuleEvent(component, method, rule));
		}
		return result;
	}

	@Override
	public List<GameComponent<?>> filter(final GameComponent<?> self,
			final List<GameComponent<?>> all) {
		return ComponentUtils.filter(all).byClass(rule.second()).get();
	}

	@Override
	protected void doApply(final GameComponent<?> component,
			final List<GameComponent<?>> others) {
		for (final GameComponent<?> current : others) {
			if ((rule.first() == component.getClass())
					&& rule.isCollision(this.component, current)) {
				ComponentUtils.invoke(component, method, current);
			}
		}

	}

	@Override
	public String toString() {
		final String ruleName = rule.getClass().getSimpleName();
		return "ByRule(" + ruleName + ":" + method.toGenericString() + ")";
	}
}
