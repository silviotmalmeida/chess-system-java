package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

// classe responsável por imprimir as informações na tela
public class UI {

	// definindo as cores a serem utilizadas na saída do terminal
	// cores de texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	// cores de background
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// método responsável por limpar o terminal
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// método responsável por obter a peça de uma determinada posição
	public static ChessPosition readChessPosition(Scanner sc) {

		// tratando as exceções
		try {

			// obtendo os dados do terminal
			String s = sc.nextLine();

			// obtendo a letra da coluna
			char column = s.charAt(0);

			// obtendo o número da linha
			int row = Integer.parseInt(s.substring(1));

			return new ChessPosition(column, row);
		}
		// caso existam exceções, lança
		catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}

	// método responsável por imprimir uma única peça
	private static void printPiece(ChessPiece piece, boolean background) {

		// se estiver setada a existência de um background, atribui
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}

		// se a peça for nula
		if (piece == null) {

			// imprime um traço
			System.out.print("-" + ANSI_RESET);
		}
		// senão
		else {

			// se a cor da peça for branca, imprime o valor referente à peça na cor branca
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			}
			// senão, imprime o valor referente à peça na cor amarela
			else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}

		}
		// imprime um espaço em branco
		System.out.print(" ");
	}

	// método responsável por imprimir as peças do tabuleiro
	private static void printBoard(ChessPiece[][] pieces) {

		// iterando sobre a matriz de peças
		for (int i = 0; i < pieces.length; i++) {

			// imprimindo o número da linha
			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {

				// imprimindo as peças da linha, sem considerar background
				printPiece(pieces[i][j], false);
			}

			// quebrando a linha
			System.out.println();
		}

		// imprimindo as letras das colunas
		System.out.println("  a b c d e f g h");

	}

	// método responsável por imprimir as peças capturadas na partida, organizadas
	// por cor
	private static void printCapturedPieces(List<ChessPiece> captured) {

		// gerando uma nova lista filtrada somente com peças brancas
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());

		// gerando uma nova lista filtrada somente com peças pretas
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());

		// imprimindo o título
		System.out.println("Captured pieces:");

		// imprimindo as peças brancas capturadas na cor branca
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);

		// imprimindo as peças pretas capturadas na cor amarela
		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);

	}

	// método responsável por imprimir os dados da partida
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {

		printBoard(chessMatch.getPieces());

		printCapturedPieces(captured);

		System.out.println();

		System.out.println("Turn : " + chessMatch.getTurn());

		System.out.println("Waiting player : " + chessMatch.getCurrentPlayer());

	}

	// método responsável por imprimir as peças do tabuleiro com as posições de
	// destino possíveis
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {

		// iterando sobre a matriz de peças
		for (int i = 0; i < pieces.length; i++) {

			// imprimindo o número da linha
			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {

				// imprimindo as peças da linha, considerando o background nas posições onde o
				// movimento é possível
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}

			// quebrando a linha
			System.out.println();
		}

		// imprimindo as letras das colunas
		System.out.println("  a b c d e f g h");

	}

}
