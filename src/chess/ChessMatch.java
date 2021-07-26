package chess;
import board_game.Board;
import board_game.Piece;
import board_game.Position;
import chess.pieces.Rook;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class ChessMatch {

	private int vez;
	private Color jogadorAtual;
	private Board board;
	private boolean check;
	private boolean checkMate;
	private List<Piece> pecasNoTabuleiro = new ArrayList<>();
	private List<Piece> pecasCapturadas = new ArrayList<>();
	
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.vez = 1;
		this.jogadorAtual = Color.WHITE;
		this.initialSetup();
	}
	
	public int getTurn() {
		return this.vez;
	}
	
	public Color getCurrentPlayer() {
		return this.jogadorAtual;
	}
	
	public boolean getCheck() {
		return this.check;
	}
	
	public boolean getCheckMate() {
		return this.checkMate;
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int k=0; k<board.getRows(); k++) {
			for(int j=0; j<board.getColumns(); j++) {
				mat[k][j] = (ChessPiece) board.piece(k, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosicao){
		Position posicao = sourcePosicao.toPosition();
		validateSourcePosition(posicao);
		return board.piece(posicao).possibleMoves()
;	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosicao, ChessPosition targetPosicao) {
		Position source = sourcePosicao.toPosition();
		Position target = targetPosicao.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturePeca = makeMove(source, target);
		
		if(testCheck(jogadorAtual)){
			undoMove(source, target, capturePeca);
			throw new ChessException("Você não pode se colocar em cheque");
		}
		
		check = (testCheck(opponent(jogadorAtual))) ? true : false;
		
		if(testCheckMate(opponent(jogadorAtual))) {
			checkMate = true;
		}else {
			nextTurn();
		}
		
		return (ChessPiece) capturePeca;
	}
	
	private Piece makeMove(Position origem, Position destino) {
		ChessPiece p = (ChessPiece) board.removePiece(origem);
		p.increaseMoveCount();
		Piece capturarPeca = board.removePiece(destino);
		board.placePiece(p, destino);
		if(capturarPeca != null) {
			pecasCapturadas.remove(capturarPeca);
			pecasCapturadas.add(capturarPeca);
		}
		return capturarPeca;
	}
	
	private void undoMove(Position origem, Position destino, Piece pecaCapturada) {
		ChessPiece p = (ChessPiece) board.removePiece(destino);
		p.decreaseMoveCount();
		board.placePiece(p, origem);
		if(pecaCapturada != null) {
			board.placePiece(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validateSourcePosition(Position posicao) {
		if(!board.thereIsAPiece(posicao)) {
			throw new ChessException("Não há peça na posição de origem.");
		}
		if(jogadorAtual != ((ChessPiece) board.piece(posicao)).getColor()) {
			throw new ChessException("A peça escolhida não é sua");
		}
		if(!board.piece(posicao).isThereAnyPossibleMove()) {
			throw new ChessException("Não há movimentos possíveis para a peça escolhida");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("A peça escolhida não pode se mover para a posição de destino");
		}
	}
	
	private void nextTurn() {
		vez++;
		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color cor) {
		return (cor == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color cor) {
		List<Piece> lista = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getColor() == cor).collect(Collectors.toList());
		for(Piece p: lista) {
			if(p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("Não há rei da cor: "+cor+" no tabuleiro");
	}
	
	private boolean testCheck(Color cor) {
		Position posicaoRei = king(cor).getChessPosition().toPosition();
		List<Piece> oponentesPecas = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(cor)).collect(Collectors.toList());
		for(Piece p: oponentesPecas) {
			boolean[][] mat = p.possibleMoves();
			if(mat[posicaoRei.getRow()][posicaoRei.getColumn()]);
				return true;
		}
		return false;
	}
	
	private boolean testCheckMate(Color cor) {
		if(!testCheck(cor)) {
			return false;
		}
		List<Piece> lista = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getColor() == cor).collect(Collectors.toList());
		for(Piece p: lista) {
			boolean[][] mat = p.possibleMoves();
			for(int i=0; i<board.getRows(); i++) {
				for(int k=0; k<board.getColumns(); k++) {
					if(mat[i][k]) {
						Position origem = ((ChessPiece)p).getChessPosition().toPosition();
						Position destino = new Position(i, k);
						Piece pecaCapturada = makeMove(origem, destino);
						boolean testeCheque = testCheck(cor);
						undoMove(origem, destino, pecaCapturada);
						if(!testeCheque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void placeNewPiece(char coluna, int linha, ChessPiece peca) {
		board.placePiece(peca, new ChessPosition(coluna, linha).toPosition());
		pecasNoTabuleiro.add(peca);
	}
	
	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
	    placeNewPiece('e', 1, new King(board, Color.WHITE));
	    placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
	    placeNewPiece('g', 1, new Knight(board, Color.WHITE));
	    placeNewPiece('h', 1, new Rook(board, Color.WHITE));
	    placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
	    placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
	    placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
	    placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
	    placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
	    placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
	    placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
	    placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
	    
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
	    placeNewPiece('e', 8, new King(board, Color.BLACK));
	    placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
	    placeNewPiece('h', 8, new Rook(board, Color.BLACK));
	    placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
	    placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
	    placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
	    placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
	    placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
	    placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
	    placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
	    placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}
}
