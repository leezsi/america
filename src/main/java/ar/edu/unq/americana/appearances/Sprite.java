package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ar.edu.unq.americana.exceptions.GameException;
import ar.edu.unq.americana.utils.ClassLoaderResourcesProvider;
import ar.edu.unq.americana.utils.ResourceProvider;

@SuppressWarnings("unchecked")
public class Sprite extends SimpleAppearance<Sprite> {

	private BufferedImage image;
	public static ResourceProvider defaultResourceProvider = new ClassLoaderResourcesProvider();

	// ****************************************************************
	// ** STATIC
	// ****************************************************************

	public static Sprite fromImage(final String imageFileName) {
		return fromImage(imageFileName, defaultResourceProvider);
	}

	public static Sprite fromImage(final String imageFileName,
			final ResourceProvider provider) {
		BufferedImage image;

		try {
			image = ImageIO.read(provider.getResource(imageFileName));
		} catch (final Exception e) {
			throw new GameException("The resource '" + imageFileName
					+ "' was not found using " + provider.getClass().getName(),
					e);
		}

		return new Sprite(image);
	}

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Sprite(final BufferedImage image) {
		this.setImage(image);
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.getImage().getWidth();
	}

	@Override
	public double getHeight() {
		return this.getImage().getHeight();
	}

	protected BufferedImage getTransformedImage(
			final AffineTransform transformation) {
		final AffineTransformOp transformOperation = new AffineTransformOp(
				transformation, AffineTransformOp.TYPE_BICUBIC);

		return transformOperation.filter(
				this.getImage(),
				new BufferedImage((int) (this.getImage().getWidth() * Math
						.abs(transformation.getScaleX())), (int) (this
						.getImage().getHeight() * Math.abs(transformation
						.getScaleY())), this.getImage().getType()));
	}

	// ****************************************************************
	// ** TRANSFORMATIONS
	// ****************************************************************

	@Override
	public Sprite scale(final double scaleX, final double scaleY) {
		return new Sprite(this.getTransformedImage(AffineTransform
				.getScaleInstance(scaleX, scaleY)));
	}

	public Sprite rotate(final double radians) {
		final BufferedImage newImage = new BufferedImage((int) this.getWidth(),
				(int) this.getHeight(), this.getImage().getType());

		final Graphics2D graphics = newImage.createGraphics();
		graphics.rotate(radians, this.getWidth() / 2, this.getHeight() / 2);
		graphics.drawImage(this.getImage(), null, 0, 0);
		graphics.dispose();

		return new Sprite(newImage);
	}

	// ****************************************************************
	// ** FLIPING
	// ****************************************************************

	public Sprite flipHorizontally() {
		final AffineTransform transformation = new AffineTransform();
		transformation.translate(this.getImage().getWidth(), 0);
		transformation.scale(-1, 1);

		return new Sprite(this.getTransformedImage(transformation));
	}

	public Sprite flipVertically() {
		final AffineTransform transformation = new AffineTransform();
		transformation.translate(0, this.getImage().getHeight());
		transformation.scale(1, -1);

		return new Sprite(this.getTransformedImage(transformation));
	}

	// ****************************************************************
	// ** CROPPING
	// ****************************************************************

	public Sprite crop(final int width, final int height) {
		return this.crop(0, 0, width, height);
	}

	public Sprite crop(final int x, final int y, final int width,
			final int height) {
		return new Sprite(this.getImage().getSubimage(x, y, width, height));
	}

	// ****************************************************************
	// ** REPEATING
	// ****************************************************************

	public Sprite repeat(final double horizontalRepetitions,
			final double verticalRepetitions) {
		final double horizontalIterations = Math.ceil(horizontalRepetitions);
		final double verticalIterations = Math.ceil(verticalRepetitions);
		final BufferedImage newImage = new BufferedImage( //
				(int) (this.getWidth() * horizontalRepetitions), //
				(int) (this.getHeight() * verticalRepetitions), //
				this.getImage().getType() //
		);
		final Graphics2D graphics = newImage.createGraphics();

		for (int i = 0; i < horizontalIterations; i++) {
			for (int j = 0; j < verticalIterations; j++) {
				graphics.drawImage(this.getImage(), i * (int) this.getWidth(),
						j * (int) this.getHeight(), null);
			}
		}

		graphics.dispose();

		return new Sprite(newImage);
	}

	public Sprite repeatHorizontally(final double repetitions) {
		return this.repeat(repetitions, 1);
	}

	public Sprite repeatVertically(final double repetitions) {
		return this.repeat(1, repetitions);
	}

	public Sprite repeatHorizontallyToCover(final double width) {
		return this.repeatHorizontally(width / this.getWidth());
	}

	public Sprite repeatVerticallyToCover(final double height) {
		return this.repeatVertically(height / this.getHeight());
	}

	public Sprite repeatToCover(final double width, final double height) {
		return this.repeat(width / this.getWidth(), height / this.getHeight());
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(final double delta) {
	}

	@Override
	protected void doRenderAt(final int x, final int y,
			final Graphics2D graphics) {
		graphics.drawImage(this.getImage(), x, y, null);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public BufferedImage getImage() {
		return this.image;
	}

	protected void setImage(final BufferedImage currentImage) {
		this.image = currentImage;
	}

}
package ar.edu.unq.americana.appearances;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ar.edu.unq.americana.exceptions.GameException;
import ar.edu.unq.americana.utils.ClassLoaderResourcesProvider;
import ar.edu.unq.americana.utils.ResourceProvider;

@SuppressWarnings("unchecked")
public class Sprite extends SimpleAppearance<Sprite> {

	private BufferedImage image;
	public static ResourceProvider defaultResourceProvider = new ClassLoaderResourcesProvider();

	// ****************************************************************
	// ** STATIC
	// ****************************************************************

	public static Sprite fromImage(final String imageFileName) {
		return fromImage(imageFileName, defaultResourceProvider);
	}

	public static Sprite fromImage(final String imageFileName,
			final ResourceProvider provider) {
		BufferedImage image;

		try {
			image = ImageIO.read(provider.getResource(imageFileName));
		} catch (final Exception e) {
			throw new GameException("The resource '" + imageFileName
					+ "' was not found using " + provider.getClass().getName(),
					e);
		}

		return new Sprite(image);
	}

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Sprite(final BufferedImage image) {
		this.setImage(image);
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		return this.getImage().getWidth();
	}

	@Override
	public double getHeight() {
		return this.getImage().getHeight();
	}

	protected BufferedImage getTransformedImage(
			final AffineTransform transformation) {
		final AffineTransformOp transformOperation = new AffineTransformOp(
				transformation, AffineTransformOp.TYPE_BICUBIC);

		return transformOperation.filter(
				this.getImage(),
				new BufferedImage((int) (this.getImage().getWidth() * Math
						.abs(transformation.getScaleX())), (int) (this
						.getImage().getHeight() * Math.abs(transformation
						.getScaleY())), this.getImage().getType()));
	}

	// ****************************************************************
	// ** TRANSFORMATIONS
	// ****************************************************************

	@Override
	public Sprite scale(final double scaleX, final double scaleY) {
		return new Sprite(this.getTransformedImage(AffineTransform
				.getScaleInstance(scaleX, scaleY)));
	}

	public Sprite rotate(final double radians) {
		final BufferedImage newImage = new BufferedImage((int) this.getWidth(),
				(int) this.getHeight(), this.getImage().getType());

		final Graphics2D graphics = newImage.createGraphics();
		graphics.rotate(radians, this.getWidth() / 2, this.getHeight() / 2);
		graphics.drawImage(this.getImage(), null, 0, 0);
		graphics.dispose();

		return new Sprite(newImage);
	}

	// ****************************************************************
	// ** FLIPING
	// ****************************************************************

	public Sprite flipHorizontally() {
		final AffineTransform transformation = new AffineTransform();
		transformation.translate(this.getImage().getWidth(), 0);
		transformation.scale(-1, 1);

		return new Sprite(this.getTransformedImage(transformation));
	}

	public Sprite flipVertically() {
		final AffineTransform transformation = new AffineTransform();
		transformation.translate(0, this.getImage().getHeight());
		transformation.scale(1, -1);

		return new Sprite(this.getTransformedImage(transformation));
	}

	// ****************************************************************
	// ** CROPPING
	// ****************************************************************

	public Sprite crop(final int width, final int height) {
		return this.crop(0, 0, width, height);
	}

	public Sprite crop(final int x, final int y, final int width,
			final int height) {
		return new Sprite(this.getImage().getSubimage(x, y, width, height));
	}

	// ****************************************************************
	// ** REPEATING
	// ****************************************************************

	public Sprite repeat(final double horizontalRepetitions,
			final double verticalRepetitions) {
		final double horizontalIterations = Math.ceil(horizontalRepetitions);
		final double verticalIterations = Math.ceil(verticalRepetitions);
		final BufferedImage newImage = new BufferedImage( //
				(int) (this.getWidth() * horizontalRepetitions), //
				(int) (this.getHeight() * verticalRepetitions), //
				this.getImage().getType() //
		);
		final Graphics2D graphics = newImage.createGraphics();

		for (int i = 0; i < horizontalIterations; i++) {
			for (int j = 0; j < verticalIterations; j++) {
				graphics.drawImage(this.getImage(), i * (int) this.getWidth(),
						j * (int) this.getHeight(), null);
			}
		}

		graphics.dispose();

		return new Sprite(newImage);
	}

	public Sprite repeatHorizontally(final double repetitions) {
		return this.repeat(repetitions, 1);
	}

	public Sprite repeatVertically(final double repetitions) {
		return this.repeat(1, repetitions);
	}

	public Sprite repeatHorizontallyToCover(final double width) {
		return this.repeatHorizontally(width / this.getWidth());
	}

	public Sprite repeatVerticallyToCover(final double height) {
		return this.repeatVertically(height / this.getHeight());
	}

	public Sprite repeatToCover(final double width, final double height) {
		return this.repeat(width / this.getWidth(), height / this.getHeight());
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(final double delta) {
	}

	@Override
	protected void doRenderAt(final int x, final int y,
			final Graphics2D graphics) {
		graphics.drawImage(this.getImage(), x, y, null);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public BufferedImage getImage() {
		return image;
	}

	protected void setImage(final BufferedImage currentImage) {
		image = currentImage;
	}

}