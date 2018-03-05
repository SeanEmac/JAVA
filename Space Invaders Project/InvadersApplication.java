
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class InvadersApplication extends JFrame implements Runnable, KeyListener {
	
	public static final Dimension WindowSize = new Dimension(800,600);
	private BufferStrategy strategy;
	private Graphics offscreenGraphics;
	private static final int NUMALIENS = 30;
	private Alien[] AliensArray = new Alien[NUMALIENS];
	private Spaceship PlayerShip;
	private Image bulletImage;
	private ArrayList bulletsList=new ArrayList();
	private static String workingDirectory;
	private static boolean isGraphicsInitialised = false;
	
	private boolean isGameInProgress = false;
	private int enemyWave = 1;
	private int score = 0;
	private int highscore = 0;
	
	public InvadersApplication() {
		
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		setVisible(true);
		this.setTitle("Space Invaders Game SeanMcCann");
		
		ImageIcon icon = new ImageIcon(workingDirectory + "\\alien_ship_1.png");
		Image alienImage1 = icon.getImage();
		icon = new ImageIcon(workingDirectory + "\\alien_ship_2.png");
		Image alienImage2 = icon.getImage();
		icon = new ImageIcon(workingDirectory + "\\bullet.png ");
		bulletImage = icon.getImage();
		
		for(int i = 0; i<NUMALIENS; i++) {
			
			AliensArray[i] = new Alien(alienImage1, alienImage2);
			
		}

		icon = new ImageIcon(workingDirectory + "\\player_ship.png");
		Image shipImage = icon.getImage();
		PlayerShip = new Spaceship(shipImage, bulletImage);
		
		Thread t = new Thread(this);
		t.start();
		
		addKeyListener(this);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		offscreenGraphics = strategy.getDrawGraphics();
		isGraphicsInitialised = true;
		
	}

	public void run() {
		while(true){
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {}
			
			if(isGameInProgress) {
				
					boolean anyAliensAlive = false;
					boolean alienDirectionReversalNeeded = false;
					
					for(int i = 0; i<NUMALIENS; i++) {
						if(AliensArray[i].isAlive) {
							anyAliensAlive = true;
							if(AliensArray[i].move()) {
								alienDirectionReversalNeeded = true;
	
							}
							if(isCollision(PlayerShip.x, AliensArray[i].x, PlayerShip.y,AliensArray[i].y,54,50,32,32)) {
								isGameInProgress = false;
							}
						}
					}
					if(alienDirectionReversalNeeded) {
						for(int i=0; i<NUMALIENS; i++) {
							AliensArray[i].reverseDirection();
							if(AliensArray[i].y > WindowSize.height-20) {
								isGameInProgress = false;
							}
						}
						
					}
					if(!anyAliensAlive) {
						enemyWave++;
						startNewWave();
					}
					PlayerShip.move();
					Iterator iterator =bulletsList.iterator();
					while(iterator.hasNext()){
						PlayerBullet b = (PlayerBullet) iterator.next();
						if(b.move()) {
							iterator.remove();
						}else {
							double x1 = b.x;
							double y1 = b.y;
							double w1 = 50;
							double h1 = 32;
							double w2 = 6;//inspect image properties
							double h2 = 16;
							for(int i = 0; i<NUMALIENS; i++) {
								if(AliensArray[i].isAlive) {
									double x2 = AliensArray[i].x;
									double y2 = AliensArray[i].y;
									if(isCollision(x2,x1,y2,y1,w2,w1,h2,h1)) {
										AliensArray[i].isAlive = false;
										iterator.remove();
										score+= 1;
										if(highscore<score){
											highscore = score;
										}
										break;	
									}
								}
							}
						}
					}
			}
			this.repaint();
		}
	}

	public void keyPressed(KeyEvent e) {
		if(isGameInProgress) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				PlayerShip.setXSpeed(-4);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				PlayerShip.setXSpeed(+4);
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				bulletsList.add(PlayerShip.shootBullet());
			}
		}else {
			startNewGame();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			PlayerShip.setXSpeed(0);
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	

	public void startNewGame() {
		enemyWave = 1;
		score = 0;
		isGameInProgress = true;
		startNewWave();
		
	}
	public void startNewWave() {
		for(int i = 0; i<NUMALIENS; i++) {
			
			double xx = (i%5)*80 + 70;
			double yy = (i/5)*40 + 50;
			AliensArray[i].setPosition(xx, yy);
			AliensArray[i].setXSpeed(1+(2*enemyWave));
			AliensArray[i].isAlive = true;
			AliensArray[i].framesDrawn = 0;;
		}
		PlayerShip.setPosition(300, 530);
	}
	private boolean isCollision(double x1, double x2, double y1, double y2, double w1, double w2, double h1, double h2) {
		if (( (x1<x2 && x1+w1>x2) || (x2<x1 && x2+w2>x1) ) && ( (y1<y2 && y1+h1>y2) || (y2<y1 && y2+h2>y1) )) {
			return true;
		}else {
			return false;
		}
	}
	private void writeString(Graphics g, int x, int y, int fontSize, String message) {
		Font f = new Font ("Courier New", Font.PLAIN, fontSize);
		g.setFont(f);
		FontMetrics fm = getFontMetrics(f);
		int width = fm.stringWidth(message);
		g.drawString(message, x-width/2, y);
        
	}

	public void paint(Graphics g) { 
		if(!isGraphicsInitialised) {
			return;
		}
			
		g = offscreenGraphics;
			
		g.setColor(Color.BLACK);
		g.fillRect(0,  0,  WindowSize.width, WindowSize.height);
		
		
		if(isGameInProgress) {
			for(int i = 0; i<NUMALIENS; i++) {
					AliensArray[i].paint(g);
			}
			
			PlayerShip.paint(g);
			Iterator iterator = bulletsList.iterator();
			
			while(iterator.hasNext()){
				PlayerBullet b = (PlayerBullet) iterator.next();
				b.paint(g);
			}
			
			g.setColor(Color.WHITE);
			writeString(g,WindowSize.width/2,60,20,"wave: "+enemyWave+ " score: " +score+ " Best: " +highscore);
			
		}else {
			g.setColor(Color.WHITE);
			writeString(g,WindowSize.width/2,200,60,"Game Over");
			writeString(g,WindowSize.width/2,300,30,"Press Any Key to Play");
			writeString(g,WindowSize.width/2,350,25,"Arrows to move, Space to shoot");
			
		}
		
		strategy.show();
	}
	public static void main(String[] args) {
		workingDirectory = System.getProperty("user.dir");
		InvadersApplication w = new InvadersApplication();
		
		
	}
	
	
	
}