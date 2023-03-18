import java.io.Console;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] playOptions = {"nw", "n", "ne", "w", "o", "e", "sw", "s", "se"};
		CheckEnd checkEnd = new CheckEnd();

		// Poderia ser apenas Array aqui, usando for depois:
		List<List<String>> game = Arrays.asList(
				Arrays.asList("   ", "|", "   ", "|", "   "),
				Arrays.asList("   ", "|", "   ", "|", "   "),
				Arrays.asList("---", "|", "---", "|", "---"),
				Arrays.asList("   ", "|", "   ", "|", "   "),
				Arrays.asList("---", "|", "---", "|", "---"),
				Arrays.asList("   ", "|", "   ", "|", "   "),
				Arrays.asList("   ", "|", "   ", "|", "   "));

		// Exemplo forEach:
		// https://www.javatpoint.com/java-8-foreach


		printGame(game);

		do {
			System.out.println("Escolha uma posicao:");
			String play = scan.next();
			playGame(play, game, true);

			playGame(playOptions[new Random().nextInt(9)], game, false);

			printGame(game);
			endGame(game, checkEnd);
		} while (checkEnd.continueGame);


		System.out.println("Eeeee acabooou!");
	}

	private static void printGame(List<List<String>> game) {
		// Anonymous functions
		game.forEach(array -> { 
			System.out.println();
			array.forEach(value -> System.out.print(value));
		});
	}
	
	private static void checkRepetitions(CheckEnd checkEnd) {
		if(checkEnd.checkRepeateadValues[0] != "   " &&
		   checkEnd.checkRepeateadValues[0] == checkEnd.checkRepeateadValues[1] &&
		   checkEnd.checkRepeateadValues[1] == checkEnd.checkRepeateadValues[2]) checkEnd.continueGame = false;
	}

	private static boolean endGame(List<List<String>> game, CheckEnd checkEnd) {
		int[] rowIndexesToCheck = {1, 3, 5};
		int[] columnIndexesToCheck = {0, 2, 4};
		int index = 0;

		// Checa se venceu por coluna
		for(int columnIndexes: columnIndexesToCheck) {
			for(int rowIndexes: rowIndexesToCheck) {
				checkEnd.checkRepeateadValues[index] = game.get(rowIndexes).get(columnIndexes);
				index++;
			}
			index = 0;
			checkRepetitions(checkEnd);
		}
		
		index = 0;
		
		// Checa se venceu na primeira diagonal (cima esquerda -> direita baixo)
		for(int rowIndexes: rowIndexesToCheck) {
			checkEnd.checkRepeateadValues[index] = game.get(rowIndexes).get(columnIndexesToCheck[index]);
			index++;
		}
		checkRepetitions(checkEnd);

		index = 2;
		
		// Checa se venceu na primeira diagonal (cima esquerda -> direita baixo)
		for(int rowIndexes: rowIndexesToCheck) {
			checkEnd.checkRepeateadValues[index] = game.get(rowIndexes).get(columnIndexesToCheck[index]);
			index--;
		}
		checkRepetitions(checkEnd);
		
		// Checa se venceu por linha
		game.forEach(array -> {
			if(array.get(0) != "   " &&
			   array.get(0) != "---" &&
			   array.get(0) == array.get(2) &&
			   array.get(2) == array.get(4)) checkEnd.continueGame = false;
		});

		return checkEnd.continueGame;
	}

	private static void playGame(String play, List<List<String>> game, boolean isPlayer) {
		String xOrO = isPlayer ? " X " : " O ";


		switch(play) {
		case "nw":
			game.get(1).set(0, xOrO);
			break;
		case "n":
			game.get(1).set(2, xOrO);
			break;
		case "ne":
			game.get(1).set(4, xOrO);
			break;
		case "w":
			game.get(3).set(0, xOrO);
			break;
		case "o":
			game.get(3).set(2, xOrO);
			break;
		case "e":
			game.get(3).set(4, xOrO);
			break;
		case "sw":
			game.get(5).set(0, xOrO);
			break;
		case "s":
			game.get(5).set(2, xOrO);
			break;
		case "se":
			game.get(5).set(4, xOrO);
			break;
		}
	}


}
