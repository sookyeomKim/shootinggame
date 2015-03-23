package myutil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

public class DDong4 {
	public int delay = 0;
	public int delay_2 = 0;
	public int delay_next = 0;
	public int delay_2_next = 0;

	public Rectangle2D.Double pos;

	double speed;

	public DDong4() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong4(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		pos.x -= speed;
		delaytimer.start();
		if (delay == 1) {
			pos.x += speed;
			delay_nexttimer.start();
		}
		if (delay_next == 1) {
			pos.x -= speed;
			pos.y -= 0.2;
		}

	}

	public void move_2() {
		pos.x -= speed;
		delay_2timer.start();
		if (delay_2 == 1) {
			pos.x += speed;
			delay_2_nexttimer.start();
		}
		if (delay_2_next == 1) {
			pos.x -= speed;
			pos.y += 0.4;
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
	Timer delaytimer = new Timer(2000, delaylistener);
	ActionListener delay_2listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay_2 = 1;
			delay_2timer.stop();
		}
	};
	Timer delay_2timer = new Timer(1400, delay_2listener);

	ActionListener delay_nextlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay_next = 1;

			delay_nexttimer.stop();
		}
	};
	Timer delay_nexttimer = new Timer(15000, delay_nextlistener);
	ActionListener delay_2_nextlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay_2_next = 1;
			delay_2_nexttimer.stop();
		}
	};
	Timer delay_2_nexttimer = new Timer(18000, delay_2_nextlistener);
}
