package ar.edu.unq.americana.components.rules;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.colissions.CollisionDetector;
import ar.edu.unq.americana.rules.IRule;
import ar.edu.unq.americana.scenes.normal.DefaultScene;

public abstract class PerfectPixelMouseCollisionRule<T extends GameComponent<?>>
		implements IRule<T, DefaultScene> {

	@Override
	public boolean mustApply(final T component, final DefaultScene scene) {
		return CollisionDetector.perfectPixel(component, scene.getMouse());
	}

	@Override
	public void apply(final T component, final DefaultScene scene) {
		this.callback(component);

	}

	protected abstract void callback(T component);

}
