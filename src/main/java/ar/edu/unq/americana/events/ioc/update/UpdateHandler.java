package ar.edu.unq.americana.events.ioc.update;

import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class UpdateHandler implements Handler<UpdateEvent> {

	private Method method;
	private Object target;

	@Override
	public Handler<UpdateEvent> copy() {
		return new UpdateHandler().fill(target, method);
	}

	@Override
	public Handler<UpdateEvent> fill(final Object target, final Method method) {
		this.method = method;
		this.target = target;
		return this;
	}

	@Override
	public void executeOn(final DeltaState deltaState) {
		ReflectionUtils.invoke(this.getComponent(), method,
				deltaState.getDelta());
	}

	private Object getComponent() {
		return target;
	}

	@Override
	public void execute(final FiredEvent event) {
		// dummy

	}

	@Override
	public Class<UpdateEvent> getEventType() {
		return UpdateEvent.class;
	}

	@Override
	public Object getTarget() {
		return target;
	}

}
