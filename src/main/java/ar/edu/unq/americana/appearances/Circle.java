package ar.edu.unq.americana.appearances;

import java.awt.Color;
import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

public class Circle extends Shape {

	private final Color color;
	private final int diameter;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Circle(final Color color, final int diameter) {
		this.color = color;
		this.diameter = diameter;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.diameter;
	}

	@Override
	public double getHeight() {
		return this.diameter;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Circle copy() {
		return new Circle(this.color, this.diameter);
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(final double delta) {
	}

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {
		graphics.setColor(this.color);
		graphics.fillOval((int) this.getX(), (int) this.getY(), this.diameter,
				this.diameter);
	}

}