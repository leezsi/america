package ar.edu.unq.americana.events.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.edu.unq.americana.constants.Key;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface KeyboarEvents {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface Press {
		Key value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface Release {
		Key value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface BeingHold {
		Key value();
	}
}
