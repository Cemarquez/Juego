package co.uniquindio.proyectojuego.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import co.uniquindio.proyectojuego.leaderboard.LeaderBoard;

public class Persistencia {

	/**
	 * M�todo que permite guardar el archivo ingresado
	 * 
	 * @param nombreArchivo direcci�n del archivo donde ser� guardado
	 * @param data          Objeto a guardar
	 */
	public static void serialize(String nombreArchivo, LeaderBoard data) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(nombreArchivo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * M�todo que obtiene el archivo guardado y lo carga
	 * 
	 * @param nombreArchivo direcci�n del archivo donde ser� guardado
	 * @return Retorna el objeto guardado
	 */
	public static LeaderBoard deserialize(String nombreArchivo) {
		LeaderBoard data = null;
		try {
			FileInputStream fis = new FileInputStream(nombreArchivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data = (LeaderBoard) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/***
	 * M�todo que lee un archivo de texto
	 * 
	 * @param ruta direcci�n del archivo
	 * @return retorta un array en el que estar� el archivo
	 * @throws IOException
	 */
	public static ArrayList<String> leerArchivo(String ruta) throws IOException {
		File miArchivo = new File(ruta);
		FileReader miFileReader = new FileReader(miArchivo);
		BufferedReader miBufferReader = new BufferedReader(miFileReader);
		String linea;
		ArrayList<String> misLineas = new ArrayList<String>();

		while ((linea = miBufferReader.readLine()) != null) {
			misLineas.add(linea);
		}

		miBufferReader.close();
		miFileReader.close();
		return misLineas;
	}
}
