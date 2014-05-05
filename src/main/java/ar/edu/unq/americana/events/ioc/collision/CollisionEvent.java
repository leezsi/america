package ar.edu.unq.americana.events.ioc.collision;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public class CollisionEvent extends FiredEvent {

	private final GameComponent<?> collided;
	private final GameComponent<?> target;

	public CollisionEvent(final GameComponent<?> target,
			final GameComponent<?> collided) {
		this.collided = collided;
		this.target = target;
	}

	protected GameComponent<?> getTarget() {
		return this.target;
	}

	public GameComponent<?> getCollidedObject() {
		return this.collided;
	}

}
