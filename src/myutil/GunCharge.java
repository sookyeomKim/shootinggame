package myutil;

import java.awt.geom.Rectangle2D;

public class GunCharge {

	public Rectangle2D.Double pos;
	double speed;

	public GunCharge() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GunCharge(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		pos.x += speed;
	}
}
