package chess;
import board_game.Board;
import board_game.Piece;

public abstract class ChessPiece extends Piece{

	private Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
		
	}

	public Color getColor() {
		return this.color;
	}
}
