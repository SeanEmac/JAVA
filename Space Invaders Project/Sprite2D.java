import java.awt.Graphics;
import java.awt.Image;

public class Sprite2D {

	protected double x;
	protected double y;
	protected double xSpeed = 0;;
	protected Image myImage;
	protected Image myImage2;
	protected static int winWidth;
	int framesDrawn = 0;
	
	public Sprite2D(Image i, Image i2) {

		myImage = i;
		myImage2 = i2;
	}

	public void setPosition(double xx, double yy) {
		x = xx;
		y = yy;
	}
	
	public void setXSpeed(double dx) {
		xSpeed = dx;
	}
	public void paint(Graphics g) {
		framesDrawn++;
		if (framesDrawn%100<50 ) {
			g.drawImage(myImage, (int)x, (int)y, null);
		}else {
		g.drawImage(myImage2, (int)x, (int)y, null);
		}
	}

}
