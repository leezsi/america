package ar.edu.unq.americana.utils;

import java.util.Random;

public class TrigonometricsAndRandomUtils {

	private static Random rnd = new Random();

	private TrigonometricsAndRandomUtils() {
	}

	public static int uniform(final int min, final int max) {
		return min + (rnd.nextInt() * (max - min));
	}

	public static double uniform(final double min, final double max) {
		return min + (rnd.nextDouble() * (max - min));
	}

	public static float uniform(final float min, final float max) {
		return min + (rnd.nextFloat() * (max - min));
	}

	public static double gausean(final double mean, final double variance) {
		return mean + (rnd.nextGaussian() * variance);
	}

	public static double gausean(final double mean, final double variance,
			final double min, final double max) {
		final double ret = gausean(mean, variance);
		if ((ret < min) || (ret > max)) {
			return gausean(mean, variance, min, max);
		}
		return ret;
	}

	public static double angle() {
		return uniform(-Math.PI, Math.PI);
	}

	public static double angle(final Vector2D vector) {
		final Vector2D versor = vector.asVersor();
		return Math.asin(versor.getY());
	}

	public static Vector2D vector(final double angle, final double module) {
		final double y = Math.sin(angle) * module;
		final double x = Math.cos(angle) * module;
		return new Vector2D(x, y);
	}
}
