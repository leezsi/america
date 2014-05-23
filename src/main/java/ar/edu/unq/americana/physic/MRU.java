package ar.edu.unq.americana.physic;

import ar.edu.unq.americana.math.Coordinate.Cartesian;

public class MRU extends InertialPhysics {

	protected MRU(final Cartesian initialSpeed, final double initialAngle) {
		super(initialAngle);
		this.speed = initialSpeed;
		super.friction(0);
	}

	@Override
	public void thrust(final double thrust) {
		super.thrust(0);
	}

	@Override
	public Physics friction(final double friction) {
		return this;
	}

}
