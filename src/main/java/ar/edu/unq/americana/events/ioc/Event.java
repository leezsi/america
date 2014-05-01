package ar.edu.unq.americana.events.ioc;

public abstract class Event<H extends Handler<? extends AmericanaEvent<?>>>
		implements AmericanaEvent<H> {

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends AmericanaEvent<?>> getType() {
		return (Class<? extends AmericanaEvent<?>>) this.getClass();
	}
}
