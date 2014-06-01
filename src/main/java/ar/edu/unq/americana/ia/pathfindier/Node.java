package ar.edu.unq.americana.ia.pathfindier;

public class Node {

	private boolean accesible = true;
	private boolean close = false;
	private Node parent;
	private final int heuristic;
	private final int row;
	private final int column;

	public Node(final Node parent, final int row, final int column,
			final int heuristic) {
		this.parent = parent;
		this.heuristic = heuristic;
		this.row = row;
		this.column = column;
	}

	public Node(final int row, final int column, final int heuristic) {
		this(null, row, column, heuristic);
	}

	public boolean isAccessible() {
		return this.accesible;
	}

	public boolean isTarget() {
		return this.heuristic == 0;
	}

	public boolean isClose() {
		return this.close;
	}

	public void setParent(final Node node) {
		this.parent = node;
	}

	public int f() {
		if (this.parent != null) {
			return this.parent.f()
					+ (((this.parent.row() == this.row) || (this.parent
							.column() == this.column)) ? 10 : 14)
					+ this.heuristic;
		}
		return this.heuristic;
	}

	public void close() {
		this.close = true;
	}

	public int row() {
		return this.row;
	}

	public int column() {
		return this.column;
	}

	public Node getParent() {
		return this.parent;
	}

	@Override
	public String toString() {
		return "Node [row=" + this.row + ", column=" + this.column + ", f()="
				+ this.f() + "]";
	}

	public void notAccesible() {
		this.accesible = false;
	}
}
