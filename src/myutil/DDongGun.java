package myutil;

import java.awt.Rectangle;

public class DDongGun {

	public Rectangle pos;

	int speed;

	public DDongGun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDongGun(Rectangle pos, int speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		pos.x -= speed;

	}

}
