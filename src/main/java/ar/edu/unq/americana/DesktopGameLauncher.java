package ar.edu.unq.americana;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.InvalidParameterException;

import javax.swing.JFrame;

public class DesktopGameLauncher extends JFrame {
	private static final long serialVersionUID = -1599133666119270979L;
	private GamePlayer player;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public DesktopGameLauncher(final Game game) {
		final GamePlayer player = new GamePlayer(game);

		this.setPlayer(player);
		this.add(player);

		this.setResizable(false);
		this.pack();
		this.setTitle(game.getTitle());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(final WindowEvent e) {
				DesktopGameLauncher.this.getPlayer().resume();
			}

			@Override
			public void windowDeactivated(final WindowEvent e) {
				DesktopGameLauncher.this.getPlayer().pause();
			}

			@Override
			public void windowOpened(final WindowEvent e) {
			}

			@Override
			public void windowIconified(final WindowEvent e) {
			}

			@Override
			public void windowDeiconified(final WindowEvent e) {
			}

			@Override
			public void windowClosing(final WindowEvent e) {
			}

			@Override
			public void windowClosed(final WindowEvent e) {
			}
		});
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void launch() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	protected GamePlayer getPlayer() {
		return player;
	}

	protected void setPlayer(final GamePlayer player) {
		this.player = player;
	}

	public static void main(final String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		if (args.length < 1) {
			throw new InvalidParameterException(
					"Se espera el nombre de la clase por parámetro");
		}
		final Game game = (Game) Class.forName(args[0]).newInstance();
		new DesktopGameLauncher(game).launch();
	}
}