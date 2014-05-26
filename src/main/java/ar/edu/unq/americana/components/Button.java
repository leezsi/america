package ar.edu.unq.americana.components;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.ButtonAppearance;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.colissions.CollisionDetector;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.scenes.normal.DefaultScene;

public class Button<SceneType extends DefaultScene> extends
		GameComponent<SceneType> {

	private final Font font;
	private final Color color;
	private final String text;
	private final FiredEvent action;

	public Button(final String text, final Font font, final FiredEvent action) {
		this.setAppearance(new Invisible());
		this.font = font;
		this.text = text;
		this.color = Color.black;
		this.action = action;
	}

	@Override
	public void onSceneActivated() {
		super.onSceneActivated();
		final ResourceBundle localeBoundle = this.getGame().getLocaleBoundle();
		this.setAppearance(new ButtonAppearance(this.font, this.color,
				localeBoundle.getString(this.text)));
	}

	@Events.Mouse(type = EventType.Released, button = MouseButton.LEFT)
	private void clicked(final DeltaState state) {
		if (CollisionDetector.perfectPixel(this, this.getScene().getMouse())) {
			this.fire(this.action);
		}
	}

}
