package ar.edu.unq.americana.scenes.camera;

public class StaticCamera implements ICamera {

	@Override
	public double adjustX(final double x) {
		return x;
	}

	@Override
	public double adjustY(final double y) {
		return y;
	}

}
