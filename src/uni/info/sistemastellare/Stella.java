package uni.info.sistemastellare;

import java.util.ArrayList;

public class Stella extends CorpoCeleste{

    ArrayList<Pianeta> pianeti = new ArrayList<Pianeta>();

    public Stella(int x, int y, int massa, String id){
        super(x,y,massa,id);
    }

}
