package boardgame;

// Classe responsável por definir a posição dos elementos no tabuleiro 
public class Position {

	// linha
	private int row;

	// coluna
	private int column;

	// construtor
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	// início dos getters and setters
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	// fim dos getters and setters

	// sobrescrita do toString para imprimir os dados do objeto
	@Override
	public String toString() {

		return this.row + ", " + this.column;

	}

}
