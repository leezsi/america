package ar.edu.unq.americana.ia.pathfindier;

public class Path {

	private Path parent;
	private final Node node;

	public Path(final Node node) {
		this.node = node;
		if (node != null) {
			this.parent = new Path(node.getParent());
		}
	}

	@Override
	public String toString() {
		if (this.parent == null) {
			return "";
		}
		String string = this.parent.toString();
		string += this.node.toString() + "\n";
		return string;
	}

}
