package ar.edu.unq.americana.appearances;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.utils.Vector2D;

public abstract class Shape implements Appearance {

	private GameComponent<?> component;

	public void setComponent(final GameComponent<?> component) {
		this.component = component;
	}

	private Vector2D componentVector() {
		return new Vector2D(component.getX(), component.getY());
	}

	@Override
	public double getX() {
		final Vector2D offset = component.getAppearanceOffset();
		return componentVector().suma(offset).getX();
	}

	@Override
	public double getY() {
		final Vector2D offset = component.getAppearanceOffset();
		return componentVector().suma(offset).getY();
	}

}
