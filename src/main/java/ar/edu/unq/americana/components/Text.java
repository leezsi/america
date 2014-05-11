package ar.edu.unq.americana.components;

import java.awt.Color;
import java.awt.Font;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Label;

public class Text<SceneType extends GameScene> extends GameComponent<SceneType> {

	public Text(final String text, final Font font, final Color color) {
		this.setAppearance(new Label(font, color, text));
	}

}
