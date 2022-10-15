package chess;

import boardgame.BoardException;

//implementa as exceções personalizadas
public class ChessException extends BoardException {

	// atributo serializável exigido (gerado automaticamente)
	private static final long serialVersionUID = 1L;

	// construtor
	public ChessException(String msg) {
		super(msg);
	}
}
