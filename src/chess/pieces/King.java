package chess.pieces;
import chess.ChessPiece;
import chess.Color;
import board_game.Board;
import board_game.Position;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position posicao) {
		ChessPiece p = (ChessPiece) getBoard().piece(posicao);
		return p != null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][]mat = new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		Position p = new Position(0, 0);
		
		//Acima
		p.setValues(posicao.getLinha() -1, posicao.getColuna());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Abaixo
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Direito
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//noroeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//nordeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//sudoeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//sudeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
		
	}
}
