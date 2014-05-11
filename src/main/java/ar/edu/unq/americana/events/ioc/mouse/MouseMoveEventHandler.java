package ar.edu.unq.americana.events.ioc.mouse;

import java.awt.geom.Point2D.Double;
import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.events.annotations.Events.Mouse;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.events.ioc.IOHandler;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class MouseMoveEventHandler extends IOHandler<MouseMoveEvent> {

	private boolean oldAnNew;

	@Override
	public Handler<MouseMoveEvent> copy() {
		return new MouseMoveEventHandler().fill(this.getTarget(),
				this.getMethod());
	}

	@Override
	public Handler<MouseMoveEvent> fill(final Object target, final Method method) {
		this.setMethod(method);
		this.setTarget(target);
		this.oldAnNew = method.getAnnotation(Mouse.Move.class).oldAndNew();
		return this;
	}

	@Override
	public Class<MouseMoveEvent> getEventType() {
		return MouseMoveEvent.class;
	}

	@Override
	public void executeOn(final DeltaState deltaState) {
		if (((GameComponent<?>) this.getTarget()).getGame() != null) {

			final Double current = deltaState.getCurrentMousePosition();
			if (this.oldAnNew) {
				final Double old = deltaState.getLastMousePosition();
				ReflectionUtils.invoke(this.getTarget(), this.getMethod(),
						old.x, old.y, current.x, current.y);
			} else {
				ReflectionUtils.invoke(this.getTarget(), this.getMethod(),
						current.x, current.y);
			}
		}
	}

}
