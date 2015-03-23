package myutil;

public class ImgMove {
	public int x, y;

	final static int bomb_interval = 1;
	int interver = bomb_interval;
	public int index_charge;// 0~22;

	final static int INV_INTERVAL = 5;
	int invinterver = INV_INTERVAL;
	public int inv_index;

	final static double CE_INTERVAL = 0.5;
	double ceinterver = CE_INTERVAL;
	public int ce_index;

	final static int BE_INTERVAL = 10;
	int beinterver = BE_INTERVAL;
	public int be_index;

	public ImgMove() {
	}

	public ImgMove(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	// 이미지 index 증가
	public boolean move() {
		if (interver == bomb_interval)
			index_charge++;
		interver--;
		if (interver < 0)
			interver = bomb_interval;
		return (index_charge < 14);

	}

	public boolean move_inv() {
		if (invinterver == INV_INTERVAL)
			inv_index++;
		invinterver--;
		if (invinterver < 0)
			invinterver = INV_INTERVAL;
		return (inv_index < 4);

	}

	public boolean move_ce() {
		if (ceinterver == CE_INTERVAL)
			ce_index++;
		ceinterver--;
		if (ceinterver < 0)
			ceinterver = CE_INTERVAL;
		return (ce_index < 5);

	}

	public boolean move_be() {
		if (beinterver == BE_INTERVAL)
			be_index++;
		beinterver--;
		if (beinterver < 0)
			beinterver = BE_INTERVAL;
		return (be_index < 31);

	}
}
