package ar.edu.unq.americana.math;

import ar.edu.unq.americana.utils.Vector2D;

public abstract class Coordinate<T extends Coordinate<?>> {

	public static class Polar extends Coordinate<Polar> {
		private double module;
		private double angle;

		public Polar(final double module, final double angle) {
			this.module = module;
			this.angle = angle;
		}

		@Override
		public Cartesian toCartesian() {
			return new Cartesian(Math.cos(this.angle) * this.module,
					Math.sin(this.angle) * this.module);
		}

		@Override
		public Polar add(final Cartesian cordinate) {
			return cordinate.add(this.toCartesian()).toPolar();
		}

		@Override
		public Polar add(final Polar cordinate) {
			return cordinate.toCartesian().add(this).toPolar();
		}

		@Override
		public Polar multiply(final double factor) {
			return new Polar(this.module * factor, this.angle);
		}

		@Override
		public Polar toPolar() {
			return new Polar(this.module, this.angle);
		}

		@Override
		public String toString() {
			return "module:" + this.module + " alpha:" + this.angle;
		}

		@Override
		public double getModule() {
			return this.module;
		}

		@Override
		public double getAlpha() {
			return this.angle;
		}

		public Polar setAlpha(final double alpha) {
			this.angle = alpha;
			return this;
		}

		public void setModule(final double module) {
			this.module = module;
		}
	}

	public static class Cartesian extends Coordinate<Cartesian> {

		private final double y;
		private final double x;

		public Cartesian(final double x, final double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public Cartesian add(final Cartesian cordinate) {
			return new Cartesian(this.x + cordinate.x, this.y + cordinate.y);
		}

		@Override
		public Cartesian add(final Polar cordinate) {
			return cordinate.toCartesian().add(this);
		}

		@Override
		public Cartesian multiply(final double factor) {
			return new Cartesian(this.x * factor, this.y * factor);
		}

		@Override
		public Polar toPolar() {
			final double module = Math.hypot(this.x, this.y);
			final double alpha = Math.atan2(this.y, this.x);
			return new Polar(module, Math.toRadians(alpha));
		}

		@Override
		public Cartesian toCartesian() {
			return new Cartesian(this.x, this.y);
		}

		@Override
		public Vector2D toVector2D() {
			return new Vector2D(this.x, this.y);
		}

		@Override
		public String toString() {
			return "x:" + this.x + ";y:" + this.y;
		}

		@Override
		public double getX() {
			return this.x;
		}

		@Override
		public double getY() {
			return this.y;
		}

	}

	public double getAlpha() {
		return this.toPolar().getAlpha();
	}

	public double getModule() {
		return this.toPolar().getModule();
	}

	public double getX() {
		return this.toCartesian().getX();
	}

	public double getY() {
		return this.toCartesian().getY();
	}

	public abstract T add(final Cartesian cordinate);

	public abstract T add(final Polar cordinate);

	public abstract T multiply(final double factor);

	public abstract Polar toPolar();

	public abstract Cartesian toCartesian();

	public Vector2D toVector2D() {
		return this.toCartesian().toVector2D();
	}
}
