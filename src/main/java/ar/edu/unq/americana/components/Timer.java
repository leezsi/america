package ar.edu.unq.americana.components;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.events.ioc.EventManager;
import ar.edu.unq.americana.events.ioc.timer.TimerEvent;
import ar.edu.unq.americana.exceptions.GameException;

public class Timer extends GameComponent<GameScene> implements Runnable {

	private final Thread thread;
	private final long time;

	public Timer(final long time) {
		this.thread = new Thread(this);
		this.time = time;
		this.thread.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(this.time);
				EventManager.fire(new TimerEvent(), null);
			}
		} catch (final InterruptedException e) {
			throw new GameException(e);
		}
	}

}
