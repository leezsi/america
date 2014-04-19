package ar.edu.unq.americana.colissions;

import static java.awt.geom.Point2D.distanceSq;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Sprite;

public class CollisionDetector {

	protected CollisionDetector() {
	}

	/**
	 * Determina si un c�rculo colisiona con un rect�ngulo.
	 * 
	 * @param cx
	 *            - Coordenada x de la esquina superior izquierda del c�rculo
	 *            (centroX - radio)
	 * @param cy
	 *            - Coordenada y de la esquina superior izquierda del c�rculo
	 *            (centroY - radio)
	 * @param cratio
	 *            - Radio del c�rculo
	 * @param rx
	 *            - Coordenada x de la esquina superior izquierda del rect�ngulo
	 * @param ry
	 *            - Coordenada y de la esquina superior izquierda del rect�ngulo
	 * @param rwidth
	 *            - Ancho del rect�ngulo
	 * @param rheight
	 *            - Alto del rect�ngulo
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
		final Sprite appearance1 = (Sprite) component1.getAppearance();
		final Sprite appearance2 = (Sprite) component2.getAppearance();

		final BufferedImage image1 = appearance1.getImage();
		final BufferedImage image2 = appearance2.getImage();

		final int aLeft = (int) appearance1.getX();
		final int aTop = (int) appearance2.getY();
		final int aWidth = (int) appearance1.getWidth();
		final int aHeight = (int) appearance1.getHeight();
		final int aRight = aLeft + aWidth;
		final int aBottom = aTop - aHeight;

		final int bLeft = (int) appearance2.getX();
		final int bTop = (int) appearance2.getY();
		final int bWidth = (int) appearance2.getWidth();
		final int bHeight = (int) appearance2.getHeight();
		final int bRight = bLeft + bWidth;
		final int bBottom = bTop - bHeight;

		final Rectangle bounds1 = new Rectangle(aLeft, aTop, aWidth, aHeight);
		final Rectangle bounds2 = new Rectangle(bLeft, bTop, bWidth, bHeight);
		final boolean isIntersect = !(bounds1.createIntersection(bounds2))
				.isEmpty();
		if (isIntersect) {
			final Rectangle collisionBounds = getCollisionBounds(aLeft, aRight,
					aTop, aBottom, bLeft, bRight, bTop, bBottom);

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

	private static Rectangle getCollisionBounds(final int aLeft,
			final int aRight, final int aTop, final int aBottom,
			final int bLeft, final int bRight, final int bTop, final int bBottom) {
		final int left = Math.max(aLeft, bLeft);
		final int top = Math.max(aTop, bTop);
		final int right = Math.min(aRight, bRight);
		final int bottom = Math.min(aBottom, bBottom);
		return new Rectangle(left, top, right - left, bottom - top);
	}

	public static boolean isFilled(final BufferedImage image, final int i,
			final int j) {
		final int pixel = image.getRGB(i, j); // get the RGB value of the pixel
		return ((pixel >> 24) & 0xff) != 0;
	}

}