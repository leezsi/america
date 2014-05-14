package ar.edu.unq.americana.scenes;

import java.awt.Color;
import java.awt.Font;

import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Shape;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.components.Background;
import ar.edu.unq.americana.components.Button;
import ar.edu.unq.americana.components.Text;

public abstract class MenuScene extends GameScene {

	private static Sprite defaultButton = Sprite
			.fromImage("button/buttonDefault.png");

	public class MenuButtonBuilder {

		private final MenuScene scene;
		private double x = -1;
		private String text;
		private int y;
		private Runnable action;

		public MenuButtonBuilder(final MenuScene menuScene) {
			this.scene = menuScene;
			this.y = 1;
		}

		public MenuButtonBuilder xPosition(final double x) {
			this.x = x;
			return this;
		}

		public MenuButtonBuilder text(final String text) {
			this.text = text;
			return this;
		}

		public void build() {
			final Runnable _action = this.action == null ? new Runnable() {
				@Override
				public void run() {

				}
			} : this.action;
			final Button<MenuScene> button = new Button<MenuScene>(this.text,
					this.font(), this.fontColor(), defaultButton, _action);
			button.setX(this.x);
			button.setY(++this.y);
			this.scene.addComponent(button);
		}

		private Color fontColor() {
			final Color buttonTextColor = this.scene.buttonTextColor();
			if (buttonTextColor == null) {
				return Color.black;
			}
			return buttonTextColor;
		}

		private Font font() {
			return this.scene.getFont();
		}

		public MenuButtonBuilder onClick(final Runnable action) {
			this.action = action;
			return this;
		}
	}

	private Text<MenuScene> text;

	public MenuScene(final Sprite background) {
		this.addComponent(new Background<MenuScene>(background.copy()));
	}

	@Override
	public void onSetAsCurrent() {
		this.addText(this.disclamer());
		this.addButtons(new MenuButtonBuilder(this));
		this.getMouse().setAppearance(this.getMouseSprite());
		this.text.setX(this.getGame().getDisplayWidth() / 2);
		super.onSetAsCurrent();
	}

	private void addText(final String disclamer) {
		this.text = new Text<MenuScene>(disclamer, this.getFont(),
				this.disclamerColor());
		this.text.setY(50);
		this.addComponent(this.text);
	}

	protected abstract Color disclamerColor();

	protected abstract String disclamer();

	protected abstract Shape getMouseSprite();

	public abstract Color buttonTextColor();

	public abstract Font getFont();

	protected abstract void addButtons(MenuButtonBuilder buttonBuilder);

}
