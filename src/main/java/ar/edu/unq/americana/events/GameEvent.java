package ar.edu.unq.americana.events;

import ar.edu.unq.americana.ConcreteDeltaState;

public interface GameEvent {

	public void applyOn(ConcreteDeltaState state);
}