package ar.edu.unq.americana.components;

import java.awt.Color;
import java.awt.Font;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Appearance;
import ar.edu.unq.americana.appearances.CompoundShape;
import ar.edu.unq.americana.appearances.Label;
import ar.edu.unq.americana.appearances.Shape;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.constants.MouseButton;
import ar.edu.unq.americana.events.annotations.Events.Mouse.BeingHold;
import ar.edu.unq.americana.events.annotations.Events.Mouse.Release;

public abstract class Button<SceneType extends GameScene> extends
		GameComponent<SceneType> {

	private final Shape defaultShape;
	private final Shape pressedShape;
	private final Label label;
	private boolean pressed;

	public Button(final String text, final Sprite defaultShape,
			final Sprite pressedShape) {
		this.label = new Label(new Font("verdana", Font.BOLD, 24), Color.black,
				text);
		this.defaultShape = new CompoundShape(defaultShape.copy()
				.scaleHorizontally(
						label.getWidth() / (defaultShape.getWidth() - 20)),
				label);
		this.pressedShape = new CompoundShape(pressedShape.copy()
				.scaleHorizontally(
						label.getWidth() / (pressedShape.getWidth() - 20)),
				label);
		this.setAppearance(this.defaultShape);
	}

	@Override
	public void alignHorizontalCenterTo(final double x) {
		super.alignHorizontalCenterTo(x + (this.getAppearance().getWidth() / 2));
	}

	@BeingHold(MouseButton.LEFT)
	public void onHold(final DeltaState deltaState) {
		if (this.mouseCollision()) {
			this.setAppearance(pressedShape);
			this.pressed = true;
		}
	}

	@Release(MouseButton.LEFT)
	public void onRelease(final DeltaState deltaState) {
		this.setAppearance(defaultShape);
		if (pressed) {
			this.action();
		}
	}

	public abstract void action();

	private boolean mouseCollision() {
		Appearance buttonAppearance = null;
		buttonAppearance = this.getAppearance();
		final Mouse mouse = Mouse.get();
		final double mx = mouse.getX();
		final double btop = buttonAppearance.getY();
		final double bleft = mouse.getX();
		final double my = mouse.getY();
		final boolean a = mx >= bleft;
		final boolean b = mx <= (bleft + buttonAppearance.getWidth());
		final boolean c = my >= btop;
		final boolean d = my <= (btop + buttonAppearance.getHeight());
		return a && b && c && d;

	}

}
