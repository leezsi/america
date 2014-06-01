package ar.edu.unq.americana.scenes.camera;

import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public class CameraUpdateEvent extends FiredEvent {

	private final double dy;
	private final double dx;

	public CameraUpdateEvent(final double dx, final double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public double getDy() {
		return this.dy;
	}

	public double getDx() {
		return this.dx;
	}

}
