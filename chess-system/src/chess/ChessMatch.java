package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

// classe que representa uma partida de xadrez
public class ChessMatch {

	// tabuleiro
	private Board board;

	// número do turno
	private int turn;

	// jogador atual
	private Color currentPlayer;

	// lista de peças no tabuleiro
	private List<Piece> piecesOnTheBoard = new ArrayList<>();

	// lista de peças capturadas
	private List<Piece> capturedPieces = new ArrayList<>();

	// construtor
	public ChessMatch() {

		// criando o tabuleiro padrão 8x8
		this.board = new Board(8, 8);

		// inicializando o turno
		this.turn = 1;

		// definindo o jogador inicial
		this.currentPlayer = Color.WHITE;

		// colocando as peças na posição inicial
		this.initialSetup();
	}

	// início dos getters and setters
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	// fim dos getters and setters

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

	// método responsável por verificar a validade da posição de origem da jogada
	private void validateSourcePosition(Position position) {

		// se não existir peça, lança uma exceção
		if (!this.board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}

		// se existir a peça, porém for do outro jogador, lança uma exceção
		if (this.currentPlayer != ((ChessPiece) this.board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}

		// se existir a peça, porém sem movimentos possíveis, lança uma exceção
		if (!this.board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	// método responsável por verificar a validade da posição de destino da jogada
	private void validateTargetPosition(Position source, Position target) {

		// se a posição de destino não estiver disponível na matriz de movimentos
		// possíveis, lança uma exceção
		if (!this.board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
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

		// se existir peça capturada,
		if (capturedPiece != null) {

			// remove da lista de peças no tabuleiro
			this.piecesOnTheBoard.remove(capturedPiece);

			// adiciona na lista de peças capturadas
			this.capturedPieces.add(capturedPiece);
		}

		// retornando a peça capturada, se existir
		return capturedPiece;
	}

	// método responsável por retornar a matriz booleana dos movimentos possíveis
	// para a peça a partir de uma posição do tabuleiro
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {

		// convertendo a posição do tabuleiro de xadrez para a matriz
		Position position = sourcePosition.toPosition();

		// validando a posição de origem
		this.validateSourcePosition(position);

		// retornando a matriz
		return this.board.piece(position).possibleMoves();

	}

	// método responsável por realizar a troca de turno entre os jogadores
	private void nextTurn() {

		// incrementando o número do turno
		this.turn++;

		// alterando o jogador atual
		this.currentPlayer = (this.currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// método responsável por realizar uma jogada no xadrez
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {

		// convertendo a posição inicial para coordenadas da matriz
		Position source = sourcePosition.toPosition();

		// convertendo a posição final para coordenadas da matriz
		Position target = targetPosition.toPosition();

		// validando as condições da posição de origem
		this.validateSourcePosition(source);

		// validando as condições da posição de destino
		this.validateTargetPosition(source, target);

		// realizando a jogada e retornando a peça capturada, se existir
		Piece capturedPiece = this.makeMove(source, target);

		// trocando o turno
		this.nextTurn();

		// retornando a peça capturada, se existir
		return (ChessPiece) capturedPiece;

	}

	// método responsável por posicionar uma nova peça no tabuleiro, bem como na
	// lista de peças
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
		this.piecesOnTheBoard.add(piece);
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
