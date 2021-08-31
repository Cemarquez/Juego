package co.uniquindio.proyectojuego.elementos;

import java.awt.Graphics;
import java.awt.Point;

import co.uniquindio.proyectojuego.sprite.HojaSprites;
/**
 * Clase creada para tener en cuenta los bloques del juego.
 * @author Sebastian David Rendon Soto.
 * @author Cesar Esteban Marquez
 */
public class Bloque extends ElementoDeJuego {
	
	//Ancho del sprite en pixeles
	public static final int PIXELES_ANCHO = 32;
	
	//Alto del sprite en pixeles
	public static final int PIXELES_ALTO = 32;

	//Cantidad de sprites.
	public static final int NUMERO_SPRITES = 0;

	//Posicion inicial de los sprites.
	public static final int NUMERO_SPRITES_ANIMACION = 0;

	private TipoBloque tipoDeBloque;
	
	//Maneja el color del bloque.
	private boolean color = false;

	/**
	 * Constructor del personaje.
	 * @param x
	 * @param y
	 * @param tipo
	 */
	public Bloque(int x, int y, TipoBloque tipo) {
		super(x, y, 0, 0);
		posX = 0;
		posY = 0;
		anchoSprite = PIXELES_ANCHO;
		altoSprite = PIXELES_ALTO;
		numeroSprites = NUMERO_SPRITES;
		this.tipoDeBloque = tipo;
		init();
	}

	@Override
	/**
	 * Metodo para realizar las actualizaciones del personaje.
	 */
	public void tick() {

	}
	
	/**
	 * Metodo para cargar la hoja de sprites de los bloques.
	 * 
	 * Imagen tomada de open game art.
	 * Autor: dorkSter.
	 */
	public void init()
	{
		hojaSprites = new HojaSprites("res/sprites/blocks.png", anchoSprite, altoSprite);
	}
	
	@Override
	/**
	 * Metodo encargado de dibujar al bloque.
	 */
	public void render(Graphics g) {
		if (tipoDeBloque == TipoBloque.ROJO) 
		{
			g.drawImage(hojaSprites.obtenerSprite(4, 0).getImagen(), x + posX, y + posY, anchoSprite, altoSprite, null);
			color = true;
		} else if (tipoDeBloque == TipoBloque.AZUL) {
			g.drawImage(hojaSprites.obtenerSprite(0, 0).getImagen(), x + posX, y + posY, anchoSprite, altoSprite, null);
			color = false;
		} else {
			g.drawImage(hojaSprites.obtenerSprite(1, 0).getImagen(), x + posX, y + posY, anchoSprite, altoSprite, null);
		}
	}
	
	//METODOS GETTERS AND SETTERS.
	
	public int getPosicionX() {
		return x + posX;
	}

	public int getPosicionY() {
		return y + posY;
	}

	public Point getPoint() {
		return new Point(x + posX, y + posY);
	}

	public TipoBloque getTipoDeBloque() {
		return tipoDeBloque;
	}

	public void setTipoDeBloque(TipoBloque tipoDeBloque) {
		this.tipoDeBloque = tipoDeBloque;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}
}
