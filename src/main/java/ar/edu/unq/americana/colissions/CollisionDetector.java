package ar.edu.unq.americana.colissions;

import static java.awt.geom.Point2D.distanceSq;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Animation;
import ar.edu.unq.americana.appearances.Appearance;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.exceptions.GameException;

public class CollisionDetector {

	protected CollisionDetector() {
	}

	/**
	 * Determina si un c?rculo colisiona con un rect?ngulo.
	 * 
	 * @param cx
	 *            - Coordenada x de la esquina superior izquierda del c?rculo
	 *            (centroX - radio)
	 * @param cy
	 *            - Coordenada y de la esquina superior izquierda del c?rculo
	 *            (centroY - radio)
	 * @param cratio
	 *            - Radio del c?rculo
	 * @param rx
	 *            - Coordenada x de la esquina superior izquierda del rect?ngulo
	 * @param ry
	 *            - Coordenada y de la esquina superior izquierda del rect?ngulo
	 * @param rwidth
	 *            - Ancho del rect?ngulo
	 * @param rheight
	 *            - Alto del rect?ngulo
	 * @return Verdadero si colisionan, falso si no.
	 */
	static public boolean collidesCircleAgainstRect(final double cx,
			final double cy, final double cratio, final double rx,
			final double ry, final double rwidth, final double rheight) {
		final Bounds circleBounds = new Bounds(cx, cy, cratio * 2, cratio * 2);
		final Bounds rectBounds = new Bounds(rx, ry, rwidth, rheight);
		final double rectLeft = rectBounds.getLeft();
		final double rectRight = rectBounds.getRight();
		final double rectTop = rectBounds.getTop();
		final double rectBottom = rectBounds.getBottom();
		final double extendedTargetTop = rectTop - cratio;
		final double extendedTargetRight = rectRight + cratio;
		final double extendedTargetBottom = rectBottom + cratio;
		final double extendedTargetLeft = rectLeft - cratio;
		final double radiusSq = cratio * cratio;
		final double centerX = circleBounds.getCenterX();
		final double centerY = circleBounds.getCenterY();

		if (centerX >= rectLeft) {
			if (centerX <= rectRight) {
				return (centerY > extendedTargetTop)
						&& (centerY < extendedTargetBottom);
			}
			if (centerY >= rectTop) {
				if (centerY <= rectBottom) {
					return centerX < extendedTargetRight;
				}
				return distanceSq(centerX, centerY, rectRight, rectBottom) < radiusSq;
			}
			return distanceSq(centerX, centerY, rectRight, rectTop) < radiusSq;
		}

		if (centerY >= rectTop) {
			if (centerY <= rectBottom) {
				return centerX > extendedTargetLeft;
			}
			return distanceSq(centerX, centerY, rectLeft, rectBottom) < radiusSq;
		}

		return distanceSq(centerX, centerY, rectLeft, rectTop) < radiusSq;
	}

	static public boolean collidesRectAgainstRect(final double x1,
			final double y1, final int width1, final int height1,
			final double x2, final double y2, final int width2,
			final int height2) {
		final double right1 = x1 + width1;
		final double right2 = x2 + width2;
		final double bottom1 = y1 + height1;
		final double bottom2 = y2 + height2;

		return (((x1 <= x2) && (x2 < right1)) || ((x2 <= x1) && (x1 < right2)))
				&& (((y1 <= y2) && (y2 < bottom1)) || ((y2 <= y1) && (y1 < bottom2)));
	}

	static public boolean collidesCircleAgainstCircle(final double x1,
			final double y1, final int ratio1, final double x2,
			final double y2, final int ratio2) {
		final double ratioSum = ratio1 + ratio2;
		final double distanceSq = distanceSq(x1, y1, x2, y2);

		return distanceSq < (ratioSum * ratioSum);
	}

	static public boolean collidesPointAgainstTriangle(final double x,
			final double y, final double vertexX1, final double vertexY1,
			final double vertexX2, final double vertexY2,
			final double vertexX3, final double vertexY3) {

		final double total = triangleArea(vertexX1, vertexY1, vertexX2,
				vertexY2, vertexX3, vertexY3);

		final double t1 = triangleArea(x, y, vertexX2, vertexY2, vertexX3,
				vertexY3);
		final double t2 = triangleArea(x, y, vertexX1, vertexY1, vertexX2,
				vertexY2);
		final double t3 = triangleArea(x, y, vertexX1, vertexY1, vertexX3,
				vertexY3);

		return (t1 + t2 + t3) == total;
	}

	static public double triangleArea(final double vertexX1,
			final double vertexY1, final double vertexX2,
			final double vertexY2, final double vertexX3, final double vertexY3) {
		final double a = vertexX1 - vertexX3;
		final double b = vertexY1 - vertexY3;
		final double c = vertexX2 - vertexX3;
		final double d = vertexY2 - vertexY3;

		return 0.5 * Math.abs((a * d) - (b * c));
	}

	public static boolean collidesCircleAgainstLine(final double cx,
			final double cy, final double radious, final double x1,
			final double y1, final double x2, final double y2) {
		final double n = Math.abs(((x2 - x1) * (y1 - cy))
				- ((x1 - cx) * (y2 - y1)));
		final double d = Math.sqrt(((x2 - x1) * (x2 - x1))
				+ ((y2 - y1) * (y2 - y1)));
		final double dist = n / d;
		if (dist > radious) {
			return false;
		}
		final double d1 = Math.sqrt(((cx - x1) * (cx - x1))
				+ ((cy - y1) * (cy - y1)));
		if ((d1 - radious) > d) {
			return false;
		}
		final double d2 = Math.sqrt(((cx - x2) * (cx - x2))
				+ ((cy - y2) * (cy - y2)));
		if ((d2 - radious) > d) {
			return false;
		}
		return true;
	}

	public static boolean perfectPixel(final GameComponent<?> component1,
			final GameComponent<?> component2) {
		final Sprite appearance1 = getSprite(component1);
		final Sprite appearance2 = getSprite(component2);
		final BufferedImage image1 = appearance1.getImage();
		final BufferedImage image2 = appearance2.getImage();

		final Rectangle bounds1 = boundFromSprite(appearance1);
		final Rectangle bounds2 = boundFromSprite(appearance2);

		final boolean isIntersect = bounds1.intersects(bounds2);

		final int aLeft = bounds1.x;
		final int aTop = bounds1.y;
		final int bLeft = bounds2.x;
		final int bTop = bounds2.y;
		if (isIntersect) {
			final Rectangle collisionBounds = bounds1.intersection(bounds2);

			final int left = (int) collisionBounds.getX();
			final double right = collisionBounds.getX()
					+ collisionBounds.getWidth();
			for (int i = left; i < right; i++) {
				final int top = (int) collisionBounds.getY();
				final double bottom = collisionBounds.getY()
						+ collisionBounds.getHeight();
				for (int j = top; j < bottom; j++) {
					if (isFilled(image1, i - aLeft, j - aTop)
							&& isFilled(image2, i - bLeft, j - bTop)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static Rectangle boundFromSprite(final Sprite sprite) {
		final int x = (int) sprite.getX();
		final int y = (int) sprite.getY();
		final int width = (int) sprite.getWidth();
		final int height = (int) sprite.getHeight();
		return new Rectangle(x, y, width, height);
	}

	private static Sprite getSprite(final GameComponent<?> component) {
		final Appearance appearance = component.getAppearance();
		final Class<? extends Appearance> appearanceClass = appearance
				.getClass();
		if (appearanceClass == Sprite.class) {
			return (Sprite) appearance;
		} else if (appearanceClass == Animation.class) {
			final Sprite currentSprite = ((Animation) appearance)
					.getCurrentSprite();
			currentSprite.setComponent(component);
			return currentSprite;
		} else {
			throw new GameException("component " + component
					+ " must have Sprite or Animation appearance");
		}
	}

	public static boolean isFilled(final BufferedImage image, final int i,
			final int j) {
		final int pixel = image.getRGB(i, j); // get the RGB value of the pixel
		return ((pixel >> 24) & 0xff) != 0;
	}

	public static boolean fromBoxes(final GameComponent<?> component1,
			final GameComponent<?> component2) {
		final Appearance app1 = component1.getAppearance();
		final double width1 = app1.getWidth() / 2;
		final double height1 = app1.getHeight() / 2;
		final Appearance app2 = component2.getAppearance();
		final double width2 = app2.getWidth() / 2;
		final double height2 = app2.getHeight() / 2;
		return collidesRectAgainstRect(component1.getX() - width1,
				component1.getY() - height1, (int) width1 * 2,
				(int) height1 * 2, component2.getX() - width2,
				component2.getY() - height2, (int) width2 * 2,
				(int) height2 * 2);

	}

}