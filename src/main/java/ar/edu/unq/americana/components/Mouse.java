package ar.edu.unq.americana.components;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.events.game.EventManager;
import ar.edu.unq.americana.events.mouse.MouseEvent;

@SuppressWarnings("rawtypes")
public class Mouse extends GameComponent {

	private static final Mouse INSTANCE = new Mouse();
	private List<MouseEvent> events = new ArrayList<MouseEvent>();

	private Mouse() {
		this.setZ(Integer.MAX_VALUE);
		this.setAppearance(new Invisible());
	}

	public static Mouse get() {
		return INSTANCE;
	}

	public void registerComponentEvents(final GameComponent component) {
		events.addAll(EventManager.getAllMouseEvents(component));
	}

	public void privateUpdate(final DeltaState state) {
		for (final MouseEvent event : events) {
			event.apply(state);
		}
	}

	public void deresgister(final GameComponent<?> component) {
		for (final MouseEvent event : events) {
			if (event.getComponent() == component) {
				events.remove(component);
			}
		}

	}

	public void reset() {
		events = new ArrayList<MouseEvent>();
	}

	@Override
	public void update(final DeltaState deltaState) {
		final Double currentMousePosition = deltaState
				.getCurrentMousePosition();
		this.setX(currentMousePosition.getX());
		this.setY(currentMousePosition.getY());
		super.update(deltaState);
	}

}
