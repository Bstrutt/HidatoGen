package HidatoGen;

import java.util.Random;
import java.util.Vector;

public class Board {
	private int width;
	private int height;
	private int playableSpaces; // nothing yet but soon will do something
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
	public boolean checkComplete(){
		int currenty = 0;
		int currentx = 0;
		//Find the first playable square to compare in the next operations
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				if(puzzle[i][j] != -1 && puzzle[i][j] != 0){
					currenty = i;
					currentx = j;
				}
			}
		}
		int endNum = puzzle[currenty][currentx];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				//Return false if any blank spaces
				if(puzzle[i][j] == 0){
					return false;
				}
				//Find start number(lowest on board) that isnt an unplayable square (-1)
				if(puzzle[i][j] < puzzle[currenty][currentx] && puzzle[i][j] != -1 ){
					currenty = i;
					currentx = j;
				}
				//Find end number (highest on board)
				if(puzzle[i][j] > endNum){
					endNum = puzzle[i][j];
				}
			}
		}
		int currentNum = puzzle[currenty][currentx];
		search: while(currentNum < endNum){
			for(int i = -1; i < 2; i++){
				for(int j = -1; j < 2; j++){
					//Don't test the coordinate we are searching
					if(i == 0 && j == 0){
						continue;
					}//Don't test the coordinates outside of the puzzle 
					else if(currenty+i == -1 || currenty+i == width || currentx+j == -1 || currentx+j == height){
						continue;
					} else {
						if(puzzle[currenty + i][currentx + j] == currentNum+1){
							currentNum++;
							currenty = currenty + i;
							currentx = currentx + j;
							continue search;
						}
					}
				}
			}
			return false;
		}
		if(currentNum == endNum){
			return true;
		}
		return false;
		
		
	}
	void populateBoard() {
		//Places the start randomly
		Integer[] currentCoords = placeOne();
		//Finds the collection of surrounding open squares to the start
		Vector<Integer[]> v = findOpenCoords(currentCoords);
		//Chooses the next square to move to randomly
		while(!placeNext(currentCoords)){
			puzzle[currentCoords[0]][currentCoords[1]] = 0;
			currentCoords = placeOne();
		}
	}
	/*
	 * Places the next 
	 */
	private boolean placeNext(Integer[] a){
		int count = 0;
		Vector<Integer[]> v = findOpenCoords(a);
		Integer[] temp;
		while(v.size() > 0){
			temp = v.remove(gen.nextInt(v.size()));
			puzzle[temp[0]][temp[1]] = puzzle[a[0]][a[1]] + 1;
			
			for(int i = 0; i < width; i++){			//This is extremely inefficient and should be removed asap
				for(int j = 0; j < height; j++){	// We need to know the amount of unplayable spaces
					if(puzzle[i][j] == -1){			// so that we can end our recursive method when we run
						count++;					// out of spaces to place things.
					}
				}
			}
			
			if(puzzle[temp[0]][temp[1]] == width*height - count){
				return true;
			} else {
				if(placeNext(temp)){
					return true;
				} else {
					puzzle[temp[0]][temp[1]] = 0;
					continue;
				}
			}
		}
		return false;
	}
	/*
	 * Takes a coordinate in. From there finds all of the open
	 * coordinates that are adjacent to the given coordinate
	 * returns as a vector
	 * 
	 * Used in creation and solution
	 */
	private Vector<Integer[]> findOpenCoords(Integer[] a){
		int y = a[0];
		int x = a[1];
		Vector<Integer[]> v = new Vector<Integer[]>();
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				//Don't test the coordinate we are searching
				if(i == 0 && j == 0){
					continue;
				}//Don't test the coordinates outside of the puzzle 
				else if(y+i == -1 || y+i == width || x+j == -1 || x+j == height){
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
	
	/*
	 * This method places the starting point on an empty board
	 * Returns the coordinate of the starting point
	 * Made to place the starting point on scarce boards as well
	 * 
	 * May be expanded to have anything as the starting point but
	 * that might be weird so we will see
	 * 
	 */
	private Integer[] placeOne(){
		Random gen = new Random();
		int tempRand = gen.nextInt(width * height);
		int startHeight = tempRand/height;
		int startWidth = tempRand/width;
		
		while(puzzle[startHeight][startWidth] == -1){
			tempRand = gen.nextInt(width * height);
			startHeight = tempRand/height;
			startWidth = tempRand/width;
		}
		
		puzzle[startHeight][startWidth]= 1;
		Integer[] a = {startHeight, startWidth};
		return a;
	}
	//Simple Print method in swing with jframe and jtable
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
