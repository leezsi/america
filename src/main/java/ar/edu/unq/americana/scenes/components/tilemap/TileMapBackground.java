package ar.edu.unq.americana.scenes.components.tilemap;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;

public class TileMapBackground extends GameComponent<GameScene> {

	public TileMapBackground(final int tileWidth, final int tileHeight,
			final ITileMapResourceProvider tileMapResourceProvider) {
		this.setAppearance(new TileMapBackgroundAppearance(tileWidth,
				tileHeight, tileMapResourceProvider));
	}

}
