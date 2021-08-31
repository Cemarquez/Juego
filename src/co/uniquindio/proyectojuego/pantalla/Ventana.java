package co.uniquindio.proyectojuego.pantalla;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import co.uniquindio.proyectojuego.Juego;
import co.uniquindio.proyectojuego.hilos.HiloMusica;
import co.uniquindio.proyectojuego.leaderboard.LeaderBoard;
import co.uniquindio.proyectojuego.leaderboard.Registro;
import co.uniquindio.proyectojuego.util.Persistencia;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 3880295608408242935L;
	private final int mapAmount = 2;
	private Juego juego;
	private HiloMusica hiloMusica;
	private String direccionMusica;
	private String nametag;
	private boolean enjuego = false;
	private static Ventana instance = new Ventana();
	private LeaderBoard leaderBoard;
	private String direccionArchivo;

	public Ventana() {
		direccionArchivo = "res/data/leaderboard.dat";
		inicializarLeaderBoard();
		this.juego = new Juego();
		init();
	}

	public void inicializarLeaderBoard() {
		File f = new File(direccionArchivo);
		if (f.exists()) {
			leaderBoard = Persistencia.deserialize(direccionArchivo);
		} else {
			leaderBoard = new LeaderBoard(mapAmount);
		}
	}

	public Juego getJuego() {
		return juego;
	}

	private void init() {
		try {
			setIconImage(ImageIO.read(new File("res/icon.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		direccionMusica = "res/music/Security.wav";
		hiloMusica = new HiloMusica(direccionMusica);
		Thread threadM = new Thread(hiloMusica);
		threadM.start();
		setSize(Juego.ANCHO, Juego.ALTO);
		add(juego);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
	}

	public static Ventana getInstance() {
		return instance;

	}

	public boolean isDisponible(int map) {
		return juego.isDisponible(map);
	}

	public void liberarMapa(int map) {
		juego.liberarMapa(map);
	}

	public boolean isEnjuego() {
		return enjuego;
	}

	public void setEnjuego(boolean enjuego) {
		this.enjuego = enjuego;
	}

	public HiloMusica getHiloMusica() {
		return hiloMusica;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public void iniciarJuego() {
		juego.iniciarJuego();
	}

	public void anadirTop(int map, Registro e) {
		leaderBoard.anadirTop(map, e);
		Persistencia.serialize(direccionArchivo, leaderBoard);

	}

	public String getNametag() {
		return nametag;
	}

	public void setNametag(String nametag) {
		this.nametag = nametag;
	}

	public int getMapAmount() {
		return mapAmount;
	}

	public LeaderBoard getLeaderBoard() {
		return leaderBoard;
	}

}
