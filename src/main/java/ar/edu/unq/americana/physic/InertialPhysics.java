package ar.edu.unq.americana.physic;

import ar.edu.unq.americana.math.Coordinate.Cartesian;
import ar.edu.unq.americana.math.Coordinate.Polar;
import ar.edu.unq.americana.utils.Vector2D;

public class InertialPhysics extends Physics {

	private Polar acceleration;
	private double friction;

	protected InertialPhysics(final double initialAngle) {
		this.setAngle(initialAngle);
		this.speed = new Cartesian(0, 0);
		this.thrust(0);
	}

	@Override
	public Physics friction(final double friction) {
		this.friction = -friction;
		return this;
	}

	@Override
	public void thrust(final double thrust) {
		this.acceleration = new Polar(thrust, this.getAngle());

	}

	private void calculateSpeed(final double delta) {
		this.speed = this.speed.add(this.acceleration.multiply(delta));
		this.speed = this.speed.add(this.speed.multiply(this.friction * delta));
	}

	@Override
	public Vector2D getPosition(final double delta) {
		this.calculateSpeed(delta);
		return super.getPosition(delta);
	}

}
