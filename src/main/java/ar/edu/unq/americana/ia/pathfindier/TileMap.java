package ar.edu.unq.americana.ia.pathfindier;

import java.awt.Point;
import java.util.List;

public interface TileMap {

	void changeTarget(Point target);

	Node getNode(int row, int column);

	List<Node> getAdjacents(Node current);

}
