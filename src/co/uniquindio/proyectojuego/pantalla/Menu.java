package co.uniquindio.proyectojuego.pantalla;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import co.uniquindio.proyectojuego.Juego;
import co.uniquindio.proyectojuego.leaderboard.Registro;
import co.uniquindio.proyectojuego.util.TextPrompt;

public class Menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Ventana ventana;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblFondo;
	private JLabel labelNombre;
	private JButton botonSiguiente;
	private JButton botonLeaderboard;
	private JButton botonSelectorMapa;
	private JButton botonMusica;
	private JButton botonPlay;
	private String nombre;
	private JTextField tfNombreJugador;
	private JButton botonSalir;
	@SuppressWarnings("rawtypes")
	private JComboBox comboMapaSelec;
	private JLabel labelSeleccion;
	private JLabel labelImagenMapa;
	private JButton botonMenuPrincipal;
	@SuppressWarnings("rawtypes")
	private JComboBox comboMapaSelecLeader;
	private JLabel labelLeader;
	private JScrollPane scrollPane;
	private JPanel panelScroll;

	public Menu() {
		this.ventana = Ventana.getInstance();
		init();
		configurarMenu();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		try {
			setIconImage(ImageIO.read(new File("res/icon.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Juego.ANCHO, Juego.ALTO);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		botonSiguiente = new JButton();
		botonSiguiente.setIcon(new ImageIcon("res/icons/iconoNext.png"));
		botonSiguiente.setBounds(Juego.ANCHO / 2 - 30, Juego.ALTO / 2 + 100, 64, 64);
		botonSiguiente.setOpaque(false);
		botonSiguiente.setContentAreaFilled(false);
		botonSiguiente.setBorderPainted(false);
		botonSiguiente.addActionListener(this);
		panel.add(botonSiguiente);

		labelNombre = new JLabel();
		labelNombre.setBounds(100, 100, 50, 10);
		panel.add(labelNombre);

		labelLeader = new JLabel();
		labelLeader.setBounds(301, 217, 320, 320);
		labelLeader.setVisible(false);
		panel.add(labelLeader);

		botonSelectorMapa = new JButton();
		botonSelectorMapa.setIcon(new ImageIcon("res/icons/iconoPlay.png"));
		botonSelectorMapa.setBounds(Juego.ANCHO / 2 - 30, Juego.ALTO / 2, 64, 64);
		botonSelectorMapa.setOpaque(false);
		botonSelectorMapa.setContentAreaFilled(false);
		botonSelectorMapa.setBorderPainted(false);
		botonSelectorMapa.addActionListener(this);
		botonSelectorMapa.setVisible(false);
		panel.add(botonSelectorMapa);

		botonPlay = new JButton();
		botonPlay.setIcon(new ImageIcon("res/icons/iconoPlay.png"));
		botonPlay.setBounds(Juego.ANCHO / 2 - 30, Juego.ALTO / 2 + 200, 64, 64);
		botonPlay.setOpaque(false);
		botonPlay.setContentAreaFilled(false);
		botonPlay.setBorderPainted(false);
		botonPlay.addActionListener(this);
		botonPlay.setVisible(false);
		panel.add(botonPlay);

		botonMenuPrincipal = new JButton();
		botonMenuPrincipal.setIcon(new ImageIcon("res/icons/iconoHome.png"));
		botonMenuPrincipal.setBounds(50, 50, 64, 64);
		botonMenuPrincipal.setOpaque(false);
		botonMenuPrincipal.setContentAreaFilled(false);
		botonMenuPrincipal.setBorderPainted(false);
		botonMenuPrincipal.addActionListener(this);
		botonMenuPrincipal.setVisible(false);
		panel.add(botonMenuPrincipal);

		String[] mapas = { "Fácil", "Medio", "Difícil" };
		comboMapaSelec = new JComboBox(mapas);
		comboMapaSelec.setBounds(Juego.ANCHO / 2 - 40, Juego.ALTO / 5, 100, 32);
		comboMapaSelec.setVisible(false);
		comboMapaSelec.addActionListener(this);
		panel.add(comboMapaSelec);

		comboMapaSelecLeader = new JComboBox(mapas);
		comboMapaSelecLeader.setBounds(Juego.ANCHO / 2 - 40, Juego.ALTO / 5, 100, 32);
		comboMapaSelecLeader.setVisible(false);
		comboMapaSelecLeader.addActionListener(this);
		panel.add(comboMapaSelecLeader);

		labelSeleccion = new JLabel("Selección de mapa");
		labelSeleccion.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
		labelSeleccion.setBounds(Juego.ANCHO / 2 - 50, Juego.ALTO / 8, 200, 32);
		labelSeleccion.setVisible(false);
		panel.add(labelSeleccion);

		labelImagenMapa = new JLabel();
		labelImagenMapa.setBounds(Juego.ANCHO / 2 - 200, Juego.ALTO / 3, 480, 200);
		labelImagenMapa.setIcon(new ImageIcon("res/facil.jpg"));
		labelImagenMapa.setVisible(false);
		panel.add(labelImagenMapa);

		botonSalir = new JButton();
		botonSalir.setIcon(new ImageIcon("res/icons/iconoSalir.png"));
		botonSalir.setBounds(Juego.ANCHO - 100, Juego.ALTO / 20, 64, 64);
		botonSalir.setOpaque(false);
		botonSalir.setContentAreaFilled(false);
		botonSalir.setBorderPainted(false);
		botonSalir.addActionListener(this);
		panel.add(botonSalir);

		botonLeaderboard = new JButton();
		botonLeaderboard.setIcon(new ImageIcon("res/icons/iconoLeaderboard.png"));
		botonLeaderboard.setBounds(Juego.ANCHO - 100, Juego.ALTO / 3, 64, 64);
		botonLeaderboard.setOpaque(false);
		botonLeaderboard.setContentAreaFilled(false);
		botonLeaderboard.setBorderPainted(false);
		botonLeaderboard.addActionListener(this);
		botonLeaderboard.setVisible(false);
		panel.add(botonLeaderboard);

		botonMusica = new JButton();
		botonMusica.setIcon(new ImageIcon("res/icons/iconoMusicaOn.png"));
		botonMusica.setBounds(Juego.ANCHO - 100, Juego.ALTO / 2, 64, 64);
		botonMusica.setOpaque(false);
		botonMusica.setContentAreaFilled(false);
		botonMusica.setBorderPainted(false);
		botonMusica.addActionListener(this);
		botonMusica.setVisible(false);
		panel.add(botonMusica);

		tfNombreJugador = new JTextField();
		tfNombreJugador.setBounds(Juego.ANCHO / 3, Juego.ALTO / 2, 320, 32);
		TextPrompt ph = new TextPrompt("                                      Ingrese nametag", tfNombreJugador);
		ph.changeAlpha(0.5f);
		ph.changeStyle(Font.BOLD + Font.ITALIC);
		panel.add(tfNombreJugador);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 232, 501, 245);
		panel.add(scrollPane);

		scrollPane.setVisible(false);
		panelScroll = new JPanel();
		scrollPane.setViewportView(panelScroll);
		panelScroll.setLayout(new BoxLayout(panelScroll, BoxLayout.Y_AXIS));
		createTabla(0);
		lblFondo = new JLabel("");
		lblFondo.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
		lblFondo.setBounds(0, 0, 960, 600);
		panel.add(lblFondo);
		lblFondo.setIcon(new ImageIcon("res/ventanaPrincipal.jpg"));

		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void configurarMenu() {

		if (ventana.isEnjuego()) {
			mostrarMenuJugar();
		} else {
			mostrarMenuNickName();
		}

	}

	public void mostrarMenuNickName() {
		tfNombreJugador.setVisible(true);
		botonSiguiente.setVisible(true);
		botonSelectorMapa.setVisible(false);
		botonLeaderboard.setVisible(false);
		botonMusica.setVisible(false);
		labelNombre.setVisible(false);
	}

	public void mostrarMenuJugar() {
		comboMapaSelec.setVisible(false);
		comboMapaSelecLeader.setVisible(false);
		botonMenuPrincipal.setVisible(false);
		labelSeleccion.setVisible(false);
		labelImagenMapa.setVisible(false);
		nombre = ventana.getNametag();
		tfNombreJugador.setVisible(false);
		botonSiguiente.setVisible(false);
		botonSelectorMapa.setVisible(true);
		botonLeaderboard.setVisible(true);
		botonMusica.setVisible(true);
		scrollPane.setVisible(false);
		labelNombre.setVisible(true);
		labelNombre.setText(nombre);
		botonPlay.setVisible(false);
		lblFondo.setIcon(new ImageIcon("res/ventanaPrincipal.jpg"));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object e = event.getSource();
		if (e.equals(botonSiguiente))
			definirAccionBotonSiguiente();

		if (e.equals(botonMusica))
			definirAccionBotonMusica();

		if (e.equals(botonPlay))
			definirAccionBotonPlay();

		if (e.equals(botonSalir))
			definirAccionBotonSalir();

		if (e.equals(botonSelectorMapa))
			definirAccionBotonSelectorMapa();

		if (e.equals(comboMapaSelec))
			definirAccionComboSelectorMapa();

		if (e.equals(botonMenuPrincipal))
			definirAccionBotonMenuPrincipal();

		if (e.equals(botonLeaderboard))
			definirAccionBotonLeaderboard();

		if (e.equals(comboMapaSelecLeader))
			definirAccionComboSelecLeader();

	}

	public void definirAccionBotonMenuPrincipal() {
		mostrarMenuJugar();
	}

	public void definirAccionComboSelecLeader() {
		scrollPane.setVisible(true);
		String selec = comboMapaSelecLeader.getSelectedItem().toString();
		if (selec.equalsIgnoreCase("fácil")) {
			createTabla(0);
		} else if (selec.equalsIgnoreCase("medio")) {
			createTabla(1);
		} else {
			createTabla(2);
		}
	}

	public void createTabla(int map) {

		ArrayList<Registro> leader = Ventana.getInstance().getLeaderBoard().getLeader(map);
		JLabel lbl;
		panelScroll = new JPanel();
		panelScroll.setLayout(new BoxLayout(panelScroll, BoxLayout.Y_AXIS));
		String s = "                        LeaderBoard  ";

		lbl = new JLabel(s);
		lbl.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
		panelScroll.add(lbl);
		JLabel label = new JLabel("   ");
		panelScroll.add(label);
		int con = 1;
		for (int i = 0; i < leader.size(); i++) {
			s = "  " + con + ". Nombre: " + leader.get(i).getJugador() + " Tiempo: " + leader.get(i).getSegundos()
					+ " segundos  ";

			lbl = new JLabel(s);
			lbl.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 14));
			panelScroll.add(lbl);
			s = "";
			con++;
		}
		scrollPane.setViewportView(panelScroll);

	}

	public void definirAccionBotonLeaderboard() {
		scrollPane.setVisible(true);
		labelLeader.setVisible(true);
		botonMenuPrincipal.setVisible(true);
		botonSelectorMapa.setVisible(false);
		botonMusica.setVisible(false);
		botonLeaderboard.setVisible(false);
		labelNombre.setVisible(false);
		comboMapaSelecLeader.setVisible(true);
		lblFondo.setIcon(new ImageIcon("res/ventanaSecundaria.jpg"));
	}

	public void definirAccionBotonSelectorMapa() {
		botonMenuPrincipal.setVisible(true);
		botonSelectorMapa.setVisible(false);
		botonMusica.setVisible(false);
		botonLeaderboard.setVisible(false);
		labelNombre.setVisible(false);
		comboMapaSelec.setVisible(true);
		botonPlay.setVisible(true);
		labelImagenMapa.setVisible(true);
		labelSeleccion.setVisible(true);
		lblFondo.setIcon(new ImageIcon("res/ventanaSecundaria.jpg"));
	}

	public void definirAccionComboSelectorMapa() {
		String s = comboMapaSelec.getSelectedItem().toString();
		if (s.equalsIgnoreCase("fácil")) {
			labelImagenMapa.setIcon(new ImageIcon("res/facil.jpg"));
		} else if (s.equalsIgnoreCase("medio")) {
			labelImagenMapa.setIcon(new ImageIcon("res/medio.png"));
		} else {

		}

	}

	public void definirAccionBotonSiguiente() {

		if (tfNombreJugador.getText().contains(" ") || tfNombreJugador.getText().contentEquals("")) {
			mostrarPantallaError("Debe ingresar un nombre de jugador válido para continuar.");
		} else {

			nombre = tfNombreJugador.getText();
			Ventana.getInstance().setNametag(nombre);
			tfNombreJugador.setVisible(false);
			botonSiguiente.setVisible(false);
			botonSelectorMapa.setVisible(true);
			botonLeaderboard.setVisible(true);
			botonMusica.setVisible(true);
			labelNombre.setVisible(true);
			labelNombre.setText(nombre);

		}

	}

	public void definirAccionBotonMusica() {
		if (ventana.getJuego().isMusicaActiva()) {
			ventana.getJuego().setMusicaActiva(false);
			ventana.getHiloMusica().pararMusica();
			botonMusica.setIcon(new ImageIcon("res/icons/iconoMusicaOff.png"));
			botonMusica.setVisible(true);
		} else {
			ventana.getJuego().setMusicaActiva(true);
			ventana.getHiloMusica().iniciarMusica();
			botonMusica.setIcon(new ImageIcon("res/icons/iconoMusicaOn.png"));
		}
	}

	public void definirAccionBotonPlay() {
		String s = comboMapaSelec.getSelectedItem().toString();
		int mapa = 0;
		if (s.equalsIgnoreCase("fácil")) {
			mapa = 0;
		} else if (s.equalsIgnoreCase("medio")) {
			mapa = 1;
		} else {
			mapa = 2;
		}
		Ventana.getInstance().getJuego().setNumMapa(mapa);
		if (Ventana.getInstance().isDisponible(mapa)) {
			Ventana.getInstance().setEnjuego(true);
			this.setVisible(false);
			ventana.setVisible(true);
			ventana.iniciarJuego();

		} else {
			JOptionPane.showMessageDialog(null,
					"Lo siento titán, pero debes desbloquear este mapa para poder jugar en él.", "Mapa bloqeueado",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void definirAccionBotonSalir() {
		System.exit(0);
	}

	public Ventana getVentana() {
		return ventana;
	}

	public void mostrarPantallaError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
