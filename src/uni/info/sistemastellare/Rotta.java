package uni.info.sistemastellare;

public class Rotta {
	private String idPartenza, idArrivo;
	private TipiCorpiCelesti tipoPartenza, tipoArrivo,tipoCorrente;
	private int[] posPartenza = new int[2];
	private int[] posArrivo = new int[2];
	private boolean nonPresente = false;
	private double distanzaPercorsa = 0;

	public Rotta(String idPartenza, String idArrivo) {
		this.idPartenza = idPartenza;
		this.idArrivo = idArrivo;
	}

	public StringBuilder calcolaRotta() {
		tipoPartenza = identificaTipo(idPartenza, posPartenza);
		tipoArrivo = identificaTipo(idArrivo, posArrivo);
		tipoCorrente = tipoPartenza;
		StringBuilder percorso = new StringBuilder();

		boolean finito = false, metaSuperta = false,thereIsPrecedente = false;

		do {
			if (metaSuperta) { //Seconda metà (dalla stella all'arrivo)
				if (tipoCorrente == TipiCorpiCelesti.PIANETA) {
					Punto p1,p2;
					p1 = Stella.pianeti.get(posArrivo[0]).posizione;
					p2 = Stella.posizione;
					distanzaPercorsa += Punto.distanzaTraDuePunti(p1, p2);
					percorso.append(Stella.pianeti.get(posArrivo[0]).getId());
					if (tipoCorrente == tipoArrivo) finito = true;
					else {
						percorso.append(" > ");
						tipoCorrente = TipiCorpiCelesti.LUNA;
					}
				} else {
					Punto p1,p2;
					p1 = Stella.pianeti.get(posArrivo[0]).lune.get(posArrivo[1]).posizione;
					p2 = Stella.pianeti.get(posArrivo[0]).posizione;
					distanzaPercorsa += Punto.distanzaTraDuePunti(p1, p2);
					percorso.append(Stella.pianeti.get(posArrivo[0]).lune.get(posArrivo[1]).getId());
					finito = true;
				}
			} else { //Prima metà (considero prima metà il percorso dal corpo celeste alla stella)
				if (tipoCorrente == TipiCorpiCelesti.STELLA) {
					if (thereIsPrecedente) { //Controllo se questo è il pianeta di partenza, lo faccio solo nella prima metà
						Punto p1,p2;
						p1 = Stella.pianeti.get(posPartenza[0]).posizione;
						p2 = Stella.posizione;
						distanzaPercorsa += Punto.distanzaTraDuePunti(p1, p2);
					}
					percorso.append(Stella.id);
					percorso.append(" > ");
					metaSuperta = true;
					tipoCorrente = TipiCorpiCelesti.PIANETA;
				} else if (tipoCorrente == TipiCorpiCelesti.PIANETA) {
					if(thereIsPrecedente){
						Punto p1,p2;
						p1 = Stella.pianeti.get(posPartenza[0]).lune.get(posPartenza[1]).posizione;
						p2 = Stella.pianeti.get(posPartenza[0]).posizione;
						distanzaPercorsa += Punto.distanzaTraDuePunti(p1, p2);
					}
					percorso.append(Stella.pianeti.get(posPartenza[0]).getId());
					percorso.append(" > ");
					thereIsPrecedente = true;
					tipoCorrente = TipiCorpiCelesti.STELLA;
				} else if (tipoCorrente == TipiCorpiCelesti.LUNA) {
					percorso.append(Stella.pianeti.get(posPartenza[0]).lune.get(posPartenza[1]).getId());
					percorso.append(" > ");
					thereIsPrecedente = true;
					tipoCorrente = TipiCorpiCelesti.PIANETA;
				}
			}
		} while (!finito);
		return percorso;
	}

	public TipiCorpiCelesti identificaTipo(String id, int[] posizione) {
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

		//Non è presente nel sistema (controllo temporaneo, da cambiare)
		nonPresente = true;
		return TipiCorpiCelesti.STELLA;
	}

	public double getDistanzaPercorsa() {
		return distanzaPercorsa;
	}
}
