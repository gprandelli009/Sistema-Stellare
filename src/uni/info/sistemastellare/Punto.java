package uni.info.sistemastellare;

public class Punto {

	private int x, y;

	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static double distanzaTraDuePunti(Punto p1, Punto p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

}

