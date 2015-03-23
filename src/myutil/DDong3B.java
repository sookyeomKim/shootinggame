package myutil;

import java.awt.Rectangle;

public class DDong3B {

	public Rectangle pos3B;

	int speed;

	public DDong3B() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong3B(Rectangle pos3B, int speed) {
		super();
		this.pos3B = pos3B;
		this.speed = speed;
	}

	public void move() {
		pos3B.x -= speed;

	}

}
