package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
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

	// flag que indica se a partida está em cheque
	private boolean check;

	// flag que indica se a partida está em cheque-mate
	private boolean checkMate;

	// registro de peão vulnerável à jogada en Passant
	private ChessPiece enPassantVulnerable;

	// registro de peão promovido
	private ChessPiece promoted;

	// construtor
	public ChessMatch() {

		// criando o tabuleiro padrão 8x8
		this.board = new Board(8, 8);

		// inicializando o turno
		this.turn = 1;

		// definindo o jogador inicial
		this.currentPlayer = Color.WHITE;

		// definindo a flag de cheque
		this.check = false;

		// definindo a flag de cheque-mate
		this.checkMate = false;

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

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public ChessPiece getPromoted() {
		return promoted;
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

		// removendo a peça movida da posição de origem
		ChessPiece p = (ChessPiece) this.board.removePiece(source);

		// incrementando o contador de movimentos da peça
		p.increaseMoveCount();

		// capturando a peça do destino, se existir
		Piece capturedPiece = this.board.removePiece(target);

		// colocando a peça movida na posição de destino
		this.board.placePiece(p, target);

		// se existir peça capturada,
		if (capturedPiece != null) {

			// remove da lista de peças no tabuleiro
			this.piecesOnTheBoard.remove(capturedPiece);

			// adiciona na lista de peças capturadas
			this.capturedPieces.add(capturedPiece);
		}

		// caso a jogada tenha sido o roque pequeno, a torre direita tem que ser movida
		// (rei moveu-se horizontalmente duas posições)
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {

			// definindo as posições de origem e destino da torre direita
			Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
			Position targetRook = new Position(source.getRow(), source.getColumn() + 1);

			// movendo a torre direita
			ChessPiece rook = (ChessPiece) this.board.removePiece(sourceRook);
			this.board.placePiece(rook, targetRook);
			rook.increaseMoveCount();
		}

		// caso a jogada tenha sido o roque grande, a torre esquerda tem que ser movida
		// (rei moveu-se horizontalmente duas posições)
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {

			// definindo as posições de origem e destino da torre esquerda
			Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
			Position targetRook = new Position(source.getRow(), source.getColumn() - 1);

			// movendo a torre esquerda
			ChessPiece rook = (ChessPiece) this.board.removePiece(sourceRook);
			this.board.placePiece(rook, targetRook);
			rook.increaseMoveCount();
		}

		// caso a jogada tenha sido en passant, o peão vulnerável terá que ser removido
		// (peão se moveu diagonalmente para casa vazia)
		if (p instanceof Pawn && source.getColumn() != target.getColumn() && capturedPiece == null) {

			// inicializando a posição do peão a ser capturado
			Position vulnerablePawnPosition;

			// se a peça for branca,
			if (p.getColor() == Color.WHITE) {

				// o peão a ser capturado encontra-se na linha abaixo da posição de destino
				vulnerablePawnPosition = new Position(target.getRow() + 1, target.getColumn());
			}
			// se a peça for preta,
			else {

				// o peão a ser capturado encontra-se na linha acima da posição de destino
				vulnerablePawnPosition = new Position(target.getRow() - 1, target.getColumn());
			}

			// capturando o peão
			capturedPiece = this.board.removePiece(vulnerablePawnPosition);

			// adicionando na lista de peças capturadas
			this.capturedPieces.add(capturedPiece);

			// removendo da lista de peças no tabuleiro
			this.piecesOnTheBoard.remove(capturedPiece);
		}

		// retornando a peça capturada, se existir
		return capturedPiece;

	}

	// método responsável por desfazer o movimento das peças na jogada
	private void undoMove(Position source, Position target, Piece capturedPiece) {

		// removendo a peça movida do destino
		ChessPiece p = (ChessPiece) this.board.removePiece(target);

		// decrementando o contador de movimentos da peça
		p.decreaseMoveCount();

		// colocando a peça movida na posição de origem
		this.board.placePiece(p, source);

		// se existir peça capturada,
		if (capturedPiece != null) {

			// recolocando a peça capturada na posição de destino
			this.board.placePiece(capturedPiece, target);

			// remover a peça capturada da lista de peças capturadas
			this.capturedPieces.remove(capturedPiece);

			// adiciona a peça capturada na lista de peças no tabuleiro
			this.piecesOnTheBoard.add(capturedPiece);
		}

		// caso a jogada tenha sido o roque pequeno, o movimento da torre direita tem de
		// ser desfeito
		// (rei moveu-se horizontalmente duas posições)
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {

			// definindo as posições de origem e destino da torre direita
			Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
			Position targetRook = new Position(source.getRow(), source.getColumn() + 1);

			// movendo a torre direita
			ChessPiece rook = (ChessPiece) this.board.removePiece(targetRook);
			this.board.placePiece(rook, sourceRook);
			rook.decreaseMoveCount();
		}

		// caso a jogada tenha sido o roque grande, o movimento da torre esquerda tem de
		// ser desfeito
		// (rei moveu-se horizontalmente duas posições)
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {

			// definindo as posições de origem e destino da torre esquerda
			Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
			Position targetRook = new Position(source.getRow(), source.getColumn() - 1);

			// movendo a torre esquerda
			ChessPiece rook = (ChessPiece) this.board.removePiece(targetRook);
			this.board.placePiece(rook, sourceRook);
			rook.decreaseMoveCount();
		}

		// caso a jogada tenha sido en passant, o peão vulnerável terá que ser
		// recolocado no local correto
		// (peão se moveu diagonalmente para casa vazia)
		if (p instanceof Pawn && source.getColumn() != target.getColumn()
				&& capturedPiece == this.enPassantVulnerable) {

			// removendo o peão vulnerável da posição incorreta, colocado pela regra geral
			// do undoMove
			ChessPiece wrongPositionPawn = (ChessPiece) this.board.removePiece(target);

			// inicializando a posição do peão vulnerável a ser colocado na posição correta
			Position vulnerablePawnPosition;

			// se a peça for branca,
			if (p.getColor() == Color.WHITE) {

				// o peão a ser recolocado encontra-se na linha 5 do xadrez (linha 3 da matriz)
				vulnerablePawnPosition = new Position(3, target.getColumn());
			}
			// se a peça for preta,
			else {

				// o peão a ser recolocado encontra-se na linha 4 do xadrez (linha 4 da matriz)
				vulnerablePawnPosition = new Position(4, target.getColumn());
			}

			// recolocando o peão vulnerável na posição correta
			this.board.placePiece(wrongPositionPawn, vulnerablePawnPosition);
		}

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

	// método que retorna a cor do oponente da jogada atual
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// método responsável por retornar os dados peça rei da cor selecionada
	private ChessPiece king(Color color) {

		// criando uma nova lista filtrada das peças em jogo da cor selecionada
		List<Piece> list = this.piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());

		// iterando sobre a lista
		for (Piece p : list) {

			// se a peça da iteração atual for o rei, retorna-o
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}

		// se não foi localizado o rei, lança uma exceção
		throw new IllegalStateException("There is no " + color + " king on the board");
	}

	// método responsável por verificar se o rei de determinada cor está em cheque
	private boolean testCheck(Color color) {

		// obtendo a posição do rei da determinada cor em coordenadas de matriz
		Position kingPosition = this.king(color).getChessPosition().toPosition();

		// criando uma nova lista filtrada das peças em jogo da cor adversária
		List<Piece> opponentPieces = this.piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == this.opponent(color)).collect(Collectors.toList());

		// iterando sobre a lista
		for (Piece p : opponentPieces) {

			// obtendo a matriz de movimentos possíveis da peça da iteração atual
			boolean[][] mat = p.possibleMoves();

			// se a posição ro rei for uma das posições possíveis de movimento
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {

				// retorna true
				return true;
			}
		}

		// se não for possível capturar o rei, retorna false
		return false;
	}

	// método responsável por verificar se o rei de determinada cor está em
	// cheque-mate
	private boolean testCheckMate(Color color) {

		// se o rei não estiver em cheque, retorna false
		if (!this.testCheck(color)) {
			return false;
		}

		// criando uma nova lista filtrada das peças em jogo da cor determinada
		List<Piece> pieces = this.piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());

		// iterando sobre a lista de peças
		for (Piece p : pieces) {

			// obtendo a matriz de movimentos possíveis da peça da iteração atual
			boolean[][] mat = p.possibleMoves();

			// iterando sobre todas as posições da matriz do tabuleiro
			for (int i = 0; i < this.board.getRows(); i++) {
				for (int j = 0; j < this.board.getColumns(); j++) {

					// se a posição da iteração atual for uma das posições possíveis de movimento da
					// peça atual
					if (mat[i][j]) {

						// obtém a posição real da peça atual
						Position source = ((ChessPiece) p).getChessPosition().toPosition();

						// cria uma nova posição com a posição da iteração atual
						Position target = new Position(i, j);

						// realiza um movimento simulado da peça atual para a posição da iteração atual
						Piece capturedPiece = makeMove(source, target);

						// realiza um teste de cheque
						boolean testCheck = this.testCheck(color);

						// desfaz o movimento simulado
						this.undoMove(source, target, capturedPiece);

						// se o movimento simulado foi capaz de eliminar o cheque, retorna false
						if (!testCheck) {
							return false;
						}
					}

				}

			}

		}

		// se não for possível eliminar o cheque, retorna true
		return true;
	}

	// método responsável por retornar uma peça a partir de uma letra e uma cor
	private ChessPiece newPiece(String type, Color color) {

		if (type.equals("B"))
			return new Bishop(this.board, color);
		if (type.equals("N"))
			return new Knight(this.board, color);
		if (type.equals("Q"))
			return new Queen(this.board, color);
		if (type.equals("R"))
			return new Rook(this.board, color);
		if (type.equals("K"))
			return new King(this.board, color, this);

		return new Pawn(this.board, color, this);
	}

	public ChessPiece replacePromotedPiece(String type) {

		// se não existir peça a ser promovida, lança uma exceção
		if (this.promoted == null) {
			throw new IllegalStateException("There is no piece to be promoted");
		}

		// se o tipo da promoção for inválido, retorna a opção default provisória Q já
		// aplicada
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
			return this.promoted;
		}

		// obtendo a posição do peão a ser promovido
		Position pos = this.promoted.getChessPosition().toPosition();

		// removendo o peão
		Piece p = this.board.removePiece(pos);

		// atualizando a lista de peças no tabuleiro
		this.piecesOnTheBoard.remove(p);

		// criando a nova peça a substituir o peão promovido
		ChessPiece newPiece = this.newPiece(type, this.promoted.getColor());

		// colocando a nova peça no tabuleiro
		this.board.placePiece(newPiece, pos);

		// atualizando a lista de peças no tabuleiro
		this.piecesOnTheBoard.add(newPiece);

		return newPiece;

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

		// se o movimento gerou um cheque para o jogador atual
		if (this.testCheck(currentPlayer)) {

			// desfaz o movimento
			this.undoMove(source, target, capturedPiece);

			// lança uma exceção para refazer a jogada
			throw new ChessException("You can't put yourself in check");
		}

		// obtendo os dados da peça movida no último movimento
		ChessPiece movedPiece = (ChessPiece) this.board.piece(target);

		// analisando a possibilidade de promoção de peões
		// zerando o registro de peça promovida anteriormente
		this.promoted = null;

		// se as condições de promoção forem atendidas
		if (movedPiece instanceof Pawn && ((movedPiece.getColor() == Color.WHITE && target.getRow() == 0)
				|| (movedPiece.getColor() == Color.BLACK && target.getRow() == 7))) {

			// registrando o peão a ser promovido
			this.promoted = (ChessPiece) this.board.piece(target);

			// promovendo o peão por uma rainha como default provisório
			// ao final do turno o jogador fará a seleção da peça definitiva a ser
			// adicionada
			this.promoted = this.replacePromotedPiece("Q");

		}

		// atualizando o estado de cheque da partida
		this.check = (this.testCheck(this.opponent(currentPlayer))) ? true : false;

		// se a jogada resultou em cheque-mate
		if (this.testCheckMate(this.opponent(currentPlayer))) {

			// atualiza o estado de cheque-mate da partida
			this.checkMate = true;
		}
		// senão
		else {

			// troca o turno
			this.nextTurn();
		}

		// caso a peça movida seja um peão que tenha se movido duas casas, o mesmo está
		// vulnerável ao en passant no próximo turno
		if (movedPiece instanceof Pawn
				&& (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {

			// atribuindo a peça vulnerável
			this.enPassantVulnerable = movedPiece;
		}
		// senão,
		else {

			// zera a condição
			this.enPassantVulnerable = null;
		}

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

		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));

	}

}
