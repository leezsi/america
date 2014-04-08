package ar.edu.unq.americana.colissions.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.edu.unq.americana.GameComponent;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface CollitionChecker {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Against {
		Class<? extends GameComponent<?>> target();

		String fire();
	}

}
