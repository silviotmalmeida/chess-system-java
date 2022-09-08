package chess;

import boardgame.Board;
import boardgame.Position;
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

	// método responsável por colocar as peças em seus devidos lugares iniciais
	private void initialSetup() {

		// colocando a torre branca
		this.board.placePiece(new Rook(this.board, Color.WHITE), new Position(2, 1));

		// colocando o rei preto
		this.board.placePiece(new King(this.board, Color.BLACK), new Position(0, 4));

		// colocando o rei branco
		this.board.placePiece(new King(this.board, Color.WHITE), new Position(7, 4));

	}

}
