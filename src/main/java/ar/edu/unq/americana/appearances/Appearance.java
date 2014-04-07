package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent.AppearanceData;

public interface Appearance {

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public double getWidth();

	public double getHeight();

	public <T extends Appearance> T copy();

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	public void update(double delta);

	public void render(AppearanceData appearanceData, Graphics2D graphics);

}