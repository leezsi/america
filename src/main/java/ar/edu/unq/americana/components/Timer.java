package ar.edu.unq.americana.components;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public class Timer extends GameComponent<GameScene> {

	private long time;

	private double elapsed;

	private FiredEvent event;

	private boolean running;

	public Timer(long time, FiredEvent event) {
		this.time = time;
		this.event = event;
		this.elapsed = 0;
		this.running = false;
	}

	@Events.Update
	public void run(final double delta) {
		if (this.running) {
			this.elapsed += delta * 1000;

			if (this.elapsed >= this.time) {
				this.fire(this.event);
				this.reset();
				this.stop();
			}
		}
	}

	public void start() {
		this.running = true;
	}

	public void stop() {
		this.running = false;
	}

	public void reset() {
		this.elapsed = 0;
	}
}
