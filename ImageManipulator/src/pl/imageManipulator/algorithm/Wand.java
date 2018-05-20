package pl.imageManipulator.algorithm;

/**
 * Wand which can be used in app
 * @author Mateusz Œliwa <mateuszsliwa7@wp.pl>
 *
 */
public class Wand {
	private boolean isActive;
	private int power;
	private int x, y;
	
	public Wand() {
		this.power = 150;
		this.isActive = false;
	}
	
	/**
	 * Increase power by 5
	 */
	public void increasePower() {
		if((this.power + 5) > 255)
			this.power = 255;
		if(this.power < 255)
			this.power += 5;
	}
	
	/**
	 * Decrease power by 5
	 */
	public void decreasePower() {
		if((this.power - 5) < 0)
			this.power = 0;
		if(this.power > 0)
			this.power -= 5;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
