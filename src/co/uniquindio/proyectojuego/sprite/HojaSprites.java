package co.uniquindio.proyectojuego.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Clase creada para manejar las hojas de sprites, Esta basada en el proyecto de
 * la hormiga.
 * 
 * @author Sebastian David Rendon Soto.
 * @author Cesar Estaban Marquez.
 */
public class HojaSprites {

	private BufferedImage hojaSprites;

	private int altoHojaSprite, anchoHojaSprite, anchoSprite, altoSprite, anchoHojaPixeles, altoHojaPixeles;

	private Sprite[] sprites;

	/**
	 * Constructor de la clase.
	 * 
	 * @param ruta       Url donde se almacena la imagen (hoja de sprites).
	 * @param ladoSprite el tamaño de cada sprite.
	 */
	public HojaSprites(final String ruta, final int anchoSprite, final int altoSprite) {

		try {
			File file = new File(ruta);
			hojaSprites = ImageIO.read(file);
			anchoHojaPixeles = hojaSprites.getWidth();
			altoHojaPixeles = hojaSprites.getHeight();
			anchoHojaSprite = hojaSprites.getWidth() / anchoSprite;
			altoHojaSprite = hojaSprites.getHeight() / altoSprite;
			sprites = new Sprite[anchoHojaSprite * altoHojaSprite];
			this.anchoSprite = anchoSprite;
			this.altoSprite = altoSprite;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rellenarSprites();
	}

	/**
	 * Método que rellenar los sprites
	 */
	public void rellenarSprites() {

		for (int y = 0; y < altoHojaSprite; y++) {
			for (int x = 0; x < anchoHojaSprite; x++) {
				final int posX = x * anchoSprite;
				final int posY = y * altoSprite;
				sprites[x + y * anchoHojaSprite] = new Sprite(
						hojaSprites.getSubimage(posX, posY, anchoSprite, altoSprite));

			}
		}
	}

	// GETTERS AND SETTERS
	public BufferedImage getImagen(int x, int y) {
		return hojaSprites.getSubimage(x * anchoSprite, y * altoSprite, anchoSprite, altoSprite);
	}

	public int getNumeroSpritesV() {
		return hojaSprites.getHeight() / altoSprite;
	}

	public int getNumeroSpritesH() {
		return hojaSprites.getWidth() / anchoSprite;
	}

	public int getAnchoHojaPixeles() {
		return anchoHojaPixeles;
	}

	public void setAnchoHojaPixeles(int anchoHojaPixeles) {
		this.anchoHojaPixeles = anchoHojaPixeles;
	}

	public int getAltoHojaPixeles() {
		return altoHojaPixeles;
	}

	public void setAltoHojaPixeles(int altoHojaPixeles) {
		this.altoHojaPixeles = altoHojaPixeles;
	}

	public Sprite obtenerSprite(final int x, final int y) {
		return sprites[x + y * anchoHojaSprite];
	}

	public Sprite obtenerSprite(final int frame) {
		return sprites[frame];
	}
}
