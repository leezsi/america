package ar.edu.unq.americana.events.ioc.fired;

import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class FiredEventHandler<E extends FiredEvent> implements Handler<E> {

	private Object target;
	private Method method;
	private Class<E> eventType;

	@Override
	public Handler<E> copy() {
		return new FiredEventHandler<E>().fill(target, method);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Handler<E> fill(final Object target, final Method method) {
		this.target = target;
		this.method = method;
		eventType = (Class<E>) method.getAnnotation(Events.Fired.class).value();
		return this;
	}

	@Override
	public Class<E> getEventType() {
		return this.eventType;
	}

	@Override
	public void executeOn(final DeltaState deltaState) {
		// dummy
	}

	@Override
	public Object getTarget() {
		return target;
	}

	@Override
	public void execute(final FiredEvent event) {
		ReflectionUtils.invoke(target, method, event);
	}

}
