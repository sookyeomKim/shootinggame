package myutil;

import java.awt.Rectangle;

public class DDong2Gun {

	public Rectangle pos;

	int speed;

	public DDong2Gun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong2Gun(Rectangle pos, int speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		pos.x -= speed;

	}

}
