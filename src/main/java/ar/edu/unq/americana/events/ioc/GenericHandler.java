package ar.edu.unq.americana.events.ioc;

import java.lang.reflect.Method;

public abstract class GenericHandler<T extends Event<?>> implements Handler<T> {

	public Method getMethod() {
		return method;
	}

	public void setMethod(final Method method) {
		this.method = method;
	}

	private Method method;

}
