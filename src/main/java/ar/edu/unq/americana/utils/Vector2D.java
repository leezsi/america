package ar.edu.unq.americana.utils;

/**
 * inmutable
 * 
 * @author leo
 * 
 */
public class Vector2D {

	private final double x;
	private final double y;
	private double module;

	public Vector2D(final double x, final double y) {
		this.x = x;
		this.y = y;
		this.module = -1;
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	public double getModule() {
		if (this.module < 0) {
			this.module = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		}
		return this.module;
	}

	public Vector2D asVersor() {
		// TODO, usar un delta?
		return this.getModule() != 1 ? new Vector2D(this.x / this.getModule(),
				this.y / this.getModule()) : this;
	}

	public Vector2D multiply(final double valor) {
		return new Vector2D(this.x * valor, this.y * valor);
	}

	public Vector2D suma(final Vector2D vector2d) {
		return new Vector2D(this.x + vector2d.getX(), this.y + vector2d.getY());
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.module);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.x);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.y);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Vector2D other = (Vector2D) obj;
		if (Double.doubleToLongBits(this.module) != Double
				.doubleToLongBits(other.module)) {
			return false;
		}
		if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		return true;
	}

}
