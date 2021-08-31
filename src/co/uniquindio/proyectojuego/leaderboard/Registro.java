package co.uniquindio.proyectojuego.leaderboard;

import java.io.Serializable;

/**
 * Clase realizada para guardar los records de los jugadores.
 * @author Sebastian David Rendon Soto
 * @author Cesar Estaban Marquez
 *
 */
public class Registro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Nombre o nick usado por el juegador.
	private String jugador;
	
	//Tiempo que tardo en completarlo.
	private int segundos;

	/**
	 * Constructor de la clase.
	 * @param jugador Nombre o nick del jugador.
	 * @param segundos tiempo que tardo en segundos.
	 */
	public Registro(String jugador, int segundos) {
		this.jugador = jugador;
		this.segundos = segundos;
	}
	
	//METODOS GETTERS AND SETTERS.

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

}
