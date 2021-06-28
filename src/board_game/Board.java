package board_game;

public class Board {

	private int linhas;
	private int colunas;
	private Piece[][] pieces;
	
	public Board(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pieces = new Piece[linhas][colunas];
	}
	
	public int getLinhas() {
		return this.linhas;
	}
	
	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}
	
	public int getColunas() {
		return this.colunas;
	}
	
	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
}
