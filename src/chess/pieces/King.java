package chess.pieces;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import board_game.Board;
import board_game.Position;

public class King extends ChessPiece{
	
	private ChessMatch jogoDeXadrez;

	public King(Board board, Color color, ChessMatch xadrez) {
		super(board, color);
		this.jogoDeXadrez = xadrez;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position posicao) {
		ChessPiece p = (ChessPiece) getBoard().piece(posicao);
		return p != null || p.getColor() != getColor();
	}
	
	private boolean testRookCastling(Position posicao) {
		ChessPiece p = (ChessPiece) getBoard().piece(posicao);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][]mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		
		//Acima
		p.setValues(posicao.getRow() -1, posicao.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Abaixo
		p.setValues(posicao.getRow() + 1, posicao.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Esquerda
		p.setValues(posicao.getRow(), posicao.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Direito
		p.setValues(posicao.getRow(), posicao.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//noroeste
		p.setValues(posicao.getRow() - 1, posicao.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//nordeste
		p.setValues(posicao.getRow() - 1, posicao.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sudoeste
		p.setValues(posicao.getRow() + 1, posicao.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sudeste
		p.setValues(posicao.getRow() + 1, posicao.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Movimento especial Roque
		if(getMoveCount() == 0 && !jogoDeXadrez.getCheck()) {
			//Movimento especial Roque, Torre do lado do Rei.
			Position posT1 = new Position(posicao.getRow(), posicao.getColumn() + 3);  
			if(testRookCastling(posT1)) {
				Position p1 = new Position(posicao.getRow(), posicao.getColumn() + 1);
				Position p2 = new Position(posicao.getRow(), posicao.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[posicao.getRow()][posicao.getColumn() + 2] = true;
				}
			}
			
			//Movimento especial Roque, Torre do lado da Rainha
			Position posT2 = new Position(posicao.getRow(), posicao.getColumn() - 4);  
			if(testRookCastling(posT2)) {
				Position p1 = new Position(posicao.getRow(), posicao.getColumn() - 1);
				Position p2 = new Position(posicao.getRow(), posicao.getColumn() - 2);
				Position p3 = new Position(posicao.getRow(), posicao.getColumn() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[posicao.getRow()][posicao.getColumn() - 2] = true;
				}
			}
		}
		
		return mat;
	}
}
