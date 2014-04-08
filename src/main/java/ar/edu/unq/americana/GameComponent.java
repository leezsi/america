package ar.edu.unq.americana;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.appearances.Appearance;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.appearances.Shape;
import ar.edu.unq.americana.utils.Vector2D;

public class GameComponent<SceneType extends GameScene> {

	private SceneType scene;
	private Appearance appearance;
	private double x;
	private double y;
	private int z;
	private boolean destroyPending;

	private final List<GameComponent<?>> collides = new ArrayList<GameComponent<?>>();
	private Vector2D appearanceOffset = new Vector2D(0, 0);

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public GameComponent() {
		this(new Invisible(), 0, 0);
	}

	public GameComponent(final double x, final double y) {
		this(new Invisible(), x, y);
	}

	public GameComponent(final Appearance appearance, final double x,
			final double y) {
		this.setAppearance((Shape) appearance);
		this.setX(x);
		this.setY(y);
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public Game getGame() {
		return this.getScene().getGame();
	}

	// ****************************************************************
	// ** INITIALIZATION
	// ****************************************************************

	public void onSceneActivated() {
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void setAppearenceOffset(final Vector2D vector) {
	}

	public void move(final double dx, final double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}

	public void destroy() {
		this.setDestroyPending(true);
	}

	// ****************************************************************
	// ** ALIGNMENT OPERATIONS
	// ****************************************************************

	public void alignTopTo(final double y) {
		this.move(0, y - this.getY());
	}

	public void alignLeftTo(final double x) {
		this.move(x - this.getX(), 0);
	}

	public void alignBottomTo(final double y) {
		this.alignTopTo(y + this.getAppearance().getHeight());
	}

	public void alignRightTo(final double x) {
		this.alignRightTo(x + this.getAppearance().getWidth());
	}

	public void alignHorizontalCenterTo(final double x) {
		this.alignLeftTo(x - (this.getAppearance().getWidth() / 2));
	}

	public void alignVerticalCenterTo(final double y) {
		this.alignTopTo(y - (this.getAppearance().getHeight() / 2));
	}

	public void alignCloserBoundTo(final GameComponent<?> target) {
		final Appearance ownBounds = this.getAppearance();
		final Appearance targetBounds = target.getAppearance();
		final double bottomDistance = abs((ownBounds.getHeight() + this.getY())
				- target.getY());
		final double targetRight = target.getX() + targetBounds.getWidth();
		final double leftDistance = abs(this.getX() - targetRight);
		final double targetBottom = target.getY() + targetBounds.getHeight();
		final double topDistance = abs(this.getY() - targetBottom);
		final double rightDistance = abs((this.getX() + ownBounds.getWidth())
				- target.getX());
		final double minDistance = min(bottomDistance,
				min(leftDistance, min(topDistance, rightDistance)));

		if (minDistance == bottomDistance) {
			this.alignBottomTo(target.getY());
		} else if (minDistance == leftDistance) {
			this.alignLeftTo(targetRight);
		} else if (minDistance == topDistance) {
			this.alignTopTo(targetBottom);
		} else {
			this.alignRightTo(target.getX());
		}
	}

	// ****************************************************************
	// ** GAME OPERATIONS
	// ****************************************************************

	public void render(final Graphics2D graphics) {
		this.getAppearance().render(this, graphics);
	}

	public void update(final DeltaState deltaState) {
		this.getAppearance().update(deltaState.getDelta());
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public SceneType getScene() {
		return this.scene;
	}

	protected void setScene(final SceneType scene) {
		this.scene = scene;
	}

	public Appearance getAppearance() {
		return this.appearance;
	}

	public void setAppearance(final Shape appearance) {
		this.appearance = appearance;
		appearance.setComponent(this);
	}

	public double getX() {
		return this.x;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public int getZ() {
		return this.z;
	}

	public void setZ(final int z) {
		this.z = z;
	}

	public boolean isDestroyPending() {
		return this.destroyPending;
	}

	protected void setDestroyPending(final boolean destroyPending) {
		this.destroyPending = destroyPending;
	}

	public Vector2D getAppearanceOffset() {
		return appearanceOffset;
	}

	protected void setAppearenceOffset(final int dx, final int x, final int dy,
			final int y) {
		final Vector2D xOffset = new Vector2D(dx, 0).asVersor().producto(x);
		final Vector2D yOffset = new Vector2D(0, dy).asVersor().producto(y);
		this.appearanceOffset = xOffset.suma(yOffset);
	}
}