package ar.edu.unq.americana;

import java.applet.Applet;
import java.util.Properties;

@SuppressWarnings("serial")
public class GameApplet extends Applet {

	protected GamePlayer player;

	/**
	 * Sobreescribir si no se quiere usar la clase como parametro
	 * 
	 * @return
	 */
	protected Game buildGame() {
		try {
			return (Game) Class.forName(this.getParameter("gameClass"))
					.newInstance();
		} catch (final RuntimeException e) {
			throw e;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() {
		super.init();
		logStart();
		player = new GamePlayer(this.buildGame());

		this.add(player);
		this.setSize(player.getSize());
		final Thread thread = new Thread(player);
		thread.setPriority(Thread.NORM_PRIORITY + 1);
		thread.start();

	}

	private void logStart() {
		System.out.println("Vainilla Applet started: "
				+ this.getParameter("gameClass"));
		System.out.println("using opengl= "
				+ System.getProperty("sun.java2d.opengl"));
		final Properties sys = System.getProperties();
		for (final Object key : sys.keySet()) {
			System.out.println(key.toString() + "=" + sys.get(key));
		}
	}

}
