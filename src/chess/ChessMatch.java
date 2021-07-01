package chess;
import board_game.Board;
import board_game.Position;
import chess.pieces.Rook;
import chess.pieces.King;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getLinhas()][board.getColunas()];
		for(int k=0; k<board.getLinhas(); k++) {
			for(int j=0; j<board.getColunas(); j++) {
				mat[k][j] = (ChessPiece) board.piece(k, j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
	}
}
