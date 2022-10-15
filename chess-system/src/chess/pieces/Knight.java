package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//classe que representa o cavalo
public class Knight extends ChessPiece {

	// construtor
	public Knight(Board board, Color color) {
		super(board, color);
	}

	// sobrescrita do toString para imprimir a peça
	@Override
	public String toString() {

		// imprimindo a letra N para representar o cavalo
		return "N";
	}

	// método que verifica se a peça pode ser movida para uma posição de destino
	private boolean canMove(Position targetPosition) {

		// obtendo a peça da posição de destino
		ChessPiece p = (ChessPiece) this.getBoard().piece(targetPosition);

		// se a posição estiver vazia ou for peça adversária, retorna true, senão
		// retorna false
		return p == null || p.getColor() != this.getColor();
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
		 * verificando a posição SUPERIOR ESQUERDA no tabuleiro
		 */
		p.setValues(this.position.getRow() - 2, this.position.getColumn() - 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição SUPERIOR DIREITA no tabuleiro
		 */
		p.setValues(this.position.getRow() - 2, this.position.getColumn() + 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição INFERIOR ESQUERDA no tabuleiro
		 */
		p.setValues(this.position.getRow() + 2, this.position.getColumn() - 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição INFERIOR DIREITA no tabuleiro
		 */
		p.setValues(this.position.getRow() + 2, this.position.getColumn() + 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição ESQUERDA SUPERIOR no tabuleiro
		 */
		p.setValues(this.position.getRow() - 1, this.position.getColumn() - 2);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição ESQUERDA INFERIOR no tabuleiro
		 */
		p.setValues(this.position.getRow() + 1, this.position.getColumn() - 2);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição DIREITA SUPERIOR no tabuleiro
		 */
		p.setValues(this.position.getRow() - 1, this.position.getColumn() + 2);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição DIREITA INFERIOR no tabuleiro
		 */
		p.setValues(this.position.getRow() + 1, this.position.getColumn() + 2);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}
}
