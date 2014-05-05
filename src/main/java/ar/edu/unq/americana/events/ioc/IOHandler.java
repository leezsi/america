package ar.edu.unq.americana.events.ioc;

import java.lang.reflect.Method;

import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public abstract class IOHandler<T extends IOEvent> implements Handler<T> {

	private EventType type;

	protected void setType(final EventType type) {
		this.type = type;
	}

	private Method method;
	private Object target;

	@Override
	public Object getTarget() {
		return target;
	}

	protected void setTarget(final Object target) {
		this.target = target;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(final Method method) {
		this.method = method;
	}

	public EventType getÈventType() {
		return type;
	}

	@Override
	public void execute(final FiredEvent event) {
		// dummy

	}

}
