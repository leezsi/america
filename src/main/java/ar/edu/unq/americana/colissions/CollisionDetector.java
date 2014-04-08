package ar.edu.unq.americana.colissions;

import static java.awt.geom.Point2D.distanceSq;

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

}