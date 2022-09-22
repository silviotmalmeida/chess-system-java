package application;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

		// criando a lista de peças capturadas
		List<ChessPiece> captured = new ArrayList<>();

		// enquanto a partida não entrar em cheque-mate
		while (!chessMatch.getCheckMate()) {

			// tratando as exceções
			try {

				// limpando a tela
				UI.clearScreen();

				// imprimindo o tabuleiro da partida
				UI.printMatch(chessMatch, captured);

				// solicitando a posição de origem da jogada
				System.out.println();
				System.out.println("Source:");
				ChessPosition source = UI.readChessPosition(sc);

				// obtendo as posições de destino possíveis
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);

				// limpando a tela
				UI.clearScreen();

				// imprimindo o tabuleiro da partida com as posições de destino possíveis
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				// solicitando a posição de destino da jogada
				System.out.println();
				System.out.println("Target:");
				ChessPosition target = UI.readChessPosition(sc);

				// realizando a jogada
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

				// se existir peça adversária capturada, adiciona-a na lista de peças capturadas
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}

				// se existir peça a ser promovida, solicita o tipo de peça a ser incluída
				if (chessMatch.getPromoted() != null) {

					// solicitando a opção do jogador
					System.out.print("Enter piece for promotion (B/N/R/Q)");
					String type = sc.nextLine().toUpperCase();

					// enquanto o tipo da promoção for inválido, repete a solicitação de opção do
					// jogador
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.print("Invalid type! Enter piece for promotion (B/N/R/Q)");
						type = sc.nextLine().toUpperCase();
					}

					// promovendo o peão
					chessMatch.replacePromotedPiece(type);
				}

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

		// limpando a tela
		UI.clearScreen();

		// imprimindo o tabuleiro da partida finalizada
		UI.printMatch(chessMatch, captured);

	}

}
