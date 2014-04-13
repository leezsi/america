package ar.edu.unq.americana.components;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.events.game.EventManager;
import ar.edu.unq.americana.events.keyboard.KeyboardEvent;

@SuppressWarnings("rawtypes")
public class KeyBoard extends GameComponent {

	private static final KeyBoard INSTANCE = new KeyBoard();

	private List<KeyboardEvent> events = new ArrayList<KeyboardEvent>();

	private KeyBoard() {
		this.setAppearance(new Invisible());
	}

	public static KeyBoard get() {
		return INSTANCE;
	}

	public void registerComponentEvents(final GameComponent component) {
		events.addAll(EventManager.getAllKeyboardEvents(component));
	}

	public void deresgister(final GameComponent<?> component) {
		for (final KeyboardEvent event : events) {
			if (event.getComponent() == component) {
				events.remove(component);
			}
		}
	}

	public void privateUpdate(final DeltaState state) {
		for (final KeyboardEvent event : events) {
			event.apply(state);
		}
	}

	public void reset() {
		events = new ArrayList<KeyboardEvent>();
	}
}
