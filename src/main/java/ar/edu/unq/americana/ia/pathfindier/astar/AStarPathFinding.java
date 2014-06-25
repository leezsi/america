package ar.edu.unq.americana.ia.pathfindier.astar;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.ia.pathfindier.Node;
import ar.edu.unq.americana.ia.pathfindier.Path;
import ar.edu.unq.americana.ia.pathfindier.PathFinding;
import ar.edu.unq.americana.ia.pathfindier.TileMap;
import ar.edu.unq.americana.scenes.components.tilemap.Positionable;

public class AStarPathFinding implements PathFinding {

	private final List<Node> opens = new ArrayList<Node>();

	@Override
	public Path find(final TileMap map, final Positionable initial,
			final Positionable target) {
		if (map.changeTarget(target)) {
			return this.doFind(map,
					map.getNode(initial.getRow(), initial.getColumn()));
		}
		return null;
	}

	private Path doFind(final TileMap map, final Node node) {
		this.addToOpens(null, node);
		while (!this.opens.isEmpty()) {
			final Node current = this.poll();
			if (current.isTarget()) {
				return Path.makePath(current);
			}
			current.close();
			for (final Node tmp : map.getAdjacents(current)) {
				this.addToOpens(current, tmp);
			}
		}
		return null;
	}

	private Node poll() {
		return this.opens.remove(0);
	}

	private void addToOpens(final Node parent, final Node node) {
		if (this.opens.contains(node)) {
			final int oldCost = node.f();
			final Node oldParent = node.getParent();
			node.setParent(parent);
			if (oldCost < node.f()) {
				node.setParent(oldParent);
			}
			this.opens.remove(node);
		} else {
			node.setParent(parent);
		}
		this.opens.add(this.getIndex(node), node);
	}

	private int getIndex(final Node node) {
		final int f = node.f();
		for (int i = 0; i < this.opens.size(); i++) {
			if (this.opens.get(i).f() > f) {
				return i;
			}
		}
		return this.opens.size();
	}

}
