package ar.edu.unq.americana.math;

import ar.edu.unq.americana.utils.Vector2D;

public abstract class Cordinate<T extends Cordinate<?>> {

	public static class Polar extends Cordinate<Polar> {
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

		public double getModule() {
			return this.module;
		}

		public double getAlpha() {
			return this.angle;
		}

		public void setAlpha(final double alpha) {
			this.angle = alpha;
		}

		public void setModule(final double module) {
			this.module = module;
		}
	}

	public static class Cartesian extends Cordinate<Cartesian> {

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
			final double module = Math.sqrt((this.x * this.x)
					+ (this.y * this.y));
			final double alpha = Math.atan(this.y / this.x);
			return new Polar(module, alpha);
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

		public double getModule() {
			return this.toPolar().getModule();
		}

		public double getX() {
			return this.x;
		}

		public double getY() {
			return this.y;
		}
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
