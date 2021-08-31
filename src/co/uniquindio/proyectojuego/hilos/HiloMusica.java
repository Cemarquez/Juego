package co.uniquindio.proyectojuego.hilos;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase encargada de reproducir la canci�n.
 * 
 * @author Sebastian David Rendon Soto.
 * @author Cesar Esteban Marquez.
 */
public class HiloMusica implements Runnable {

	private Clip musica;

	private String direccion;

	private volatile boolean musicaActiva = true;

	/**
	 * Constructor de la clase.
	 * 
	 * @param direccion Url donde est� ubicada la m�sica.
	 */
	public HiloMusica(String direccion) {
		this.direccion = direccion;
		try {
			musica = AudioSystem.getClip();
			musica.open(AudioSystem.getAudioInputStream(new File(this.direccion)));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {

			if (musicaActiva) {

				musica.start();
				musica.loop(Clip.LOOP_CONTINUOUSLY);

			} else {
				if (!musicaActiva) {
					musica.stop();
				}
			}

		}

	}

	/**
	 * M�todo para detener la m�sica.
	 */
	public synchronized void pararMusica() {
		musicaActiva = false;
	}

	/**
	 * M�todo para reiniciar la m�sica.
	 */
	public synchronized void iniciarMusica() {
		musicaActiva = true;
	}

}
