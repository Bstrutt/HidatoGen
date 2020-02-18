package HidatoGen;

import java.util.Random;

public class Board {
	private int width;
	private int height;
	int[][] puzzle;

	public Board(int height, int width) {
		this.width = width;
		this.height = height;

		puzzle = new int[width][height];
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				puzzle[i][j] = 0;
			}
		}
	}
	public Board(int[][] a){
		this.width = a.length;
		this.height = a[0].length;
		puzzle = new int[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				puzzle[i][j] = a[i][j];
			}
		}
	}

	void populateBoard() {
		//Places the start randomly
		Random gen = new Random();
		int tempRand = gen.nextInt(width * height);
		int startHeight = tempRand/height;
		int startWidth = tempRand/width;
		puzzle[startHeight][startWidth]= 1;
		
		for(int i = 0; i < width*height; i++){
			
		}
	}

	void printBoard() {
		new DisplayBoard(this);
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
