package fr.pe.dsi;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Javanais {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char reponse = ' ', mode = ' ';
		do {
			do {
				System.out.println("Choisissez votre traducteur (1 ou 2) : ");
				System.out.println("1 - Français --> Javanais");
				System.out.println("2 - Javanais --> Français");
				mode = sc.nextLine().charAt(0);

				if (mode != '1' && mode != '2')
					System.out.println("Choix inconnu, veuillez réitérer votre choix (1 ou 2).");

			} while (mode != '1' && mode != '2');

			System.out.println("Veuillez saisir un phrase :");
			String str = sc.nextLine();
			
			if (mode == '1')
				System.out.println("La phrase en javanais est : \n " + frenchToJava(str));
			if (mode == '2')
				System.out.println("La phrase en francais est : \n  " + javaToFrench(str));

			do {
				System.out.println("\nSouhaitez-vous traduire une autre phrase (o/n)?");
				reponse = sc.nextLine().charAt(0);

			} while (reponse != 'o' && reponse != 'n');

		} while (reponse == 'o');

		System.out.println("Au revoir !");
		sc.close();
	}

	private static String javaToFrench(String str) {
		// index de l'occurence "av"
		int postionAv = 0;
		// index du doublon "av"
		int positionDoublon = 0;

		if (str.substring(0, 2).toLowerCase().equals("av"))
			str = str.substring(2);

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < str.length() - 1; i++) {
			if (str.substring(i, i + 2).toLowerCase().equals("av")) {
				postionAv = i;
				if (positionDoublon == postionAv) {
					result.append(str.charAt(i));
				} else {
					i++;
					positionDoublon = postionAv + 2;
				}
			} else {
				result.append(str.charAt(i));
			}
		}
		return result.append(str.charAt(str.length() - 1)).toString();
	}

	private static String frenchToJava(String str) {

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < str.length() - 1; i++) {
			result = !isVowel(str.charAt(i)) && isVowel(str.charAt(i + 1)) ? result.append(str.charAt(i)).append("av")
					: result.append(str.charAt(i));
		}
		if (isVowel(str.charAt(0)))
			result.insert(0, "av");

		return result.append(str.charAt(str.length() - 1)).toString();
	}
	// Methode français --> Javanais avec Streams (non appelé dans le programme)
	private static String frenchToJavaStream(String str) {

		StringBuilder result = new StringBuilder();
		AtomicInteger index = new AtomicInteger(0);

		str.substring(0, str.length() - 1).chars().mapToObj(i -> (char) i).forEach(i -> {
			if (isVowel(str.charAt(index.incrementAndGet())) && !isVowel(i))
				result.append(Character.toString(i)).append("av");
			else
				result.append(Character.toString(i));
		});

		if (isVowel(str.charAt(0)))
			result.insert(0, "av");

		return result.append(str.charAt(str.length() - 1)).toString();
	}

	private static boolean isVowel(char c) {

		String vowels = "aàâeéèëêiîïoôuùyAIEOUY";
		if (vowels.contains(Character.toString(c)))
			return true;
		return false;
	}
}
