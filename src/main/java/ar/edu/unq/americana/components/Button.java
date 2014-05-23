package ar.edu.unq.americana.components;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.ButtonAppearance;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.colissions.CollisionDetector;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.annotations.Events;

public class Button<SceneType extends GameScene> extends
		GameComponent<SceneType> {

	private final Font font;
	private final Color color;
	private final String text;
	private final Runnable action;

	public Button(final String text, final Font font, final Color color,
			final Sprite background, final Runnable action) {
		this.setAppearance(new Invisible());
		this.font = font;
		this.text = text;
		this.color = color;
		this.action = action;
	}

	@Override
	public void onSceneActivated() {
		super.onSceneActivated();
		final ResourceBundle localeBoundle = this.getGame().getLocaleBoundle();
		ButtonAppearance appearance;
		this.setAppearance(appearance = new ButtonAppearance(this.font,
				this.color, localeBoundle.getString(this.text)));
		this.setX(this.getX() < 0 ? this.getGame().getDisplayWidth() / 2 : this
				.getX());
		this.setY((this.getY() * (appearance.getHeight() + 20)) + 30);
		this.setAppearance(appearance);
	}

	@Events.Mouse(type = EventType.Released, button = MouseButton.LEFT)
	private void clicked(final DeltaState state) {
		if (CollisionDetector.perfectPixel(this, this.getScene().getMouse())) {
			this.action.run();
		}
	}

}
