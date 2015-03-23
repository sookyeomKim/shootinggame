package myutil;

import java.awt.Rectangle;

public class ItemPowerUp {
	/*
	 * Random ra2 = new Random(); int direction = ra2.nextInt(4) + 1;
	 */
	// MyMain mm;

	public Rectangle pos;

	/*
	 * public ItemPowerUp(MyMain mm) { super(); this.mm = mm; }
	 */

	int speed;

	public ItemPowerUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemPowerUp(Rectangle pos, int speed) {
		super();
		this.pos = pos;
		this.speed = speed;

	}

	public void move(/* MyMain mm */) {
		pos.x -= speed;
		/*
		 * for (DDong2 ddong2 : mm.ddong2List) { if (direction == 1) { pos.x +=
		 * speed; pos.y -= speed; if (pos.y < ddong2.pos2.y - 75) { pos.x -=
		 * speed * 2.5; pos.y += speed; }
		 * 
		 * } else if (direction == 2) { pos.x -= speed; pos.y -= speed; if
		 * (pos.y < ddong2.pos2.y - 150) { pos.x -= speed * 1.5; pos.y += speed;
		 * } } else if (direction == 3) { pos.x -= speed; pos.y += speed; if
		 * (pos.y > ddong2.pos2.y + 75) { pos.x -= speed * 1.5; pos.y -= speed;
		 * } } else if (direction == 4) { pos.x += speed; pos.y += speed; if
		 * (pos.y > ddong2.pos2.y + 150) { pos.x -= speed * 2.5; pos.y -= speed;
		 * } }
		 */

		/*
		 * if (direction == 1) { pos.x += speed; pos.y -= speed; } else if
		 * (direction == 2) { pos.x -= speed; pos.y -= speed; } else if
		 * (direction == 3) { pos.x -= speed; pos.y += speed; } else if
		 * (direction == 4) { pos.x += speed; pos.y += speed; }
		 */
		/*
		 * public void move_WS() { pos.x -= speed; pos.y += speed; }
		 * 
		 * public void move_EN() { pos.x += speed; pos.y += speed; }
		 * 
		 * public void move_ES() { pos.x += speed; pos.y -= speed; }
		 * 
		 * public void move_WN() { pos.x -= speed; pos.y -= speed; }
		 */
	}
}
