package myutil;

import java.awt.Rectangle;

public class Life {

	public Rectangle pos;

	int speed;
	

	public Life() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Life(Rectangle pos, int speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move_down() {
		pos.x -= speed;// ÇÏ°­
	}

}
