package co.uniquindio.proyectojuego.sprite;

import java.awt.image.BufferedImage;

/**
 * Clase creada para manejar los Sprites o imagenes de los personajes.
 * @author Sebastian David Rendon Soto.
 * @author Cesar Esteban Marquez.
 */
public class Sprite {
	
	private final BufferedImage imagen;
	
	private final int ancho;
	
	private final int alto;

	/**
	 * Constructor de la clase.
	 * @param imagen
	 */
	public Sprite(final BufferedImage imagen) {
		this.imagen = imagen;
		ancho = imagen.getWidth();
		alto = imagen.getHeight();
	}

	//METODOS GETTER AND SETTERS.
	
	public BufferedImage getImagen() {
		return imagen;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
}
