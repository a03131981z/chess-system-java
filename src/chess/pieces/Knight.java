package chess.pieces;

import board_game.Board;
import board_game.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color cor) {
		super(board, cor);
	}
	
	@Override
	public String toString() {
		return "N";
	}
	
	private boolean canMove(Position posicao) {
		ChessPiece p = (ChessPiece) getBoard().piece(posicao);
		return p != null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][]mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		
		p.setValues(posicao.getRow() - 1, posicao.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(posicao.getRow() - 2, posicao.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
	
		p.setValues(posicao.getRow() - 2, posicao.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(posicao.getRow() - 1, posicao.getColumn() + 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(posicao.getRow() + 1, posicao.getColumn() + 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(posicao.getRow() + 2, posicao.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(posicao.getRow() + 2, posicao.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(posicao.getRow() + 1, posicao.getColumn() - 2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
		
	}
}
