package ar.edu.unq.americana.scenes.pause;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.Game;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.components.Button;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.game.events.GameResumeEvent;
import ar.edu.unq.americana.scenes.normal.DefaultScene;

public abstract class PauseGameScene extends DefaultScene {

	protected class MenuBuilder {

		private final PauseGameScene scene;
		private final List<Button<?>> buttons;
		private final double deltaY = 10;

		public MenuBuilder(final PauseGameScene scene) {
			this.scene = scene;
			this.buttons = new ArrayList<Button<?>>();
		}

		public void button(final String textKey, final FiredEvent event) {
			this.buttons.add(new Button<DefaultScene>(textKey,
					PauseGameScene.this.font(), event));
		}

		public void addButtons() {
			double y = (this.scene.getGame().getDisplayHeight() / 2);
			for (final Button<?> button : this.buttons) {
				this.scene.addComponent(button);
				button.onSceneActivated();
				final double height = button.getAppearance().getHeight();
				button.setX(PauseGameScene.this.getGame().getDisplayWidth() / 2);
				y += height + this.deltaY;
				button.setY(y);
			}
		}

	}

	private GameScene currentScene;
	private final MenuBuilder menuBuilder;

	public PauseGameScene() {
		super(null, null);
		this.getMouse().setAppearance(this.mouseSprite());
		this.addComponent(this.logo());
		this.menuBuilder = new MenuBuilder(this);
		this.addButtons(this.menuBuilder);
	}

	private Sprite mouseSprite() {
		Sprite mouse = Sprite.fromImage("mouse/cursor.png");
		mouse = mouse.scaleVertically(25 / mouse.getHeight());
		mouse = mouse.scaleHorizontally(25 / mouse.getWidth());
		return mouse;
	}

	protected abstract void addButtons(final MenuBuilder menuBuilder);

	@Override
	public void onSetAsCurrent() {
		super.onSetAsCurrent();
		this.menuBuilder.addButtons();
	}

	private GameComponent<PauseGameScene> logo() {
		return new GameComponent<PauseGameScene>() {
			private Sprite sprite;
			{
				this.sprite = Sprite.fromImage(PauseGameScene.this
						.logoResourcePath());

			}

			@Override
			public void onSceneActivated() {
				final PauseGameScene scene = this.getScene();
				final Game game = scene.getGame();
				final int width = game.getDisplayWidth();
				this.setX(width / 2);
				this.sprite = this.sprite.scaleHorizontally(width
						/ this.sprite.getWidth());
				final int height = game.getDisplayHeight();
				final int midHeight = height / 2;
				this.sprite = this.sprite.scaleVertically(midHeight
						/ this.sprite.getHeight());

				this.setY(midHeight - (this.sprite.getHeight() / 2));
				this.setAppearance(this.sprite);
			}

		};
	}

	@Events.Fired(GameResumeEvent.class)
	private void resume(final GameResumeEvent event) {
		this.getGame().resume(this.currentScene);
	}

	protected abstract String logoResourcePath();

	public void setReturn(final GameScene scene) {
		this.currentScene = scene;
	}

	protected abstract Font font();
}
