package myutil;

import java.awt.geom.Rectangle2D;
import java.util.Random;

public class DDong {
	Random ra = new Random();
	int direction_count = ra.nextInt(3);
	public Rectangle2D.Double pos;

	double speed;

	public DDong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		if (direction_count == 0) {
			pos.x -= speed;
			pos.y += 0.2;
		}
		if (direction_count == 1) {
			pos.x -= speed;
			pos.y -= 0.2;
		}
		if (direction_count == 2) {
			pos.x -= speed;
		}

	}

}
