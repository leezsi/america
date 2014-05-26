package ar.edu.unq.americana.scenes.camera;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.components.LifeCounter;
import ar.edu.unq.americana.components.Score;
import ar.edu.unq.americana.events.ioc.EventManager;
import ar.edu.unq.americana.scenes.normal.DefaultScene;

public abstract class CameraGameScene extends DefaultScene {

	private final Camera camera;
	private List<GameComponent<?>> cameraTarget;

	public CameraGameScene(final Score<?> score,
			final LifeCounter<?> lifeCounter) {
		super(score, lifeCounter);
		this.camera = new Camera(this);
	}

	protected void initializeCamera(final GameComponent<?> target) {
		this.camera.setTarget(target);
	}

	protected List<GameComponent<?>> getCameraTarget() {
		return this.cameraTarget;
	}

	protected void cameraFocusOn(final GameComponent<?> target) {
		this.camera.setTarget(target);
	}

	protected void resetCamera() {
		this.camera.reset();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addComponent(final GameComponent component) {
		if (component != null) {
			super.addComponent(component);
			this.addTargetComponent(component);
			this.cameraTarget.add(component);
		}
	}

	private void addTargetComponent(final GameComponent<?> component) {
		if (this.cameraTarget == null) {
			this.cameraTarget = new ArrayList<GameComponent<?>>();
		}
	}

	@Override
	public void removeComponent(final GameComponent<?> component) {
		super.removeComponent(component);
		this.cameraTarget.remove(component);
	}

	@Override
	public void fullEventRegistry() {
		super.fullEventRegistry();
		EventManager.registry(this.camera);
	}
}
