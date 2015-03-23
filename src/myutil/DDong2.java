package myutil;

import java.awt.geom.Rectangle2D;
import java.util.Random;

public class DDong2 {
	Random ra = new Random();
	int direction_count = ra.nextInt(3);

	public Rectangle2D.Double pos2;

	double speed;

	public DDong2() {
		super();
	}

	public DDong2(Rectangle2D.Double pos2, double speed) {
		super();
		this.pos2 = pos2;
		this.speed = speed;
	}

	public void move() {
		if (direction_count == 0) {
			pos2.x -= speed;
			pos2.y += 0.2;
		}
		if (direction_count == 1) {
			pos2.x -= speed;
			pos2.y -= 0.2;
		}
		if (direction_count == 2) {
			pos2.x -= speed;
		}
	}

}
