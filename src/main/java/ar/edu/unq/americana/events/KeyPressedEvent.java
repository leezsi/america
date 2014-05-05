package ar.edu.unq.americana.events;

import ar.edu.unq.americana.ConcreteDeltaState;
import ar.edu.unq.americana.constants.Key;

public class KeyPressedEvent extends KeyEvent {

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public KeyPressedEvent(Key key) {
		super(key);
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	@Override
	public void applyOn(ConcreteDeltaState state) {
		state.setKeyPressed(this.getKey());
	}
}