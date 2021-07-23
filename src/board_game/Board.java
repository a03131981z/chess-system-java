package board_game;

public class Board {

	private int linhas;
	private int colunas;
	private Piece[][] pieces;
	
	public Board(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new BoardException("Erro ao criar tabuleiro: deve haver pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pieces = new Piece[linhas][colunas];
	}
	
	public int getRows() {
		return this.linhas;
	}
	
	public int getColumns() {
		return this.colunas;
	}
	
	public Piece piece(int linha, int coluna) {
		if (!positionExists(linha, coluna)) {
			throw new BoardException("A posição não está no tabuleiro");
		}
		return pieces[linha][coluna];
	}
	
	public Piece piece(Position posicao) {
		if (!positionExists(posicao)) {
			throw new BoardException("A posição não está no tabuleiro");
		}
		return pieces[posicao.getRow()][posicao.getColumn()];
	}
	
	public void placePiece(Piece peca, Position posicao) {
		if(thereIsAPiece(posicao)) {
			throw new BoardException("Já existe uma peça na posição "+posicao);
		}
		pieces[posicao.getRow()][posicao.getColumn()] = peca;
		peca.posicao = posicao;
	}
	
	public Piece removePiece(Position posicao) {
		if(!positionExists(posicao)) {
			throw new BoardException("A posição não está no tabuleiro.");
		}
		if(piece(posicao) == null) {
			return null;
		}
		Piece aux = piece(posicao);
		aux.posicao = null;
		pieces[posicao.getRow()][posicao.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int linha, int coluna) {
		return linha >= 0 && linha < linha && coluna >= 0 && coluna < coluna;
	}
	
	public boolean positionExists(Position posicao) {
		return positionExists(posicao.getRow(), posicao.getColumn());
	}
	
	public boolean thereIsAPiece(Position posicao) {
		if(!positionExists(posicao)) {
			throw new BoardException("A posição não está no tabuleiro");
		}
		return piece(posicao) != null;
	}
}
