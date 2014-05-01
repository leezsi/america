package ar.edu.unq.americana.events.ioc;

import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public interface Handler<T extends AmericanaEvent<?>> {

	public Handler<T> copy();

	public Handler<T> fill(Object target, Method method);

	public Class<T> getEventType();

	void executeOn(DeltaState deltaState);

	public Object getTarget();

	void execute(FiredEvent event);

}
