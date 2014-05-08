package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.americana.GameComponent;

public class CompoundShape extends Shape {

	private final List<Shape> shapes;

	public CompoundShape(final Shape... shapes) {
		this.shapes = Arrays.asList(shapes);
	}

	@Override
	public void setComponent(final GameComponent<?> component) {
		for (final Shape shape : this.shapes) {
			shape.setComponent(component);
		}
		super.setComponent(component);
	}

	@Override
	public double getWidth() {
		double max = 0;
		for (final Shape shape : this.shapes) {
			max = Math.max(max, shape.getWidth());
		}
		return max;
	}

	@Override
	public double getHeight() {
		double max = 0;
		for (final Shape shape : this.shapes) {
			max = Math.max(max, shape.getHeight());
		}
		return max;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompoundShape copy() {
		return new CompoundShape((Shape[]) this.shapes.toArray());
	}

	@Override
	public void update(final double delta) {
		for (final Shape shape : this.shapes) {
			shape.update(delta);
		}
	}

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {
		for (final Shape shape : this.shapes) {
			shape.render(component, graphics);
		}
	}

}
