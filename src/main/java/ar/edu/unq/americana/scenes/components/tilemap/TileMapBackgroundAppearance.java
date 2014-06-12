package ar.edu.unq.americana.scenes.components.tilemap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Appearance;
import ar.edu.unq.americana.appearances.Shape;

public class TileMapBackgroundAppearance extends Shape {

	private final ITileMapResourceProvider provider;
	private final int height;
	private final int width;

	public TileMapBackgroundAppearance(final int tileWidth,
			final int tileHeight,
			final ITileMapResourceProvider tileMapResourceProvider) {
		this.provider = tileMapResourceProvider;
		this.width = tileWidth;
		this.height = tileHeight;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends Appearance> T copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(final double delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {

		for (int i = 0; i < this.provider.getColumns(); i++) {
			for (int j = 0; j < this.provider.getRows(); j++) {
				final int code = this.provider.at(j, i);
				final BufferedImage image = this.provider.forCode(code);
				final int x = (int) (this.getX() + (i * this.width))
						- (this.width / 2);
				final int y = (int) (this.getY() + (j * this.height))
						- (this.height / 2);
				graphics.drawImage(image, x, y, null);
			}
		}

	}

}
