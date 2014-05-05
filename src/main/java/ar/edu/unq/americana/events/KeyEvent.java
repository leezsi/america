package ar.edu.unq.americana.events;

import ar.edu.unq.americana.constants.Key;

public abstract class KeyEvent extends SimpleEvent {

	private Key key;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public KeyEvent(Key key) {
		this.setKey(key);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public Key getKey() {
		return this.key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
}