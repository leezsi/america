package ar.edu.unq.americana.utils;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Properties;

public class Tuning {

	static Properties properties = new Properties();

	static public void load(final String file) {
		try {
			properties.load(Tuning.class.getClassLoader().getResourceAsStream(
					file));
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	static public void load() {
		load("values.properties");
	}

	static public String getString(final String key, final String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	static public String getString(final String key) {
		return properties.getProperty(key);
	}

	static public Long getLong(final String key, final Long defaultValue) {
		final String outString = properties.getProperty(key);
		return (outString == null) ? defaultValue : Long.parseLong(outString);

	}

	static public Long getLong(final String key) {
		return getLong(key, null);
	}

	static public Integer getInteger(final String key,
			final Integer defaultValue) {
		final String outString = properties.getProperty(key);
		return (outString == null) ? defaultValue : Integer.parseInt(outString);

	}

	static public Integer getInteger(final String key) {
		return getInteger(key, null);
	}

	static public Double getDouble(final String key, final Double defaultValue) {
		final String outString = properties.getProperty(key);
		return (outString == null) ? defaultValue : Double
				.parseDouble(outString);

	}

	static public Double getDouble(final String key) {
		return getDouble(key, null);
	}

	static public Color getColor(final String key, final Color defaultValue) {
		final String outString = getString(key);
		try {
			if (outString == null) {
				return defaultValue;
			}
			final Field field = Color.class.getField(outString);
			return (Color) field.get(null);
		} catch (final Exception e) {
			throw new RuntimeException("No existe el color " + outString, e);
		}
	}

	@SuppressWarnings("unchecked")
	static public <T> Class<T> getClass(final String key,
			final Class<T> defaultValue) {
		try {
			final String outString = properties.getProperty(key);
			return (outString == null) ? defaultValue : (Class<T>) Class
					.forName(outString);
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	static public Class<?> getClass(final String key) {
		return getClass(key, null);
	}

	static public <T> T newInstance(final String key,
			final Class<T> defaultClass) {
		try {
			return getClass(key, defaultClass).newInstance();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	static public <T> T newInstance(final String key,
			final Class<T> defaultClass, final Class<?>[] parameterTypes,
			final Object... params) {
		try {
			return getClass(key, defaultClass).getConstructor(parameterTypes)
					.newInstance(params);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
