package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

// classe que representa a torre
public class Rook extends ChessPiece {

	// construtor
	public Rook(Board board, Color color) {
		super(board, color);
	}

	// sobrescrita do toString para imprimir a peça
	@Override
	public String toString() {

		// imprimindo a letra R para representar a torre
		return "R";
	}

}
