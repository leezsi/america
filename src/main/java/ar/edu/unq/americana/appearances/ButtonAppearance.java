package ar.edu.unq.americana.appearances;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ar.edu.unq.americana.GameComponent;

public class ButtonAppearance extends Label implements IImageGet {
	private Sprite sprite;

	public ButtonAppearance(final Font font, final Color color,
			final String... text) {
		super(font, color, text);
		this.sprite = Sprite.fromImage("button/buttonDefault.png");
	}

	@Override
	public void setComponent(final GameComponent<?> component) {
		this.sprite.setComponent(component);
		super.setComponent(component);
		this.sprite = this.sprite.scaleHorizontally(super.getWidth()
				/ this.sprite.getWidth());
		this.sprite = this.sprite.scaleVertically(super.getHeight()
				/ this.sprite.getHeight());
		this.sprite.setComponent(component);
		this.changeOffset(-1, super.getWidth() / 2, -1,
				((super.getHeight() * 2) / 3));
	}

	@Override
	public double getWidth() {
		return this.sprite.getWidth();
	}

	@Override
	public double getHeight() {
		return this.sprite.getHeight();
	}

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {
		this.sprite.render(component, graphics);
		super.render(component, graphics);
	}

	@Override
	public ButtonAppearance copy() {
		return new ButtonAppearance(this.getFont(), this.getColor(), this
				.getTextLines().toArray(new String[this.getTextLines().size()]));
	}

	@Override
	public BufferedImage getImage() {
		return this.sprite.getImage();
	}

}
