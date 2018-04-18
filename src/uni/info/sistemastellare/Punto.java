package uni.info.sistemastellare;

public class Punto {

    private int x,y;

    public Punto(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String stampa(){
        return "("+x+","+y+")";
    }

}

