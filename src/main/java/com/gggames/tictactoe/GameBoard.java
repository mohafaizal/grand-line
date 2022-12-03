package com.gggames.tictactoe;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class GameBoard {
	private final int[][] board;
	private final int n;
	private final int[] rowSum, colSum;
	private int diagSum, revDiagSum;
	private int winner;
		

	public GameBoard() {
		this.n = 3;
		board = new int[n][n];
		rowSum = new int[n];
		colSum = new int[n];
	}
	
	public GameBoard(final int n) {
		this.n = n;
		board = new int[n][n];
		rowSum = new int[n];
		colSum = new int[n];
		this.diagSum = 0;
		this.revDiagSum = 0;
	}
	
	public GameBoard(int[][] board, int n, int[] rowSum, int[] colSum, int diagSum, int revDiagSum, int winner) {
		super();
		this.board = board;
		this.n = n;
		this.rowSum = rowSum;
		this.colSum = colSum;
		this.diagSum = diagSum;
		this.revDiagSum = revDiagSum;
		this.winner = winner;
	}

	public int getDiagSum() {
		return diagSum;
	}

	public void setDiagSum(int diagSum) {
		this.diagSum = diagSum;
	}

	public int getRevDiagSum() {
		return revDiagSum;
	}

	public void setRevDiagSum(int revDiagSum) {
		this.revDiagSum = revDiagSum;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public int[][] getBoard() {
		return board;
	}

	public int getN() {
		return n;
	}

	public int[] getRowSum() {
		return rowSum;
	}

	public int[] getColSum() {
		return colSum;
	}

	@Override
	public String toString() {
		return "GameBoard [board=" + Arrays.toString(board) + ", n=" + n + ", rowSum=" + Arrays.toString(rowSum)
				+ ", colSum=" + Arrays.toString(colSum) + ", diagSum=" + diagSum + ", revDiagSum=" + revDiagSum
				+ ", winner=" + winner + "]";
	}
	
	
	
	
}
