package uni.info.sistemastellare;

import java.util.Scanner;

// Delle funzionalità base manca il calcolo del centro di massa

public class Sistema {


	final static int MAX_PIANETI = 26600;
	final static int MAX_LUNE_PIANETA = 5000;


	public static void main(String[] args) {

		System.out.println("Creazione stella sistema");
		aggiungiCorpoCeleste(TipiCorpiCelesti.STELLA); //Viene subito creata una stella perchè pilastro del sistema

		StringBuilder menu = creaMenu();

		Scanner sc = new Scanner(System.in);

		int scelta;

		do {

			System.out.println(menu);

			scelta = sc.nextInt();
			String id;
			switch (scelta) {
				case 1:
					if ((Stella.pianeti.size() < MAX_PIANETI)) {
						aggiungiCorpoCeleste(TipiCorpiCelesti.PIANETA);
						break;
					} else {
						System.out.println("Limite pianeti raggiunto");
						break;
					}
				case 2:
					aggiungiCorpoCeleste(TipiCorpiCelesti.LUNA);
					break;
				case 3:
					System.out.println("id corpo da rimuovere : ");
					id = sc.next();
					rimuoviCorpoCeleste(id);
					break;
				case 4:
					System.out.println("id corpo da individuare : ");
					id = sc.next();
					individuaCorpoCeleste(id);
					break;
				case 5:
					System.out.println("id pianeta: ");
					id = sc.next();
					informazioniPianeta(id);
					break;
				case 6:
					System.out.println("id luna : ");
					id = sc.next();
					informazioniLuna(id);
					break;
				case 7:
					String idPartenza, idArrivo;
					System.out.println("id partenza : ");
					idPartenza = sc.next();
					System.out.println("id arrivo : ");
					idArrivo = sc.next();
					calcoloRotta(idPartenza, idArrivo);
					break;
			}
		} while (scelta != 0);

	}

	public static void aggiungiCorpoCeleste(TipiCorpiCelesti tipo) {
		int x, y, massa;
		String id;
		Scanner sc = new Scanner(System.in);

		System.out.println("Inserisci coordinata x : ");
		x = sc.nextInt();
		System.out.println("Inserisci coordinata y : ");
		y = sc.nextInt();
		System.out.println("Inserisci coordinata massa : ");
		massa = sc.nextInt();
		System.out.println("Inserisci coordinata id : ");
		id = sc.next();

		switch (tipo)

		{
			case PIANETA:
				Stella.aggiungiPianeta(x, y, massa, id);
				break;
			case LUNA:
				String pianetaDiRotazione;
				System.out.println("Inserisci coordinata pianeta di rotazione : ");
				pianetaDiRotazione = sc.next();
				int pianeta = cercaPianetaById(pianetaDiRotazione);
				if (pianeta != -1) {
					if (Stella.pianeti.get(pianeta).lune.size() < MAX_LUNE_PIANETA) {
						Stella.pianeti.get(pianeta).aggiungiLuna(x, y, massa, id);
					} else {
						System.out.println("Limite lune per questo pianeta raggiunto");
					}
				} else {
					System.out.println("Pianeta non trovato");
				}
				break;
			case STELLA:
				Stella.riempi(x, y, massa, id);
				break;
		}
	}

	public static int cercaPianetaById(String id) {
		for (int i = 0; i < Stella.pianeti.size(); i++) {
			if (Stella.pianeti.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1; //Fa passare il nome di tutti i pianeti, se non trova corrispondenza restituisce -1
	}

	public static int[] cercaLunaById(String id) {
		for (int i = 0; i < Stella.pianeti.size(); i++) {
			for (int j = 0; j < Stella.pianeti.get(i).lune.size(); j++) {
				if (Stella.pianeti.get(i).lune.get(j).getId().equals(id)) {
					return new int[]{i, j};
				}
			}
		}
		return new int[]{-1, -1};
	}

	public static void rimuoviCorpoCeleste(String id) {
		int i;
		if ((i = cercaPianetaById(id)) != -1) {
			Stella.pianeti.remove(i);
			return;
		}
		// se arriva qui è una luna o non esiste
		int[] j = cercaLunaById(id);
		if (j[0] != -1) {
			Stella.pianeti.get(j[0]).lune.remove(j[1]);
		}
		// se arriva qui il corpo celeste non esiste
		System.out.println("Corpo celeste inesistente");
	}

	public static void individuaCorpoCeleste(String id) {
		int i;
		if ((i = cercaPianetaById(id)) != -1) {
			System.out.println(Stella.pianeti.get(i).toString());
			return;
		}

		// se arriva qui è una luna o non esiste
		int[] j = cercaLunaById(id);
		if (j[0] != -1) {
			System.out.println(Stella.pianeti.get(j[0]).lune.get(j[1]).toString() + "\nIl pianeta intorno a cui orbita è : \n" + Stella.pianeti.get(j[0]).toString());
			return;
		}

		// se arriva qui il corpo celeste non esiste
		System.out.println("Corpo celeste inesistente");
	}

	public static void informazioniPianeta(String id) {
		int i;

		if ((i = cercaPianetaById(id)) != -1) {
			System.out.println("Le lune che gli orbitano intorno sono : ");
			for (Luna l : Stella.pianeti.get(i).lune)
				System.out.println(l.toString());
		} else System.out.println("Pianeta non trovato");
	}

	public static void informazioniLuna(String id) {
		int[] j = cercaLunaById(id);

		if (j[0] != -1) {
			System.out.println(Stella.id + " > " + Stella.pianeti.get(j[0]).getId() + " > " + Stella.pianeti.get(j[0]).lune.get(j[1]).getId());
		} else System.out.println("Luna non trovata");
	}

	public static StringBuilder creaMenu() {
		final String MENU_DELIMITATORE = "-------------------------------------------------";
		final String MENU_SCELTA_ESCI = "0 per uscire dal programma";
		final String MENU_SCELTA_AGGIUNGI_PIANETA = "1 aggiungi pianeta";
		final String MENU_SCELTA_AGGIUNGI_LUNA = "2 aggiungi luna";
		final String MENU_SCELTA_RIMUOVI = "3 rimuovi corpo celeste";
		final String MENU_SCELTA_INDIVIDUA = "4 individua corpo celeste";
		final String MENU_SCELTA_INFO_PIANETA = "5 informazioni pianeta";
		final String MENU_SCELTA_INFO_LUNA = "6 informazioni luna";
		final String MENU_SCELTA_ROTTA = "7 calcola rotta tra due corpi celesti";
		final String MENU_BENVENUTO = "Scegli una delle opzioni sottostanti per continuare";

		StringBuilder menu = new StringBuilder();

		menu.append(MENU_DELIMITATORE);
		menu.append("\n");
		menu.append(MENU_BENVENUTO);
		menu.append("\n");
		menu.append(MENU_SCELTA_ESCI);
		menu.append("\n");
		menu.append(MENU_SCELTA_AGGIUNGI_PIANETA);
		menu.append("\n");
		menu.append(MENU_SCELTA_AGGIUNGI_LUNA);
		menu.append("\n");
		menu.append(MENU_SCELTA_RIMUOVI);
		menu.append("\n");
		menu.append(MENU_SCELTA_INDIVIDUA);
		menu.append("\n");
		menu.append(MENU_SCELTA_INFO_PIANETA);
		menu.append("\n");
		menu.append(MENU_SCELTA_INFO_LUNA);
		menu.append("\n");
		menu.append(MENU_SCELTA_ROTTA);
		menu.append("\n");
		menu.append(MENU_DELIMITATORE);

		return menu;
	}

	//Metodi funzionalità extra, calcolo della rotta

	public static void calcoloRotta(String idPartenza, String idArrivo) {
		Rotta rotta = new Rotta(idPartenza, idArrivo);
		System.out.println("La rotta da percorrere è : ");
		System.out.println(rotta.calcolaRotta());
		System.out.println(String.format("Distanza da percorrere : %.2f", rotta.getDistanzaPercorsa()));
	}

}

