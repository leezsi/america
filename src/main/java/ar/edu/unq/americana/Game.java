package ar.edu.unq.americana;

import java.awt.Dimension;
import java.awt.Graphics2D;

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
		this.initializeResources();
		this.setUpScenes();
	}

	// ****************************************************************
	// ** INITIALIZATIONS
	// ****************************************************************

	protected abstract void initializeResources();

	protected abstract void setUpScenes();

	protected String[] propertiesFiles() {
		return new String[0];
	}

	private void loadProperties() {
		for (final String path : this.propertiesFiles()) {
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
		return currentScene;
	}

	public void setCurrentScene(final GameScene scene) {
		if (this.getCurrentScene() != null) {
			this.getCurrentScene().setGame(null);
		}

		currentScene = scene;

		scene.setGame(this);

		scene.onSetAsCurrent();
	}

}