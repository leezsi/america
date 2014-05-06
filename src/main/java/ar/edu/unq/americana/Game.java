package ar.edu.unq.americana;

import java.awt.Dimension;
import java.awt.Graphics2D;

import ar.edu.unq.americana.configs.Configs;
import ar.edu.unq.americana.events.GameEvent;
import ar.edu.unq.americana.utils.Tuning;

public abstract class Game {

	private GameScene currentScene;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Game() {
		this.setCurrentScene(new GameScene());
		this.loadProperties();
		Configs.injectAndReadBeans(this);
		Configs.injectConfigs(this.getClass());
		this.preInitialize();
		this.initializeResources();
		this.setUpScenes();
	}

	protected void preInitialize() {

	}

	// ****************************************************************
	// ** INITIALIZATIONS
	// ****************************************************************

	protected abstract void initializeResources();

	protected abstract void setUpScenes();

	protected String[] properties() {
		return new String[0];
	}

	private void loadProperties() {
		for (final String path : this.properties()) {
			Tuning.load(path);
		}

	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public int getDisplayWidth() {
		return this.getDisplaySize().width;
	}

	public int getDisplayHeight() {
		return this.getDisplaySize().height;
	}

	public abstract Dimension getDisplaySize();

	public abstract String getTitle();

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void takeStep(final Graphics2D graphics) {
		this.getCurrentScene().takeStep(graphics);
	}

	public void pause() {
	}

	public void pushEvent(final GameEvent event) {
		this.getCurrentScene().pushEvent(event);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public GameScene getCurrentScene() {
		return this.currentScene;
	}

	public void setCurrentScene(final GameScene scene) {
		if (this.getCurrentScene() != null) {
			this.getCurrentScene().setGame(null);
		}
		this.currentScene = scene;
		scene.setGame(this);
		scene.onSetAsCurrent();
	}

}
