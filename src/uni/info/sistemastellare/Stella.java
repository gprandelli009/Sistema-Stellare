package uni.info.sistemastellare;

import java.util.ArrayList;

public class Stella {
	static Punto posizione;
	static int massa;
	static String id;
	static ArrayList<Pianeta> pianeti = new ArrayList<>();

	public static void riempi(int x, int y, int massa, String id) {
		posizione = new Punto(x, y);
		Stella.massa = massa;
		Stella.id = id;
	}

	public static void aggiungiPianeta(int x, int y, int massa, String id) {
		pianeti.add(new Pianeta(x, y, massa, id));
	}

	public static String getId() {
		return id;
	}

	public static int getMassa() {

		return massa;
	}
}
