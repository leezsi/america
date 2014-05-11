package ar.edu.unq.americana.events.ioc.collision;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.colissions.CollisionDetector;

public enum CollisionStrategy {
	PerfectPixel;

	public boolean isCollision(final GameComponent<?> component1,
			final GameComponent<?> component2) {
		return CollisionDetector.perfectPixel(component1, component2);
	}
}
