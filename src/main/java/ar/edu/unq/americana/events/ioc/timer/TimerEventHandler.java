package ar.edu.unq.americana.events.ioc.timer;

import java.lang.reflect.Method;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.ioc.Handler;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class TimerEventHandler implements Handler<TimerEvent> {

	private Method method;
	private Object target;

	@Override
	public Handler<TimerEvent> copy() {
		return new TimerEventHandler().fill(this.target, this.method);
	}

	@Override
	public Handler<TimerEvent> fill(final Object target, final Method method) {
		this.target = target;
		this.method = method;
		return this;
	}

	@Override
	public Class<TimerEvent> getEventType() {
		return TimerEvent.class;
	}

	@Override
	public void executeOn(final DeltaState deltaState) {
		ReflectionUtils.invoke(this.target, this.method);
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
