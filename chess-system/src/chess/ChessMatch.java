package chess;

import boardgame.Board;
import boardgame.Piece;
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

	// método responsável por verificar se existe alguma peça na posição inicial da
	// jogada
	private void validateSourcePosition(Position position) {

		// se não existir peça, lança uma exceção
		if (!this.board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
	}

	// método responsável por realizar o movimento das peças, retornando a peça
	// capturada se existir
	private Piece makeMove(Position source, Position target) {

		// removendo a peça movida da posição inicial
		Piece p = this.board.removePiece(source);

		// capturando a peça do destino, se existir
		Piece capturedPiece = this.board.removePiece(target);

		// colocando a peça movida na posição final
		this.board.placePiece(p, target);

		// retornando a peça capturada, se existir
		return capturedPiece;
	}

	// método responsável por realizar uma jogada no xadrez
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {

		// convertendo a posição inicial para coordenadas da matriz
		Position source = sourcePosition.toPosition();

		// convertendo a posição final para coordenadas da matriz
		Position target = targetPosition.toPosition();

		// validando se na posição inicial existe alguma peça
		this.validateSourcePosition(source);

		// realizando a jogada e retornando a peça capturada, se existir
		Piece capturedPiece = this.makeMove(source, target);

		// retornando a peça capturada, se existir
		return (ChessPiece) capturedPiece;

	}

	// método responsável por posicionar uma nova peça no tabuleiro
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	// método responsável por colocar as peças em seus devidos lugares iniciais
	private void initialSetup() {

		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));

	}

}
