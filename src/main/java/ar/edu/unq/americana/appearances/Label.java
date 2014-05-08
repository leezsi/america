package ar.edu.unq.americana.appearances;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.americana.GameComponent;

public class Label extends Shape {

	private Font font;
	private Color color;
	private List<String> textLines;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Label(final Font font, final Color color, final String text) {
		this(font, color);
		this.setText(text);
	}

	public Label(final Font font, final Color color, final String... textLines) {
		this(font, color, Arrays.asList(textLines));
	}

	protected Label(final Font font, final Color color,
			final List<String> textLines) {
		this.setFont(font);
		this.setColor(color);
		this.setTextLines(textLines);
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	@Override
	public double getWidth() {
		double answer = 0;
		final FontMetrics metrics = new Canvas().getFontMetrics(this.getFont());

		for (final String line : this.getTextLines()) {
			answer = Math.max(answer, metrics.stringWidth(line));
		}

		return answer;
	}

	@Override
	public double getHeight() {
		return this.getLinesCount() * 1.05 * this.getLineHeight();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Appearance copy() {
		return new Label(this.getFont(), this.getColor(), this.getTextLines());
	}

	protected int getLinesCount() {
		return this.getTextLines().size();
	}

	protected double getLineHeight() {
		return this.getFont().getSize2D();
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void setText(final String text) {
		this.setTextLines(Arrays.asList(text.split("\n")));
	}

	// ****************************************************************
	// ** GAME LOOP OPERATIONS
	// ****************************************************************

	@Override
	public void update(final double delta) {
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public Font getFont() {
		return this.font;
	}

	public void setFont(final Font font) {
		this.font = font;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

	protected List<String> getTextLines() {
		return this.textLines;
	}

	protected void setTextLines(final List<String> textLines) {
		this.textLines = textLines;
	}

	@Override
	public void render(final GameComponent<?> component,
			final Graphics2D graphics) {
		graphics.setFont(this.getFont());
		graphics.setColor(this.getColor());
		this.getTextLines().get(0);
		for (int index = 0; index < this.getTextLines().size(); index++) {
			graphics.drawString(this.getTextLines().get(index), //
					(int) this.getX(), //
					(int) (this.getY() + (this.getLineHeight() * (index + 1))) //
			);
		}
	}

}