package co.uniquindio.proyectojuego;

import javax.swing.JFrame;

import co.uniquindio.proyectojuego.pantalla.Menu;

/**
 * Clase principal
 * 
 * @author Sebastian David Rendon Soto.
 * @author Cesar Esteban Marquez.
 *
 */
public class Launcher {

	public static JFrame marco = new JFrame();

	/**
	 * Método principal que permite ejecutar el juego
	 * 
	 * @param arg
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] arg) {
		Menu menu = new Menu();
		menu.show();
	}

}
