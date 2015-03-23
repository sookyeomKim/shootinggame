package myutil;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Random;

public class DDong6Gun {
	Random ra = new Random();
	Random ra_1 = new Random();
	Random ra_2 = new Random();
	int direction_count = ra.nextInt(6);
	int direction_count_1 = ra_1.nextInt(3);
	int direction_count_2 = ra_2.nextInt(3);

	public Rectangle2D.Double pos;

	double speed;

	public DDong6Gun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong6Gun(Double pos) {
		super();
		this.pos = pos;
	}

	public DDong6Gun(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move_1() {
		pos.x -= 2;
		pos.y -= 6.6;

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
	
	
	public void move2_18() {
		pos.x -= 2;
		pos.y -= 6.6;

	}

	public void move2_17() {
		pos.x -= 3;
		pos.y -= 6.25;

	}

	public void move2_16() {
		pos.x -= 4;
		pos.y -= 5.8;

	}

	public void move2_15() {
		pos.x -= 5;
		pos.y -= 5;

	}

	public void move2_14() {
		pos.x -= 5.7;
		pos.y -= 4;

	}

	public void move2_13() {
		pos.x -= 6.25;
		pos.y -= 3;

	}

	public void move2_12() {
		pos.x -= 6.6;
		pos.y -= 2;

	}

	public void move2_11() {
		pos.x -= 6.9;
		pos.y -= 1;
	}

	public void move2_10() {
		pos.x -= speed;

	}

	public void move2_9() {
		pos.x -= 6.9;
		pos.y += 1;
	}

	public void move2_8() {
		pos.x -= 6.6;
		pos.y += 2;
	}

	public void move2_7() {
		pos.x -= 6.25;
		pos.y += 3;
	}

	public void move2_6() {
		pos.x -= 5.7;
		pos.y += 4;
	}

	public void move2_5() {
		pos.x -= 5;
		pos.y += 5;
	}

	public void move2_4() {
		pos.x -= 4;
		pos.y += 5.8;
	}

	public void move2_3() {
		pos.x -= 3;
		pos.y += 6.25;
	}

	public void move2_2() {
		pos.x -= 2;
		pos.y += 6.6;
	}

	public void move2_1() {
		pos.x -= 1;
		pos.y += 6.9;
	}
	

	public void move3() {

		pos.x -= speed;
		pos.y -= 5.5;

	}

	public void move4() {
		pos.x -= speed;
		pos.y += 5.5;

	}
}
