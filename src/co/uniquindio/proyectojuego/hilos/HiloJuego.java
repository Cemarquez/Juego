package co.uniquindio.proyectojuego.hilos;

import co.uniquindio.proyectojuego.Juego;

/**
 * Clase encargada de atualizar el juego.
 * 
 * @author Sebastian David Rendon Soto.
 * @author Cesar Esteban Marquez.
 */
public class HiloJuego implements Runnable {

	private Juego juego;

	public static int fps = 0;

	public static int aps = 0;

	/**
	 * Constructor de la clase.
	 * 
	 * @param juego.
	 */
	public HiloJuego(Juego juego) {
		// TODO Auto-generated constructor stub
		this.juego = juego;
	}

	/**
	 * Método run que ejecuta el hilo
	 */
	@Override
	public void run() {
		int actualizacionesAcumuladas = 0;
		int framesAcumulados = 0;

		final int NN_POR_SEGUNDOS = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NN_POR_ACTUALIZACION = NN_POR_SEGUNDOS / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;
		while (true) {

			final long inicioBucle = System.nanoTime();

			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;
			delta += tiempoTranscurrido / NN_POR_ACTUALIZACION;

			while (delta >= 1) {
				juego.tick();
				actualizacionesAcumuladas++;
				delta--;
			}
			juego.render();
			framesAcumulados++;
			if (System.nanoTime() - referenciaContador > NN_POR_SEGUNDOS) {
				fps = framesAcumulados;
				aps = actualizacionesAcumuladas;

				actualizacionesAcumuladas = 0;
				framesAcumulados = 0;
				referenciaContador = System.nanoTime();
			}

		}
	}

}
