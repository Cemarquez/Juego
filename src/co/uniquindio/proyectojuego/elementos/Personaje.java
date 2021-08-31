package co.uniquindio.proyectojuego.elementos;

import java.awt.Graphics;
import java.awt.Rectangle;

import co.uniquindio.proyectojuego.sprite.HojaSprites;

/**
 * Clase creada para tener en cuenta al personaje a la hora de jugar
 * @author Sebastian David Rendon Soto.
 * @author Cesar Estaban Marquez.
 */
public class Personaje extends ElementoDeJuego {
	
	//Constante para tener en cuenta cuando debe saltar el personaje
	public final int FUERZA_DE_SALTO = 10;
	
	//Constante que limita la cantidad de sprites para el personaje
	public static final int TOTAL_SPRITES = 5;
	
	//Constante para iniciar a tomar los sprites
	public static final int TOTAL_SPRITES_ANIMACION = 0;
	
	//Ancho de los sprites
	public static final int PIXELES_ANCHO = 32;
	
	//alto de los sprites
	public static final int PIXELES_ALTO = 32;
	
	//variable para modificar que color debe tener el sprite y para confirmar choque con otra clase
	private boolean color = false;
	
	//variable para modificar si cae o no el personaje.
	private boolean caer = false;
	
	//variable para confirmar si el personaje esta saltando o no.
	private boolean saltar = false;
	
	//variable para aumentar la altura del personaje.
	private double salto;
	
	//variables para poder usar los sprites en diferentes tiempos.
	private int frame, frameDelay;
	
	//varibale para reducir las coliciones de largo o ancho.
	private int espacio = 3;
	
	//variable para aumentar la cantidad de pixeles a la cual detectar
	private int alcance = 1;
	
	private HojaSprites hojaSpriteRoja;
	
	/**
	 * Constructor del personaje
	 * @param x posicion inicial del personaje en el eje x
	 * @param y posicion inicial del personaje en el eje y
	 */
	public Personaje(int x, int y) {
		super(x, y, 0, 0);
		nombreHojaSprite = "res/sprites/spriteJugadorAzul.png";
		numeroSprites = TOTAL_SPRITES;
		this.anchoSprite = PIXELES_ANCHO;
		this.altoSprite = PIXELES_ALTO;
		velocidad = 0;
		salto = 0;
		frame = 0;
		frameDelay = 0;
		posX = 0;
		posY = 0;
		init();
	}

	@Override
	/**
	 * Metodo para actualizar al personaje durante el juego.
	 */
	public void tick() {
		frameDelay++;
		if (isSaltar()) {
			frame = 2;
		} else if (frameDelay % 10 == 0) {
			frame++;
			if (frame >= TOTAL_SPRITES) {
				frame = 0;
			}
			if (frameDelay > 100) {
				frameDelay = 0;
			}
		}

		posY += salto;
		posX += velocidad;
	}

	@Override
	/**
	 * Metodo para cargar los sprites necesario del personaje.
	 */
	public void init() {
		hojaSprites = new HojaSprites(nombreHojaSprite, anchoSprite, altoSprite);
		hojaSpriteRoja = new HojaSprites("res/sprites/spriteJugadorRojo.png", anchoSprite, altoSprite);
	}

	@Override
	/**
	 * Metodo para dibujar al personaje
	 * @param Graphics g es la clase que se encarga de dibujar al personaje.
	 */
	public void render(Graphics g) {

		if (!color) {
			g.drawImage(hojaSprites.obtenerSprite(frame).getImagen(), x + posX, y + posY,  anchoSprite, altoSprite, null);
		} else {
			g.drawImage(hojaSpriteRoja.obtenerSprite(frame).getImagen(), x + posX, y + posY,  anchoSprite, altoSprite,
					null);
		}
	}
	
	/**
	 * Metodo para realizar un verificacion arriba del personaje.
	 * @param bloque rectangulo que sera comprobado si hay o no interseccion.
	 * @return ward.
	 */
	public boolean verificarArriba(Rectangle bloque) {
		boolean ward = false;
		if (new Rectangle(x + posX, y + posY - alcance, anchoSprite - espacio*2, alcance).intersects(bloque)) {
			ward = true;
		}
		return ward;
	}
	
	/**
	 * Metodo para realizar un verificacion abajo del personaje.
	 * @param bloque rectangulo que sera comprobado si hay o no interseccion.
	 * @return ward.
	 */
	public boolean verificarAbajo(Rectangle bloque) {
		boolean ward = false;
		if (new Rectangle(x + posX, y + posY + altoSprite, anchoSprite - espacio*2, alcance)
				.intersects(bloque)) {
			ward = true;
		}
		return ward;
	}
	
	/**
	 * Metodo para realizar una verificacion a la izquierda del personaje
	 * @param bloque rectangulo que sera comprobado si hay o no interseccion.
	 * @return ward
	 */
	public boolean verificarIzquierda(Rectangle bloque) {
		boolean ward = false;
		if (new Rectangle(x + posX - alcance, y + posY, alcance, altoSprite - espacio*2).intersects(bloque)) {
			ward = true;
		}
		return ward;
	}
	
	/**
	 * Metodo para verificiar a la derecha del personaje.
	 * @param bloque rectangulo que sera comprobado si hay o no interseccion.
	 * @return ward.
	 */
	public boolean verificarDerecha(Rectangle bloque) {
		boolean ward = false;
		if (new Rectangle(x + posX + anchoSprite, y + posY, alcance, altoSprite - espacio*2)
				.intersects(bloque)) {
			ward = true;
		}

		return ward;
	}
	
	/**
	 * Metodo para detercar si al tocar el bloque es del mismo color.
	 * @param color color del bloque que se toca.
	 * @return ward.
	 */
	public boolean detectarColor(boolean color) {
		boolean ward = false;
		if (this.color == color) {
			ward = true;
		}
		return ward;
	}
	
	// METODOS GETTERS AND SETTERS 
	
	public int getPosicionX() {
		return posX + x;
	}

	public int getPosicionY() {
		return posY + y;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public boolean isCaer() {
		return caer;
	}

	public void setCaer(boolean caer) {
		this.caer = caer;
	}

	public double getSalto() {
		return salto;
	}

	public void setSalto(double salto) {
		this.salto = salto;
	}

	public boolean isSaltar() {
		return saltar;
	}

	public void setSaltar(boolean saltar) {
		this.saltar = saltar;
	}

}
