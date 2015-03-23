package myutil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.Timer;

public class DDong4Gun {
	Random ra = new Random();
	int direction_count = ra.nextInt(4);

	public int delay = 0;
	public int delay_next = 0;
	public Rectangle2D.Double pos;

	double speed;

	public DDong4Gun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong4Gun(Rectangle2D.Double pos, double speed) {
		super();
		this.pos = pos;
		this.speed = speed;
	}

	public void move() {

		if (direction_count == 0) {
			pos.x -= speed;
			pos.y += 0.45;
		}
		if (direction_count == 1) {
			pos.x -= speed;
			pos.y -= 0.45;
		}
		if (direction_count == 2) {
			pos.x -= speed;
			pos.y += 0.25;
		}
		if (direction_count == 3) {
			pos.x -= speed;
			pos.y -= 0.25;
		}
	}

	public void move_2() {

		if (direction_count == 0) {
			pos.x -= speed;
			pos.y += 0.45;
		}
		if (direction_count == 1) {
			pos.x -= speed;
			pos.y -= 0.45;
		}
		if (direction_count == 2) {
			pos.x -= speed;
			pos.y += 0.25;
		}
		if (direction_count == 3) {
			pos.x -= speed;
			pos.y -= 0.25;
		}

		/*
		 * delaytimer.start(); if (delay == 1) { //pos.x -= speed; pos.y += 1;
		 * delay_nexttimer.start(); } if (delay_next == 1) { //pos.x -= speed;
		 * pos.y -= 2; }
		 */
	}

	ActionListener delaylistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay = 1;
			delaytimer.stop();
		}
	};
	Timer delaytimer = new Timer(10, delaylistener);
	ActionListener delay_nextlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			delay_next = 1;
			delay_nexttimer.stop();
		}
	};
	Timer delay_nexttimer = new Timer(20, delay_nextlistener);

}
