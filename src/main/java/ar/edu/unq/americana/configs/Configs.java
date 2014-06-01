package ar.edu.unq.americana.configs;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.exceptions.GameException;
import ar.edu.unq.americana.utils.ReflectionUtils;
import ar.edu.unq.americana.utils.Tuning;

public class Configs {

	private static Map<String, Object> beans = new HashMap<String, Object>();
	private static Set<WeakReference<Object>> references = new HashSet<WeakReference<Object>>();

	private static void readBeans(final Object object) {
		readBeans(object, object.getClass());
	}

	private static void readBeans(final Object object, final Class<?> clazz) {
		if (clazz != null) {
			Class<?>[] all = clazz.getInterfaces();
			all = Arrays.copyOf(all, all.length + 1);
			all[all.length - 1] = clazz;
			for (final Class<?> target : all) {
				if (target.isAnnotationPresent(Bean.class)) {
					final Bean annotation = target.getAnnotation(Bean.class);
					beans.put(
							annotation.name().equals("") ? target
									.getCanonicalName() : annotation.name(),
							object);
					refreshAndAddNewBeans();
				}
			}
			readBeans(object, clazz.getSuperclass());
		}

	}

	private static void refreshAndAddNewBeans() {
		final Set<WeakReference<Object>> tmp = new HashSet<WeakReference<Object>>();
		for (final WeakReference<Object> currentReference : references) {
			if (currentReference.get() != null) {
				tmp.add(currentReference);
				injectBeans(currentReference.get());
			}
		}

		references = tmp;
	}

	private static void injectBeans(final Object object) {
		final Class<?> clazz = object.getClass();
		final List<Field> fields = ReflectionUtils.getAnnotatedFields(clazz,
				Bean.class);
		for (final Field field : fields) {
			final Bean annotation = field.getAnnotation(Bean.class);
			Object toSet;
			if (annotation.name().equals("")) {
				toSet = beans.get(field.getType().getCanonicalName());
			} else {
				toSet = beans.get(annotation.name());
			}
			ReflectionUtils.set(object, field, toSet);
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

	public static void injectConfigs(final Object object) {
		injectConfigs(object.getClass());
	}
}