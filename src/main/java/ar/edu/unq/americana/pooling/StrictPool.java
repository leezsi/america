package ar.edu.unq.americana.pooling;

import ar.edu.unq.americana.GameComponent;

public abstract class StrictPool<T extends GameComponent<?>> extends
		AbstractPool<T> {

	@Override
	public T get() {
		if (this.isEmpty()) {
			return null;
		}
		return super.get();
	}

}
