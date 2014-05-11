package ar.edu.unq.americana;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.ioc.EventManager;
import ar.edu.unq.americana.events.ioc.keyboard.KeyboarEvent;
import ar.edu.unq.americana.events.ioc.mouse.MouseEvent;
import ar.edu.unq.americana.events.ioc.mouse.MouseMoveEvent;
import ar.edu.unq.americana.events.ioc.update.UpdateEvent;

public class ConcreteDeltaState implements DeltaState {

	private double delta;
	private Set<Key> pressedKeys;
	private Set<Key> releasedKeys;
	private Set<Key> holdedKeys;
	private Point2D.Double lastMousePosition;
	private Point2D.Double currentMousePosition;
	private Set<MouseButton> pressedMouseButtons;
	private Set<MouseButton> releasedMouseButtons;
	private Set<MouseButton> holdedMouseButtons;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public ConcreteDeltaState() {
		this.setPressedKeys(new HashSet<Key>());
		this.setReleasedKeys(new HashSet<Key>());
		this.setHoldedKeys(new HashSet<Key>());
		this.setLastMousePosition(new Point2D.Double(-100, -100));
		this.setCurrentMousePosition(new Point2D.Double(-100, -100));
		this.setPressedMouseButtons(new HashSet<MouseButton>());
		this.setHoldedMouseButtons(new HashSet<MouseButton>());
		this.setReleasedMouseButtons(new HashSet<MouseButton>());

		this.reset();
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public boolean isKeyPressed(final Key key) {
		return this.getPressedKeys().contains(key);
	}

	@Override
	public boolean isKeyReleased(final Key key) {
		return this.getReleasedKeys().contains(key);
	}

	@Override
	public boolean isKeyBeingHold(final Key key) {
		return this.getHoldedKeys().contains(key);
	}

	@Override
	public boolean hasMouseMoved() {
		return this.getCurrentMousePosition() != this.getLastMousePosition();
	}

	@Override
	public boolean isMouseButtonPressed(final MouseButton button) {
		return this.getPressedMouseButtons().contains(button);
	}

	@Override
	public boolean isMouseButtonReleased(final MouseButton button) {
		return this.getReleasedMouseButtons().contains(button);
	}

	@Override
	public boolean isMouseButtonBeingHold(final MouseButton button) {
		return this.getHoldedMouseButtons().contains(button);
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void reset() {
		this.getPressedKeys().clear();
		this.getReleasedKeys().clear();
		this.setLastMousePosition(this.getCurrentMousePosition());
		this.getPressedMouseButtons().clear();
		this.getReleasedMouseButtons().clear();
	}

	public void setKeyPressed(final Key key) {
		this.getPressedKeys().add(key);
		this.getHoldedKeys().add(key);
		EventManager.fire(new KeyboarEvent(EventType.Pressed, key), this);
	}

	public void setKeyReleased(final Key key) {
		this.getReleasedKeys().add(key);
		this.getHoldedKeys().remove(key);
		EventManager.fire(new KeyboarEvent(EventType.Released, key), this);
	}

	public void setMousePosition(final Point2D.Double position) {
		this.setCurrentMousePosition(position);
		if (!position.equals(this.getLastMousePosition())) {
			EventManager.fire(new MouseMoveEvent(), this);
		}
	}

	public void setMouseButtonPressed(final MouseButton button) {
		this.getPressedMouseButtons().add(button);
		this.getHoldedMouseButtons().add(button);
		EventManager.fire(new MouseEvent(EventType.Pressed, button), this);
	}

	public void setMouseButtonReleased(final MouseButton button) {
		this.getReleasedMouseButtons().add(button);
		this.getHoldedMouseButtons().remove(button);
		EventManager.fire(new MouseEvent(EventType.Released, button), this);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	@Override
	public double getDelta() {
		return this.delta;
	}

	public void setDelta(final double delta) {
		this.delta = delta;
		EventManager.fire(new UpdateEvent(), this);
	}

	protected Set<Key> getPressedKeys() {
		return this.pressedKeys;
	}

	protected void setPressedKeys(final Set<Key> pressedKeys) {
		this.pressedKeys = pressedKeys;
	}

	protected Set<Key> getReleasedKeys() {
		return this.releasedKeys;
	}

	protected void setReleasedKeys(final Set<Key> releasedKeys) {
		this.releasedKeys = releasedKeys;
	}

	protected Set<Key> getHoldedKeys() {
		return this.holdedKeys;
	}

	protected void setHoldedKeys(final Set<Key> holdedKeys) {
		this.holdedKeys = holdedKeys;
	}

	@Override
	public Point2D.Double getLastMousePosition() {
		return this.lastMousePosition;
	}

	protected void setLastMousePosition(final Point2D.Double lastMousePosition) {
		this.lastMousePosition = lastMousePosition;
	}

	@Override
	public Point2D.Double getCurrentMousePosition() {
		return this.currentMousePosition;
	}

	protected void setCurrentMousePosition(
			final Point2D.Double currentMousePosition) {
		this.currentMousePosition = currentMousePosition;
	}

	protected Set<MouseButton> getPressedMouseButtons() {
		return this.pressedMouseButtons;
	}

	protected void setPressedMouseButtons(
			final Set<MouseButton> pressedMouseButtons) {
		this.pressedMouseButtons = pressedMouseButtons;
	}

	protected Set<MouseButton> getReleasedMouseButtons() {
		return this.releasedMouseButtons;
	}

	protected void setReleasedMouseButtons(
			final Set<MouseButton> releasedMouseButtons) {
		this.releasedMouseButtons = releasedMouseButtons;
	}

	protected Set<MouseButton> getHoldedMouseButtons() {
		return this.holdedMouseButtons;
	}

	protected void setHoldedMouseButtons(
			final Set<MouseButton> holdedMouseButtons) {
		this.holdedMouseButtons = holdedMouseButtons;
	}

	@Override
	public void fireEvents() {
		for (final Key key : this.getHoldedKeys()) {
			EventManager.fire(new KeyboarEvent(EventType.BeingHold, key), this);
		}

		for (final MouseButton button : this.getHoldedMouseButtons()) {
			EventManager
					.fire(new MouseEvent(EventType.BeingHold, button), this);
		}
	}
}