package ar.edu.unq.americana.components.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.KeyBoard;
import ar.edu.unq.americana.components.Mouse;
import ar.edu.unq.americana.exceptions.GameException;

public class ComponentUtils {

	public static GameComponent<?>[] commonComponents() {
		return new GameComponent<?>[] { Mouse.get(), KeyBoard.get() };
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

	public static List<GameComponent<?>> filterNonTrivialComponents(
			final GameComponent<?> component,
			final List<GameComponent<?>> components) {
		final List<GameComponent<?>> clone = new ArrayList<GameComponent<?>>(
				components);
		for (final GameComponent<?> common : commonComponents()) {
			clone.remove(common);
		}
		clone.remove(component);
		return clone;
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
			final Class<? extends GameComponent>... args) {
		try {
			return component.getClass().getMethod(methodName, args);
		} catch (final Exception e) {
			throw new GameException(e);
		}
	}
}
