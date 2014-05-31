package ar.edu.unq.americana.ia.pathfindier;

public interface Node {

	public boolean isAccessible();

	public boolean isTarget();

	public boolean isClose();

	public void setParent(Node node);

	public int f();

	public void close();

	public int row();

	public int column();

	public Node getParent();
}
