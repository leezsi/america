package ar.edu.unq.americana.scenes.components.tilemap;

import java.util.List;

import ar.edu.unq.americana.ia.pathfindier.Node;
import ar.edu.unq.americana.ia.pathfindier.TileMap;

public interface ITileMapScene {

	int columnsCount();

	int rowsCount();

	boolean isAccessible(int row, int column);

	List<Node> adjacents(Node node);

	TileMap getTileMap();

}
