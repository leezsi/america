package ar.edu.unq.americana.components;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.events.ioc.EventManager;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.exceptions.GameException;

public class Timer extends GameComponent<GameScene> implements Runnable {

	private Thread thread;
	private long time;
	private FiredEvent event;
	private boolean alive;

	@Override
	public void run() {
		try {
			while (this.alive) {
				Thread.sleep(this.time);
				EventManager.fire(this.event);
			}
		} catch (final InterruptedException e) {
			throw new GameException(e);
		}
	}

	public void start(final long time, final FiredEvent event) {
		this.thread = new Thread(this);
		this.alive = true;
		this.time = time;
		this.event = event;
		this.thread.start();
	}

	public void stop() {
		this.alive = false;
	}
}
