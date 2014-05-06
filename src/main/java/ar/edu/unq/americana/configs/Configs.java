package ar.edu.unq.americana.configs;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.exceptions.GameException;
import ar.edu.unq.americana.utils.ReflectionUtils;
import ar.edu.unq.americana.utils.Tuning;

public class Configs {

	private static Map<Class<?>, Object> beans = new HashMap<Class<?>, Object>();

	private static void readBeans(final Object object) {
		final Class<?> clazz = object.getClass();
		if (clazz.getAnnotation(Bean.class) != null) {
			beans.put(clazz, object);
		}
	}

	private static void injectBeans(final Object object) {
		final Class<?> clazz = object.getClass();
		final List<Field> fields = ReflectionUtils.getAnnotatedFields(clazz,
				Bean.class);
		for (final Field field : fields) {
			ReflectionUtils.set(object, field, beans.get(field.getType()));
		}
	}

	public static void injectAndReadBeans(final Object object) {
		readBeans(object);
		injectBeans(object);
	}

	public static void injectConfigs(final Class<?> clazz) {
		final List<Field> fields = ReflectionUtils.getAnnotatedFields(clazz,
				Property.class);
		for (final Field field : fields) {
			final String property = field.getAnnotation(Property.class).value();
			if (!Tuning.isSet(property)) {
				throw new GameException("property: [" + property
						+ "] is not set in config file");
			}
			final Class<?> type = field.getType();
			ReflectionUtils.set(clazz, field, forType(type, property));
		}
	}

	private static Object forType(final Class<?> type, final String property) {
		if ((type == Integer.class) || (type == int.class)) {
			return Tuning.getInteger(property);
		} else if ((type == Long.class) || (type == long.class)) {
			return Tuning.getLong(property);
		} else if ((type == Double.class) || (type == double.class)) {
			return Tuning.getDouble(property);
		} else if (type == Color.class) {
			return Tuning.getColor(property, null);
		} else if (type == String.class) {
			return Tuning.getString(property);
		} else if ((type == Boolean.class) || (type == boolean.class)) {
			final String value = Tuning.getString(property);
			return (value.equalsIgnoreCase("true") || value.equals("1"));
		} else if (type == Sprite.class) {
			return Sprite.fromImage(Tuning.getString(property));
		} else {
			return null;
		}
	}
}