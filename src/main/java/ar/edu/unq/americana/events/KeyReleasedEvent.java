package ar.edu.unq.americana.events;

import ar.edu.unq.americana.ConcreteDeltaState;
import ar.edu.unq.americana.constants.Key;

public class KeyReleasedEvent extends KeyEvent {

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public KeyReleasedEvent(Key key) {
		super(key);
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	@Override
	public void applyOn(ConcreteDeltaState state) {
		state.setKeyReleased(this.getKey());
	}
}