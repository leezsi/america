package ar.edu.unq.americana.components;

import java.awt.Color;
import java.awt.Font;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Label;
import ar.edu.unq.americana.colissions.CollitionGroup;

public class Score<SceneType extends GameScene> extends
		GameComponent<SceneType> {

	private int score;
	private final int deltaScore;
	private final Label label;
	private boolean firstX = true;
	private boolean firstY = true;

	public Score(final int deltaScore, final String font, final int fontSize,
			final Color color) {
		this(deltaScore, new Font(font, Font.BOLD, 24), color);
	}

	@Override
	public void setX(final double x) {
		if (this.firstX) {
			super.setX(x);
			this.firstX = false;
		}
	}

	@Override
	public void setY(final double y) {
		if (this.firstY) {
			super.setY(y);
			this.firstY = false;
		}
	}

	@Override
	protected boolean isCollisionable() {
		return false;
	}

	public Score(final int deltaScore, final Font font, final Color color) {
		CollitionGroup.setUncollisionableGroup(this);
		this.score = 0;
		this.deltaScore = deltaScore;
		this.setZ(Integer.MAX_VALUE);
		this.label = new Label(font, color, String.valueOf(this.score));
		this.setAppearance(this.label);
		this.updateXY();
	}

	public void addPoint() {
		this.score += this.deltaScore;
		this.label.setText(String.valueOf(this.score));
		this.updateXY();
	}

	private void updateXY() {
		this.setX(10 + (this.label.getWidth() / 2));
		this.setY((this.label.getHeight() / 2));
	}

	public int getScore() {
		return this.score;
	}
}
