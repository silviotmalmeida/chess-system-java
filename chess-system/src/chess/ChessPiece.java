package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//Classe que representa uma peça no tabuleiro
public abstract class ChessPiece extends Piece {

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

	// método que verifica se em uma determinada posição existe uma peça adversária
	protected boolean isThereOpponentPiece(Position position) {

		// obtendo a peça da posição solicitada
		ChessPiece p = (ChessPiece) this.getBoard().piece(position);

		// se existir a peça e a sua cor for diferente, retorna true
		// senão retorna false
		return p != null && p.getColor() != this.color;
	}

}
