package ar.edu.unq.americana.components;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Sprite;

public final class Background<SceneType extends GameScene> extends
		GameComponent<SceneType> {
	public Background(final Sprite image) {
		this.setAppearance(image);
	}

	@Override
	public void onSceneActivated() {
		this.setX(this.getGame().getDisplayWidth() / 2);
		this.setY(this.getGame().getDisplayHeight() / 2);
		this.setZ(Integer.MIN_VALUE);
	}

}