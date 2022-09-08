package boardgame;

// Classe que representa uma peça no tabuleiro
public class Piece {

	// posição da peça
	protected Position position;

	// tabuleiro
	private Board board;

	// construtor
	// considera a posicao inicial da peça como nula
	public Piece(Board board) {
		this.board = board;
		this.position = null;
	}

	// início dos getters and setters
	protected Board getBoard() {
		return board;
	}
	// fim dos getters and setters

}
