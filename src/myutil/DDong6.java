package myutil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

public class DDong6 {
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

	public DDong6() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong6(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {
		pos.x -= speed;

		delaytimer.start();
		if (delay == 1) {
			pos.x += speed;
			delay1imer.start();
		}
		if (delay1 == 1) {
			pos.x -= speed * 7;
			delay2++;
		}
		if (delay2 >= 45) {
			pos.x += speed * 8;
			delay3++;
		}
		if (delay3 >= 300) {
			pos.x -= speed;
			delay4++;
		}
		if (delay4 >= 100) {
			pos.y -= speed / 2;
			delay7 = 0;
			delay5 += 1;
		}
		if (delay5 >= 100) {
			pos.y += speed / 2;
			delay4 = 0;
			delay6 += 1;
		}
		if (delay6 >= 1) {
			pos.y += speed / 2;
			delay5 = 0;
			delay7 += 1;
		}
		if (delay7 >= 100) {
			pos.y -= speed / 2;
			delay6 = 0;

			delay2imer.start();
		}
	}

	ActionListener delaylistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
				delay = 1;
			delaytimer.stop();
		}
	};
	Timer delaytimer = new Timer(2400, delaylistener);

	ActionListener delay_nextlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			delay1 = 1;

			delay1imer.stop();
		}
	};
	Timer delay1imer = new Timer(1600, delay_nextlistener);
	ActionListener delay2_nextlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			delay1 = 0;
			delay2 = 0;
			delay3 = 0;
			delay4 = 0;
			delay5 = 0;
			delay6 = 0;
			delay7 = 0;
			// delay8 = 0;
			delay2imer.stop();
		}
	};
	Timer delay2imer = new Timer(6520, delay2_nextlistener);
	public void move1(){
		pos.x+=5;
	}
}
