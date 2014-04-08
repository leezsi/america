package ar.edu.unq.americana.events.game;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface GameEvents {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Fired {
		Class<? extends GameEvent> type();
	}
}
