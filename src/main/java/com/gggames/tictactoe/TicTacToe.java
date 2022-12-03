package com.gggames.tictactoe;


import static java.lang.Math.abs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.AbstractLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToe {
	
	private GameBoard gameBoard;
	
	private final int[][] board;
	private final int n;
	private final int[] rowSum, colSum;
	private int diagSum, revDiagSum;
	private int winner;


	public TicTacToe() {
		this.n = 3;
		board = new int[n][n];
		rowSum = new int[n];
		colSum = new int[n];
	}


	public TicTacToe(final int n) {
		this.n = n;
		board = new int[n][n];
		rowSum = new int[n];
		colSum = new int[n];
		this.diagSum = 0;
		this.revDiagSum = 0;
	}

	public TicTacToe(int[][] board, int n, int[] rowSum, int[] colSum, int diagSum, int revDiagSum, int winner) {
		super();
		this.board = board;
		this.n = n;
		this.rowSum = rowSum;
		this.colSum = colSum;
		this.diagSum = diagSum;
		this.revDiagSum = revDiagSum;
		this.winner = winner;
	}
	
	public static final Logger log = LoggerFactory.getLogger(TicTacToe.class);
	/**
	 * 
	 * @param player is either 0 or 1
	 * @param row    is the move's row index
	 * @param col    is the move's column index
	 * @return winner -1 if the first player, +1 if the second player and zero
	 *         otherwise
	 * @throws IllegalArgumentException if the move is an illegal move
	 */
	@PostMapping(path="/move")
	public int move(@RequestParam int player,@RequestParam int row,@RequestParam int col) throws IllegalArgumentException {
		
		if (row < 0 || col < 0 || row >= n || col >= n) {
			throw new IllegalArgumentException("Move out of the board boundary.");
		}
		if (board[row][col] != 0) {
			throw new IllegalArgumentException("Square is already occupuied.");
		}
		if (player != 0 && player != 1) {
			throw new IllegalArgumentException("Invalid player.");
		}
		if (getWinner() != 0) {
			throw new IllegalArgumentException("Board is decided.");
		}

		player = player == 0 ? -1 : +1;
		board[row][col] = player;
		rowSum[row] += player;
		colSum[col] += player;
		if (row == col) {
			diagSum += player;
		}
		if (row == n - 1 - col) {
			revDiagSum += player;
		}

		if (rowSum[row] == abs(n) || colSum[row] == abs(n) || diagSum == abs(n) || revDiagSum == abs(n)) {
			winner = player;
		}

		/*
		 * boolean winRow = true, winCol = true, winDiag = true, winRevDiag = true; for
		 * (int i = 0; i < n; i++) { if (board[row][i] != player) { winRow = false; }
		 * 
		 * if (board[i][col] != player) { winCol = false; }
		 * 
		 * if (board[i][i] != player) { winDiag = false; } if (board[i][n - 1 - i] !=
		 * player) { winRevDiag = false; }
		 * 
		 * } if (winRow || winCol || winDiag || winRevDiag) return player;
		 */

		log.info("Winner: {}", winner);
		return winner;
	}
	
	@GetMapping(path="/winner")
	public int getWinner() {
		return winner;
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

	@GetMapping(path="/board")
	public int[][] getBoard() {
		return board;
	}


	public int getN() {
		return n;
	}

	@GetMapping(path="/rowSum")
	public int[] getRowSum() {
		return rowSum;
	}


	public int[] getColSum() {
		return colSum;
	}


	public static Logger getLog() {
		return log;
	}


	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	
}
