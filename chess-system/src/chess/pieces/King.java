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

	// método que calcula uma matriz booleana dos movimentos possíveis para a peça
	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] mat = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		return mat;
	}
}
