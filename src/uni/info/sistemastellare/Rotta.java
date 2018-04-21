package uni.info.sistemastellare;

public class Rotta {
	String idPartenza,idArrivo;
	TipiCorpiCelesti tipoPartenza,tipoArrivo;
	int[] posPartenza,posArrivo;
	boolean nonPresente = false;

	public Rotta(String idPartenza, String idArrivo) {
		this.idPartenza = idPartenza;
		this.idArrivo = idArrivo;
	}

	public String calcolaRotta(){
		tipoPartenza = identificaTipo(idPartenza,posPartenza);
		tipoArrivo = identificaTipo(idArrivo,posArrivo);
		TipiCorpiCelesti tipoCorrente = tipoPartenza;
		StringBuilder percorso = new StringBuilder();

		//prima metà del percorso (fino alla stella)
		do{
			if(tipoCorrente == TipiCorpiCelesti.STELLA){
				percorso.append(Stella.id);
				percorso.append(" > ");
			}

			else if(tipoCorrente == TipiCorpiCelesti.PIANETA){
				if(tipoPartenza == TipiCorpiCelesti.LUNA){

				}
				percorso.append();
				percorso.append(" > ");
			}
		}

	}

	public TipiCorpiCelesti identificaTipo(String id, int[] posizione){
		//Controllo se è la stella
		if(id.equals(Stella.id)) return TipiCorpiCelesti.STELLA;

		//Controllo se è un pianeta
		int i;
		if((i = Sistema.cercaPianetaById(id))!=-1) {
			posizione = new int[1];
			posizione[0] = i;
			return TipiCorpiCelesti.PIANETA;
		}

		//Controllo se è una luna
		int[] j = Sistema.cercaLunaById(id);
		if(j[0] != -1){
			posizione = new int[2];
			posizione[0] = j[0];
			posizione[1] = j[1];
			return TipiCorpiCelesti.LUNA;
		}

		//Non è presente nel sistema (controllo temporaneo, da cambiare)
		nonPresente = true;
		return TipiCorpiCelesti.STELLA;
	}
}
