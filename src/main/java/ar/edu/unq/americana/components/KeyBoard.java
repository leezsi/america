package ar.edu.unq.americana.components;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.events.keyboard.KeybordEvent;

@SuppressWarnings("rawtypes")
public class KeyBoard extends GameComponent {

	private static final KeyBoard INSTANCE = new KeyBoard();

	private List<KeybordEvent> events = new ArrayList<KeybordEvent>();

	private KeyBoard() {
		this.setAppearance(new Invisible());
	}

	public static KeyBoard get() {
		return INSTANCE;
	}

	public void registerComponentEvents(final GameComponent component) {
		events.addAll(KeybordEvent.getAll(component));
	}

	public void deresgister(final GameComponent<?> component) {
		for (final KeybordEvent event : events) {
			if (event.getComponent() == component) {
				events.remove(component);
			}
		}
	}

	public void privateUpdate(final DeltaState state) {
		for (final KeybordEvent event : events) {
			event.apply(state);
		}
	}

	public void reset() {
		events = new ArrayList<KeybordEvent>();
	}
}
