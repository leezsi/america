package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

public abstract class SimpleAppearance<T extends Appearance> extends Shape
		implements Cloneable {

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	@SuppressWarnings("unchecked")
	public T copy() {
		try {
			return (T) this.clone();
		} catch (final CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	// ****************************************************************
	// ** SCALING
	// ****************************************************************

	public T scale(final double scale) {
		return this.scale(scale, scale);
	}

	public T scaleHorizontally(final double scale) {
		return this.scale(scale, 1);
	}

	public T scaleVertically(final double scale) {
		return this.scale(1, scale);
	}

	public T scaleTo(final double width, final double height) {
		final double horizontalScale = width / this.getWidth();
		final double verticalScale = height / this.getHeight();

		return this.scale(horizontalScale, verticalScale);
	}

	public T scaleHorizontallyTo(final double width,
			final boolean keepAspectRatio) {
		final double scale = width / this.getWidth();

		return this.scale(scale, keepAspectRatio ? scale : 1);
	}

	public T scaleVerticallyTo(final int height, final boolean keepAspectRatio) {
		final double scale = height / this.getHeight();

		return this.scale(keepAspectRatio ? scale : 1, scale);
	}

	public abstract T scale(double scaleX, double scaleY);

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {
		this.doRenderAt((int) this.getX(), (int) this.getY(), graphics);
	}

	public void renderAt(final int x, final int y, final Graphics2D graphics) {
		this.doRenderAt(x + (int) this.getX(), y + (int) this.getY(), graphics);
	}

	protected abstract void doRenderAt(int x, int y, Graphics2D graphics);

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

}