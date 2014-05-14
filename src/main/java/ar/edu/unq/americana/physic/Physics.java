package ar.edu.unq.americana.physic;

import static java.lang.Math.PI;
import ar.edu.unq.americana.math.Cordinate;
import ar.edu.unq.americana.math.Cordinate.Cartesian;
import ar.edu.unq.americana.math.Cordinate.Polar;
import ar.edu.unq.americana.utils.Vector2D;

public class Physics {
	private double angle = 0;
	private Polar acceleration = new Cordinate.Polar(0, 0);
	private Cartesian speed = new Cordinate.Cartesian(0, 0);
	private double friction;

	private Physics(final double initialAngle) {
		this.angle = initialAngle;
	}

	public static Physics initialize(final double initialAngle) {
		return new Physics(initialAngle);
	}

	public void friction(final double friction) {
		this.friction = -friction;
	}

	public void rotate(final double angularSpeed) {
		this.angle = this.piAndMinusPiAdjust(this.angle + angularSpeed);
	}

	private double piAndMinusPiAdjust(final double angle) {
		final double range = angle % (2 * PI);
		return range;
	}

	public void thrust(final double thrust) {
		this.acceleration = new Polar(thrust, this.angle);
	}

	private void calculateSpeed(final double delta) {
		this.speed = this.speed.add(this.acceleration.multiply(delta));
		this.speed = this.speed.add(this.speed.multiply(this.friction * delta));
		// this.speed = this.limit(this.speed);
	}

	public Vector2D getPosition(final double delta) {
		this.calculateSpeed(delta);
		return this.speed.toVector2D();
	}

	public double angle() {
		return this.angle;
	}

	public void maxSpeed(final double value) {
	}

}
