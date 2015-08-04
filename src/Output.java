import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Output extends JFrame{
	
	public Output(String s){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		

		
		JTextArea t = new JTextArea(s);
		t.setEditable(false);
		JScrollPane sp = new JScrollPane(t);

		
		this.setContentPane(sp);
		this.setVisible(true);	
		
	}
}
