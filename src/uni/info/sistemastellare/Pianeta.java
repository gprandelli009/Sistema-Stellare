package uni.info.sistemastellare;

import java.util.ArrayList;

public class Pianeta extends CorpoCeleste {

    Stella stellaDiRotazione;
    ArrayList<Luna> lune = new ArrayList<Luna>();

    public Pianeta(int x, int y, int massa, String id,Stella stellaDiRotazione){
        super(x,y,massa,id);
        this.stellaDiRotazione = stellaDiRotazione;
    }

}
