package ar.edu.unq.americana.components;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.events.ioc.EventManager;
import ar.edu.unq.americana.events.ioc.timer.TimerEvent;
import ar.edu.unq.americana.exceptions.GameException;

public class Timer extends GameComponent<GameScene> implements Runnable {

	private Thread thread;
	private long time;

	@Override
	public void run() {
		try {
			Thread.sleep(this.time);
			EventManager.fire(new TimerEvent(), null);
		} catch (final InterruptedException e) {
			throw new GameException(e);
		}
	}

	public void setInterval(final double time) {
		this.thread = new Thread(this);
		this.time = (long) time;
		this.thread.start();
	}

}
