package co.uniquindio.proyectojuego.elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import co.uniquindio.proyectojuego.Juego;
import co.uniquindio.proyectojuego.leaderboard.Registro;
import co.uniquindio.proyectojuego.pantalla.Menu;
import co.uniquindio.proyectojuego.pantalla.Ventana;

public class Mapa extends ElementoDeJuego {

	// Constante para delimitar el ancho de la imagen del fondo
	public static final int ANCHO_FONDO = 0;

	// Constante para delimitar el alto de la imagen del fondo.
	public static final int ALTO_FONDO = 0;

	// Constante del tamaño que tienen los bloques ( siempre son cuadrados ).
	public static final int TAMANIO_BLOQUE = 32;

	// Cantidad de sprites que seran cargados para el fondo.
	public static final int CANTIDAD_SPRITES = 1;

	private Personaje jugador;

	private boolean perdio = false;

	private boolean gano = false;

	private ArrayList<Bloque> nivel;

	private String direccion;

	private int velocidad = 3;

	private int mapWith;

	private int mapHeith;

	public static int contSec = 0;

	private int[][] mapa;

	private int mapNumber;

	private boolean iniciar = false;

	private double gravedad = 0.2821;

	private Timer timer;

	/**
	 * Constructor de la clase Mapa
	 * 
	 * @param x
	 * @param y
	 * @param direc     ubicación del archivo del mapa
	 * @param mapNumber número del mapa a jugar
	 */
	public Mapa(int x, int y, String direc, int mapNumber) {
		super(x, y, 0, 0);
		this.mapNumber = mapNumber;
		this.direccion = direc;
		nivel = new ArrayList<>();
		jugador = new Personaje(200, 300);
		generarMapa();
		iniciarTemporizador();
	}

	/**
	 * Metodo para contar el tiempo que se tarda en completar un nivel.
	 */
	public void iniciarTemporizador() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				contSec++;
			}
		});
		timer.setRepeats(true);
		timer.start();
	}

	/**
	 * Metodo usado para generar el mapa por medio de un archivo de texto.
	 */
	public void generarMapa() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(direccion));
			mapWith = Integer.parseInt(br.readLine());
			mapHeith = Integer.parseInt(br.readLine());
			mapa = new int[mapHeith][mapWith];

			String delimitiers = " ";
			for (int row = 0; row < mapHeith; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimitiers);
				for (int col = 0; col < mapWith; col++) {
					mapa[row][col] = Integer.parseInt(tokens[col]);
					int rc = mapa[row][col];
					if (rc == 1) {
						Bloque aux = new Bloque(x + col * (TAMANIO_BLOQUE), y + row * TAMANIO_BLOQUE, TipoBloque.AZUL);
						nivel.add(aux);
					}
					if (rc == 2) {
						Bloque aux = new Bloque(x + col * (TAMANIO_BLOQUE), y + row * TAMANIO_BLOQUE, TipoBloque.ROJO);
						nivel.add(aux);
					}
					if (rc == 5) {
						Bloque aux = new Bloque(x + col * (TAMANIO_BLOQUE), y + row * TAMANIO_BLOQUE, TipoBloque.META);
						nivel.add(aux);
					}

				}
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Metodo encargado de dibujar el mapa.
	 */
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 8000, 1200);

		jugador.render(g);
		for (Bloque bloque : nivel) {
			if (bloque.getPosicionX() > -TAMANIO_BLOQUE && bloque.getPosicionX() < Juego.ANCHO + TAMANIO_BLOQUE) {
				bloque.render(g);
			}
		}

	}

	/**
	 * Metodo encargado de modificar las posiciones de todos los bloques del mapa.
	 */
	public void actualizarPosiciones() {
		for (Bloque bloque : nivel) {
			if (iniciar) {
				bloque.setPosX(bloque.getPosX() - velocidad);
			}

		}
	}

	/**
	 * Metodo usado para dibujar 1 linea de bloques.
	 * 
	 * @param tipoDeBloque Tipo de bloque que desea que sea la linea.
	 * @param bloques      Lugar donde se guardaran los bloques.
	 * @param tamanoLinea  cantidad de bloques.
	 * @param distancia    posiciones x de donde iniciara (se multiplican por
	 *                     TAMANIO_BLOQUE para hacer una cuadricula)
	 * @param altura       posiciones y de donde iniciara (se multiplican por
	 *                     TAMANIO_BLOQUES para hacer una cuadricula)
	 * @param direccion    true = horizontal, false = vertical.
	 */
	public void realizarLineaDeBloques(TipoBloque tipoDeBloque, ArrayList<Bloque> bloques, int tamanoLinea,
			int distancia, int altura, boolean direccion) {
		Bloque nuevoBloque;
		int posX = 0, posY = 0;

		for (int i = 0; i < tamanoLinea; i++) {
			if (direccion) {
				posX = (distancia + i) * (TAMANIO_BLOQUE);
				posY = altura * TAMANIO_BLOQUE;
			} else {
				posX = distancia * TAMANIO_BLOQUE;
				posY = (altura + i) * TAMANIO_BLOQUE;
			}
			nuevoBloque = new Bloque(posX, posY, tipoDeBloque);
			bloques.add(nuevoBloque);
		}
	}

	@Override
	/**
	 * Metodo encargado de actualizar las interacciones del mapa.
	 */
	public void tick() {
		if (!(perdio || gano)) {
			jugador.tick();

			if (jugador.isCaer()) {
				if (jugador.getSalto() < jugador.FUERZA_DE_SALTO) {
					jugador.setSalto(jugador.getSalto() + gravedad);
				}
			}

			if (jugador.getPosX() < 0) {
				jugador.setPosX(jugador.getPosX() + velocidad);
			}

			if (jugador.getPosicionX() < -TAMANIO_BLOQUE * 2) {
				perdio = true;
			}

			if (jugador.getPosicionY() > 700) {
				perdio = true;
			}

			actualizarPosiciones();
			detectarColiciones();
		} else {
			if (iniciar && Ventana.getInstance().isVisible()) {
				pararJuego();
			}
		}

	}

	/**
	 * Metodo encargado de detectar las coliciones
	 */
	public void detectarColiciones() {
		for (int i = 0; i < nivel.size(); i++) {
			if (jugador.verificarAbajo(nivel.get(i).getBounds())) {
				jugador.setPosY(-(jugador.getY() - nivel.get(i).getPosicionY() + jugador.altoSprite));
				jugador.setCaer(false);
				jugador.setSaltar(false);
				jugador.setSalto(0);

				if (nivel.get(i).getTipoDeBloque() == TipoBloque.META) {
					setGano(true);
					String jugador = Ventana.getInstance().getNametag();
					int segundos = contSec;
					Registro r = new Registro(jugador, segundos);
					Ventana.getInstance().anadirTop(mapNumber, r);
					liberarSiguienteMapa();
				} else {
					if (!jugador.detectarColor(nivel.get(i).isColor())) {
						perdio = true;
					}
				}

			} else {
				jugador.setCaer(true);
			}

			if (jugador.verificarArriba(nivel.get(i).getBounds())) {
				jugador.setPosY(-(jugador.getY() - nivel.get(i).getPosicionY() - TAMANIO_BLOQUE));
				if (nivel.get(i).getTipoDeBloque() == TipoBloque.META) {
					setGano(true);
					String jugador = Ventana.getInstance().getNametag();
					int segundos = contSec;
					Registro r = new Registro(jugador, segundos);
					Ventana.getInstance().anadirTop(mapNumber, r);
					liberarSiguienteMapa();
				} else if (!jugador.detectarColor(nivel.get(i).isColor())) {
					perdio = true;
				}

			}

			if (jugador.verificarDerecha(nivel.get(i).getBounds())) {
				jugador.setPosX(-(jugador.getX() - nivel.get(i).getPosicionX() + jugador.anchoSprite));
				if (nivel.get(i).getTipoDeBloque() == TipoBloque.META) {
					setGano(true);
					String jugador = Ventana.getInstance().getNametag();
					int segundos = contSec;
					Registro r = new Registro(jugador, segundos);
					Ventana.getInstance().anadirTop(mapNumber, r);
					liberarSiguienteMapa();
				} else if (!jugador.detectarColor(nivel.get(i).isColor())) {
					perdio = true;
				}
			}
		}
	}

	/**
	 * Metodo para iniciar el mapa.
	 */
	public void iniciarMapa() {
		contSec = 0;
		iniciar = true;
		setGano(false);
		perdio = false;
	}

	/**
	 * Metodo para reiniciar el mapa en cuanto se pierde.
	 */
	public void reiniciarMapa() {
		timer.stop();
		contSec = 0;
		iniciar = false;
		perdio = false;
		gano = false;
		jugador.setPosX(0);
		jugador.setPosY(0);
		jugador.setColor(false);

		for (Bloque bloque : nivel) {
			bloque.setPosX(0);
			bloque.setPosY(0);
		}

	}

	/**
	 * Metodo para detener el juego.
	 */
	public void pararJuego() {

		iniciar = false;
		if (gano) {
			JOptionPane.showMessageDialog(null, "Felicidades, has pasado de nivel!", "Enhorabuena",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Has perdido, más suerte para la próxima, crack.", "Has perdido",
					JOptionPane.WARNING_MESSAGE);
		}
		Timer timer = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Ventana.getInstance().setVisible(false);
				@SuppressWarnings("unused")
				Menu menu = new Menu();
				reiniciarMapa();

			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	/**
	 * Metodo para desbloquear los niveles en cuanto se completa el nivel anterior.
	 */
	public void liberarSiguienteMapa() {
		if (mapNumber + 1 > Ventana.getInstance().getMapAmount()) {
			Ventana.getInstance().liberarMapa(mapNumber);
		} else {
			int map = mapNumber + 1;
			Ventana.getInstance().liberarMapa(map);
		}
	}

	// METODOS GETTERS AND SETTERS.

	@Override
	public int getVelocidad() {
		return velocidad;
	}

	@Override
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public Personaje getJugador() {
		return jugador;
	}

	public boolean isGano() {
		return gano;
	}

	public void setGano(boolean gano) {
		this.gano = gano;
	}
}
