package ar.edu.unq.americana.scenes.camera;

import ar.edu.unq.americana.Game;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.configs.Bean;
import ar.edu.unq.americana.configs.Configs;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.annotations.Events.Fired;
import ar.edu.unq.americana.events.ioc.EventManager;

public class Camera implements ICamera {
	@Bean
	private Game game;
	private double lBound, rBound, tBound, bBound;
	private double deltaY;
	private double deltaX;
	private double currentX;
	private double currentY;

	public Camera() {
		Configs.injectAndReadBeans(this);
		Configs.injectConfigs(this);
		EventManager.registry(this);
	}

	@Fired(CameraResetEvent.class)
	private void reset(final CameraResetEvent cameraResetEvent) {
		final int displayWidth = this.game.getDisplayWidth();
		final GameScene currentScene = this.game.getCurrentScene();
		this.lBound = currentScene.getLeft() + (displayWidth / 2);
		this.rBound = currentScene.getRight() - (displayWidth / 2);
		final int displayHeight = this.game.getDisplayHeight();
		this.tBound = currentScene.getTop() + (displayHeight / 2);
		this.bBound = currentScene.getBottom() - (displayHeight / 2);
		this.updateInitialValues(cameraResetEvent);
		this.fixCurrentValues(currentScene);
	}

	private void fixCurrentValues(final GameScene currentScene) {
		if (this.currentX < this.lBound) {
			this.currentX = currentScene.getLeft();
		}
		if (this.currentX > this.rBound) {
			this.currentX = currentScene.getRight();
		}

		if (this.currentY < this.tBound) {
			this.currentY = currentScene.getTop();
		}

		if (this.currentY > this.bBound) {
			this.currentY = currentScene.getBottom();
		}
	}

	private void updateInitialValues(final CameraResetEvent cameraResetEvent) {
		this.currentX = this.deltaX = cameraResetEvent.getX();
		this.currentY = this.deltaY = cameraResetEvent.getY();
	}

	@Events.Fired(CameraUpdateEvent.class)
	private void update(final CameraUpdateEvent event) {
		this.deltaX += event.getDx();
		this.deltaY += event.getDy();

		if ((this.deltaX > this.lBound) && (this.deltaX < this.rBound)) {
			this.currentX += event.getDx();
		}
		if ((this.deltaY > this.tBound) && (this.deltaY < this.bBound)) {
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
