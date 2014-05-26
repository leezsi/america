package ar.edu.unq.americana.utils;

import java.util.Random;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.math.Coordinate;

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
		return new Coordinate.Polar(module, angle).toVector2D();
	}

	public static void fixPositionTo(final GameComponent<?> from,
			final GameComponent<?> to) {
		final double deltaY = from.getY() - to.getY();
		final double deltaX = from.getX() - to.getX();
		final double theta = Math.atan2(deltaY, deltaX);
		final double module = Math.hypot(deltaX, deltaY);
		new Coordinate.Polar(-module, theta).toVector2D();

	}

	public static boolean nextBoolean() {
		return rnd.nextBoolean();
	}

	public static double vectoreanBoolean() {
		return nextBoolean() ? 1 : -1;
	}
}
