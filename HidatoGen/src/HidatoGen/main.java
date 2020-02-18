package HidatoGen;



//import javax.swing.SwingUtilities;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] test = {{1,2,3,4},
						{10,9,8,7},
						{11,12,13,14},
						{20,19,18,17},
						{21,22,23,24}};
		//Board b = new Board(test);
		Board b = new Board(6, 6);
		b.printBoard();
//		 SwingUtilities.invokeLater(new Runnable() {
//	            public void run() {
//	                new DisplayBoard();
//	            }
//	        });
	}
}


