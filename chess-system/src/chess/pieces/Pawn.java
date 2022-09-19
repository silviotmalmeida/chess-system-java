package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	// construtor
	public Pawn(Board board, Color color) {
		super(board, color);
	}

	// sobrescrita do toString para imprimir a peça
	@Override
	public String toString() {

		// imprimindo a letra K para representar o rei
		return "P";
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
		 * movimentos da peça BRANCA
		 */
		if (this.getColor() == Color.WHITE) {

			// posicionando na posição imediatamente acima da peça
			p.setValues(this.position.getRow() - 1, this.position.getColumn());

			// se a posição for válida e a peça puder ser movida para esta posição
			if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
				// atribui true na matriz de posições possíveis
				mat[p.getRow()][p.getColumn()] = true;

				// posicionando duas posições acima da peça, válido somente na primeira jogada
				// do peão
				p.setValues(this.position.getRow() - 2, this.position.getColumn());

				// se a posição for válida, a peça puder ser movida para esta posição e for seu
				// primeiro movimento
				if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)
						&& this.getMoveCount() == 0) {
					// atribui true na matriz de posições possíveis
					mat[p.getRow()][p.getColumn()] = true;
				}
			}

			// posicionando na diagonal esquerda, válido somente se exixtir peça adversária
			// nesta posição
			p.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);

			// se a posição for válida e contiver peça adversária
			if (this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
				// atribui true na matriz de posições possíveis
				mat[p.getRow()][p.getColumn()] = true;
			}

			// posicionando na diagonal direita, válido somente se exixtir peça adversária
			// nesta posição
			p.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);

			// se a posição for válida e contiver peça adversária
			if (this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
				// atribui true na matriz de posições possíveis
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		/*
		 * movimentos da peça PRETA
		 */
		else {

			// posicionando na posição imediatamente abaixo da peça
			p.setValues(this.position.getRow() + 1, this.position.getColumn());

			// se a posição for válida e a peça puder ser movida para esta posição
			if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
				// atribui true na matriz de posições possíveis
				mat[p.getRow()][p.getColumn()] = true;

				// posicionando duas posições abaixo da peça, válido somente na primeira jogada
				// do peão
				p.setValues(this.position.getRow() + 2, this.position.getColumn());

				// se a posição for válida, a peça puder ser movida para esta posição e for seu
				// primeiro movimento
				if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)
						&& this.getMoveCount() == 0) {
					// atribui true na matriz de posições possíveis
					mat[p.getRow()][p.getColumn()] = true;
				}
			}

			// posicionando na diagonal esquerda, válido somente se exixtir peça adversária
			// nesta posição
			p.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);

			// se a posição for válida e contiver peça adversária
			if (this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
				// atribui true na matriz de posições possíveis
				mat[p.getRow()][p.getColumn()] = true;
			}

			// posicionando na diagonal direita, válido somente se exixtir peça adversária
			// nesta posição
			p.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);

			// se a posição for válida e contiver peça adversária
			if (this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
				// atribui true na matriz de posições possíveis
				mat[p.getRow()][p.getColumn()] = true;
			}
		}

		return mat;
	}

}
