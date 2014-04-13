package ar.edu.unq.americana.colissions;


public interface ICollisionRule<F, S> {

	boolean isCollision(F first, S second);

	Class<?> first();

	Class<?> second();

}
