package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

// classe que representa a torre
public class Rook extends ChessPiece {

	// construtor
	public Rook(Board board, Color color) {
		super(board, color);
	}

	// sobrescrita do toString para imprimir a peça
	@Override
	public String toString() {

		// imprimindo a letra R para representar a torre
		return "R";
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
		 * verificando as posições ACIMA no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn());

		// enquanto a posição for válida e estiver vaga
		while (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {

			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;

			// move mais uma posição acima
			p.setRow(p.getRow() - 1);
		}

		// se a posição for válida, porém estiver ocupada por peça adversária
		if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando as posições ABAIXO no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn());

		// enquanto a posição for válida e estiver vaga
		while (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {

			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;

			// move mais uma posição acima
			p.setRow(p.getRow() + 1);
		}

		// se a posição for válida, porém estiver ocupada por peça adversária
		if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando as posições À ESQUERDA no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow(), this.position.getColumn() - 1);

		// enquanto a posição for válida e estiver vaga
		while (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {

			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;

			// move mais uma posição à esquerda
			p.setColumn(p.getColumn() - 1);
		}

		// se a posição for válida, porém estiver ocupada por peça adversária
		if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando as posições À DIREITA no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow(), this.position.getColumn() + 1);

		// enquanto a posição for válida e estiver vaga
		while (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {

			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;

			// move mais uma posição à esquerda
			p.setColumn(p.getColumn() + 1);
		}

		// se a posição for válida, porém estiver ocupada por peça adversária
		if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
