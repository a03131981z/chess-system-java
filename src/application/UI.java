package application;
import chess.ChessPiece;

public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
		for(int k=0; k<pieces.length; k++) {
			System.out.print((8 - k)+" ");
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[k][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
