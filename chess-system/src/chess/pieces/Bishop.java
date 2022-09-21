package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//classe que representa o bispo
public class Bishop extends ChessPiece {

	// construtor
	public Bishop(Board board, Color color) {
		super(board, color);
	}

	// sobrescrita do toString para imprimir a peça
	@Override
	public String toString() {

		// imprimindo a letra B para representar o bispo
		return "B";
	}

	// método que calcula uma matriz booleana dos movimentos possíveis para a peça
	@Override
	public boolean[][] possibleMoves() {

		// inicializando a matriz booleana de movimentos possíveis com todos os campos
		// false
		boolean[][] mat = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];

		// inicializando uma posição genérica
		Position p = new Position(0, 0);

		/*
		 * verificando as posições NOROESTE no tabuleiro
		 */
		// posicionando na posição imediatamente à noroeste da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);

		// enquanto a posição for válida e estiver vaga
		while (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {

			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;

			// move mais uma posição noroeste
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}

		// se a posição for válida, porém estiver ocupada por peça adversária
		if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando as posições NORDESTE no tabuleiro
		 */
		// posicionando na posição imediatamente à nordeste da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);

		// enquanto a posição for válida e estiver vaga
		while (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {

			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;

			// move mais uma posição nordeste
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}

		// se a posição for válida, porém estiver ocupada por peça adversária
		if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando as posições À SUDESTE no tabuleiro
		 */
		// posicionando na posição imediatamente à sudeste da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);

		// enquanto a posição for válida e estiver vaga
		while (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {

			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;

			// move mais uma posição sudeste
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}

		// se a posição for válida, porém estiver ocupada por peça adversária
		if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando as posições À SUDOESTE no tabuleiro
		 */
		// posicionando na posição imediatamente à sudoeste da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);

		// enquanto a posição for válida e estiver vaga
		while (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {

			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;

			// move mais uma posição sudoeste
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}

		// se a posição for válida, porém estiver ocupada por peça adversária
		if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
