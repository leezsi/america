package ar.edu.unq.americana.ia.pathfindier;

import java.awt.Point;

public interface PathFinding {

	public Path find(TileMap map, Point initial, Point target);

}
