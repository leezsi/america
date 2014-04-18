package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

public class CompoundShape extends Shape {

	private final Shape[] shapes;

	public CompoundShape(final Shape... shapes) {
		this.shapes = shapes;
	}

	@Override
	public void setComponent(final GameComponent<?> component) {
		for (final Shape shape : shapes) {
			shape.setComponent(component);
		}
	}

	@Override
	public double getWidth() {
		double max = 0;
		for (final Shape shape : shapes) {
			max += (shape.getWidth() > max) ? shape.getWidth() : 0;
		}
		return max;
	}

	@Override
	public double getHeight() {
		double max = 0;
		for (final Shape shape : shapes) {
			max += (shape.getHeight() > max) ? shape.getHeight() : 0;
		}
		return max;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompoundShape copy() {
		return new CompoundShape(shapes);
	}

	@Override
	public void update(final double delta) {
		for (final Shape shape : shapes) {
			shape.update(delta);
		}
	}

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {
		for (final Shape shape : shapes) {
			shape.render(component, graphics);
		}
	}

}
