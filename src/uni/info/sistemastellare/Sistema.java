package uni.info.sistemastellare;

import java.util.Scanner;

public class Sistema {

    public static void main(String[] args) {

        final int MAX_PIANETI = 26600;
        final int MAX_LUNE_PIANETA = 5000;
        Scanner sc = new Scanner(System.in);

//        aggiungiStella();

        int scelta;

        do{
            System.out.println("0 esci \n 1 aggiungi corpo celeste \n 2 rimuovi corpo celeste \n 3 individua corpo celeste \n 4 info pianeta \n 5 info luna");
            scelta = sc.nextInt();
            String id;
            switch(scelta){
                case 1:
                    aggiungiCorpoCeleste();
                    break;
                case 2:
                    System.out.println("id corpo da rimuovere : ");
                    id = sc.next();
                    rimuoviCorpoCeleste(id);
                    break;
                case 3:
                    System.out.println("id corpo da individuare : ");
                    id = sc.next();
                    individuaCorpoCeleste(id);
                    break;
                case 4:
                    System.out.println("id pianeta: ");
                    id = sc.next();
                    informazioniPianeta(id);
                    break;
                case 5:
                    System.out.println("id luna : ");
                    id = sc.next();
                    informazioniLuna(id);
                    break;
            }
            }while(scelta!=0);

    }

    public static void aggiungiStella(){
        int x,y,massa;
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

        Stella.riempi(x,y,massa,id);
    }

    public static void aggiungiCorpoCeleste(){
        int x,y,massa,tipo;
        String id;
        Scanner sc = new Scanner(System.in);

        System.out.println("Pianeta (1) o Luna (2) ? : ");
        tipo = sc.nextInt();
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
            case 1:
                Stella.aggiungiPianeta(x,y,massa,id);
                break;
            case 2:
                String pianetaDiRotazione;
                System.out.println("Inserisci coordinata pianeta di rotazione : ");
                pianetaDiRotazione = sc.next();
                int pianeta = cercaPianeta(pianetaDiRotazione);
                Stella.pianeti.get(pianeta).aggiungiLuna(x,y,massa,id);
                break;
        }
    }

    public static int cercaPianeta(String id) {
        for (int i = 0; i < Stella.pianeti.size(); i++) {
            if (Stella.pianeti.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static void rimuoviCorpoCeleste(String id){

        for(int i=0; i<Stella.pianeti.size(); i++){
            if(Stella.pianeti.get(i).getId().equals(id)){
                Stella.pianeti.remove(i);
                return ;
            }
        }
        // se arriva qui è una luna o non esiste
        for(int i=0; i<Stella.pianeti.size(); i++) {
            for (int j = 0; j < Stella.pianeti.get(i).lune.size(); j++) {
                if(Stella.pianeti.get(i).lune.get(j).getId().equals(id)){
                    Stella.pianeti.get(i).lune.remove(j);
                    return ;
                }
            }
        }
        // se arriva qui il corpo celeste non esiste
        System.out.println("Corpo celeste inesistente");
    }

    public static void individuaCorpoCeleste(String id){
        for(int i=0; i<Stella.pianeti.size(); i++){
            if(Stella.pianeti.get(i).getId().equals(id)){
                System.out.println(Stella.pianeti.get(i).stampa());
                return ;
            }
        }
        // se arriva qui è una luna o non esiste
        for(int i=0; i<Stella.pianeti.size(); i++) {
            for (int j = 0; j < Stella.pianeti.get(i).lune.size(); j++) {
                if(Stella.pianeti.get(i).lune.get(j).getId().equals(id)){
                    System.out.println(Stella.pianeti.get(i).lune.get(j).stampa()+"\n Il pianeta intorno a cui orbita è \n"+Stella.pianeti.get(i).stampa());
                    return ;
                }
            }
        }
        // se arriva qui il corpo celeste non esiste
        System.out.println("Corpo celeste inesistente");
    }

    public static void informazioniPianeta(String id) {
        int posPianeta=-1;

        for (int i = 0; i < Stella.pianeti.size(); i++) {
            if (Stella.pianeti.get(i).getId().equals(id)) {
                posPianeta = i;
            }

            for (Luna l : Stella.pianeti.get(posPianeta).lune)
                System.out.println(l.stampa());
        }
    }

    public static void informazioniLuna (String id){
            for (int i = 0; i < Stella.pianeti.size(); i++) {
                for (int j = 0; j < Stella.pianeti.get(i).lune.size(); j++) {
                    if (Stella.pianeti.get(i).lune.get(j).getId().equals(id)) {
                        System.out.println(Stella.id + " > " + Stella.pianeti.get(i).getId() + " > " + Stella.pianeti.get(i).lune.get(j).getId());
                    }
                }
            }
        }
}

