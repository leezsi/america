package ar.edu.unq.americana.events.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.events.ioc.fired.FiredEventHandler;
import ar.edu.unq.americana.events.ioc.keyboard.KeyboardHandler;
import ar.edu.unq.americana.events.ioc.mouse.MouseHandler;
import ar.edu.unq.americana.events.ioc.update.UpdateHandler;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class EventManager {

	private static Map<Class<? extends Annotation>, Handler<?>> annotationMap = new HashMap<Class<? extends Annotation>, Handler<?>>();

	static {
		annotationMap.put(Events.Keyboard.class, new KeyboardHandler());
		annotationMap.put(Events.Mouse.class, new MouseHandler());
		annotationMap.put(Events.Update.class, new UpdateHandler());
		annotationMap.put(Events.Fired.class,
				new FiredEventHandler<FiredEvent>());
		// annotationMap.put(Events.Collision.class, new
		// CollisionEventHandler());
	}

	private static Map<Class<?>, List<Handler<?>>> handlers = new HashMap<Class<?>, List<Handler<?>>>();

	public static void registry(final Object object) {
		for (final Class<? extends Annotation> currentAnnotation : annotationMap
				.keySet()) {
			final List<Method> methods = ReflectionUtils.getAnnotatedMethods(
					object.getClass(), currentAnnotation);
			final Handler<?> handler = annotationMap.get(currentAnnotation);
			for (final Method method : methods) {
				final Handler<?> copy = handler.fill(object, method).copy();
				map(copy);
			}
		}
	}

	public static void unregistry(final Object object) {
		for (final Class<?> key : handlers.keySet()) {
			final List<Handler<?>> currentHandlers = new ArrayList<Handler<?>>(
					handlers.get(key));
			for (final Handler<?> handler : currentHandlers) {
				if (handler.getTarget() == object) {
					handlers.get(key).remove(handler);
				}
			}
		}

	}

	private static void map(final Handler<?> copy) {
		final Class<?> event = copy.getEventType();
		List<Handler<?>> currentHandlers = handlers.get(event);
		if (currentHandlers == null) {
			currentHandlers = new ArrayList<Handler<?>>();
			handlers.put(event, currentHandlers);
		}
		currentHandlers.add(copy);
	}

	public static void fire(final AmericanaEvent<?> event,
			final DeltaState deltaState) {
		final List<Handler<?>> toExecute = new ArrayList<Handler<?>>(
				handlers.get(event.getClass()));
		if (toExecute != null) {
			for (final Handler<?> handler : toExecute) {
				handler.executeOn(deltaState);
			}
		}
	}

	public static void fire(final FiredEvent event) {
		final List<Handler<?>> toExecute = handlers.get(event.getClass());
		if (toExecute != null) {
			for (final Handler<?> handler : toExecute) {
				handler.execute(event);
			}
		}
	}

	public static void reset() {
		handlers = new HashMap<Class<?>, List<Handler<?>>>();
	}

}
package ar.edu.unq.americana.events.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.events.ioc.fired.FiredEventHandler;
import ar.edu.unq.americana.events.ioc.keyboard.KeyboardHandler;
import ar.edu.unq.americana.events.ioc.mouse.MouseHandler;
import ar.edu.unq.americana.events.ioc.update.UpdateHandler;
import ar.edu.unq.americana.utils.ReflectionUtils;

public class EventManager {

	private static Map<Class<? extends Annotation>, Handler<?>> annotationMap = new HashMap<Class<? extends Annotation>, Handler<?>>();

	static {
		annotationMap.put(Events.Keyboard.class, new KeyboardHandler());
		annotationMap.put(Events.Mouse.class, new MouseHandler());
		annotationMap.put(Events.Update.class, new UpdateHandler());
		annotationMap.put(Events.Fired.class,
				new FiredEventHandler<FiredEvent>());
	}

	private static Map<Class<?>, List<Handler<?>>> handlers = new HashMap<Class<?>, List<Handler<?>>>();

	public static void registry(final Object object) {
		for (final Class<? extends Annotation> currentAnnotation : annotationMap
				.keySet()) {
			final List<Method> methods = ReflectionUtils.getAnnotatedMethods(
					object.getClass(), currentAnnotation);
			final Handler<?> handler = annotationMap.get(currentAnnotation);
			for (final Method method : methods) {
				final Handler<?> copy = handler.fill(object, method).copy();
				map(copy);
			}
		}
	}

	public static void unregistry(final Object object) {
		for (final Class<?> key : handlers.keySet()) {
			final List<Handler<?>> currentHandlers = handlers.get(key);
			for (final Handler<?> handler : currentHandlers) {
				if (handler.getTarget() == object) {
					currentHandlers.remove(handler);
				}
			}
		}

	}

	private static void map(final Handler<?> copy) {
		final Class<?> event = copy.getEventType();
		List<Handler<?>> currentHandlers = handlers.get(event);
		if (currentHandlers == null) {
			currentHandlers = new ArrayList<Handler<?>>();
			handlers.put(event, currentHandlers);
		}
		currentHandlers.add(copy);
	}

	public static void fire(final AmericanaEvent<?> event,
			final DeltaState deltaState) {
		final List<Handler<?>> toExecute = handlers.get(event.getClass());
		if (toExecute != null) {
			for (final Handler<?> handler : toExecute) {
				handler.executeOn(deltaState);
			}
		}
	}

	public static void fire(final FiredEvent event) {
		final List<Handler<?>> toExecute = handlers.get(event.getClass());
		if (toExecute != null) {
			for (final Handler<?> handler : toExecute) {
				handler.execute(event);
			}
		}
	}

	public static void reset() {
		handlers = new HashMap<Class<?>, List<Handler<?>>>();
	}

}
