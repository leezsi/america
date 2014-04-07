package ar.edu.unq.americana.appearances;

import java.awt.Color;
import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent.AppearanceData;

public class Rectangle implements Appearance {

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
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Rectangle copy() {
		return new Rectangle(color, height, width);
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
		graphics.fillRect((int) appearanceData.getX(),
				(int) appearanceData.getY(), width, height);
	}

}
