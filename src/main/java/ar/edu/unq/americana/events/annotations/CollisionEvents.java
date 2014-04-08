package ar.edu.unq.americana.events.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.edu.unq.americana.GameComponent;

@Target(value = { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CollisionEvents {

	@Target(value = { ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface IfCollide {
		Class<? extends GameComponent<?>> value();
	}

	@Target(value = { ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface IfNotCollide {
		Class<? extends GameComponent<?>> value();
	}

}
