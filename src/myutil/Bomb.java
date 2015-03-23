package myutil;

public class Bomb {
	final static int bomb_interval = 5;
	// 폭발위치
	public double x, y;
	public int index;// 0~22;
	int interver = bomb_interval;

	public Bomb() {
	}

	public Bomb(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	// 이미지 index 증가
	public boolean move() {
		if (interver == bomb_interval)
			index++;
		interver--;
		if (interver < 0)
			interver = bomb_interval;
		return (index < 27);

	}
}
