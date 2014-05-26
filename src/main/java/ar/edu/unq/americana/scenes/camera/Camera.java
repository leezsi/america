package ar.edu.unq.americana.scenes.camera;

import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.EventManager;

public class Camera {

	private double totalX;
	private double totalY;
	private GameComponent<?> target;
	private double lastX;
	private double lastY;
	private final CameraGameScene scene;

	public Camera(final CameraGameScene scene) {
		EventManager.registry(this);
		this.totalX = 0.0;
		this.totalY = 0.0;
		this.scene = scene;
	}

	public void setTarget(final GameComponent<?> target) {
		this.reset();
		this.target = target;
		this.initialize();
		this.lastX = target.getX();
		this.lastY = target.getY();
	}

	private void initialize() {
		final int x = this.scene.getGame().getDisplayWidth() / 2;
		final int y = this.scene.getGame().getDisplayHeight() / 2;
		final double dx = this.target.getX() - x;
		final double dy = this.target.getY() - y;
		this.fixComponentPositionX(-dx);
		this.fixComponentPositionY(-dy);

	}

	@Events.Fired(CameraUpdateEvent.class)
	private void updateCamera(final CameraUpdateEvent event) {
		final double newX = this.target.getX();
		final double newY = this.target.getY();
		final double dx = this.lastX - newX;
		final double dy = this.lastY - newY;
		this.fixComponentPositionX(dx);
		this.fixComponentPositionY(dy);
	}

	private void fixComponentPositionX(final double delta) {
		this.totalX += delta;
		for (final GameComponent<?> component : this.getComponents()) {
			component.setX(component.getX() + delta);
		}

	}

	public void reset() {
		this.fixComponentPositionX(-this.totalX);
		this.totalX = 0;
		this.fixComponentPositionY(-this.totalY);
		this.totalY = 0;
	}

	private List<GameComponent<?>> getComponents() {
		return this.scene.getCameraTarget();
	}

	private void fixComponentPositionY(final double delta) {
		this.totalY += delta;
		for (final GameComponent<?> component : this.getComponents()) {
			component.setY(component.getY() + delta);
		}
	}

}
