package myutil;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Random;

public class DDong7Gun {
	Random ra = new Random();
	int direction_count = ra.nextInt(2);
	
	public Rectangle2D.Double pos;

	double speed;

	public DDong7Gun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DDong7Gun(Double pos) {
		super();
		this.pos = pos;
	}

	public DDong7Gun(Rectangle2D.Double pos, double speed) {
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
		pos.y += 1;

	}

	// TODO 12시시작
	public void move4_1() {
		pos.x -= 6.6;
		pos.y -= 2;

	}

	public void move4_2() {
		pos.x -= 6.9;
		pos.y -= 1;
	}

	public void move4_3() {
		pos.x -= speed;

	}

	public void move4_4() {
		pos.x -= 6.9;
		pos.y += 1;
	}

	public void move4_5() {
		pos.x -= 6.6;
		pos.y += 2;
	}

	public void move4_6() {
		pos.x -= 6.25;
		pos.y += 3;
	}

	public void move4_7() {
		pos.x -= 5.7;
		pos.y += 4;
	}

	public void move4_8() {
		pos.x -= 5;
		pos.y += 5;
	}

	public void move4_9() {
		pos.x -= 4;
		pos.y += 5.7;
	}

	public void move4_10() {
		pos.x -= 3;
		pos.y += 6.25;
	}

	public void move4_11() {
		pos.x -= 2;
		pos.y += 6.6;
	}

	public void move4_12() {
		pos.x -= 1;
		pos.y += 6.9;
	}

	public void move4_13() {
		pos.y += speed;
	}

	public void move4_14() {
		pos.x += 1;
		pos.y += 6.9;
	}

	public void move4_15() {
		pos.x += 2;
		pos.y += 6.6;
	}

	public void move4_16() {
		pos.x += 3;
		pos.y += 6.25;
	}

	public void move4_17() {
		pos.x += 4;
		pos.y += 5.7;
	}

	public void move4_18() {
		pos.x += 5;
		pos.y += 5;
	}

	public void move4_19() {
		pos.x += 5.7;
		pos.y += 4;
	}

	public void move4_20() {
		pos.x += 6.25;
		pos.y += 3;

	}

	public void move4_21() {
		pos.x += 6.6;
		pos.y += 2;

	}

	public void move4_22() {
		pos.x += 6.9;
		pos.y += 1;

	}

	public void move4_23() {
		pos.x += speed;
	}

	public void move4_24() {
		pos.x += 6.9;
		pos.y -= 1;
	}

	public void move4_25() {
		pos.x += 6.6;
		pos.y -= 2;
	}

	public void move4_26() {
		pos.x += 6.25;
		pos.y -= 3;
	}

	public void move4_27() {
		pos.x += 5.7;
		pos.y -= 4;
	}

	public void move4_28() {
		pos.x += 5;
		pos.y -= 5;
	}

	public void move4_29() {
		pos.x += 4;
		pos.y -= 5.7;
	}

	public void move4_30() {
		pos.x += 3;
		pos.y -= 6.25;
	}

	public void move4_31() {
		pos.x += 2;
		pos.y -= 6.6;
	}

	public void move4_32() {
		pos.x += 1;
		pos.y -= 6.9;
	}

	public void move4_33() {
		pos.y -= speed;
	}

	public void move4_34() {
		pos.x -= 1;
		pos.y -= 6.9;
	}
	public void move4_35() {
		pos.x -= 2;
		pos.y -= 6.6;

	}

	public void move4_36() {
		pos.x -= 3;
		pos.y -= 6.25;

	}

	public void move4_37() {
		pos.x -= 4;
		pos.y -= 5.7;

	}

	public void move4_38() {
		pos.x -= 5;
		pos.y -= 5;

	}

	public void move4_39() {
		pos.x -= 5.7;
		pos.y -= 4;

	}

	public void move4_40() {
		pos.x -= 6.25;
		pos.y -= 3;

	}

	// TODO 9시 시작
	public void move4_1_1() {
		pos.x -= 2;
		pos.y += 6.6;
	}

	public void move4_1_2() {
		pos.x -= 1;
		pos.y += 6.9;
	}

	public void move4_1_3() {
		pos.y += speed;
	}

	public void move4_1_4() {
		pos.x += 1;
		pos.y += 6.9;
	}

	public void move4_1_5() {
		pos.x += 2;
		pos.y += 6.6;
	}

	public void move4_1_6() {
		pos.x += 3;
		pos.y += 6.25;
	}

	public void move4_1_7() {
		pos.x += 4;
		pos.y += 5.7;
	}

	public void move4_1_8() {
		pos.x += 5;
		pos.y += 5;
	}

	public void move4_1_9() {
		pos.x += 5.7;
		pos.y += 4;
	}

	public void move4_1_10() {
		pos.x += 6.25;
		pos.y += 3;

	}

	public void move4_1_11() {
		pos.x += 6.6;
		pos.y += 2;

	}

	public void move4_1_12() {
		pos.x += 6.9;
		pos.y += 1;

	}

	public void move4_1_13() {
		pos.x += speed;
	}

	public void move4_1_14() {
		pos.x += 6.9;
		pos.y -= 1;
	}

	public void move4_1_15() {
		pos.x += 6.6;
		pos.y -= 2;
	}

	public void move4_1_16() {
		pos.x += 6.25;
		pos.y -= 3;
	}

	public void move4_1_17() {
		pos.x += 5.7;
		pos.y -= 4;
	}

	public void move4_1_18() {
		pos.x += 5;
		pos.y -= 5;
	}

	public void move4_1_19() {
		pos.x += 4;
		pos.y -= 5.7;
	}

	public void move4_1_20() {
		pos.x += 3;
		pos.y -= 6.25;
	}

	public void move4_1_21() {
		pos.x += 2;
		pos.y -= 6.6;
	}

	public void move4_1_22() {
		pos.x += 1;
		pos.y -= 6.9;
	}

	public void move4_1_23() {
		pos.y -= speed;
	}

	public void move4_1_24() {
		pos.x -= 1;
		pos.y -= 6.9;
	}

	public void move4_1_25() {
		pos.x -= 2;
		pos.y -= 6.6;

	}

	public void move4_1_26() {
		pos.x -= 3;
		pos.y -= 6.25;

	}

	public void move4_1_27() {
		pos.x -= 4;
		pos.y -= 5.7;

	}

	public void move4_1_28() {
		pos.x -= 5;
		pos.y -= 5;

	}

	public void move4_1_29() {
		pos.x -= 5.7;
		pos.y -= 4;

	}

	public void move4_1_30() {
		pos.x -= 6.25;
		pos.y -= 3;

	}

	public void move4_1_31() {
		pos.x -= 6.6;
		pos.y -= 2;

	}

	public void move4_1_32() {
		pos.x -= 6.9;
		pos.y -= 1;
	}

	public void move4_1_33() {
		pos.x -= speed;

	}

	public void move4_1_34() {
		pos.x -= 6.9;
		pos.y += 1;
	}

	public void move4_1_35() {
		pos.x -= 6.6;
		pos.y += 2;
	}

	public void move4_1_36() {
		pos.x -= 6.25;
		pos.y += 3;
	}

	public void move4_1_37() {
		pos.x -= 5.7;
		pos.y += 4;
	}

	public void move4_1_38() {
		pos.x -= 5;
		pos.y += 5;
	}

	public void move4_1_39() {
		pos.x -= 4;
		pos.y += 5.7;
	}

	public void move4_1_40() {
		pos.x -= 3;
		pos.y += 6.25;
	}

	// TODO 6시 시작
	public void move4_2_1() {
		pos.x += 6.6;
		pos.y += 2;

	}

	public void move4_2_2() {
		pos.x += 6.9;
		pos.y += 1;

	}

	public void move4_2_3() {
		pos.x += speed;
	}

	public void move4_2_4() {
		pos.x += 6.9;
		pos.y -= 1;
	}

	public void move4_2_5() {
		pos.x += 6.6;
		pos.y -= 2;
	}

	public void move4_2_6() {
		pos.x += 6.25;
		pos.y -= 3;
	}

	public void move4_2_7() {
		pos.x += 5.7;
		pos.y -= 4;
	}

	public void move4_2_8() {
		pos.x += 5;
		pos.y -= 5;
	}

	public void move4_2_9() {
		pos.x += 4;
		pos.y -= 5.7;
	}

	public void move4_2_10() {
		pos.x += 3;
		pos.y -= 6.25;
	}

	public void move4_2_11() {
		pos.x += 2;
		pos.y -= 6.6;
	}

	public void move4_2_12() {
		pos.x += 1;
		pos.y -= 6.9;
	}

	public void move4_2_13() {
		pos.y -= speed;
	}

	public void move4_2_14() {
		pos.x -= 1;
		pos.y -= 6.9;
	}

	public void move4_2_15() {
		pos.x -= 2;
		pos.y -= 6.6;

	}

	public void move4_2_16() {
		pos.x -= 3;
		pos.y -= 6.25;

	}

	public void move4_2_17() {
		pos.x -= 4;
		pos.y -= 5.7;

	}

	public void move4_2_18() {
		pos.x -= 5;
		pos.y -= 5;

	}

	public void move4_2_19() {
		pos.x -= 5.7;
		pos.y -= 4;

	}

	public void move4_2_20() {
		pos.x -= 6.25;
		pos.y -= 3;

	}

	public void move4_2_21() {
		pos.x -= 6.6;
		pos.y -= 2;

	}

	public void move4_2_22() {
		pos.x -= 6.9;
		pos.y -= 1;
	}

	public void move4_2_23() {
		pos.x -= speed;

	}

	public void move4_2_24() {
		pos.x -= 6.9;
		pos.y += 1;
	}

	public void move4_2_25() {
		pos.x -= 6.6;
		pos.y += 2;
	}

	public void move4_2_26() {
		pos.x -= 6.25;
		pos.y += 3;
	}

	public void move4_2_27() {
		pos.x -= 5.7;
		pos.y += 4;
	}

	public void move4_2_28() {
		pos.x -= 5;
		pos.y += 5;
	}

	public void move4_2_29() {
		pos.x -= 4;
		pos.y += 5.7;
	}

	public void move4_2_30() {
		pos.x -= 3;
		pos.y += 6.25;
	}

	public void move4_2_31() {
		pos.x -= 2;
		pos.y += 6.6;
	}

	public void move4_2_32() {
		pos.x -= 1;
		pos.y += 6.9;
	}

	public void move4_2_33() {
		pos.y += speed;
	}

	public void move4_2_34() {
		pos.x += 1;
		pos.y += 6.9;
	}

	public void move4_2_35() {
		pos.x += 2;
		pos.y += 6.6;
	}

	public void move4_2_36() {
		pos.x += 3;
		pos.y += 6.25;
	}

	public void move4_2_37() {
		pos.x += 4;
		pos.y += 5.7;
	}

	public void move4_2_38() {
		pos.x += 5;
		pos.y += 5;
	}

	public void move4_2_39() {
		pos.x += 5.7;
		pos.y += 4;
	}

	public void move4_2_40() {
		pos.x += 6.25;
		pos.y += 3;

	}

	//TODO 3시방향
	public void move4_3_1() {
		pos.x += 2;
		pos.y -= 6.6;
	}

	public void move4_3_2() {
		pos.x += 1;
		pos.y -= 6.9;
	}

	public void move4_3_3() {
		pos.y -= speed;
	}

	public void move4_3_4() {
		pos.x -= 1;
		pos.y -= 6.9;
	}

	public void move4_3_5() {
		pos.x -= 2;
		pos.y -= 6.6;

	}

	public void move4_3_6() {
		pos.x -= 3;
		pos.y -= 6.25;

	}

	public void move4_3_7() {
		pos.x -= 4;
		pos.y -= 5.7;

	}

	public void move4_3_8() {
		pos.x -= 5;
		pos.y -= 5;

	}

	public void move4_3_9() {
		pos.x -= 5.7;
		pos.y -= 4;

	}

	public void move4_3_10() {
		pos.x -= 6.25;
		pos.y -= 3;

	}

	public void move4_3_11() {
		pos.x -= 6.6;
		pos.y -= 2;

	}

	public void move4_3_12() {
		pos.x -= 6.9;
		pos.y -= 1;
	}

	public void move4_3_13() {
		pos.x -= speed;

	}

	public void move4_3_14() {
		pos.x -= 6.9;
		pos.y += 1;
	}

	public void move4_3_15() {
		pos.x -= 6.6;
		pos.y += 2;
	}

	public void move4_3_16() {
		pos.x -= 6.25;
		pos.y += 3;
	}

	public void move4_3_17() {
		pos.x -= 5.7;
		pos.y += 4;
	}

	public void move4_3_18() {
		pos.x -= 5;
		pos.y += 5;
	}

	public void move4_3_19() {
		pos.x -= 4;
		pos.y += 5.7;
	}

	public void move4_3_20() {
		pos.x -= 3;
		pos.y += 6.25;
	}

	public void move4_3_21() {
		pos.x -= 2;
		pos.y += 6.6;
	}

	public void move4_3_22() {
		pos.x -= 1;
		pos.y += 6.9;
	}

	public void move4_3_23() {
		pos.y += speed;
	}

	public void move4_3_24() {
		pos.x += 1;
		pos.y += 6.9;
	}

	public void move4_3_25() {
		pos.x += 2;
		pos.y += 6.6;
	}

	public void move4_3_26() {
		pos.x += 3;
		pos.y += 6.25;
	}

	public void move4_3_27() {
		pos.x += 4;
		pos.y += 5.7;
	}

	public void move4_3_28() {
		pos.x += 5;
		pos.y += 5;
	}

	public void move4_3_29() {
		pos.x += 5.7;
		pos.y += 4;
	}

	public void move4_3_30() {
		pos.x += 6.25;
		pos.y += 3;

	}

	public void move4_3_31() {
		pos.x += 6.6;
		pos.y += 2;

	}

	public void move4_3_32() {
		pos.x += 6.9;
		pos.y += 1;

	}

	public void move4_3_33() {
		pos.x += speed;
	}

	public void move4_3_34() {
		pos.x += 6.9;
		pos.y -= 1;
	}

	public void move4_3_35() {
		pos.x += 6.6;
		pos.y -= 2;
	}

	public void move4_3_36() {
		pos.x += 6.25;
		pos.y -= 3;
	}

	public void move4_3_37() {
		pos.x += 5.7;
		pos.y -= 4;
	}

	public void move4_3_38() {
		pos.x += 5;
		pos.y -= 5;
	}

	public void move4_3_39() {
		pos.x += 4;
		pos.y -= 5.7;
	}

	public void move4_3_40() {
		pos.x += 3;
		pos.y -= 6.25;
	}

	//TODO 12시 시작 시계방향

	public void move4_4_40() {
		pos.x -= 2;
		pos.y -= 6.6;

	}

	public void move4_4_39() {
		pos.x -= 3;
		pos.y -= 6.25;

	}

	public void move4_4_38() {
		pos.x -= 4;
		pos.y -= 5.7;

	}

	public void move4_4_37() {
		pos.x -= 5;
		pos.y -= 5;

	}

	public void move4_4_36() {
		pos.x -= 5.7;
		pos.y -= 4;

	}

	public void move4_4_35() {
		pos.x -= 6.25;
		pos.y -= 3;

	}

	public void move4_4_34() {
		pos.x -= 6.6;
		pos.y -= 2;

	}

	public void move4_4_33() {
		pos.x -= 6.9;
		pos.y -= 1;
	}

	public void move4_4_32() {
		pos.x -= speed;

	}

	public void move4_4_31() {
		pos.x -= 6.9;
		pos.y += 1;
	}

	public void move4_4_30() {
		pos.x -= 6.6;
		pos.y += 2;
	}

	public void move4_4_29() {
		pos.x -= 6.25;
		pos.y += 3;
	}

	public void move4_4_28() {
		pos.x -= 5.7;
		pos.y += 4;
	}

	public void move4_4_27() {
		pos.x -= 5;
		pos.y += 5;
	}

	public void move4_4_26() {
		pos.x -= 4;
		pos.y += 5.7;
	}

	public void move4_4_25() {
		pos.x -= 3;
		pos.y += 6.25;
	}

	public void move4_4_24() {
		pos.x -= 2;
		pos.y += 6.6;
	}

	public void move4_4_23() {
		pos.x -= 1;
		pos.y += 6.9;
	}

	public void move4_4_22() {
		pos.y += speed;
	}

	public void move4_4_21() {
		pos.x += 1;
		pos.y += 6.9;
	}

	public void move4_4_20() {
		pos.x += 2;
		pos.y += 6.6;
	}

	public void move4_4_19() {
		pos.x += 3;
		pos.y += 6.25;
	}

	public void move4_4_18() {
		pos.x += 4;
		pos.y += 5.7;
	}

	public void move4_4_17() {
		pos.x += 5;
		pos.y += 5;
	}

	public void move4_4_16() {
		pos.x += 5.7;
		pos.y += 4;
	}

	public void move4_4_15() {
		pos.x += 6.25;
		pos.y += 3;

	}

	public void move4_4_14() {
		pos.x += 6.6;
		pos.y += 2;

	}

	public void move4_4_13() {
		pos.x += 6.9;
		pos.y += 1;

	}

	public void move4_4_12() {
		pos.x += speed;
	}

	public void move4_4_11() {
		pos.x += 6.9;
		pos.y -= 1;
	}

	public void move4_4_10() {
		pos.x += 6.6;
		pos.y -= 2;
	}

	public void move4_4_9() {
		pos.x += 6.25;
		pos.y -= 3;
	}

	public void move4_4_8() {
		pos.x += 5.7;
		pos.y -= 4;
	}

	public void move4_4_7() {
		pos.x += 5;
		pos.y -= 5;
	}

	public void move4_4_6() {
		pos.x += 4;
		pos.y -= 5.7;
	}

	public void move4_4_5() {
		pos.x += 3;
		pos.y -= 6.25;
	}

	public void move4_4_4() {
		pos.x += 2;
		pos.y -= 6.6;
	}

	public void move4_4_3() {
		pos.x += 1;
		pos.y -= 6.9;
	}

	public void move4_4_2() {
		pos.y -= speed;
	}

	public void move4_4_1() {
		pos.x -= 1;
		pos.y -= 6.9;
	}

}
