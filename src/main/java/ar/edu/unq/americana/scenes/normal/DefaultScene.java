package ar.edu.unq.americana.scenes.normal;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.components.LifeCounter;
import ar.edu.unq.americana.components.Mouse;
import ar.edu.unq.americana.components.Score;

public class DefaultScene extends GameScene {

	private final LifeCounter<?> lifeCounter;
	private final Score<?> score;
	private final Mouse<GameScene> mouse;
	private final List<GameComponent<?>> collisionableComponents;

	public DefaultScene(final Score<?> score, final LifeCounter<?> lifeCounter) {
		this.score = score;
		super.addComponent(score);
		this.lifeCounter = lifeCounter;
		super.addComponent(lifeCounter);
		this.mouse = new Mouse<GameScene>();
		super.addComponent(this.mouse);
		this.collisionableComponents = new ArrayList<GameComponent<?>>();
	}

	public Mouse<GameScene> getMouse() {
		return this.mouse;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addComponent(final GameComponent component) {
		super.addComponent(component);
		this.collisionableComponents.add(component);
	}

	@Override
	public void removeComponent(final GameComponent<?> component) {
		super.removeComponent(component);
		this.collisionableComponents.remove(component);
	}

	@Override
	public List<GameComponent<?>> getCollisionableComponents() {
		return this.collisionableComponents;
	}

	public LifeCounter<?> getLifeCounter() {
		return this.lifeCounter;
	}

	public Score<?> getScore() {
		return this.score;
	}

}
