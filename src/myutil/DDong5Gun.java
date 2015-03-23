package myutil;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Random;

public class DDong5Gun {
	Random ra = new Random();
	int direction_count = ra.nextInt(6);

	public Rectangle2D.Double pos;

	double speed;

	public DDong5Gun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong5Gun(Double pos) {
		super();
		this.pos = pos;
	}

	public DDong5Gun(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move_1() {
		pos.x -= 2;
		pos.y -= 6.9;

	}

	public void move_2() {
		pos.x -= 3;
		pos.y -= 6.25;

	}

	public void move_3() {
		pos.x -= 4;
		pos.y -= 5.8;

	}

	public void move_4() {
		pos.x -= 5;
		pos.y -= 5;

	}

	public void move_5() {
		pos.x -= 5.7;
		pos.y -= 4;

	}

	public void move_6() {
		pos.x -= 6.25;
		pos.y -= 3;

	}

	public void move_7() {
		pos.x -= 6.6;
		pos.y -= 2;

	}

	public void move_8() {
		pos.x -= 6.9;
		pos.y -= 1;
	}

	public void move_9() {
		pos.x -= speed;

	}

	public void move_10() {
		pos.x -= 6.9;
		pos.y += 1;
	}

	public void move_11() {
		pos.x -= 6.6;
		pos.y += 2;
	}

	public void move_12() {
		pos.x -= 6.25;
		pos.y += 3;
	}

	public void move_13() {
		pos.x -= 5.7;
		pos.y += 4;
	}

	public void move_14() {
		pos.x -= 5;
		pos.y += 5;
	}

	public void move_15() {
		pos.x -= 4;
		pos.y += 5.8;
	}

	public void move_16() {
		pos.x -= 3;
		pos.y += 6.25;
	}

	public void move_17() {
		pos.x -= 2;
		pos.y += 6.6;
	}

	public void move_18() {
		pos.x -= 1;
		pos.y += 6.9;
	}

	public void move_19() {
		pos.y += speed;
	}

	public void move_20() {
		pos.x += 1;
		pos.y += 6.9;
	}

	public void move_21() {
		pos.x += 2;
		pos.y += 6.6;
	}

	public void move_22() {
		pos.x += 3;
		pos.y += 6.25;
	}

	public void move_23() {
		pos.x += 4;
		pos.y += 5.8;
	}

	public void move_24() {
		pos.x += 5;
		pos.y += 5;
	}

	public void move2() {
		if (direction_count == 0) {
			pos.x -= speed;
			pos.y -= 2;
		}
		if (direction_count == 1) {
			pos.x -= speed;
			pos.y += 2;
		}
		if (direction_count == 2) {
			pos.x -= speed;
			pos.y -= 4;
		}
		if (direction_count == 3) {
			pos.x -= speed;
			pos.y += 4;
		}
		if (direction_count == 4) {
			pos.x -= speed;
		}
		if (direction_count == 5) {
			pos.x -= speed;
		}
	}
}
