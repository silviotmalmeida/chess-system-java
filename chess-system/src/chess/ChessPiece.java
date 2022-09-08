package chess;

import boardgame.Board;
import boardgame.Piece;

//Classe que representa uma peça no tabuleiro
public class ChessPiece extends Piece {

	// cor da peça
	private Color color;

	// construtor
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	// início dos getters and setters
	public Color getColor() {
		return color;
	}
	// fim dos getters and setters

}
