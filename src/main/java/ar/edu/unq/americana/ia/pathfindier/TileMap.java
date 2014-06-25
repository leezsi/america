package ar.edu.unq.americana.ia.pathfindier;

import java.util.List;

import ar.edu.unq.americana.configs.Bean;
import ar.edu.unq.americana.scenes.components.tilemap.Positionable;

@Bean
public interface TileMap {

	boolean changeTarget(Positionable target);

	Node getNode(int row, int column);

	List<Node> getAdjacents(Node current);

}
