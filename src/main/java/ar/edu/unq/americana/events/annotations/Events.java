package ar.edu.unq.americana.events.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface Events {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Mouse {
		MouseButton button() default MouseButton.ANY;

		EventType type();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Keyboard {
		Key key() default Key.ANY;

		EventType type();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Fired {

		Class<? extends FiredEvent> value();

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Update {

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Collision {
		Class<? extends GameComponent<?>> target();
	}
}
