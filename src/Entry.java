import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Entry extends JFrame implements ActionListener {
	JPanel main;
	String outerHTML = "";
	
	ArrayList<JTextArea> titles = new ArrayList<JTextArea>();
	ArrayList<JTextArea> links = new ArrayList<JTextArea>();
	ArrayList<JTextArea> articles = new ArrayList<JTextArea>();

	public Entry() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);

		JPanel outer  = new JPanel();
		outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
		
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

		addArticle();
		
		JScrollPane sp = new JScrollPane(main);
		outer.add(sp);

		//bottom panel
		JButton button = new JButton("add article");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				addArticle();
			}
		});

		JButton finish = new JButton("Finish");
		finish.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String HTMLOutput = getText();
				Output s = new Output(HTMLOutput);
			}
		});

		JPanel advance = new JPanel();
		advance.setBackground(Color.LIGHT_GRAY);
		advance.add(button);
		advance.add(finish);
		outer.add(advance);
		
		this.add(outer);
		this.setVisible(true);
	}

	public void addArticle() {
		JTextArea title = new JTextArea(1, 50);
		title.setText("enter the title!");
		title.setBackground(new Color(255, 240, 255));
		main.add(title);
		titles.add(title);

		JTextArea link = new JTextArea(1, 50);
		link.setText("enter the link!");
		link.setBackground(new Color(240, 255, 255));
		main.add(link);
		links.add(link);

		JTextArea article = new JTextArea(20, 50);
		article.setText("enter the article!");
		article.setBackground(new Color(255, 255, 240));
		main.add(article);
		articles.add(article);
		
		JTextArea filler = new JTextArea(5,50);
		filler.setEditable(false);
		filler.setBackground(Color.BLACK);
		main.add(filler);
		
		this.setVisible(true);
	}

	public String getText(){
		String innerAdding = "";
				
		for(int i = 0; i < titles.size(); i ++){
			 String iterationInner = getFile("InnerHTML");
			 iterationInner = iterationInner.replaceAll("~##Title##~", titles.get(i).getText());
			 iterationInner = iterationInner.replaceAll("~##Link##~", links.get(i).getText());
			 iterationInner = iterationInner.replaceAll("~##Article##~", articles.get(i).getText());	 
			 
			 innerAdding += "\n" + iterationInner + "\n";
		}
		return "\n" + getFile("OuterHTML").replaceAll("~##innerHTML##~", innerAdding) + "\n";
		
		
	}
	
	public String getFile(String fileName){
		try {
			String returner = "";
			Scanner scan = new Scanner(new File(fileName));
			while(scan.hasNextLine()){
				returner += scan.nextLine() + "\n";
			}
			return returner;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
