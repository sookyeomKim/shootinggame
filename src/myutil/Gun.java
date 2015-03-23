package myutil;

import java.awt.geom.Rectangle2D;

public class Gun {

	public Rectangle2D.Double pos;

	double speed;

	public Gun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gun(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		pos.x += speed;
	}

	public void move_up() {
		pos.x += speed;
		pos.y -= 0.8;
	}

	public void move_down() {
		pos.x += speed;
		pos.y += 0.8;
	}

	public void move_up_1() {
		pos.x += speed;
		pos.y -= 1.5;
	}

	public void move_down_1() {
		pos.x += speed;
		pos.y += 1.5;
	}

}
