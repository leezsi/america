package ar.edu.unq.americana.scenes.components.tilemap;

public interface Positionable {

	public int getColumn();

	public void fixColumn(int delta);

	public int getRow();

	public void fixRow(int delta);

}
