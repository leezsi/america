package ar.edu.unq.americana.rules;


public interface IRule<T, R> {
	boolean mustApply(T component, R scene);

	void apply(T component, R scene);
}
