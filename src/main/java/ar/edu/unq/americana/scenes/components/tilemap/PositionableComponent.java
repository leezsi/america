package ar.edu.unq.americana.scenes.components.tilemap;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;

public class PositionableComponent<SceneType extends GameScene> extends
		GameComponent<SceneType> implements Positionable {
	private int column;
	private int row;
	private int rColumn;
	private int rRow;

	protected void resetPosition(final int row, final int column) {
		this.rRow = row;
		this.rColumn = column;
	}

	protected void resetPosition() {
		this.row = this.rRow;
		this.column = this.rColumn;
	}

	protected void setColumn(final int column) {
		this.column = column;
	}

	protected void setRow(final int row) {
		this.row = row;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public void fixColumn(final int delta) {
		this.column += delta;
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public void fixRow(final int delta) {
		this.row += delta;
	}
}
