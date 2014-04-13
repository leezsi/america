package ar.edu.unq.americana.appearances;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.utils.Vector2D;

public abstract class Shape implements Appearance {

	private GameComponent<?> component;
	public Vector2D offset;

	public void setComponent(final GameComponent<?> component) {
		this.component = component;
		final Vector2D xOffset = new Vector2D(-1, 0).asVersor().producto(
				this.getWidth() / 2);
		final Vector2D yOffset = new Vector2D(0, -1).asVersor().producto(
				this.getHeight() / 2);
		offset = xOffset.suma(yOffset);
	}

	private Vector2D componentVector() {
		return new Vector2D(component.getX(), component.getY());
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
