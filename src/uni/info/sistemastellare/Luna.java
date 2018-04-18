package uni.info.sistemastellare;

public class Luna extends CorpoCeleste {

    Pianeta pianetaDiRotazione;

    public Luna(int x, int y, int massa, String id, Pianeta pianetaDiRotazione){
        super(x,y,massa,id);
        this.pianetaDiRotazione = pianetaDiRotazione;
    }
}
