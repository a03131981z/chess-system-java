package board_game;

public class Piece {

	protected Position posicao;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		this.posicao = null;
	}
	
	protected Board getBoard() {
		return this.board;
	}
}
