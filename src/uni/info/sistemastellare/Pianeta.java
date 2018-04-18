package uni.info.sistemastellare;

public class Pianeta extends CorpoCeleste {

    Stella stellaDiRotazione;

    public Pianeta(int x, int y, int massa, String id,Stella stellaDiRotazione){
        super(x,y,massa,id);
        this.stellaDiRotazione = stellaDiRotazione;
    }

}
