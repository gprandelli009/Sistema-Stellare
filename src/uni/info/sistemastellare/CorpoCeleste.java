package uni.info.sistemastellare;

public abstract class CorpoCeleste {

	protected Punto posizione;
	protected int massa;
	protected String id;

	public CorpoCeleste(int x, int y, int massa, String id) {
		posizione = new Punto(x, y);
		this.massa = massa;
		this.id = id;
	}

	public String toString() {
		return ("Corpo celeste " + id + "\nNella posizione " + posizione.toString() + "\nDi massa " + massa);
	}

}
