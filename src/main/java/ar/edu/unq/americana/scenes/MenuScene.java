package ar.edu.unq.americana.scenes;

import java.awt.Color;
import java.awt.Font;

import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.components.Background;
import ar.edu.unq.americana.components.Button;

public abstract class MenuScene extends GameScene {

	private static Sprite defaultButton = Sprite
			.fromImage("button/buttonDefault.png");

	public class MenuButtonBuilder {

		private final MenuScene scene;
		private double x = -1;
		private String text;
		private int y;

		public MenuButtonBuilder(final MenuScene menuScene) {
			this.scene = menuScene;
			this.y = 0;
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
			final Button<MenuScene> button = new Button<MenuScene>(this.text,
					this.font(), this.fontColor(), defaultButton);
			button.setX(this.x);
			button.setY(++this.y);
			this.scene.addComponent(button);
		}

		private Color fontColor() {
			return this.scene.buttonTextColor();
		}

		private Font font() {
			return this.scene.getFont();
		}
	}

	public MenuScene(final Sprite background) {
		this.addComponent(new Background<MenuScene>(background.copy()));
		this.addButtons(new MenuButtonBuilder(this));
	}

	public abstract Color buttonTextColor();

	public abstract Font getFont();

	protected abstract void addButtons(MenuButtonBuilder buttonBuilder);

}
