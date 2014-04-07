package ar.edu.unq.americana.events;

import java.awt.geom.Point2D;

import ar.edu.unq.americana.ConcreteDeltaState;

public class MouseMovedEvent extends MouseEvent {

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public MouseMovedEvent(Point2D.Double position) {
		super(position);
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	@Override
	public void applyOn(ConcreteDeltaState state) {
		state.setMousePosition(this.getPosition());
	}
}