package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

// classe que representa o rei
public class King extends ChessPiece {

	// construtor
	public King(Board board, Color color) {
		super(board, color);
	}

	// sobrescrita do toString para imprimir a peça
	@Override
	public String toString() {

		// imprimindo a letra K para representar o rei
		return "K";
	}

}
