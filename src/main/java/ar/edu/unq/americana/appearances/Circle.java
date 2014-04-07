package ar.edu.unq.americana.appearances;

import java.awt.Color;
import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent.AppearanceData;

public class Circle implements Appearance {

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
		return diameter;
	}

	@Override
	public double getHeight() {
		return diameter;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Circle copy() {
		return new Circle(color, diameter);
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(final double delta) {
	}

	@Override
	public void render(final AppearanceData appearanceData,
			final Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillOval((int) appearanceData.getX(),
				(int) appearanceData.getY(), diameter, diameter);

	}

}