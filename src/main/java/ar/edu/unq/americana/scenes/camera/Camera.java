package ar.edu.unq.americana.scenes.camera;

import ar.edu.unq.americana.Game;
import ar.edu.unq.americana.configs.Bean;
import ar.edu.unq.americana.configs.Configs;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.EventManager;

public class Camera implements ICamera {
	private double currentX, currentY, deltaX, deltaY;
	@Bean
	private Game game;

	public Camera() {
		Configs.injectAndReadBeans(this);
		Configs.injectConfigs(this);
		EventManager.registry(this);
	}

	@Events.Fired(CameraUpdateEvent.class)
	private void update(final CameraUpdateEvent event) {
		this.deltaX += event.getDx();
		final int displayWidth = this.game.getDisplayWidth();
		final boolean lx = this.deltaX >= (displayWidth / 2);
		final boolean rx = this.deltaX <= (this.game.getCurrentScene()
				.getWidth() - (this.game.getDisplayWidth() / 2));
		if (lx && rx) {
			this.currentX += event.getDx();
		}
		this.deltaY += event.getDy();
		int displayHeight = this.game.getDisplayHeight();
		final boolean ty = this.deltaY >= (displayHeight / 2);
		final boolean by = this.deltaY <= (this.game.getCurrentScene()
				.getHeight() - (displayHeight / 2));
		if (ty && by) {
			this.currentY += event.getDy();
		}
	}

	@Override
	public double adjustX(final double x) {
		return x - this.currentX;
	}

	@Override
	public double adjustY(final double y) {
		return y - this.currentY;
	}

}
