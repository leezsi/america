package ar.edu.unq.americana.utils;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Sprite;

public class Screen {

	public static Dimension getDisplaySize() {
		final Rectangle bounds = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration().getBounds();
		return new Dimension(bounds.width, bounds.height);
	}

	public static Sprite scale(final Sprite sprite, final double widthPercent,
			final double heightPercent) {
		final Dimension displaySize = getDisplaySize();
		final Sprite tmp = sprite
				.scaleHorizontally(((displaySize.getWidth() * widthPercent) / 100)
						/ sprite.getWidth());
		return tmp
				.scaleVertically(((displaySize.getHeight() * heightPercent) / 100)
						/ sprite.getHeight());
	}

	public static void alignPercentedAt(final GameComponent<?> component,
			final double horizontally, final double vertically) {
		final Dimension displaySize = getDisplaySize();
		component.setX((displaySize.getWidth() * horizontally) / 100);
		component.setY((displaySize.getHeight() * vertically) / 100);
	}

}
