package ar.edu.unq.americana.events.ioc.collision;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.colissions.CollisionDetector;

public enum CollisionStrategy {
	PerfectPixel {
		@Override
		public boolean isCollision(final GameComponent<?> component1,
				final GameComponent<?> component2) {
			return CollisionDetector.perfectPixel(component1, component2);
		}
	},
	FromBounds {

		@Override
		public boolean isCollision(final GameComponent<?> component1,
				final GameComponent<?> component2) {
			return CollisionDetector.fromBoxes(component1, component2);
		}

	};

	public abstract boolean isCollision(final GameComponent<?> component1,
			final GameComponent<?> component2);
}
