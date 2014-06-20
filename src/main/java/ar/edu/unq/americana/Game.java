package ar.edu.unq.americana;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import ar.edu.unq.americana.configs.Bean;
import ar.edu.unq.americana.configs.Configs;
import ar.edu.unq.americana.events.GameEvent;
import ar.edu.unq.americana.events.ioc.EventManager;
import ar.edu.unq.americana.scenes.pause.PauseGameScene;
import ar.edu.unq.americana.utils.Tuning;

@Bean
public abstract class Game {

	private GameScene currentScene;
	private ResourceBundle localeBoundle;
	private DesktopGameLauncher launcher;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public Game() {
		Configs.injectAndReadBeans(this);
		this.loadProperties();
		Configs.injectConfigs(this);
		this.initializeLocale();
		this.setCurrentScene(new GameScene());
		this.preInitialize();
		this.initializeResources();
		this.setUpScenes();
		EventManager.registry(this);
	}

	protected void initializeLocale() {
		this.localeBoundle = ResourceBundle.getBundle(this.localeFile());
	}

	public ResourceBundle getLocaleBoundle() {
		return this.localeBoundle;
	}

	protected String localeFile() {
		return this.getClass().getSimpleName() + "_strings";
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

	public void closeGame() {
		final WindowEvent wev = new WindowEvent(this.launcher,
				WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public GameScene getCurrentScene() {
		return this.currentScene;
	}

	public void setCurrentScene(final GameScene scene) {
		if (this.getCurrentScene() != null) {
			this.getCurrentScene().destroy();
		}
		this.currentScene = scene;
		scene.setGame(this);
		scene.onSetAsCurrent();
	}

	public void pause(final PauseGameScene scene) {
		scene.setReturn(this.currentScene);
		this.currentScene = scene;
		scene.setGame(this);
		scene.onSetAsCurrent();
	}

	public void resume(final GameScene scene) {
		this.currentScene.destroy();
		this.currentScene = scene;
		scene.setGame(this);
		scene.fullEventRegistry();
	}

	public void setLauncher(final DesktopGameLauncher launcher) {
		this.launcher = launcher;
	}

	public DesktopGameLauncher getLauncher() {
		return this.launcher;
	}

	public void startGame() {

	}

}
