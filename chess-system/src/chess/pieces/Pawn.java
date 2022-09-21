package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//classe que representa o peão
public class Pawn extends ChessPiece {

	// dependência com a classe ChessMatch
	// a peça peão precisa ter acesso aos dados da partida para verificar a
	// possibilidade de jogadas especiais como o en passant
	private ChessMatch chessMatch;

	// construtor
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		// associando com a partida
		this.chessMatch = chessMatch;
	}

	// sobrescrita do toString para imprimir a peça
	@Override
	public String toString() {

		// imprimindo a letra K para representar o peão
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

			/*
			 * verificando se existe a possibilidade da jogada en passant
			 */
			// se o peão estiver na linha 5 do xadrez (linha 3 da matriz), existe a
			// possibilidade de en passant
			if (this.position.getRow() == 3) {

				// inicializando a posição imediatamente à esquerda do peão
				Position pLeft = new Position(this.position.getRow(), this.position.getColumn() - 1);

				// se as condições do en passant à esquerda forem atendidas
				if (this.getBoard().positionExists(pLeft) && isThereOpponentPiece(pLeft)
						&& this.getBoard().piece(pLeft) == this.chessMatch.getEnPassantVulnerable()) {

					// atribui true na matriz de posições possíveis para a posição resultante do en
					// passant
					mat[pLeft.getRow() - 1][pLeft.getColumn()] = true;
				}

				// inicializando a posição imediatamente à direita do peão
				Position pRight = new Position(this.position.getRow(), this.position.getColumn() + 1);

				// se as condições do en passant à direita forem atendidas
				if (this.getBoard().positionExists(pRight) && isThereOpponentPiece(pRight)
						&& this.getBoard().piece(pRight) == this.chessMatch.getEnPassantVulnerable()) {

					// atribui true na matriz de posições possíveis para a posição resultante do en
					// passant
					mat[pRight.getRow() - 1][pRight.getColumn()] = true;
				}
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

			/*
			 * verificando se existe a possibilidade da jogada en passant
			 */
			// se o peão estiver na linha 4 do xadrez (linha 4 da matriz), existe a
			// possibilidade de en passant
			if (this.position.getRow() == 4) {

				// inicializando a posição imediatamente à esquerda do peão
				Position pLeft = new Position(this.position.getRow(), this.position.getColumn() - 1);

				// se as condições do en passant à esquerda forem atendidas
				if (this.getBoard().positionExists(pLeft) && isThereOpponentPiece(pLeft)
						&& this.getBoard().piece(pLeft) == this.chessMatch.getEnPassantVulnerable()) {

					// atribui true na matriz de posições possíveis para a posição resultante do en
					// passant
					mat[pLeft.getRow() + 1][pLeft.getColumn()] = true;
				}

				// inicializando a posição imediatamente à direita do peão
				Position pRight = new Position(this.position.getRow(), this.position.getColumn() + 1);

				// se as condições do en passant à direita forem atendidas
				if (this.getBoard().positionExists(pRight) && isThereOpponentPiece(pRight)
						&& this.getBoard().piece(pRight) == this.chessMatch.getEnPassantVulnerable()) {

					// atribui true na matriz de posições possíveis para a posição resultante do en
					// passant
					mat[pRight.getRow() + 1][pRight.getColumn()] = true;
				}
			}

		}

		return mat;
	}

}
