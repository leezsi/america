package ar.edu.unq.americana.scenes.components.tilemap;

public class StandPosition implements Positionable {

	private final int column;
	private final int row;

	public StandPosition(final int row, final int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public void fixColumn(final int delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public void fixRow(final int delta) {
		// TODO Auto-generated method stub

	}

}
