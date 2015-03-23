package myutil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

public class DDong5 {
	public int delay = 0;
	public int delay1 = 0;
	public int delay2 = 0;
	public int delay3 = 0;
	public int delay4 = 0;
	public int delay5 = 0;
	public int delay6 = 0;
	public int delay7 = 0;
	public Rectangle2D.Double pos;

	double speed;

	public DDong5() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong5(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
	
		pos.x -= speed;
		pos.y -= 0.4;
		delaytimer.start();
		if (delay == 1) {
			pos.x += speed;
			pos.y += 0.4;
			delay1 += 1;
		}
		if (delay1 >= 170) {
			pos.x += speed / 2;
			pos.y += speed / 2;
			delay1imer.start();
		}
		if (delay2 == 1) {
			pos.x -= speed / 2;
			pos.y -= speed / 2;
			// delay2imer.start();
			delay3 += 1;
		}
		// up & down loop
		if ((delay3 >= 100) || (delay7 >= 100)) {
			pos.y -= speed / 2;
			delay6 = 0;
			delay4 += 1;
		}
		if (delay4 >= 101) {
			pos.y += speed / 2;
			delay3 = 0;
			delay5 += 1;
		}
		if (delay5 >= 1) {
			pos.y += speed / 2;
			delay4 = 0;
			delay6 += 1;
		}
		if (delay6 >= 100) {
			pos.y -= speed / 2;
			delay5 = 0;
			delay7 += 1;
		}

	}

	public void move1() {
		pos.y -= 5;
	}

	ActionListener delaylistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay = 1;
			delaytimer.stop();
		}
	};
	Timer delaytimer = new Timer(4000, delaylistener);
	ActionListener delay_nextlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay2 = 1;

			delay1imer.stop();
		}
	};
	Timer delay1imer = new Timer(1600, delay_nextlistener);
	ActionListener delay2_nextlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay3 = 1;

			delay2imer.stop();
		}
	};
	Timer delay2imer = new Timer(1600, delay2_nextlistener);
}
