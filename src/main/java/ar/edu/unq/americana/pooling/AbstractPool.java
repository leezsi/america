package ar.edu.unq.americana.pooling;

import java.util.LinkedList;
import java.util.Queue;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.configs.Configs;
import ar.edu.unq.americana.utils.ReflectionUtils;

public abstract class AbstractPool<T extends GameComponent<?>> {

	private int capacity;

	private Queue<T> queue;

	protected abstract void initialize();

	public AbstractPool() {
		Configs.injectConfigs(this.getClass());
		Configs.injectAndReadBeans(this);
		this.initialize();
	}

	protected void initialize(final int capacity) {
		this.capacity = capacity;
		this.queue = new LinkedList<T>();
		for (int i = 0; i < capacity; i++) {
			this.add(this.createNew());
		}
	}

	public T get() {
		if (this.queue.isEmpty()) {
			return this.createNew();
		} else {
			return this.queue.remove();
		}
	}

	public void add(final T object) {
		if (this.queue.size() < this.capacity) {
			this.queue.add(object);
		}
	}

	protected boolean isEmpty() {
		return this.queue.isEmpty();
	}

	protected T createNew() {
		return ReflectionUtils.newInstance(this.getType());
	}

	protected abstract Class<T> getType();

}
