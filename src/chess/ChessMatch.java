package chess;
import board_game.Board;
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
	
	private void placeNewPiece(char coluna, int linha, ChessPiece peca) {
		board.placePiece(peca, new ChessPosition(coluna, linha).toPosition());
	}
	
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
