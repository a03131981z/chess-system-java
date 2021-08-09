package application;
import chess.ChessException;
import chess.ChessMatch;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import chess.ChessPosition;
import chess.ChessPiece;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> capturadas = new ArrayList<>();
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, capturadas);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source) ;
				application.UI.clearScreen(); //application é o nome do pacote, não precisa colocar ele, mas vou deixar
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				ChessPiece pecasCapturadas = chessMatch.performChessMove(source, target);
				
				if(pecasCapturadas != null) {
					capturadas.add(pecasCapturadas);
				}
				if(chessMatch.getPromoted() != null) {
					System.out.print("Digite uma peça pra promover (B/N/R/Q): ");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}
		
			}catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, capturadas);
	}
}
