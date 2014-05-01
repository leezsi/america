package ar.edu.unq.americana.events.ioc;

import ar.edu.unq.americana.DeltaState;

public interface AmericanaEvent<H extends Handler<? extends AmericanaEvent<?>>> {

	public Class<? extends AmericanaEvent<?>> getType();

	public void applyOn(H handler);

	public void applyOn(H handler, DeltaState deltaState);

}
