package HidatoGen;

import java.util.Random;
import java.util.Vector;

public class Board {
	private int width;
	private int height;
	int[][] puzzle;
	Random gen;
	
	public Board(int height, int width) {
		this.width = width;
		this.height = height;
		gen = new Random();

		puzzle = new int[width][height];
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				puzzle[i][j] = 0;
			}
		}
		populateBoard();
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
		gen = new Random();
	}

	void populateBoard() {
		//Places the start randomly
		Integer[] currentCoords = placeOne();
		//Finds the collection of surrounding open squares to the start
		Vector<Integer[]> v = findOpenCoords(currentCoords);
		//Chooses the next square to move to randomly
		//This needs a lot of work, might be easiest to make a recursive helper
		for( int i = 0; i < width*height; i++){
			currentCoords = v.remove(gen.nextInt(v.size()));
		}
		gen.nextInt(v.size());
//		for(int i = 0; i < width*height + 1; i++){
//			
//		}
	}
	//This method gets a coordinate and finds an open coordinate next to it randomly
	private Vector<Integer[]> findOpenCoords(Integer[] a){
		int y = a[0];
		int x = a[1];
		Vector<Integer[]> v = new Vector<Integer[]>();
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				//Dont test the coordinate we are searching
				if(i == 0 && j == 0){
					continue;
				}//Dont test the coordinates outside of the puzzle 
				else if(y+i == -1 || y+i == height || x+j == -1 || x+j == width){
					continue;
				} else {
					if(puzzle[y + i][x + j] == 0){
						Integer[] b = {y+i, x+j};
						v.add(b);
					}
				}
			}
		}
		return v;
	}
	//Places the first one on the board. This is the starting point
	//Returns the coordinate of the first one
	private Integer[] placeOne(){
		Random gen = new Random();
		int tempRand = gen.nextInt(width * height);
		int startHeight = tempRand/height;
		int startWidth = tempRand/width;
		puzzle[startHeight][startWidth]= 1;
		Integer[] a = {startHeight, startWidth};
		return a;
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
