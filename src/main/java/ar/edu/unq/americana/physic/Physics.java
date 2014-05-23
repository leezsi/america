package ar.edu.unq.americana.physic;

import static java.lang.Math.PI;
import ar.edu.unq.americana.math.Coordinate.Cartesian;
import ar.edu.unq.americana.utils.Vector2D;

public abstract class Physics {

	public static Physics inersialEngine(final double initialAngle) {
		return new InertialPhysics(initialAngle);
	}

	public static Physics mruEngine(final Cartesian initialSpeed,
			final double angle) {
		return new MRU(initialSpeed, angle);
	}

	private double angle = 0;

	public double getAngle() {
		return this.angle;
	}

	protected void setAngle(final double angle) {
		this.angle = angle;
	}

	protected Cartesian speed;

	public abstract Physics friction(final double friction);

	public abstract void thrust(final double thrust);

	public void rotate(final double angularSpeed) {
		this.angle = this.piAndMinusPiAdjust(this.angle + angularSpeed);
	}

	protected double piAndMinusPiAdjust(final double angle) {
		final double range = angle % (2 * PI);
		return range;
	}

	public Cartesian getSpeed() {
		return this.speed;
	}

	public double spriteRotatationAngle() {
		return this.piAndMinusPiAdjust(this.angle) + (PI / 2);
	}

	public Vector2D getPosition(final double delta) {
		return this.speed.toVector2D().multiply(delta);
	}
}
