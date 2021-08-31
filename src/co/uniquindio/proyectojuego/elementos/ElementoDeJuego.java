package co.uniquindio.proyectojuego.elementos;

import java.awt.Graphics;
import java.awt.Rectangle;

import co.uniquindio.proyectojuego.intefaces.IElementoDeJuego;
import co.uniquindio.proyectojuego.sprite.HojaSprites;
/**
 * Clase creada para relacionar de alguna manera todos los elementos dentro del juego.
 * @author Sebastian David Rendon Soto
 * @author Cesar Esteban Marquez
 */
public abstract class ElementoDeJuego implements IElementoDeJuego {
	
	protected String nombreHojaSprite;
	
	//Variables de la posicion inicial del personaje.
	protected int x, y;
	
	//Variables modificiables para cambiar la posicion del personaje.
	protected int posX, posY;
	
	//Variables para tener en cuenta el ancho y alto del sprite.
	protected int anchoSprite, altoSprite;
	
	//Variable para aumentar o reduccir el movimiento en el eje X.
	protected int velocidad;

	protected HojaSprites hojaSprites;
	
	//Cantidad de sprites a cargar.
	protected int numeroSprites;
	
	//protected int ladoSprite;
	
	/**
	 * Metodo Constructor de la Clase.
	 * @param x Posicion inicial x
	 * @param y Posicion inicial y
	 * @param altoSprite alto del sprite (en pixeles)
	 * @param anchoSprite ancho del sprite (en pixeles)
	 */
	public ElementoDeJuego(int x, int y, int altoSprite, int anchoSprite) {
		this.x = x;
		this.y = y;
		this.anchoSprite = anchoSprite;
		this.altoSprite = altoSprite;
	}

	@Override
	/**
	 * Metodo para cargar los sprites al iniciar.
	 */
	public void init() {
		hojaSprites = new HojaSprites(nombreHojaSprite, anchoSprite, altoSprite);
	}

	@Override
	/**
	 * Metodo para dibujar.
	 */
	public void render(Graphics g) {

	}
	
	/**
	 * Metodo devolvia un rectangulo con la posicion y dimensiones dadas del personaje
	 * con anchoSprite y altoSprite.
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(x + posX, y + posY, anchoSprite, altoSprite);
	}
	
	//METODOS GETTERS AND SETTERS

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getAnchoSprite() {
		return anchoSprite;
	}

	public void setAnchoSprite(int anchoSprite) {
		this.anchoSprite = anchoSprite;
	}

	public int getAltoSprite() {
		return altoSprite;
	}

	public void setAltoSprite(int altoSprite) {
		this.altoSprite = altoSprite;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
}
