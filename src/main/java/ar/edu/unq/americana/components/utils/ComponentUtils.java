package ar.edu.unq.americana.components.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.KeyBoard;
import ar.edu.unq.americana.components.Mouse;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.KeyboarEvents;
import ar.edu.unq.americana.events.annotations.MouseEvents;
import ar.edu.unq.americana.exceptions.GameException;

public class ComponentUtils {

	public static GameComponent<?>[] commonComponents() {
		return new GameComponent<?>[] { Mouse.get(), KeyBoard.get() };
	}

	public static void fireButtonPressed(
			final List<GameComponent<?>> components, final MouseButton button,
			final DeltaState deltaState) {
		final List<GameComponent<?>> filterd = filterNonTrivialComponents(
				Mouse.get(), components);
		for (final GameComponent<?> component : filterd) {
			final Method methods[] = filterMethodsByAnnotation(
					component.getClass(), MouseEvents.Press.class);
			for (final Method method : methods) {
				final MouseButton value = method.getAnnotation(
						MouseEvents.Press.class).value();
				if (value.equals(button) || value.equals(MouseButton.ANY)) {
					invoke(method, component, deltaState, button);
				}
			}
		}
	}

	private static void invoke(final Method method,
			final GameComponent<?> component, final Object... args) {
		final boolean isAccessible = method.isAccessible();
		try {
			method.setAccessible(true);
			method.invoke(component, args);
		} catch (final Exception e) {
			throw new GameException(e);
		}
		method.setAccessible(isAccessible);
	}

	private static Method[] filterMethodsByAnnotation(final Class<?> clazz,
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

	private static List<GameComponent<?>> filterNonTrivialComponents(
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

	public static void fireButtonReleased(
			final List<GameComponent<?>> components, final MouseButton button,
			final DeltaState deltaState) {
		final List<GameComponent<?>> filterd = filterNonTrivialComponents(
				Mouse.get(), components);
		for (final GameComponent<?> component : filterd) {
			final Method methods[] = filterMethodsByAnnotation(
					component.getClass(), MouseEvents.Release.class);
			for (final Method method : methods) {
				final MouseButton value = method.getAnnotation(
						MouseEvents.Release.class).value();
				if (value.equals(button) || value.equals(MouseButton.ANY)) {
					invoke(method, component, deltaState, button);
				}
			}
		}
	}

	public static void fireButtonBeingHold(
			final List<GameComponent<?>> components, final MouseButton button,
			final DeltaState deltaState) {
		final List<GameComponent<?>> filterd = filterNonTrivialComponents(
				Mouse.get(), components);
		for (final GameComponent<?> component : filterd) {
			final Method methods[] = filterMethodsByAnnotation(
					component.getClass(), MouseEvents.BeingHold.class);
			for (final Method method : methods) {
				final MouseButton value = method.getAnnotation(
						MouseEvents.BeingHold.class).value();
				if (value.equals(button) || value.equals(MouseButton.ANY)) {
					invoke(method, component, deltaState, button);
				}
			}
		}

	}

	public static void fireKeyPressed(final List<GameComponent<?>> components,
			final Key key, final DeltaState deltaState) {
		final List<GameComponent<?>> filterd = filterNonTrivialComponents(
				Mouse.get(), components);
		for (final GameComponent<?> component : filterd) {
			final Method methods[] = filterMethodsByAnnotation(
					component.getClass(), KeyboarEvents.Press.class);
			for (final Method method : methods) {
				final Key value = method.getAnnotation(
						KeyboarEvents.Press.class).value();
				if (value.equals(key) || value.equals(Key.ANY)) {
					invoke(method, component, deltaState, key);
				}
			}
		}
	}

	public static void fireKeyBeingHold(
			final List<GameComponent<?>> components, final Key key,
			final DeltaState deltaState) {
		final List<GameComponent<?>> filterd = filterNonTrivialComponents(
				Mouse.get(), components);
		for (final GameComponent<?> component : filterd) {
			final Method methods[] = filterMethodsByAnnotation(
					component.getClass(), KeyboarEvents.BeingHold.class);
			for (final Method method : methods) {
				final Key value = method.getAnnotation(
						KeyboarEvents.BeingHold.class).value();
				if (value.equals(key) || value.equals(Key.ANY)) {
					invoke(method, component, deltaState, key);
				}
			}
		}
	}

	public static void fireKeyReleased(final List<GameComponent<?>> components,
			final Key key, final DeltaState deltaState) {
		final List<GameComponent<?>> filterd = filterNonTrivialComponents(
				Mouse.get(), components);
		for (final GameComponent<?> component : filterd) {
			final Method methods[] = filterMethodsByAnnotation(
					component.getClass(), KeyboarEvents.Release.class);
			for (final Method method : methods) {
				final Key value = method.getAnnotation(
						KeyboarEvents.Release.class).value();
				if (value.equals(key) || value.equals(Key.ANY)) {
					invoke(method, component, deltaState, key);
				}
			}
		}
	}

}
