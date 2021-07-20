package chess;
import board_game.Board;
import board_game.Piece;
import board_game.Position;

public abstract class ChessPiece extends Piece{

	private Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
		
	}

	public Color getColor() {
		return this.color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(posicao);
	}
	
	protected boolean isThereOpponentPiece(Position posicao) {
		ChessPiece p = (ChessPiece) getBoard().piece(posicao);
		return p != null && p.getColor() != color;
	}
}
