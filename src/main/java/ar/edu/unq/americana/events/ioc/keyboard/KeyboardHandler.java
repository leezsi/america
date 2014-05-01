package ar.edu.unq.americana.events.ioc.keyboard;

import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.annotations.Events.Keyboard;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.events.ioc.IOHandler;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class KeyboardHandler extends IOHandler<KeyboarEvent> {

	private Key key;

	public Key getKey() {
		return key;
	}

	@Override
	public Handler<KeyboarEvent> copy() {
		return new KeyboardHandler().fill(this.getTarget(), getMethod());
	}

	@Override
	public KeyboardHandler fill(final Object target, final Method method) {
		this.setMethod(method);
		this.setTarget(target);
		final Keyboard annotation = method.getAnnotation(Events.Keyboard.class);
		key = annotation.key();
		this.setType(annotation.type());
		return this;
	}

	@Override
	public void executeOn(final DeltaState deltaState) {
		if (this.getÈventType().mustExecute(this.getKey(), deltaState)) {
			if (this.getKey().equals(Key.ANY)) {
				ReflectionUtils.invoke(this.getTarget(), this.getMethod(),
						deltaState, this.getKey());
			} else {
				ReflectionUtils.invoke(this.getTarget(), this.getMethod(),
						deltaState);
			}
		}
	}

	@Override
	public Class<KeyboarEvent> getEventType() {
		return KeyboarEvent.class;
	}

}
