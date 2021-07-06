package chess.pieces;
import chess.ChessPiece;
import chess.Color;
import board_game.Board;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][]mat = new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		return mat;
	}
}
