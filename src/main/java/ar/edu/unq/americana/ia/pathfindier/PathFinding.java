package ar.edu.unq.americana.ia.pathfindier;

import ar.edu.unq.americana.scenes.components.tilemap.Positionable;

public interface PathFinding {

	public Path find(TileMap map, Positionable initial, Positionable target);

}
