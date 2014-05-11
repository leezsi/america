package ar.edu.unq.americana.components.rules;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.colissions.CollisionDetector;
import ar.edu.unq.americana.rules.IRule;

public abstract class PerfectPixelMouseCollisionRule<T extends GameComponent<?>>
		implements IRule<T, GameScene> {

	@Override
	public boolean mustApply(final T component, final GameScene scene) {
		return CollisionDetector.perfectPixel(component, scene.getMouse());
	}

	@Override
	public void apply(final T component, final GameScene scene) {
		this.callback(component);

	}

	protected abstract void callback(T component);

}
