package ar.edu.unq.americana.components.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.exceptions.GameException;

public class ComponentUtils {

	public static class ListFilter {

		private final List<GameComponent<?>> list;

		public ListFilter(final List<GameComponent<?>> components) {
			list = components;
		}

		public ListFilter byClass(final Class<?> clazz) {
			final List<GameComponent<?>> result = new ArrayList<GameComponent<?>>();
			for (final GameComponent<?> component : list) {
				if (component.getClass() == clazz) {
					result.add(component);
				}
			}
			return new ListFilter(result);
		}

		public List<GameComponent<?>> get() {
			return list;

		}

		public ListFilter remove(final GameComponent<?> component) {
			final List<GameComponent<?>> copy = new ArrayList<GameComponent<?>>(
					list);
			copy.remove(component);
			return new ListFilter(copy);
		}

	}

	public static Method[] filterMethodsByAnnotation(final Class<?> clazz,
			final Class<? extends Annotation> annotationClass) {
		final Method[] all = clazz.getMethods();
		final List<Method> result = new ArrayList<Method>();
		for (final Method method : all) {
			if (method.isAnnotationPresent(annotationClass)) {
				result.add(method);
			}
		}
		return result.toArray(new Method[result.size()]);
	}

	@SuppressWarnings("unchecked")
	public static <T> T invoke(final GameComponent<?> component,
			final Method method, final Object... args) {
		final boolean isAccessible = method.isAccessible();
		method.setAccessible(true);
		T result = null;
		try {
			result = (T) method.invoke(component, args);
		} catch (final Exception e) {
			new GameException(e);
		} finally {
			method.setAccessible(isAccessible);
		}
		return result;
	}

	public static Method getMethod(final GameComponent<?> component,
			final String methodName,
			final Class<? extends GameComponent<?>>... args) {
		try {
			return component.getClass().getMethod(methodName, args);
		} catch (final Exception e) {
			throw new GameException(e);
		}
	}

	public static ListFilter filter(final List<GameComponent<?>> components) {
		return new ComponentUtils.ListFilter(components);
	}
}
