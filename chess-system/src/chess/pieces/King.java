package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

// classe que representa o rei
public class King extends ChessPiece {

	// dependência com a classe ChessMatch
	// a peça rei precisa ter acesso aos dados da partida para verificar a
	// possibilidade de jogadas especiais como o roque
	private ChessMatch chessMatch;

	// construtor
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		// associando com a partida
		this.chessMatch = chessMatch;
	}

	// sobrescrita do toString para imprimir a peça
	@Override
	public String toString() {

		// imprimindo a letra K para representar o rei
		return "K";
	}

	// método que verifica se a peça pode ser movida para uma posição de destino
	private boolean canMove(Position targetPosition) {

		// obtendo a peça da posição de destino
		ChessPiece p = (ChessPiece) this.getBoard().piece(targetPosition);

		// se a posição estiver vazia ou for peça adversária, retorna true, senão
		// retorna false
		return p == null || p.getColor() != this.getColor();
	}

	// método responsável por verificar se a jogada roque está disponível
	// vai testar se as torres ainda não possuem nenhum movimento efetuado
	// na partida
	private boolean testRookCastling(Position position) {

		// obtendo a peça da posição
		ChessPiece p = (ChessPiece) this.getBoard().piece(position);

		// faz a verificação da disponibilidade do roque
		return p != null && p instanceof Rook && p.getColor() == this.getColor() && p.getMoveCount() == 0;
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
		 * verificando a posição ACIMA no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn());

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição ABAIXO no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn());

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição À ESQUERDA no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow(), this.position.getColumn() - 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição À DIREITA no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow(), this.position.getColumn() + 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição À NOROESTE no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição À NORDESTE no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição À SUDOESTE no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando a posição À SUDESTE no tabuleiro
		 */
		// posicionando na posição imediatamente acima da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);

		// se a posição for válida e a peça puder ser movida para esta posição
		if (this.getBoard().positionExists(p) && this.canMove(p)) {
			// atribui true na matriz de posições possíveis
			mat[p.getRow()][p.getColumn()] = true;
		}

		/*
		 * verificando se existe a possibilidade da jogada roque
		 */
		// se o rei ainda não se moveu e a pertida ainda não está em cheque
		if (this.getMoveCount() == 0 && !this.chessMatch.getCheck()) {

			/*
			 * Verificando a possibilidade do roque curto
			 */
			// inicializando a posição esperada da torre 1
			Position pRook1 = new Position(this.position.getRow(), this.position.getColumn() + 3);

			// se as condições para o roque forem satisfeitas, prossegue
			if (this.testRookCastling(pRook1)) {

				// inicializando as duas casas à direita do rei
				Position pRight1 = new Position(this.position.getRow(), this.position.getColumn() + 1);
				Position pRight2 = new Position(this.position.getRow(), this.position.getColumn() + 2);

				// se as duas casas estiverem vazias
				if (this.getBoard().piece(pRight1) == null && this.getBoard().piece(pRight2) == null) {

					// atribui true na matriz de posições possíveis
					mat[pRight2.getRow()][pRight2.getColumn()] = true;
				}
			}

			/*
			 * Verificando a possibilidade do roque grande
			 */
			// inicializando a posição esperada da torre 2
			Position pRook2 = new Position(this.position.getRow(), this.position.getColumn() - 4);

			// se as condições para o roque forem satisfeitas, prossegue
			if (this.testRookCastling(pRook2)) {

				// inicializando as três casas à esquerda do rei
				Position pLeft1 = new Position(this.position.getRow(), this.position.getColumn() - 1);
				Position pLeft2 = new Position(this.position.getRow(), this.position.getColumn() - 2);
				Position pLeft3 = new Position(this.position.getRow(), this.position.getColumn() - 3);

				// se as três casas estiverem vazias
				if (this.getBoard().piece(pLeft1) == null && this.getBoard().piece(pLeft2) == null
						&& this.getBoard().piece(pLeft3) == null) {

					// atribui true na matriz de posições possíveis
					mat[pLeft2.getRow()][pLeft2.getColumn()] = true;
				}
			}

		}

		return mat;
	}
}
