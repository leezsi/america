package ar.edu.unq.americana.extras;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageExtras {

	public static boolean isTransparent(final BufferedImage image,
			final int pixelRow, final int pixelColumn) {
		final int pixel = image.getRGB(pixelColumn, pixelRow);
		return new Color(pixel, true).getAlpha() == 0;
	}

	public static void clean(final BufferedImage image, final int pixelRow,
			final int pixelColumn) {
		put(image, pixelRow, pixelColumn, new Color(0x00000000, true));
	}

	public static void put(final BufferedImage image, final int pixelRow,
			final int pixelColumn, final Color color) {
		image.setRGB(pixelColumn, pixelRow, color.getRGB());
	}

	public static Color getColor(final BufferedImage image, final int pixelRow,
			final int pixelColumn) {
		final int pixel = image.getRGB(pixelColumn, pixelRow);
		return new Color(pixel, true);
	}

}
