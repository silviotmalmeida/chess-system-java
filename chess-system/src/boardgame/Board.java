package boardgame;

// Classe que representa um tabuleiro
public class Board {

	// quantidade de linhas no tabuleiro
	private int rows;

	// quantidade de colunas no tabuleiro
	private int columns;

	// matriz de peças
	private Piece[][] pieces;

	// construtor
	public Board(int rows, int columns) {

		// se a quantidade de linhas ou colunas for inferior a 1, lança exceção
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	// início dos getters and setters
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	// fim dos getters and setters

	// método que retorna a peça de uma determinada localização da matriz
	// a partir dos dados de linha e coluna
	public Piece piece(int row, int column) {

		// se a posição não existir no tabuleiro, lança uma exceção
		if (!this.positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}

		// retorna a peça
		return this.pieces[row][column];
	}

	// método que retorna a peça de uma determinada localização da matriz
	// a partir dos dados de um objeto Position
	public Piece piece(Position position) {
		return this.pieces[position.getRow()][position.getColumn()];
	}

	// método que atribui uma determinada posição a uma peça
	public void placePiece(Piece piece, Position position) {

		// se já existir alguma peça na posição, lança uma exceção
		if (this.thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}

		// inserindo a peça na matriz
		this.pieces[position.getRow()][position.getColumn()] = piece;

		// atualizando a posição da peça
		piece.position = position;
	}

	// método que remove a posição de uma peça
	public Piece removePiece(Position position) {

		// se a posição não existir no tabuleiro, lança uma exceção
		if (!this.positionExists(position)) {
			throw new BoardException("Position not on the board");
		}

		// se não existir nenhuma peça na posição, retorna null
		if (this.piece(position) == null) {
			return null;
		}

		// obtendo a peça a ser removida
		Piece aux = piece(position);

		// atualizando a posição da peça para null
		aux.position = null;

		// atualizando a matriz de peças
		pieces[position.getRow()][position.getColumn()] = null;

		//retornando a peça que foi retirada
		return aux;
	}

	// método que verifica se uma posição é válida no tabuleiro
	private boolean positionExists(int row, int column) {

		// condições da verificação
		return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
	}

	// método que verifica se uma posição é válida no tabuleiro
	public boolean positionExists(Position position) {

		return this.positionExists(position.getRow(), position.getColumn());
	}

	// método que verifica a existÊncia de uma peça em uma determinada posição
	public boolean thereIsAPiece(Position position) {

		// se a posição não existir no tabuleiro, lança uma exceção
		if (!this.positionExists(position)) {
			throw new BoardException("Position not on the board");
		}

		return this.piece(position) != null;
	}

}
