package ar.edu.unq.americana.components;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.events.annotations.Events.Update;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.scenes.camera.ICamera;
import ar.edu.unq.americana.scenes.camera.StaticCamera;

public class LifeCounter<SceneType extends GameScene> extends
		GameComponent<SceneType> {
	public static class GameOverEvent extends FiredEvent {

	}

	private int lives;
	private final Sprite image;
	private final Sprite sprite;

	public LifeCounter(final int lives, final Sprite image) {
		this.lives = lives;
		this.image = image;
		this.setZ(Integer.MAX_VALUE);
		this.sprite = this.image.repeatHorizontally(lives);
		this.setAppearance(this.sprite);
	}

	@Override
	public void onSceneActivated() {
		this.setX(this.getGame().getDisplayWidth()
				- (this.sprite.getWidth() / 2) - 10);
		this.setY((this.sprite.getHeight() / 2) + 10);
	}

	@Update
	private void update(final double delta) {
		if (this.lives > 0) {
			this.setAppearance(this.image.repeatHorizontally(this.lives));
		} else {
			this.setAppearance(Sprite.fromImage("mouse/mouse.png"));
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

	@Override
	public ICamera getCamera() {
		return new StaticCamera();
	}
}
