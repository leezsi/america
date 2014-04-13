package ar.edu.unq.americana.events.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.edu.unq.americana.colissions.ICollisionRule;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.game.GameEvent;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface Events {

	// Collision

	public @interface Collision {

		@Retention(RetentionPolicy.RUNTIME)
		@Target(value = { ElementType.METHOD })
		public @interface ByRule {
			Class<? extends ICollisionRule<?, ?>> value();
		}

	}

	// keyboard
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface Keyboard {

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

	// mouse

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface Mouse {

		@Retention(RetentionPolicy.RUNTIME)
		@Target(value = ElementType.METHOD)
		public @interface In {

		}

		@Retention(RetentionPolicy.RUNTIME)
		@Target(value = ElementType.METHOD)
		public @interface Out {

		}

		@Retention(RetentionPolicy.RUNTIME)
		@Target(value = ElementType.METHOD)
		public @interface Move {

		}

		@Retention(RetentionPolicy.RUNTIME)
		@Target(value = ElementType.METHOD)
		public @interface Press {
			MouseButton value();
		}

		@Retention(RetentionPolicy.RUNTIME)
		@Target(value = ElementType.METHOD)
		public @interface Release {
			MouseButton value();
		}

		@Retention(RetentionPolicy.RUNTIME)
		@Target(value = ElementType.METHOD)
		public @interface BeingHold {
			MouseButton value();
		}
	}

	// update
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface Update {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.METHOD })
	public @interface Fired {
		Class<? extends GameEvent> value();
	}

}
