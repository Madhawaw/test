package com.eyepax.assignment;

import java.util.Scanner;

/**
 * Created by madhawa.w on 7/24/2022.
 */
public class ColorGridChallenge {

	static int n = 6;
	static int m = 8;

	public static final int visitedArr[][] = new int[n][m];
	public static final int resultArr[][] = new int[n][m];

	public static int count;

	public static void main(String[] args) {

		try {
			System.out.println("--------------------------------------------------------------");
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter row count : ");
			int x = sc.nextInt();
			System.out.print("Enter column count : ");
			int y = sc.nextInt();

			if (x <= y) {
				n = x;
				m = y;
			} else {
				n = y;
				m = x;
			}
		} catch (Exception e) {
			System.out.println("Grid generation failed!");
		}
		System.out.println("--------------------------------------------------------------");

		int input[][] = generateGrid();
		printInput(input);
		System.out.println("--------------------------------------------------------------");
		calculateLargestBlock(input);
		System.out.println("--------------------------------------------------------------");
	}

	// calculate the largest continuous color block
	static void calculateLargestBlock(int input[][]) {

		int max = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				// initiate the flow
				resetCell();
				count = 0;

				// checking cell to the right
				if (j + 1 < m)
					BFS(input[i][j], input[i][j + 1], i, j, input);

				// updating result
				if (count >= max) {
					max = count;
					storeResult(input[i][j], input);
				}
				resetCell();
				count = 0;

				// checking cell downwards
				if (i + 1 < n)
					BFS(input[i][j], input[i + 1][j], i, j, input);

				// updating result
				if (count >= max) {
					max = count;
					storeResult(input[i][j], input);
				}
			}
		}

		printOutput(max);

	}

	// reset to 0 before a BFS
	static void resetCell() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				visitedArr[i][j] = 0;
	}

	// BFS to find all cells in
	// connection with key = input[i][j]
	static void BFS(int x, int y, int i, int j, int input[][]) {
		// terminating case for BFS
		if (x != y)
			return;

		visitedArr[i][j] = 1;
		count++;

		// x_move and y_move arrays
		// are the possible movements
		// in x or y direction
		int x_move[] = { 0, 0, 1, -1 };
		int y_move[] = { 1, -1, 0, 0 };

		// checks all four points
		// connected with input[i][j]
		for (int u = 0; u < 4; u++)
			if ((is_valid(i + y_move[u], j + x_move[u], x, input)) == true)
				BFS(x, y, i + y_move[u], j + x_move[u], input);
	}

	// validate cell
	static boolean is_valid(int x, int y, int key, int input[][]) {
		if (x < n && y < m && x >= 0 && y >= 0) {
			if (visitedArr[x][y] == 0 && input[x][y] == key)
				return true;
			else
				return false;
		} else
			return false;
	}

	// store the details of large continuous block
	static void storeResult(int key, int input[][]) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visitedArr[i][j] == 1 && input[i][j] == key)
					resultArr[i][j] = visitedArr[i][j];
				else
					resultArr[i][j] = 0;
			}
		}
	}

	// print result
	static void printOutput(int x) {
		System.out.println("The largest continuous color blocks count :" + x);

		// prints the largest block pattern
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (resultArr[i][j] != 0)
					System.out.print("#" + " ");
				else
					System.out.print(". ");
			}
			System.out.println();
		}
	}

	static int[][] generateGrid() {

		int[][] arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				// get random RGB color code
				int[] rgb = new ColorGenerator().getRandomRgb();
				int color = 0;
				for (int c : rgb) {
					color = color << 8;
					color = color | c;
				}
				arr[i][j] = color;
			}
		}

		return arr;
	}

	// print Input
	static void printInput(int input[][]) {
		System.out.println("---Color Grid---");

		// print the largest block pattern
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(input[i][j] + " ");
			}
			System.out.println();
		}
	}

}
