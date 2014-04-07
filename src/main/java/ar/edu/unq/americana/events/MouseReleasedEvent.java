package ar.edu.unq.americana.events;

import java.awt.geom.Point2D;

import ar.edu.unq.americana.ConcreteDeltaState;
import ar.edu.unq.americana.constants.MouseButton;

public class MouseReleasedEvent extends MouseButtonEvent {

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public MouseReleasedEvent(Point2D.Double position, MouseButton button) {
		super(position, button);
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	@Override
	public void applyOn(ConcreteDeltaState state) {
		state.setMouseButtonReleased(this.getButton());
	}
}