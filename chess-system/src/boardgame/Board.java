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
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	// início dos getters and setters
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	// fim dos getters and setters

	// método que retorna a peça de uma determinada localização da matriz
	// a partir dos dados de linha e coluna
	public Piece piece(int row, int column) {
		return this.pieces[row][column];
	}

	// método que retorna a peça de uma determinada localização da matriz
	// a partir dos dados de um objeto Position
	public Piece piece(Position position) {
		return this.pieces[position.getRow()][position.getColumn()];
	}

	// método que atribui uma determinada posição a uma peça
	public void placePiece(Piece piece, Position position) {

		// inserindo a peça na matriz
		this.pieces[position.getRow()][position.getColumn()] = piece;

		// atualizando a posição da peça
		piece.position = position;
	}

}
