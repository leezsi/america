package ar.edu.unq.americana.scenes.components.tilemap;

import java.awt.image.BufferedImage;

import ar.edu.unq.americana.appearances.Sprite;

public interface ITileMapResourceProvider {

	public void register(int code, Sprite tile);

	public void putAt(final int row, final int column, final int imageCode);

	public int getColumns();

	public int getRows();

	public int at(int row, int column);

	public BufferedImage forCode(int code);

}
