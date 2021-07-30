package chess.pieces;
import board_game.Board;
import board_game.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		if(getColor() == Color.WHITE) {
			p.setValues(posicao.getRow() - 1, posicao.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(posicao.getRow() - 2, posicao.getColumn());
			Position p2 = new Position(posicao.getRow() - 1, posicao.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(posicao.getRow() - 1, posicao.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(posicao.getRow() - 1, posicao.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Movimento especial passante brancas
			if(posicao.getRow() == 3) {
				Position left = new Position(posicao.getRow(), posicao.getColumn() - 1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getPassoVulneravel()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(posicao.getRow(), posicao.getColumn() + 1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getPassoVulneravel()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		
		}else {
			p.setValues(posicao.getRow() + 1, posicao.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(posicao.getRow() + 2, posicao.getColumn());
			Position p2 = new Position(posicao.getRow() + 1, posicao.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(posicao.getRow() + 1, posicao.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(posicao.getRow() + 1, posicao.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Movimento especial passante pretas
			if(posicao.getRow() == 4) {
			Position left = new Position(posicao.getRow(), posicao.getColumn() - 1);
			if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getPassoVulneravel()) {
				mat[left.getRow() + 1][left.getColumn()] = true;
			}
			Position right = new Position(posicao.getRow(), posicao.getColumn() + 1);
			if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getPassoVulneravel()) {
				mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}
