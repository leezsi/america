package ar.edu.unq.americana.components;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.colissions.CollitionGroup;
import ar.edu.unq.americana.events.annotations.Events.Update;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public class LifeCounter<SceneType extends GameScene> extends
		GameComponent<SceneType> {
	public static class GameOverEvent extends FiredEvent {

	}

	private int lives;
	private final Sprite image;

	public LifeCounter(final int lives, final Sprite image) {
		CollitionGroup.setUncollisionableGroup(this);
		this.lives = lives;
		this.image = image;
		this.setZ(Integer.MAX_VALUE);
		this.setAppearance(this.image.repeatHorizontally(lives));
	}

	@Update
	private void update(final double delta) {
		if (this.lives > 0) {
			this.setAppearance(this.image.repeatHorizontally(this.lives));
		} else {
			this.setAppearance(new Invisible());
		}
	}

	public void lossLife() {
		this.lives--;
		if (this.lives == 0) {
			this.fire(new GameOverEvent());
		}
	}

	public boolean isDead() {
		return this.lives == 0;
	}
}
