package ar.edu.unq.americana.colissions;

import java.util.HashMap;
import java.util.Map;

import ar.edu.unq.americana.GameComponent;

public class CollitionGroup {

	private static int uncollisionable = -1;
	private static int collisionable = 1;
	private static Map<Class<?>, Integer> groups = new HashMap<Class<?>, Integer>();

	public static void setUncollisionableGroup(final GameComponent<?> component) {
		component.setCollitionGroup(uncollisionable--);
	}

	@SuppressWarnings("unchecked")
	public static void setAs(final GameComponent<?> component1,
			final Class<?> type2) {
		final Class<? extends GameComponent<?>> type1 = (Class<? extends GameComponent<?>>) component1
				.getClass();

		if (groups.containsKey(type1)) {
			final Integer type = groups.get(type1);
			component1.setCollitionGroup(type);
			groups.put(type2, groups.get(type1));
		} else if (groups.containsKey(type2)) {
			final Integer type = groups.get(type2);
			component1.setCollitionGroup(type);
			groups.put(type1, type);
		} else {
			final int type = collisionable++;
			component1.setCollitionGroup(type);
			groups.put(type1, type);
			groups.put(type2, type);
		}
	}

	@SuppressWarnings("unchecked")
	public static void independent(final GameComponent<?> component) {
		final Class<? extends GameComponent<?>> type1 = (Class<? extends GameComponent<?>>) component
				.getClass();
		if (groups.containsKey(type1)) {
			final Integer type = groups.get(type1);
			component.setCollitionGroup(type);
		} else {
			final Integer type = collisionable++;
			component.setCollitionGroup(type);
			groups.put(type1, type);
		}
	}

	public static boolean isCollisionable(final GameComponent<?> component1,
			final GameComponent<?> component2) {
		final int group1 = component1.getCollitionGroup();
		final int group2 = component2.getCollitionGroup();
		if ((group1 < 0) || (group2 < 0)) {
			return false;
		}
		return group1 == group2;
	}

}
