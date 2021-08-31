package co.uniquindio.proyectojuego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import co.uniquindio.proyectojuego.elementos.Personaje;

/**
 * Clase para manejar las teclas precionadas por el juegador.
 * @author Sebastian David Rendon Soto.
 * @author Cesar Esteban Marquez.
 */
public class Teclado implements KeyListener {

	private Personaje jugador;

	/**
	 * Constructo de la clase.
	 * @param jugador.
	 */
	public Teclado(Personaje jugador) {
		this.jugador = jugador;
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int key = event.getKeyCode();

		switch (key) {
		case KeyEvent.VK_SPACE:
			jugador.setColor(!jugador.isColor());
			break;
		case KeyEvent.VK_W:
			if (!jugador.isSaltar()) {
				jugador.setSalto(-jugador.FUERZA_DE_SALTO);
				jugador.setSaltar(true);
			}
			break;

		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

}
