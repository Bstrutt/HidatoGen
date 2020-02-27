package HidatoGen;



//import javax.swing.SwingUtilities;

public class main {

	public static void main(String[] args) {
		int[][] test = {{1,2,3,4,5,-1},
						{10,9,8,7,6,-1},
						{11,12,13,14,15,-1},
						{20,19,18,17,16,-1},
						{21,22,23,24,25,-1}};
		Board b = new Board(test);
		//Board b = new Board(4, 6);
		b.printBoard();
		if(b.checkComplete()){
			System.out.println("Complete");
		} else {
			System.out.println("Incomplete");
		}
//		 SwingUtilities.invokeLater(new Runnable() {
//	            public void run() {
//	                new DisplayBoard();
//	            }
//	        });
	}
}


