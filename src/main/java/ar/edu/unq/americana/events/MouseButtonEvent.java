package ar.edu.unq.americana.events;

import java.awt.geom.Point2D;

import ar.edu.unq.americana.constants.MouseButton;

public abstract class MouseButtonEvent extends MouseEvent {

	private MouseButton button;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public MouseButtonEvent(Point2D.Double position, MouseButton button) {
		super(position);

		this.setButton(button);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public MouseButton getButton() {
		return this.button;
	}

	protected void setButton(MouseButton button) {
		this.button = button;
	}
}