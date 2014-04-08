package ar.edu.unq.americana;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ar.edu.unq.americana.colissions.CollisionManager;
import ar.edu.unq.americana.components.KeyBoard;
import ar.edu.unq.americana.components.Mouse;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.events.EventQueue;
import ar.edu.unq.americana.events.GameEvent;
import ar.edu.unq.americana.events.game.EventManager;

public class GameScene {

	private Game game;
	private List<GameComponent<?>> components;
	private EventQueue eventQueue;
	private double lastUpdateTime;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public GameScene() {
		this.setComponents(new ArrayList<GameComponent<?>>());
		this.setEventQueue(new EventQueue());
		this.addComponents(Arrays.asList(ComponentUtils.commonComponents()));
	}

	public GameScene(final GameComponent<? extends GameScene>... components) {
		this(Arrays.asList(components));
	}

	public GameScene(
			final Collection<? extends GameComponent<? extends GameScene>> components) {
		this();

		for (final GameComponent<? extends GameScene> component : components) {
			this.addComponent(component);
		}
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public int getComponentCount() {
		return this.getComponents().size();
	}

	protected int getZFromComponentAt(final int index) {
		return this.getComponents().get(index).getZ();
	}

	protected int indexToInsert(final GameComponent<?> component) {
		int lowerIndex = 0;
		int higherIndex = this.getComponentCount() - 1;
		final int searchedZ = component.getZ();

		if (this.getComponents().isEmpty()
				|| (searchedZ < this.getZFromComponentAt(lowerIndex))) {
			return 0;
		}

		if (searchedZ >= this.getZFromComponentAt(higherIndex)) {
			return this.getComponentCount();
		}

		while (lowerIndex <= higherIndex) {
			final int middleIndex = (lowerIndex + higherIndex) >>> 1;
			final int middleZ = this.getZFromComponentAt(middleIndex);

			if (middleZ <= searchedZ) {
				lowerIndex = middleIndex + 1;
			} else if (middleZ > searchedZ) {
				higherIndex = middleIndex - 1;
			}
		}

		return lowerIndex;
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void onSetAsCurrent() {
		for (final GameComponent<?> component : components) {
			component.onSceneActivated();
		}
	}

	public void pushEvent(final GameEvent event) {
		this.getEventQueue().pushEvent(event);
	}

	public void takeStep(final Graphics2D graphics) {
		final long now = System.nanoTime();
		double delta = this.getLastUpdateTime() > 0 ? (now - this
				.getLastUpdateTime()) / 1000000000L : 0;
		if (delta > 1) {
			delta = 0;
		}
		this.setLastUpdateTime(now);

		final DeltaState state = this.getEventQueue().takeState(delta);
		final ArrayList<GameComponent<?>> allComponents = new ArrayList<GameComponent<?>>(
				this.getComponents());

		Mouse.get().privateUpdate(state);
		KeyBoard.get().privateUpdate(state);
		CollisionManager.get().privateUpdate(allComponents);
		for (final GameComponent<?> component : allComponents) {
			if (component.isDestroyPending()) {
				this.removeComponent(component);
			} else {
				component.update(state);
				component.render(graphics);
			}
		}
	}

	// ****************************************************************
	// ** ACCESS OPERATIONS
	// ****************************************************************

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addComponent(final GameComponent component) {
		this.getComponents().add(this.indexToInsert(component), component);
		component.setScene(this);
		Mouse.get().registerComponentEvents(component);
		KeyBoard.get().registerComponentEvents(component);
		CollisionManager.get().registerComponentEvents(component);
		EventManager.get().registerComponentEvents(component);
	}

	public void addComponents(final GameComponent<?>... components) {
		for (final GameComponent<?> component : components) {
			this.addComponent(component);
		}
	}

	public void addComponents(
			final Collection<? extends GameComponent<?>> components) {
		for (final GameComponent<?> component : components) {
			this.addComponent(component);
		}
	}

	public void removeComponent(final GameComponent<?> component) {
		this.getComponents().remove(component);
		component.setScene(null);
		Mouse.get().deresgister(component);
		KeyBoard.get().deresgister(component);
		CollisionManager.get().deresgister(component);
		EventManager.get().deresgister(component);
	}

	public void removeComponents(final GameComponent<?>... components) {
		for (final GameComponent<?> component : components) {
			this.removeComponent(component);
		}
	}

	public void removeComponents(
			final Collection<? extends GameComponent<?>> components) {
		for (final GameComponent<?> component : components) {
			this.removeComponent(component);
		}
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public Game getGame() {
		return game;
	}

	public void setGame(final Game game) {
		this.game = game;
	}

	public List<GameComponent<?>> getComponents() {
		return components;
	}

	protected void setComponents(final List<GameComponent<?>> components) {
		this.components = components;
	}

	protected EventQueue getEventQueue() {
		return eventQueue;
	}

	protected void setEventQueue(final EventQueue eventQueue) {
		this.eventQueue = eventQueue;
	}

	protected double getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(final double lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}