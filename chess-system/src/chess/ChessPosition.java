package chess;

import boardgame.Position;

// classe responsável por efetuar a conversão entre a posição da matriz
// e a posição do tabuleiro de xadrez
public class ChessPosition {

	// letra da coluna
	private char column;

	// número da linha
	private int row;

	// construtor
	public ChessPosition(char column, int row) {

		// se a posição for inválida, lança uma exceção
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}

	// início dos getters and setters
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	// fim dos getters and setters

	// método responsável por converter as coordenadas do padrão xadrez para a
	// matriz
	protected Position toPosition() {
		return new Position(8 - this.row, this.column - 'a');
	}

	// método responsável por converter as coordenadas da matriz para o padrão xadez
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
	}

	// sobrescrita do toString para imprimir a posição
	@Override
	public String toString() {
		return "" + this.column + this.row;
	}
}
