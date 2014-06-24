package ar.edu.unq.americana.scenes.camera;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public class CameraResetEvent extends FiredEvent {

	private final GameComponent<?> component;
	private final double x;
	private final double y;

	public CameraResetEvent(final GameComponent<?> component) {
		this.component = component;
		this.x = component.getX();
		this.y = component.getY();
	}

	public GameComponent<?> getComponent() {
		return this.component;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

}
