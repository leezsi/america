package ar.edu.unq.americana.scenes.components.tilemap;

import java.util.List;

import ar.edu.unq.americana.ia.pathfindier.Node;
import ar.edu.unq.americana.ia.pathfindier.TileMap;

public class BaseTileMap implements TileMap {

	private final Node[][] nodes;
	private final ITileMapScene scene;
	private Positionable target;

	public BaseTileMap(final ITileMapScene scene, final int tileWidth,
			final int tileHeight,
			final ITileMapResourceProvider tileMapResourceProvider) {
		this.nodes = new Node[scene.rowsCount()][scene.columnsCount()];
		this.scene = scene;
		scene.addTileBackground(new TileMapBackground(tileWidth, tileHeight,
				tileMapResourceProvider));
	}

	private void generateNodes(final int rows, final int columns) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.nodes[i][j] = new Node(i, j, this.getHeristic(i, j));
			}

		}
	}

	public int getHeristic(final int row, final int column) {
		return Math.abs(row - this.target.getRow())
				+ Math.abs(column - this.target.getColumn());
	}

	@Override
	public boolean changeTarget(final Positionable target) {
		this.target = target;
		if (this.scene.isAccessible(target.getRow(), target.getColumn())) {
			this.generateNodes(this.scene.rowsCount(),
					this.scene.columnsCount());
			return true;
		}
		return false;
	}

	@Override
	public Node getNode(final int row, final int column) {
		final Node node = this.nodes[row][column];
		if (!this.scene.isAccessible(row, column)) {
			node.notAccesible();
		}
		return node;
	}

	@Override
	public List<Node> getAdjacents(final Node current) {
		return this.scene.adjacents(current);
	}

}
