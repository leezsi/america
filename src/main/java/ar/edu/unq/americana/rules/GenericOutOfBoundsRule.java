package ar.edu.unq.americana.rules;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Appearance;

public abstract class GenericOutOfBoundsRule<T extends GameComponent<?>>
		implements IRule<T, GameScene> {

	private double width;
	private double height;
	private int displayWidth;
	private int displayHeight;

	@Override
	public boolean mustApply(final T component, final GameScene scene) {
		final Appearance appearance = component.getAppearance();
		this.width = appearance.getWidth() / 2;
		this.height = appearance.getHeight() / 2;
		this.displayWidth = component.getGame().getDisplayWidth();
		this.displayHeight = component.getGame().getDisplayHeight();
		return this.mustApply(component, this.width, this.height,
				this.displayWidth, this.displayHeight);
	}

	protected abstract boolean mustApply(T component, double width,
			double height, int displayWidth, int displayHeight);

	@Override
	public void apply(final T component, final GameScene scene) {
		this.apply(component, this.width, this.height, this.displayWidth,
				this.displayHeight);
	}

	protected abstract void apply(T component, double width, double height,
			int displayWidth, int displayHeight);
}
