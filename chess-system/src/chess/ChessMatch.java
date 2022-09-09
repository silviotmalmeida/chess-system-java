package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

// classe que representa uma partida de xadrez
public class ChessMatch {

	// tabuleiro
	private Board board;

	// construtor
	public ChessMatch() {

		// criando o tabuleiro padrão 8x8
		this.board = new Board(8, 8);

		// colocando as peças na posição inicial
		this.initialSetup();
	}

	// método responsável por retornar a matriz de peças no tabuleiro
	public ChessPiece[][] getPieces() {

		// iniciando a matriz vazia
		ChessPiece[][] mat = new ChessPiece[this.board.getRows()][this.board.getColumns()];

		// iterando sobre a matriz de peças do tabuleiro
		for (int i = 0; i < this.board.getRows(); i++) {
			for (int j = 0; j < this.board.getColumns(); j++) {

				// transformando a peça simples em peça de xadrez (downcasting)
				// e inserindo na matriz
				mat[i][j] = (ChessPiece) board.piece(i, j);

			}
		}

		// retornando a matriz
		return mat;
	}

	// método responsável por posicionar uma nova peça no tabuleiro
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	// método responsável por colocar as peças em seus devidos lugares iniciais
	private void initialSetup() {

		// colocando a torre branca
		this.placeNewPiece('b', 6, new Rook(this.board, Color.WHITE));

		// colocando o rei preto
		this.placeNewPiece('e', 8, new King(this.board, Color.BLACK));

		// colocando o rei branco
		this.placeNewPiece('e', 1, new King(this.board, Color.WHITE));

	}

}
