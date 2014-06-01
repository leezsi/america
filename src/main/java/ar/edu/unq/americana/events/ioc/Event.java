package ar.edu.unq.americana.events.ioc;

import ar.edu.unq.americana.configs.Configs;

public abstract class Event<H extends Handler<? extends AmericanaEvent<?>>>
		implements AmericanaEvent<H> {
	public Event() {
		Configs.injectAndReadBeans(this);
		Configs.injectConfigs(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends AmericanaEvent<?>> getType() {
		return (Class<? extends AmericanaEvent<?>>) this.getClass();
	}
}
