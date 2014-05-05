package ar.edu.unq.americana.rules;


public interface IRule<T, R> {
	boolean mustApply(T component, R scene);

	void apply(T component, R scene);
}
package ar.edu.unq.americana.rules;

import ar.edu.unq.americana.utils.Vector2D;

public interface IRule<T, R> {
	boolean mustApply(T component, Vector2D nextPosition, R scene);

	void apply(T component, Vector2D nextPosition, R scene);
}
