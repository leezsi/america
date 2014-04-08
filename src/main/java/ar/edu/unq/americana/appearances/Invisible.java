package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

public class Invisible extends Shape {

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return 0;
	}

	@Override
	public double getHeight() {
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Invisible copy() {
		return this;
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
		// TODO Auto-generated method stub

	}

}