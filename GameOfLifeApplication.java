import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.swing.JFrame;

public class GameOfLifeApplication extends JFrame implements Runnable, MouseListener, MouseMotionListener {
	
	public static final Dimension WindowSize = new Dimension(800,800);
	private BufferStrategy strategy;
	private static boolean isGraphicsInitialised = false;
	private boolean[][][] gameState = new boolean[40][40][2];
	private boolean playing = false;
	private int gameStateFrontBuffer = 0;
	
	public GameOfLifeApplication() {
		
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		setVisible(true);
		this.setTitle("Game Of Life SeanMcCann");
	
		Thread t = new Thread(this);
		t.start();
		
		addMouseListener(this);
		addMouseMotionListener(this);

		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		isGraphicsInitialised = true;
		
        for(int m=0;m<40;m++){
        	for(int n=0;n<40;n++){
        		for(int z = 0; z<2; z++){
        			gameState[m][n][z] = false;
        		}
        	}
        }
	}
	
	public void mouseClicked(MouseEvent e) {

	   	 int x = e.getX()/20;
	   	 int y = e.getY()/20;
	   	
	   	 if((e.getX()>= 15 && e.getX()<= 85) && (e.getY()>=40 && e.getY()<=70)){  //start button
	   		 playing = true;                       
	   	 }
	   	 
	   	if((e.getX()>= 110 && e.getX()<= 215) && (e.getY()>=40 && e.getY()<=70)){ //randomize button
	   		int r = (Math.random() <= 0.5) ? 1 : 2;   
	   		
	   		if(r == 1){
	   			playing = true;
	   		}                                  
	   		else{
	   			playing = false;
	   		}
	   	}
	   	
	   	if((e.getX()>= 300 && e.getX()<= 380) && (e.getY()>=40 && e.getY()<=70)){  //save button
	   		saveGame();
	   	}
	   	
	   	if((e.getX()>= 400 && e.getX()<= 480) && (e.getY()>=40 && e.getY()<=70)){  //load button
	   		loadGame();
	   	}
	   	
	    gameState[x][y][gameStateFrontBuffer] = !gameState[x][y][gameStateFrontBuffer];
	   	this.repaint();
    
	}
	
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {	
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {	
	}
	public void mouseMoved(MouseEvent e) {
	}
	
	public void mouseDragged(MouseEvent e) {
		int x = e.getX()/20;
		int y = e.getY()/20;
	   	 
		gameState[x][y][gameStateFrontBuffer] = true;//when the mouse is dragged the game state is changed to true
		this.repaint();
	}
	
	public void run() {
		while(true){
			try {
				Thread.sleep(200);
			} catch(InterruptedException e) {}
			this.repaint();
			if(playing) {
				doOneEpochOfGame();
			}
		}
	}
	public void saveGame() {
		String filename = "E:\\lifegame.txt";
		String state = "";
        for(int m=0;m<40;m++){
        	for(int n=0;n<40;n++){
        		for(int z = 0; z<2; z++){
        			if(gameState[m][n][0]) {// if the game state is true then add a 1 to the string
        				state += "1";
        			}else {
        				state += "0";//else add a 0
        			}
        		}
        	}
        }
		
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(state);//store the whole string in a text file
		writer.close();
		}
		catch (IOException e) { }
	}
	public void loadGame() {
		String line=null;
		String filename = "E:\\lifegame.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			do {
				try {
					line = reader.readLine();//read the whole string from the text file
					int j = 0;//j keeps track of where we are on the string
			        for(int m=0;m<40;m++){
			        	for(int n=0;n<40;n++){
			        		for(int z = 0; z<2; z++){//loop through the game state again
			        			if(line.charAt(j) == '1') {//if the string contains the character '1'
			        				gameState[m][n][z] = true;//change the game state to true for the matching 1 in the text file
			        				j++;
			        			}else {
			        				gameState[m][n][z] = false;
			        				j++;
			        			}
			        		}
			        	}
			        }
				} catch (IOException e) { }
			}
		while (line != null);
		reader.close();
		} catch (IOException e) { }

	}
	private void doOneEpochOfGame() {
		
    	int front = gameStateFrontBuffer;
    	int back = (front+1)%2;
        for (int x=0;x<40;x++) {
        	for (int y=0;y<40;y++) {
        		int liveneighbours=0;
        		for (int xx=-1;xx<=1;xx++) {
        			for (int yy=-1;yy<=1;yy++) {
        				if (xx!=0 || yy!=0) {
        					int xxx=x+xx;
        					if (xxx<0)
        						xxx=39;
        					else if (xxx>39)
        						xxx=0;
        					int yyy=y+yy;
        					
        					if (yyy<0)
        						yyy=39;
        					else if (yyy>39)
        						yyy=0;           					
        					if (gameState[xxx][yyy][front])
        						liveneighbours++;
        				}
        			}
        		}

        		if (gameState[x][y][front]) {
        			if (liveneighbours<2)
        				gameState[x][y][back] = false;
        			else if (liveneighbours<4)
        				gameState[x][y][back] = true;  
        			else
        				gameState[x][y][back] = false;
        		}
        		else {
        			if (liveneighbours==3)
        				gameState[x][y][back] = true;
        			else
        				gameState[x][y][back] = false;
        		}
        	}
        }
    	gameStateFrontBuffer = back;		
	
	}
	
	public void paint(Graphics g) {
		
		if(!isGraphicsInitialised) {
			return;
		}
		
		g = strategy.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 800); 
		
		g.setColor(Color.WHITE);
		for (int x=0;x<40;x++) {
			for (int y=0;y<40;y++) {
				if (gameState[x][y][gameStateFrontBuffer] == true) {
					g.fillRect(x*20, y*20, 20, 20);
				}
			}
		}
		
        g.setColor(Color.GREEN);
        g.fillRect(15, 40, 70, 30);
        g.fillRect(115, 40, 100, 30);
        g.fillRect(300, 40, 80, 30);
        g.fillRect(400, 40, 80, 30);
       	g.setFont(new Font("Times", Font.PLAIN, 24));
        g.setColor(Color.BLACK);
    	g.drawString("Start", 22, 62);
        g.drawString("Random", 120, 62);
        g.drawString("Save", 305, 62);
        g.drawString("Load", 405, 62);
		
		g.dispose();
		strategy.show();
	}
	
	public static void main(String[] args) {
		GameOfLifeApplication w = new GameOfLifeApplication();	
	}
}
