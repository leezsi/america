package ar.edu.unq.americana.appearances;

import java.awt.Color;
import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

public class FilledArc extends Shape {

	private final Color color;
	private final int radius;
	private final double startAngle;
	private final double arcAngle;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public FilledArc(final Color color, final int radius,
			final double startAngle, final double arcAngle) {
		this.color = color;
		this.radius = radius;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return radius * 2;
	}

	@Override
	public double getHeight() {
		return radius * 2;
	}

	@Override
	@SuppressWarnings("unchecked")
	public FilledArc copy() {
		return new FilledArc(color, radius, startAngle, arcAngle);
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

		graphics.setColor(color);
		graphics.fillArc((int) this.getX(), (int) getY(),
				(int) this.getWidth(), (int) this.getHeight(),
				(int) startAngle, (int) arcAngle);
	}
}