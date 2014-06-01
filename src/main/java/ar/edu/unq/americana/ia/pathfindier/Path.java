package ar.edu.unq.americana.ia.pathfindier;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.scenes.components.tilemap.Positionable;

public class Path {

	private final Node node;
	private final Path child;

	public Path(final Node node) {
		this(node, null);
	}

	private Path(final Node node, final Path child) {
		this.node = node;
		this.child = child;
	}

	@Override
	public String toString() {
		final String string = this.node.toString() + "\n";
		if (this.child != null) {
			return string + this.child.toString();
		}
		return string;
	}

	public int deltaRow(final Positionable component) {
		return this.node.row() - component.getRow();
	}

	public int deltaColumn(final Positionable component) {
		return this.node.column() - component.getColumn();
	}

	public static Path makePath(final Node current) {
		return makePath(current, null);
	}

	private static Path makePath(final Node current, final Path path) {
		final Path tmpPath = new Path(current, path);
		if (current.getParent() != null) {
			return makePath(current.getParent(), tmpPath);
		}
		return tmpPath.child;
	}

	public void takeStep(final double delta, final Positionable component) {
		final int dr = this.deltaRow(component);
		final int dc = this.deltaColumn(component);
		System.err.println(component.getColumn() + " " + this.node.column()
				+ this);
		((GameComponent<?>) component).move(dc * delta, dr * delta);
	}
}
