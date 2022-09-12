package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

// Classe de entrypoint do programa
public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// criando a partida de xadrez
		ChessMatch chessMatch = new ChessMatch();

		while (true) {

			// tratando as exceções
			try {

				// limpando a tela
				UI.clearScreen();

				// imprimindo o tabuleiro da partida
				UI.printBoard(chessMatch.getPieces());

				System.out.println();
				System.out.println("Source:");
				ChessPosition source = UI.readChessPosition(sc);

				System.out.println();
				System.out.println("Target:");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			}
			// no caso de ChessException, imprime e aguarda o Enter
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			// no caso de InputMismatchException, imprime e aguarda o Enter
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}

	}

}
