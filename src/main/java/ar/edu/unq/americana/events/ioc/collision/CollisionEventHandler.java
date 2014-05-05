package ar.edu.unq.americana.events.ioc.collision;

import java.lang.reflect.Method;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.events.ioc.fired.FiredEventHandler;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class CollisionEventHandler extends FiredEventHandler<CollisionEvent> {
	private Class<? extends GameComponent<?>> collisionTarget;

	@Override
	public Handler<CollisionEvent> fill(final Object target, final Method method) {
		this.setTarget(target);
		this.setMethod(method);
		this.collisionTarget = method.getAnnotation(Events.Collision.class)
				.target();
		return this;
	}

	@Override
	public Class<CollisionEvent> getEventType() {
		return CollisionEvent.class;
	}

	@Override
	public void execute(final FiredEvent event) {
		final CollisionEvent casted = (CollisionEvent) event;
		final GameComponent<?> collidedObject = casted.getCollidedObject();
		final GameComponent<?> targetObject = casted.getTarget();
		if ((targetObject == this.getTarget())
				&& (collidedObject.getClass() == this.collisionTarget)) {
			ReflectionUtils.invoke(this.getTarget(), this.getMethod(),
					collidedObject);
		}
	}
}
