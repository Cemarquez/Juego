package co.uniquindio.proyectojuego.leaderboard;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Clase creada para manejar los mejores tiempos.
 * @author Sebastian David Rendon Soto.
 * @author Cesar Esteban Marquez.
 */
public class Leader implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Registro> registros;
	
	/**
	 * Constructor de la clase.
	 */
	public Leader() {
		registros = new ArrayList<Registro>();
	}
	
	/**
	 * Metodo para poner al mejor tiempo en pasar un nivel
	 * @param e Ingresa un registro para verificar si puede o no estar en los mejores.
	 */
	public void anadirTop(Registro e) {
		ordenarRegistros();
		if (buscarRegistro(e) != -1) {
			Registro r = registros.get(buscarRegistro(e));
			if (e.getSegundos() < r.getSegundos()) {
				r.setSegundos(e.getSegundos());
				registros.set(buscarRegistro(e), r);
			}
		} else {
			if (registros.size() <= LeaderBoard.MAX_PER_LEADER) {
				registros.add(e);
			} else {
				if (puedeEntrar(e)) {
					registros.set(registros.size() - 1, e);
				}
			}
		}
		ordenarRegistros();
	}
	
	/**
	 * Metodo para realizar la busqueda de un registro.
	 * @param e Registro que se desea buscar.
	 * @return posicion dentro del ArrayList
	 */
	public int buscarRegistro(Registro e) {
		for (int i = 0; i < registros.size(); i++) {
			Registro r = registros.get(i);
			if (r.getJugador().equals(e.getJugador())) {
				return i;
			}
		}

		return -1;
	}
	
	/**
	 * Metodo burbuja para ordenar los registros dependiendo del tiempo.
	 */
	public void ordenarRegistros() {

		for (int i = 1; i < registros.size(); i++) {
			for (int j = 0; j < registros.size() - 1; j++) {
				if (registros.get(j).getSegundos() < registros.get(j + 1).getSegundos()) {
					Registro aux = registros.get(j);
					registros.set(j, registros.get(j + 1));
					registros.set(j + 1, aux);

				}
			}
		}
	}
	
	/**
	 * Metodo para verificar que el registro no es mayor que cualquiera dentro para evitar guardarlo
	 * @param e Registro de tiempo a guardar.
	 * @return boolean true = puede entrar, false = no puede entrar.
	 */
	public boolean puedeEntrar(Registro e) {
		int con = 0;
		for (Registro r : registros) {
			if (e.getSegundos() < r.getSegundos()) {
				con++;
			}

			if (con > 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo para obtener los registros y ordenados.
	 * @return registros.
	 */
	public ArrayList<Registro> getRegistros() {
		ordenarRegistros();
		return registros;
	}

}
