package ar.edu.unq.americana.events.ioc.collision;

import java.lang.reflect.Method;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.annotations.Events.ColitionCheck.ForType;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class CollisionCheckForTypeEventHandler implements
		Handler<CollisionCheckForTypeEvent> {

	private Object target;
	private Method method;
	private CollisionStrategy collisionStrategy;
	@SuppressWarnings("rawtypes")
	private Class<? extends GameComponent> type;

	@Override
	public Handler<CollisionCheckForTypeEvent> copy() {
		return new CollisionCheckForTypeEventHandler().fill(this.target,
				this.method);
	}

	@Override
	public Handler<CollisionCheckForTypeEvent> fill(final Object target,
			final Method method) {
		this.target = target;
		this.method = method;
		final ForType annotation = method
				.getAnnotation(Events.ColitionCheck.ForType.class);
		this.type = annotation.type();
		this.collisionStrategy = annotation.collisionStrategy();
		return this;
	}

	@Override
	public Class<CollisionCheckForTypeEvent> getEventType() {
		return CollisionCheckForTypeEvent.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void executeOn(final DeltaState deltaState) {
		final GameComponent<?> component = (GameComponent<?>) this.target;
		final List<? extends GameComponent> targets = ComponentUtils
				.filter(component.getScene().getCollisionableComponents())
				.byClass(this.type).get();
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
