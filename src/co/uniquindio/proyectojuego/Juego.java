package co.uniquindio.proyectojuego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Hashtable;

import co.uniquindio.proyectojuego.elementos.Mapa;
import co.uniquindio.proyectojuego.hilos.HiloJuego;

public class Juego extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ANCHO = 960;

	public static final int ALTO = 600;

	private boolean juegoIniciado = false;
	public static String nombre = "";
	private boolean musicaActiva = true;
	private Mapa nivel;
	private Graphics g;
	private int numMapa = 0;
	private Hashtable<Integer, Boolean> mapasDisponibles = new Hashtable<Integer, Boolean>();

	/**
	 * Constructor de la clase
	 */
	public Juego() {
		Dimension dimension = new Dimension(ANCHO, ALTO);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		preInit();
	}

	/**
	 * Método que permite inicializar el mapa seleccionado
	 */
	private void init() {
		nivel = new Mapa(0, 0, "res/maps/mapa" + numMapa + ".txt", numMapa);
		addKeyListener(new Teclado(nivel.getJugador()));
	}

	/**
	 * Método que permite precargar los mapas establecidos
	 */
	public void preInit() {
		mapasDisponibles.put(0, true);
		mapasDisponibles.put(1, false);
		mapasDisponibles.put(2, false);

	}

	/**
	 * Método que permite dibujar en la superficie de dibujo todos los elementos del
	 * juego
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, ANCHO, ALTO);
		nivel.render(g);
		g.setColor(Color.BLUE);
		g.drawString("Duración: " + Mapa.contSec + " segundos", 20, 40);
		g.drawString("Fps: " + HiloJuego.fps + " Aps: " + HiloJuego.aps, 20, 20);
		g.dispose();
		bs.show();
	}

	/**
	 * Método que actualiza los elementos del juego
	 */
	public void tick() {
		nivel.tick();
	}

	/**
	 * Método que inicia el juego
	 */
	public void iniciarJuego() {
		if (juegoIniciado) {
			init();
			nivel.iniciarMapa();
		} else {
			juegoIniciado = true;
			init();
			requestFocus();
			new Thread(new HiloJuego(this)).start();
			nivel.iniciarMapa();
		}

	}

	/**
	 * Método que permite desbloquear un mapa ingresado
	 * 
	 * @param numMapa Número del mapa a desbloquear
	 */
	public void liberarMapa(int numMapa) {
		mapasDisponibles.put(numMapa, true);
	}

	public boolean isDisponible(int numMapa) {
		return mapasDisponibles.get(numMapa);
	}

	public int getNumMapa() {
		return numMapa;
	}

	public void setNumMapa(int numMapa) {
		this.numMapa = numMapa;
	}

	public boolean isMusicaActiva() {
		return musicaActiva;
	}

	public synchronized void setMusicaActiva(boolean musicaActiva) {
		this.musicaActiva = musicaActiva;
	}
}
