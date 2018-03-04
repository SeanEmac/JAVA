package RandBox;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

//SEAN MCCANN 16343643
public class MyApplication extends JFrame {
	
	private static final Dimension WindowSize = new Dimension(600,600);
	private ArrayList<Color> colors;
	
	public MyApplication() {
	
	this.setTitle("16343643");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	int x = screensize.width/2 - WindowSize.width/2;
	int y = screensize.height/2 - WindowSize.height/2;
	setBounds(x, y, WindowSize.width, WindowSize.height);
	setVisible(true);
	}
	
	public void paint ( Graphics g ) {
		//SET VARIABLES
		int ROWS = 10;
		int COLS = 10;
		int i = 0;
		int k = 0;
		int SIZE = 55;
        int arrayLength = 100;
        colors = new ArrayList<>(arrayLength);
        
        //MAKE AN ARRAY OF COLOURS
        for (i = 0; i < arrayLength; i++) {
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            colors.add(new Color(red, green, blue));
        }
        
        //DRAW THE SQUARES
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
	            g.setColor(colors.get(k));
	            g.fillRect(15 + (col * SIZE),40 + (row * SIZE), 50, 50);//SQUARES SHOULD BE SPACED 
	            k++;//CHANGE ARRAY INDEX = DIFFERENT COLOUR
            }
        }

	}
	
	public static void main(String [ ] args) {
	MyApplication w = new MyApplication();
	}
}


