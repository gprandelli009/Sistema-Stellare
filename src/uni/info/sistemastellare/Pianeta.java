package uni.info.sistemastellare;

import java.util.ArrayList;

public class Pianeta extends CorpoCeleste {

	ArrayList<Luna> lune = new ArrayList<>();

	public Pianeta(int x, int y, int massa, String id) {
		super(x, y, massa, id);
	}

	public void aggiungiLuna(int x, int y, int massa, String id) {
		lune.add(new Luna(x, y, massa, id));
	}

	public String getId() {
		return id;
	}

}
