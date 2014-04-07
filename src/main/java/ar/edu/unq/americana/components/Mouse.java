package ar.edu.unq.americana.components;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.constants.MouseButton;

@SuppressWarnings("rawtypes")
public class Mouse extends GameComponent {

	private static final Mouse INSTANCE = new Mouse();

	private Mouse() {
		this.setZ(Integer.MAX_VALUE);
		this.setAppearance(new Invisible());
	}

	public static Mouse get() {
		return INSTANCE;
	}

	@Override
	public void update(final DeltaState deltaState) {
		for (final MouseButton button : MouseButton.values()) {
			if (!button.equals(MouseButton.ANY)) {

				if (deltaState.isMouseButtonPressed(button)) {
					ComponentUtils.fireButtonPressed(this.getScene()
							.getComponents(), button, deltaState);
				}
				if (deltaState.isMouseButtonBeingHold(button)) {
					ComponentUtils.fireButtonBeingHold(this.getScene()
							.getComponents(), button, deltaState);
				}
				if (deltaState.isMouseButtonReleased(button)) {
					ComponentUtils.fireButtonReleased(this.getScene()
							.getComponents(), button, deltaState);
				}
			}
		}
		super.update(deltaState);
	}
}
