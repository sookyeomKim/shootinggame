package myutil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

public class DDong7 {
	public int a_b_count = 0;
	public int delay = 0;
	public int delay1 = 0;
	public int delay2 = 0;
	public int delay3 = 0;
	public int delay4 = 0;
	public int delay5 = 0;
	public int delay6 = 0;
	public int delay7 = 0;
	public int delay8 = 0;
	public Rectangle2D.Double pos;

	double speed;

	public DDong7() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong7(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		pos.x -= speed;

		delaytimer.start();
		if (delay == 1) {
			pos.x += speed;
				delay4++;
		}
		if (delay4 >= 40) {
			pos.y -= speed / 2;
			delay7 = 0;
			delay5 += 1;
		}
		if (delay5 >= 40) {
			pos.y += speed / 2;
			delay4 = 0;
			delay6 += 1;
		}
		if (delay6 >= 1) {
			pos.y += speed / 2;
			delay5 = 0;
			delay7 += 1;
		}
		if (delay7 >= 40) {
			pos.y -= speed / 2;
			delay6 = 0;
		
		}
			}

	

	ActionListener delaylistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay = 1;
			delaytimer.stop();
		}
	};
	Timer delaytimer = new Timer(2400, delaylistener);

	
}
