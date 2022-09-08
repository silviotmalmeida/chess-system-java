package application;

import chess.ChessMatch;

// Classe de entrypoint do programa
public class Program {

	public static void main(String[] args) {

		// criando a partida de xadrez
		ChessMatch chessMatch = new ChessMatch();

		// imprimindo o tabuleiro da partida
		UI.printBoard(chessMatch.getPieces());

	}

}
