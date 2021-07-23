package board_game;

public abstract class Piece {

	protected Position posicao;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		this.posicao = null;
	}
	
	protected Board getBoard() {
		return this.board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position posicao) {
		return possibleMoves()[posicao.getRow()][posicao.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
