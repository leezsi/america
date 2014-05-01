package ar.edu.unq.americana.events.annotations;

import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.constants.MouseButton;

public enum EventType {
	Pressed {
		@Override
		public boolean mustExecute(final Key key, final DeltaState deltaState) {
			return deltaState.isKeyPressed(key);
		}

		@Override
		public boolean mustExecute(final MouseButton button,
				final DeltaState deltaState) {
			return deltaState.isMouseButtonPressed(button);
		}
	},
	Released {
		@Override
		public boolean mustExecute(final Key key, final DeltaState deltaState) {
			return deltaState.isKeyReleased(key);
		}

		@Override
		public boolean mustExecute(final MouseButton button,
				final DeltaState deltaState) {
			return deltaState.isMouseButtonReleased(button);
		}
	},
	BeingHold {
		@Override
		public boolean mustExecute(final Key key, final DeltaState deltaState) {
			return deltaState.isKeyBeingHold(key);
		}

		@Override
		public boolean mustExecute(final MouseButton button,
				final DeltaState deltaState) {
			return deltaState.isMouseButtonBeingHold(button);
		}
	};

	public abstract boolean mustExecute(final Key key,
			final DeltaState deltaState);

	public abstract boolean mustExecute(MouseButton button,
			DeltaState deltaState);

}
