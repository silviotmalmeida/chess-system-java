package application;

import chess.ChessPiece;

// classe responsável por imprimir as informações na tela
public class UI {

	public static void printBoard(ChessPiece[][] pieces) {

		// iterando sobre a matriz de peças
		for (int i = 0; i < pieces.length; i++) {

			// imprimindo o número da linha
			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {

				// imprimindo as peças da linha
				printPiece(pieces[i][j]);
			}

			// quebrando a linha
			System.out.println();
		}
		
		// imprimindo as letras das colunas
		System.out.println("  a b c d e f g h");

	}

	// método responsável por imprimir uma única peça
	private static void printPiece(ChessPiece piece) {

		// se a peça for nula
		if (piece == null) {

			// imprime um traço
			System.out.print("-");
		}
		// senão
		else {

			// imprime o valor referente à peça
			System.out.print(piece);

		}
		// imprime um espaço em branco
		System.out.print(" ");
	}

}
