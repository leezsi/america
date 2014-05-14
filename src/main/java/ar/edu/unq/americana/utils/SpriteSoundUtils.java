package ar.edu.unq.americana.utils;

import java.io.InputStream;

import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.sound.Sound;
import ar.edu.unq.americana.sound.SoundBuilder;

public class SpriteSoundUtils {
	private SpriteSoundUtils() {
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

	public static Sprite rotate(final Sprite sprite, final double angle) {
		return sprite.rotate(angle);
	}
}
