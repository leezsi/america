package ar.edu.unq.americana.utils;

import ar.edu.unq.americana.exceptions.GameException;

public class ReflectionUtils {
	private ReflectionUtils() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(final Class<?> clazz) {
		try {
			return (T) clazz.newInstance();
		} catch (final Exception e) {
			throw new GameException(e);
		}
	}

}
