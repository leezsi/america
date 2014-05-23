package ar.edu.unq.americana.events.ioc.mouse;

import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.annotations.Events.Mouse;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.events.ioc.IOHandler;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class MouseHandler extends IOHandler<MouseEvent> {

	private MouseButton button;

	@Override
	public Handler<MouseEvent> copy() {
		return new MouseHandler().fill(this.getTarget(), this.getMethod());
	}

	protected MouseButton getButton() {
		return this.button;
	}

	@Override
	public void executeOn(final DeltaState deltaState) {
		if (this.getÈventType().mustExecute(this.getButton(), deltaState)) {
			if (this.button.equals(Key.ANY)) {
				ReflectionUtils.invoke(this.getTarget(), this.getMethod(),
						this.button);
			} else {
				ReflectionUtils.invoke(this.getTarget(), this.getMethod(),
						deltaState);
			}
		}
	}

	@Override
	public Handler<MouseEvent> fill(final Object target, final Method method) {
		this.setMethod(method);
		this.setTarget(target);
		final Mouse annotation = method.getAnnotation(Events.Mouse.class);
		this.button = annotation.button();
		this.setType(annotation.type());
		return this;
	}

	@Override
	public Class<MouseEvent> getEventType() {
		return MouseEvent.class;
	}

}
