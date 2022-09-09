package boardgame;

// implementa as exceções personalizadas
public class BoardException extends RuntimeException {

	// atributo serializável exigido (gerado automaticamente)
	private static final long serialVersionUID = 1L;

	// construtor
	public BoardException(String msg) {
		super(msg);
	}
}
