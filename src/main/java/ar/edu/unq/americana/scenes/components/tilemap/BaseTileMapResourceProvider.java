package ar.edu.unq.americana.scenes.components.tilemap;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import ar.edu.unq.americana.appearances.Sprite;

public class BaseTileMapResourceProvider implements ITileMapResourceProvider {

	private static final BufferedImage nullImage = new BufferedImage(1, 1,
			BufferedImage.SCALE_DEFAULT);
	private final Map<Integer, BufferedImage> tileCodes = new HashMap<Integer, BufferedImage>();
	private final int[][] tiles;
	private int columns;
	private int rows;

	public BaseTileMapResourceProvider(final int rows, final int columns) {
		this.tiles = new int[this.rows = rows][this.columns = columns];

	}

	@Override
	public int getColumns() {
		return this.columns;
	}

	@Override
	public int getRows() {
		return this.rows;
	}

	@Override
	public void register(final int code, final Sprite tile) {
		this.tileCodes.put(code, tile.getImage());
	}

	@Override
	public void putAt(final int row, final int column, final int imageCode) {
		this.tiles[row][column] = imageCode;
	}

	@Override
	public int at(final int row, final int column) {
		return this.tiles[row][column];
	}

	@Override
	public BufferedImage forCode(final int code) {
		final BufferedImage bufferedImage = this.tileCodes.get(code);
		if (bufferedImage == null) {
			return nullImage;
		}
		return bufferedImage;
	}

}
