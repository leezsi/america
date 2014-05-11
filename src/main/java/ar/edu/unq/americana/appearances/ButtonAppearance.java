package ar.edu.unq.americana.appearances;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.exceptions.GameException;

public class ButtonAppearance extends Label implements IImageGet {
	private BufferedImage defaultImage;

	public ButtonAppearance(final Font font, final Color color,
			final String... text) {
		super(font, color, text);
		final ClassLoader provider = ClassLoader.getSystemClassLoader();

		try {
			this.defaultImage = ImageIO.read(provider
					.getResource("button/buttonDefault.png"));
		} catch (final IOException e) {
			throw new GameException(e);
		}
	}

	@Override
	public void setComponent(final GameComponent<?> component) {
		super.setComponent(component);
		this.defaultImage = this.scaleImage(this.defaultImage);
	}

	private BufferedImage scaleImage(final BufferedImage image) {
		final AffineTransform affineTransform = AffineTransform
				.getScaleInstance(this.getWidth() / image.getWidth(),
						this.getHeight() / image.getHeight());
		final AffineTransformOp transformOperation = new AffineTransformOp(
				affineTransform, AffineTransformOp.TYPE_BICUBIC);
		return transformOperation.filter(
				image,
				new BufferedImage((int) (image.getWidth() * Math
						.abs(affineTransform.getScaleX())), (int) (image
						.getHeight() * Math.abs(affineTransform.getScaleY())),
						image.getType()));
	}

	@Override
	public double getWidth() {
		return super.getWidth() + 20;
	}

	@Override
	public double getHeight() {
		return super.getHeight() + 10;
	}

	@Override
	public ButtonAppearance copy() {
		return new ButtonAppearance(this.getFont(), this.getColor(),
				(String[]) this.getTextLines().toArray());
	}

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {
		graphics.drawImage(this.defaultImage, (int) this.getX() - 10,
				(int) this.getY() + 5, null);
		super.render(component, graphics);
	}

	@Override
	public BufferedImage getImage() {
		return this.defaultImage;
	}

}
