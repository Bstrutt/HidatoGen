package HidatoGen;



import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class DisplayBoard extends JFrame {
	
	
	public DisplayBoard(Board b) {
		
		JTable table = new JTable(b.getWidth(), b.getHeight());
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		for (int i = 0; i < table.getColumnCount(); i++){
			table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
		table.setRowHeight(30);
		for(int i = 0; i < b.getHeight(); i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(30);
		}
		
		for (int i = 0; i < b.getWidth(); i++){
			for (int j = 0; j < b.getHeight(); j++){
				if(b.puzzle[i][j] == -1){
					centerRenderer.setBackground(Color.BLUE);
					table.setValueAt(" ",i,j);
				} else {
					table.setValueAt("" + b.puzzle[i][j],i,j);
				}
			}
		}
		
		//this.add(table, BorderLayout.CENTER);
	    this.add(table);
	    this.setTitle("Hidato " + b.getWidth() + "by " + b.getHeight());
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	    this.pack();
	    
	    setTitle("Simple example");
	    doLayout();
	    //setSize(500, 500);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setVisible(true);
	    
	}  
}