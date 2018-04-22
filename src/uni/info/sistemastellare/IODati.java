package uni.info.sistemastellare;

import java.util.InputMismatchException;
import java.util.Scanner;

//Metodi presi dalla libreria myUtil del prof serina

public class IODati {
	private final static Scanner sc = new Scanner(System.in);
	private final static String ERRORE_FORMATO = "Input non valido";

	public static String leggiStringa(String out) {
		System.out.println(out);
		return sc.next();
	}

	public static int leggiIntero(String messaggio) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = sc.nextInt();
				finito = true;
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				String daButtare = sc.next(); //flush
			}
		} while (!finito);
		return valoreLetto;
	}

	public static int leggiIntero(String messaggio, int minimo, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo && valoreLetto <= massimo)
				finito = true;
			else
				System.out.println(ERRORE_FORMATO);
		} while (!finito);

		return valoreLetto;
	}
}
