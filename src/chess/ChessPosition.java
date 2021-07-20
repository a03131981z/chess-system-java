package chess;
import board_game.Position;;

public class ChessPosition {

	private char coluna;
	private int linha;
	
	public ChessPosition(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ChessException("Erro ao instanciar ChessPosition. Os valores válidos são de a1 a h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public char getColuna() {
		return this.coluna;
	}
	
	public int getLinha() {
		return this.linha;
	}
	
	protected Position toPosition() {
		return new Position(8 - linha, coluna - 'a');
	}
	
	protected static ChessPosition fromPosition(Position posicao) {
		return new ChessPosition((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return ""+this.coluna+this.linha;
	}
}
