package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;

import ar.edu.unq.americana.GameComponent;

public class Animation extends Shape {
	private double meantime;
	private Sprite[] sprites;
	private int currentIndex;
	private double remainingTime;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Animation(final double meantime, final Sprite... sprites) {
		this.setMeantime(meantime);
		this.setSprites(sprites);
		this.setCurrentIndex(0);
		this.setRemainingTime(meantime);
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.getCurrentSprite().getWidth();
	}

	@Override
	public double getHeight() {
		return this.getCurrentSprite().getHeight();
	}

	public double getDuration() {
		return this.getMeantime() * this.getSprites().length;
	}

	protected Sprite getCurrentSprite() {
		return this.getSprites()[this.getCurrentIndex()];
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	@Override
	public void update(final double delta) {
		this.setRemainingTime(this.getRemainingTime() - delta);

		if (this.getRemainingTime() <= 0) {
			this.advance();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Animation copy() {
		return new Animation(this.getMeantime(), this.getSprites());
	}

	protected void advance() {
		this.setCurrentIndex(this.getCurrentIndex() + 1);

		if (this.getCurrentIndex() >= this.getSprites().length) {
			this.setCurrentIndex(0);
		}

		this.setRemainingTime(this.getMeantime() - this.getRemainingTime());
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	protected double getMeantime() {
		return meantime;
	}

	protected void setMeantime(final double meantime) {
		this.meantime = meantime;
	}

	protected Sprite[] getSprites() {
		return sprites;
	}

	protected void setSprites(final Sprite... sprites) {
		this.sprites = sprites;
	}

	protected int getCurrentIndex() {
		return currentIndex;
	}

	protected void setCurrentIndex(final int index) {
		currentIndex = index;
	}

	protected double getRemainingTime() {
		return remainingTime;
	}

	protected void setRemainingTime(final double remainingTime) {
		this.remainingTime = remainingTime;
	}

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {
		this.getCurrentSprite().setComponent(component);
		this.getCurrentSprite().render(component, graphics);
	}

}
