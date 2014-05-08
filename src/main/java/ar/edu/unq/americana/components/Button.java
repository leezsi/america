package ar.edu.unq.americana.components;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.ButtonAppearance;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.appearances.Sprite;

public class Button<SceneType extends GameScene> extends
		GameComponent<SceneType> {

	private final Font font;
	private final Color color;
	private final String text;

	public Button(final String text, final Font font, final Color color,
			final Sprite background) {
		this.setAppearance(new Invisible());
		this.font = font;
		this.text = text;
		this.color = color;
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
		this.setY((this.getY() * appearance.getHeight()) + 20);
		this.setAppearance(appearance);
	}

}
