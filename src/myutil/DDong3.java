package myutil;

import java.awt.Rectangle;

public class DDong3 {

	public Rectangle pos3;

	int speed;

	public DDong3() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong3(Rectangle pos3, int speed) {
		super();
		this.pos3 = pos3;
		this.speed = speed;
	}

	public void move() {
		pos3.x -= speed;

	}
	
}
