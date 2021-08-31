package co.uniquindio.proyectojuego.intefaces;

import java.awt.Graphics;

/**
 * Interface creada para guardado de metodos que estarian en todas las clases.
 * @author Sebastian RS
 *
 */
public interface IElementoDeJuego 
{
	/**
	 * Metodo para cargar los componentes del elemento de juego.
	 */
	public void init();
	
	/**
	 * Metodo para dibujar.
	 * @param g parametro encargado de dibujar.
	 */
	public void render(Graphics g);
	
	/**
	 * Actualizaciones que tenga el elemento de juego.
	 */
	public void tick();
}
