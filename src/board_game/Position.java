package board_game;

public class Position {

	private int linha;
	private int coluna;
	
	public Position(int linha, int coluna) {
		this.setLinha(linha);
		this.setColuna(coluna);
	}
	
	public int getLinha() {
		return this.linha;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	public int getColuna() {
		return this.coluna;
	}
	
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	@Override
	public String toString() {
		return linha+", "+coluna;
	}
}
