package ar.edu.unq.americana.components.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.exceptions.GameException;

public class ComponentUtils {

	public static class ListFilter<T> {
		public static abstract class Condition<T> {
			public abstract boolean apply(T component);
		}

		private final List<T> list;

		public ListFilter(final List<T> components) {
			this.list = components;
		}

		@SuppressWarnings("unchecked")
		public <R> ListFilter<R> byClass(final Class<R> clazz) {
			final List<R> result = new ArrayList<R>();
			for (final T component : this.list) {
				if (clazz.isInstance(component)) {
					result.add((R) component);
				}
			}
			return new ListFilter<R>(result);
		}

		public List<T> get() {
			return this.list;

		}

		public ListFilter<T> remove(final T component) {
			final List<T> copy = new ArrayList<T>(this.list);
			copy.remove(component);
			return new ListFilter<T>(copy);
		}

		public ListFilter<T> byCondition(final Condition<T> condition) {
			final List<T> copy = new ArrayList<T>(this.list);
			final List<T> ret = new ArrayList<T>();
			for (final T component : copy) {
				if (condition.apply(component)) {
					ret.add(component);
				}
			}
			return new ListFilter<T>(ret);
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

	public static <T> ListFilter<T> filter(final List<T> components) {
		return new ComponentUtils.ListFilter<T>(components);
	}
}
