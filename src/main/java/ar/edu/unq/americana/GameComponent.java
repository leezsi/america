package ar.edu.unq.americana;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Graphics2D;

import ar.edu.unq.americana.appearances.Appearance;
import ar.edu.unq.americana.appearances.Invisible;
import ar.edu.unq.americana.appearances.Shape;
import ar.edu.unq.americana.configs.Configs;
import ar.edu.unq.americana.events.ioc.EventManager;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.rules.IRule;
import ar.edu.unq.americana.utils.Vector2D;

public abstract class GameComponent<SceneType extends GameScene> {

	public void setCollitionGroup(final int collitionGroup) {
		this.collitionGroup = collitionGroup;
	}

	private SceneType scene;
	private Appearance appearance;
	private double x;
	private double y;
	private int z;
	private boolean destroyPending;
	private IRule<?, ?>[] rules;
	private Vector2D direction = new Vector2D(0, 0);
	private int collitionGroup;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public GameComponent() {
		this(new Invisible(), 0, 0);
	}

	public int getCollitionGroup() {
		return this.collitionGroup;
	}

	public GameComponent(final double x, final double y) {
		this(new Invisible(), x, y);
	}

	public GameComponent(final Appearance appearance, final double x,
			final double y) {
		Configs.injectAndReadBeans(this);
		Configs.injectConfigs(this.getClass());
		this.setAppearance((Shape) appearance);
		this.setX(x);
		this.setY(y);
		this.rules = this.rules();
	}

	protected boolean isCollisionable() {
		return true;
	}

	protected IRule<?, ?>[] rules() {
		return new IRule<?, ?>[0];
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

	public void move(final Vector2D where) {
		final Vector2D newPos = new Vector2D(this.getX(), this.getY())
				.suma(where);
		this.setX(newPos.getX());
		this.setY(newPos.getY());
	}

	public void destroy() {
		this.setDestroyPending(true);
	}

	protected void fire(final FiredEvent event) {
		EventManager.fire(event);

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
		this.alignLeftTo(x + this.getAppearance().getWidth());
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
		final double bottomDistance = abs((ownBounds.getHeight() + ownBounds
				.getY()) - targetBounds.getY());
		final double targetRight = targetBounds.getX()
				+ targetBounds.getWidth();
		final double leftDistance = abs(ownBounds.getX() - targetRight);
		final double targetBottom = targetBounds.getY()
				+ targetBounds.getHeight();
		final double topDistance = abs(ownBounds.getY() - targetBottom);
		final double rightDistance = abs((ownBounds.getX() + ownBounds
				.getWidth()) - targetBounds.getX());
		final double minDistance = min(bottomDistance,
				min(leftDistance, min(topDistance, rightDistance)));

		if (minDistance == bottomDistance) {
			this.alignBottomTo(targetBounds.getY());
		} else if (minDistance == leftDistance) {
			this.alignLeftTo(targetRight);
		} else if (minDistance == topDistance) {
			this.alignTopTo(targetBottom);
		} else {
			this.alignRightTo(targetBounds.getX());
		}
	}

	// ****************************************************************
	// ** GAME OPERATIONS
	// ****************************************************************

	public void render(final Graphics2D graphics) {
		this.getAppearance().render(this, graphics);
	}

	@SuppressWarnings("unchecked")
	public void update(final DeltaState deltaState) {

		for (final IRule<?, ?> rule : this.rules) {
			if (((IRule<GameComponent<?>, SceneType>) rule).mustApply(this,
					this.getScene())) {
				((IRule<GameComponent<SceneType>, SceneType>) rule).apply(this,
						this.getScene());
				break;
			}
		}

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

	public Vector2D getDirection() {
		return this.direction;
	}

	public void setDirection(final double x, final double y) {
		this.direction = new Vector2D(x, y).asVersor();
	}

	public void changeRules(final IRule<?, ?>[] rules) {
		this.rules = rules;
	}

}
