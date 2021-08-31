package co.uniquindio.proyectojuego.leaderboard;

import java.io.Serializable;
import java.util.ArrayList;

public class LeaderBoard implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_PER_LEADER = 8;

	private ArrayList<Leader> leaders;
	private int mapAmount;

	/**
	 * Constructor de la clase
	 * 
	 * @param mapAmount
	 */
	public LeaderBoard(int mapAmount) {
		this.mapAmount = mapAmount;
		leaders = new ArrayList<Leader>();
		preInit();
	}

	/**
	 * Método creo los leaders máximos por mapa
	 */
	public void preInit() {
		for (int i = 0; i <= mapAmount; i++) {
			leaders.add(new Leader());
		}
	}

	public void anadirTop(int map, Registro r) {
		leaders.get(map).anadirTop(r);
	}

	public ArrayList<Registro> getLeader(int map) {
		return leaders.get(map).getRegistros();
	}

	public boolean leaderIsEmpty(int map) {
		if (getLeader(map).isEmpty()) {
			return true;
		}

		return false;
	}

}
