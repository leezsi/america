package ar.edu.unq.americana.appearances;

import java.awt.Color;
import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

public class Rectangle extends Shape {

	private final Color color;
	private final int width, height;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Rectangle(final Color color, final int width, final int height) {
		this.color = color;
		this.height = height;
		this.width = width;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Rectangle copy() {
		return new Rectangle(this.color, this.height, this.width);
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
		graphics.fillRect((int) this.getX(), (int) this.getY(), this.width,
				this.height);
	}

}