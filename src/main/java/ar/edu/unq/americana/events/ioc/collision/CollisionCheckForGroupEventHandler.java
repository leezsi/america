package ar.edu.unq.americana.events.ioc.collision;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.components.utils.ComponentUtils.ListFilter;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.annotations.Events.ColitionCheck.ForGroup;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class CollisionCheckForGroupEventHandler implements
		Handler<CollisionCheckForGroupEvent> {

	private Object target;
	private Method method;
	private CollisionStrategy collisionStrategy;
	private boolean same;
	private Class<GameComponent<?>> classFilter;
	private Class<?>[] excluded;

	@Override
	public Handler<CollisionCheckForGroupEvent> copy() {
		return new CollisionCheckForGroupEventHandler().fill(this.target,
				this.method);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Handler<CollisionCheckForGroupEvent> fill(final Object target,
			final Method method) {
		this.target = target;
		this.method = method;
		final ForGroup annotation = method
				.getAnnotation(Events.ColitionCheck.ForGroup.class);
		this.excluded = annotation.exclude();
		this.same = annotation.same();
		this.collisionStrategy = annotation.collisionStrategy();
		this.classFilter = (Class<GameComponent<?>>) method.getParameterTypes()[0];
		return this;
	}

	@Override
	public Class<CollisionCheckForGroupEvent> getEventType() {
		return CollisionCheckForGroupEvent.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void executeOn(final DeltaState deltaState) {
		final GameComponent<?> component = (GameComponent<?>) this.target;
		final ListFilter<GameComponent<?>> filter = ComponentUtils
				.filter(component.getScene().getComponents())
				.byClass(this.classFilter).removeOfType(this.excluded);

		List<? extends GameComponent> targets = new ArrayList<GameComponent>();
		if (this.same) {
			targets = filter.bySameGroup(component).get();
		} else {
			targets = filter.byDiferentGroup(component).get();
		}
		for (final GameComponent gameComponent : targets) {
			if (component.isDestroyPending()) {
				break;
			}
			if (!gameComponent.isDestroyPending()
					&& this.collisionStrategy.isCollision(component,
							gameComponent)) {
				ReflectionUtils.invoke(this.target, this.method, gameComponent);
			}
		}
	}

	@Override
	public Object getTarget() {
		return this.target;
	}

	@Override
	public void execute(final FiredEvent event) {
		// TODO Auto-generated method stub

	}

}
