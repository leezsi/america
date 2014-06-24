package ar.edu.unq.americana.components;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Label;

public class Text<SceneType extends GameScene> extends GameComponent<SceneType> {

	private String text;
	private final Font font;
	private final Color color;
	private String key;

	public Text(final String text, final Font font, final Color color) {
		this.text = text;
		this.color = color;
		this.font = font;
	}

	public Text(final Font font, final Color color, final String key) {
		this.key = key;
		this.color = color;
		this.font = font;
	}

	@Override
	public void onSceneActivated() {
		if (this.key != null) {
			final ResourceBundle localeBoundle = this.getGame()
					.getLocaleBoundle();
			this.text = localeBoundle.getString(this.key);
		}
		this.setAppearance(new Label(this.font, this.color, this.text));
		super.onSceneActivated();
	}
}
