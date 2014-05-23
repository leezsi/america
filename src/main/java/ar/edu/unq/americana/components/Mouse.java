package ar.edu.unq.americana.components;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.annotations.Events;

public class Mouse<SceneType extends GameScene> extends
		GameComponent<SceneType> {

	private boolean leftClicked;

	public Mouse() {
		this.setAppearance(Sprite.fromImage("mouse/mouse.png"));
		this.setZ(Integer.MAX_VALUE);
	}

	@Events.Mouse.Move(oldAndNew = false)
	private void mouseMove(final double newX, final double newY) {
		this.setX(newX);
		this.setY(newY);
	}

	@Events.Mouse(type = EventType.Pressed, button = MouseButton.LEFT)
	private void leftClick(final DeltaState state) {
		this.leftClicked = true;
	}

	@Events.Mouse(type = EventType.Released, button = MouseButton.LEFT)
	private void leftReleased(final DeltaState state) {
		this.leftClicked = false;
	}

	public boolean isLeftClick() {
		return this.leftClicked;
	}

}
