package ar.edu.unq.americana.exceptions;

import java.lang.reflect.Method;
import java.util.Arrays;

public class InvalidMethodParameters extends RuntimeException {
	private static final long serialVersionUID = -5179820091300581573L;

	private final Method method;
	private final Class<?>[] expecteds;

	public InvalidMethodParameters(final Method method,
			final Class<?>... expecteds) {
		this.method = method;
		this.expecteds = expecteds;
	}

	@Override
	public String getMessage() {
		return "Method [" + method.getName() + "] must have ["
				+ Arrays.toString(expecteds) + "] parameters";
	}
}
