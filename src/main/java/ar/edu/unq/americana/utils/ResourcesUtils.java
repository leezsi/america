package ar.edu.unq.americana.utils;

import java.awt.Font;
import java.io.InputStream;

import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.exceptions.GameException;
import ar.edu.unq.americana.sound.Sound;
import ar.edu.unq.americana.sound.SoundBuilder;

public class ResourcesUtils {
	private ResourcesUtils() {
	}

	public static Sprite scale(final Sprite sprite, final double newWidth,
			final double newHeight) {
		final Sprite img = sprite.scaleHorizontally(newWidth
				/ sprite.getWidth());
		return img.scaleVertically(newHeight / img.getHeight());
	}

	public static Sound getSound(final String path) {
		final InputStream input = ClassLoader.getSystemResourceAsStream(path);
		return new SoundBuilder().buildSound(input);
	}

	public static Font getFont(final String file, final int tt,
			final int style, final float size) {
		try {
			Font font = Font.createFont(tt, ClassLoader.getSystemClassLoader()
					.getResourceAsStream(file));
			font = font.deriveFont(size);
			font = font.deriveFont(style);
			return font;
		} catch (final Exception e) {
			throw new GameException(e);
		}
	}
}
