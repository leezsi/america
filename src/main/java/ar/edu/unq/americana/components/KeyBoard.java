package ar.edu.unq.americana.components;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.constants.Key;

@SuppressWarnings("rawtypes")
public class KeyBoard extends GameComponent {

	private static final KeyBoard INSTANCE = new KeyBoard();

	private KeyBoard() {
		this.setAppearance(new Invisible());
	}

	public static KeyBoard get() {
		return INSTANCE;
	}

	@Override
	public void update(final DeltaState deltaState) {
		for (final Key key : Key.values()) {
			if (!key.equals(Key.ANY)) {
				if (deltaState.isKeyPressed(key)) {
					ComponentUtils.fireKeyPressed(this.getScene()
							.getComponents(), key, deltaState);
				}
				if (deltaState.isKeyBeingHold(key)) {
					ComponentUtils.fireKeyBeingHold(this.getScene()
							.getComponents(), key, deltaState);
				}
				if (deltaState.isKeyReleased(key)) {
					ComponentUtils.fireKeyReleased(this.getScene()
							.getComponents(), key, deltaState);
				}
			}
		}
		super.update(deltaState);
	}
}
