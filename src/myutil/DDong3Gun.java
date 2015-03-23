package myutil;

import java.awt.Rectangle;

public class DDong3Gun {

	public Rectangle pos;

	int speed;

	public DDong3Gun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong3Gun(Rectangle pos, int speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		pos.x -= speed;

	}

}
