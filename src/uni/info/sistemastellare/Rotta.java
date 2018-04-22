package uni.info.sistemastellare;

public class Rotta {
	final private static String ERRORE_ROTTA = "Rotta non valida";
	private String idPartenza, idArrivo, idCorrente;
	private TipiCorpiCelesti tipoCorrente;
	private int[] posPartenza = new int[2];
	private int[] posArrivo = new int[2];
	private double distanzaPercorsa = 0;
	boolean metaSuperata = false;

	public Rotta(String idPartenza, String idArrivo) {
		this.idPartenza = idPartenza;
		this.idArrivo = idArrivo;
		idCorrente = idPartenza;
		tipoCorrente = identificaTipo(idPartenza, posPartenza);
		identificaTipo(idArrivo, posArrivo); //Devo riempire l`array e controllare che
											//il pianeta di arrivo esista
	}

	public StringBuilder calcolaRotta() {
		StringBuilder percorso = new StringBuilder();

		percorso.append(idPartenza);
		percorso.append(">");

		if (tipoCorrente == TipiCorpiCelesti.STELLA) metaSuperata = true;
		prossimoSalto();

		while (!idCorrente.equals(idArrivo)) {
			switch (tipoCorrente) {
				case STELLA:
					calcolaSalto();
					idCorrente = Stella.id;
					percorso.append(idCorrente);
					percorso.append(">");
					metaSuperata = true;
					prossimoSalto();
					break;

				case PIANETA:
					calcolaSalto();
					idCorrente = metaSuperata ?
							Stella.pianeti.get(posArrivo[0]).getId() :
							Stella.pianeti.get(posPartenza[0]).getId();
					percorso.append(idCorrente);
					percorso.append(">");
					prossimoSalto();
					break;

				case LUNA:
					calcolaSalto();
					idCorrente = metaSuperata ?
							Stella.pianeti.get(posArrivo[0]).lune.get(posArrivo[1]).getId() :
							Stella.pianeti.get(posPartenza[0]).lune.get(posPartenza[1]).getId();
					percorso.append(idCorrente);
					percorso.append(">");
					prossimoSalto();
					break;
			}

		}
		percorso = percorso.deleteCharAt(percorso.length() - 1);  //Rimuovo l'ultimo carattere che corrisponde a '>'
		return percorso;
	}

	public void prossimoSalto() {

		switch (tipoCorrente) {
			case STELLA:
				tipoCorrente = TipiCorpiCelesti.PIANETA;
				break;

			case PIANETA:
				if (metaSuperata) tipoCorrente = TipiCorpiCelesti.LUNA;
				else tipoCorrente = TipiCorpiCelesti.STELLA;
				break;

			case LUNA:
				tipoCorrente = TipiCorpiCelesti.PIANETA;
				break;
		}
	}

	public void calcolaSalto() {
		Punto p1, p2;
		switch (tipoCorrente) {
			case STELLA:
				if (metaSuperata) {
					p1 = Stella.pianeti.get(posArrivo[0]).posizione;
					p2 = Stella.posizione;
				} else {
					p1 = Stella.pianeti.get(posPartenza[0]).posizione;
					p2 = Stella.posizione;
				}
				distanzaPercorsa += Punto.distanzaTraDuePunti(p1, p2);
				break;
			case PIANETA:
				if (metaSuperata) {
					p1 = Stella.pianeti.get(posArrivo[0]).posizione;
					p2 = Stella.posizione;
				} else {
					p1 = Stella.pianeti.get(posPartenza[0]).lune.get(posPartenza[1]).posizione;
					p2 = Stella.pianeti.get(posPartenza[0]).posizione;
				}
				distanzaPercorsa += Punto.distanzaTraDuePunti(p1, p2);
				break;
			case LUNA:
				p1 = Stella.pianeti.get(posArrivo[0]).lune.get(posArrivo[1]).posizione;
				p2 = Stella.pianeti.get(posArrivo[0]).posizione;
				distanzaPercorsa += Punto.distanzaTraDuePunti(p1, p2);
				break;
		}

	}

	public TipiCorpiCelesti identificaTipo(String id, int[] posizione) throws IllegalArgumentException {
		//Controllo se è la stella
		if (id.equals(Stella.id)) return TipiCorpiCelesti.STELLA;

		//Controllo se è un pianeta
		int i;
		if ((i = Sistema.cercaPianetaById(id)) != -1) {
			posizione[0] = i;
			return TipiCorpiCelesti.PIANETA;
		}

		//Controllo se è una luna
		int[] j = Sistema.cercaLunaById(id);
		if (j[0] != -1) {
			posizione[0] = j[0];
			posizione[1] = j[1];
			return TipiCorpiCelesti.LUNA;
		}

		throw new IllegalArgumentException(ERRORE_ROTTA);
	}

	public double getDistanzaPercorsa() {
		return distanzaPercorsa;
	}
}
