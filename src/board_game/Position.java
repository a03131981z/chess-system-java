package board_game;

public class Position {

	private int linha;
	private int coluna;
	
	public Position(int linha, int coluna) {
		this.setLinha(linha);
		this.setColuna(coluna);
	}
	
	public int getRow() {
		return this.linha;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	public int getColumn() {
		return this.coluna;
	}
	
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public void setValues(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	@Override
	public String toString() {
		return linha+", "+coluna;
	}
}
