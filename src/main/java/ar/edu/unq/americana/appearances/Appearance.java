package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

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

	public void render(GameComponent<?> component, Graphics2D graphics);

	public double getX();

	public double getY();

}
package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

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

	public void render(GameComponent<?> component, Graphics2D graphics);

	public double getX();

	public double getY();
}