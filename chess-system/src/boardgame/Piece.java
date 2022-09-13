package boardgame;

// Classe que representa uma peça no tabuleiro
public abstract class Piece {

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

	// método que calcula uma matriz booleana dos movimentos possíveis para a peça
	// a ser detalhada nas classes filhas
	public abstract boolean[][] possibleMoves();

	// método que verifica se é possivel mover a peça para uma determinada posição
	// baseando-se na matriz booleana de movimentos possíveis
	public boolean possibleMove(Position position) {
		return this.possibleMoves()[position.getRow()][position.getColumn()];
	}

	// método que verifica se existe algum movimento possível para a peça
	// baseando-se na matriz booleana de movimentos possíveis
	public boolean isThereAnyPossibleMove() {

		// calculando a matriz de movimentos possíveis da peça
		boolean[][] mat = this.possibleMoves();

		// iterando sobre a matriz
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				// se existir ao menos um campo verdadeiro na matriz, retorna true
				if (mat[i][j]) {
					return true;
				}
			}
		}

		// senão retorna false
		return false;
	}
}
