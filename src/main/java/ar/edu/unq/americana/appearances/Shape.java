package ar.edu.unq.americana.appearances;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.utils.Vector2D;

public abstract class Shape implements Appearance {

	private GameComponent<?> component;
	public Vector2D offset;

	public void setComponent(final GameComponent<?> component) {
		this.component = component;
		this.changeOffset(-1, this.getWidth() / 2, -1, this.getHeight() / 2);
	}

	private Vector2D componentVector() {
		return new Vector2D(this.component.getX(), this.component.getY());
	}

	public void changeOffset(final double dx, final double x, final double dy,
			final double y) {
		final Vector2D xOffset = new Vector2D(dx, 0).asVersor().producto(x);
		final Vector2D yOffset = new Vector2D(0, dy).asVersor().producto(y);
		this.offset = xOffset.suma(yOffset);
	}

	@Override
	public double getX() {
		return this.componentVector().suma(this.offset).getX();
	}

	@Override
	public double getY() {
		return this.componentVector().suma(this.offset).getY();
	}

	protected GameComponent<?> getComponent() {
		return this.component;
	}

}
package ar.edu.unq.americana.appearances;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.utils.Vector2D;

public abstract class Shape implements Appearance {

	private GameComponent<?> component;
	public Vector2D offset;

	public void setComponent(final GameComponent<?> component) {
		this.component = component;
		this.changeOffset(-1, this.getWidth() / 2, -1, this.getHeight() / 2);
	}

	private Vector2D componentVector() {
		return new Vector2D(component.getX(), component.getY());
	}

	public void changeOffset(final double dx, final double x, final double dy,
			final double y) {
		final Vector2D xOffset = new Vector2D(dx, 0).asVersor().producto(x);
		final Vector2D yOffset = new Vector2D(0, dy).asVersor().producto(y);
		offset = xOffset.suma(yOffset);
	}

	@Override
	public double getX() {
		return componentVector().suma(offset).getX();
	}

	@Override
	public double getY() {
		return componentVector().suma(offset).getY();
	}

	protected GameComponent<?> getComponent() {
		return component;
	}

}
