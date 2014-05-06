package ar.edu.unq.americana.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.exceptions.GameException;

public class ReflectionUtils {
	private ReflectionUtils() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(final Class<?> clazz,
			final Object... params) {
		try {
			return (T) clazz.newInstance();
		} catch (final Exception e) {
			throw new GameException(e);
		}
	}

	public static void invoke(final GameComponent<?> component,
			final Method method, final Object... params) {
		try {
			method.invoke(component, params);
		} catch (final Exception e) {
			throw new GameException(e);
		}
	}

	public static List<Method> getAnnotatedMethods(final Class<?> target,
			final Class<? extends Annotation> type) {
		return getAnnotatedMethods(target, type, new ArrayList<Method>());
	}

	private static List<Method> getAnnotatedMethods(final Class<?> target,
			final Class<? extends Annotation> type,
			final ArrayList<Method> accumulator) {
		if (target == null) {
			return accumulator;
		}

		final Method[] all = target.getDeclaredMethods();
		for (final Method method : all) {
			if (method.isAnnotationPresent(type)) {
				accumulator.add(method);
			}
		}
		return getAnnotatedMethods(target.getSuperclass(), type, accumulator);
	}

	public static void invoke(final Object target, final Method method,
			final Object... params) {
		final boolean isAccessible = method.isAccessible();
		method.setAccessible(true);
		try {
			method.invoke(target, params);
		} catch (final Exception e) {
			throw new GameException(e);
		}
		method.setAccessible(isAccessible);
	}

	public static List<Field> getAnnotatedFields(final Class<?> clazz,
			final Class<? extends Annotation> annotationClass) {
		return getAnnotatedFields(clazz, annotationClass,
				new ArrayList<Field>());
	}

	private static List<Field> getAnnotatedFields(final Class<?> target,
			final Class<? extends Annotation> type,
			final List<Field> accumulator) {
		if (target == null) {
			return accumulator;
		}

		final Field[] all = target.getDeclaredFields();
		for (final Field field : all) {
			if (field.isAnnotationPresent(type)) {
				accumulator.add(field);
			}
		}
		return getAnnotatedFields(target.getSuperclass(), type, accumulator);
	}

	public static void set(final Object object, final Field field,
			final Object value) {
		final boolean isAccessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, value);
		} catch (final Exception e) {
			throw new GameException(e);
		}
		field.setAccessible(isAccessible);
	}

}