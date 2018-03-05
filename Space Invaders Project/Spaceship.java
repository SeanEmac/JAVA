import java.awt.Image;

public class Spaceship extends Sprite2D {

	private Image bulletImage;
	
	public Spaceship(Image i, Image bullet) {
		super(i, i);
		bulletImage = bullet;
	}
	
	public void setXSpeed(double dx) {
		xSpeed = dx;
	}
	public void move(){
		x += xSpeed;
		
		if(x<=0) {
			x = 0;
			xSpeed = 0;
		}
		else if(x>=InvadersApplication.WindowSize.width-myImage.getWidth(null)) {
			x = InvadersApplication.WindowSize.width-myImage.getWidth(null);
			xSpeed = 0;
		}
	}
	
	public PlayerBullet shootBullet() {
		PlayerBullet b = new PlayerBullet(bulletImage);//create bullet
		b.setPosition(this.x+54/2, this.y);// set to position of the ship
		return b;
	}
}
