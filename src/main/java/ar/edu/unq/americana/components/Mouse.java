package ar.edu.unq.americana.components;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.events.annotations.Events;

public class Mouse<SceneType extends GameScene> extends
		GameComponent<SceneType> {

	public Mouse() {
		this.setAppearance(new Invisible());
		this.setZ(Integer.MAX_VALUE);
	}

	@Events.Mouse.Move(oldAndNew = false)
	private void mouseMove(final double newX, final double newY) {
		this.setX(newX);
		this.setY(newY);
	}

}
