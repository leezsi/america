package ar.edu.unq.americana;

import java.awt.Frame;
import java.security.InvalidParameterException;

public class FullScreenGameLauncher extends DesktopGameLauncher {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1830326426755389406L;

	public FullScreenGameLauncher(final Game game) {
		super(game);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
	}

	public static void main(final String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		if (args.length < 1) {
			throw new InvalidParameterException(
					"Se espera el nombre de la clase por parámetro");
		}
		final Game game = (Game) Class.forName(args[0]).newInstance();
		new FullScreenGameLauncher(game).launch();
	}
}
