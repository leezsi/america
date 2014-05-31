package ar.edu.unq.americana.ia.pathfindier.astar;

import ar.edu.unq.americana.ia.pathfindier.Node;

public class AStarNode implements Node {

	private boolean accesible = true;
	private boolean close = false;
	private Node parent;
	private final int heuristic;
	private final int row;
	private final int column;

	public AStarNode(final Node parent, final int row, final int column,
			final int heuristic) {
		this.parent = parent;
		this.heuristic = heuristic;
		this.row = row;
		this.column = column;
	}

	public AStarNode(final int row, final int column, final int heuristic) {
		this(null, row, column, heuristic);
	}

	@Override
	public boolean isAccessible() {
		return this.accesible;
	}

	@Override
	public boolean isTarget() {
		return this.heuristic == 0;
	}

	@Override
	public boolean isClose() {
		return this.close;
	}

	@Override
	public void setParent(final Node node) {
		this.parent = node;
	}

	@Override
	public int f() {
		if (this.parent != null) {
			return this.parent.f()
					+ (((this.parent.row() == this.row) || (this.parent
							.column() == this.column)) ? 10 : 14)
					+ this.heuristic;
		}
		return this.heuristic;
	}

	@Override
	public void close() {
		this.close = true;
	}

	@Override
	public int row() {
		return this.row;
	}

	@Override
	public int column() {
		return this.column;
	}

	@Override
	public Node getParent() {
		return this.parent;
	}

	@Override
	public String toString() {
		return "AStarNode [row=" + this.row + ", column=" + this.column
				+ ", f()=" + this.f() + "]";
	}

	public void notAccesible() {
		this.accesible = false;
	}

}
