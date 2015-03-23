package mymain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import myutil.Bomb;
import myutil.DDong;
import myutil.DDong2;
import myutil.DDong2Gun;
import myutil.DDong3;
import myutil.DDong3B;
import myutil.DDong3Gun;
import myutil.DDong4;
import myutil.DDong4Gun;
import myutil.DDong5;
import myutil.DDong5Gun;
import myutil.DDong6;
import myutil.DDong6Gun;
import myutil.DDong7;
import myutil.DDong7Gun;
import myutil.DDongGun;
import myutil.Gun;
import myutil.GunCharge;
import myutil.ImgMove;
import myutil.ItemPowerUp;
import myutil.Life;

@SuppressWarnings("serial")
public class MyMain extends JFrame {
	// TODO 상수
	// 객체생성
	public JPanel gamePanel;
	public JPanel startgamePanel;
	public Random ra = new Random();

	// 게임판의 크기설정상수
	public static final int GAMEPAN_W = 1024;
	public static final int GAMEPAN_H = 576;

	// 백그라운드 상수
	public double offset_x = 0;
	// 이미지 초기화
	public static Image img_start, img_ball, img_bar, img_base, img_hp,
			img_powerup, img_engel, img_invincible1, img_enemy2, img_enemy3,
			img_enemy3_1, img_enemy4, img_enemy4_2, img_missile1, img_missile2,
			img_missile3, img_missile4, img_missile5, img_background,
			img_buboss, img_boss1, img_boss2;
	public static Image[] img_bomb, img_charge, img_gage, img_chargegage,
			img_invincible, img_ce, img_be;// 폭발이미지

	static {
		img_start = new ImageIcon("image/start.png").getImage();

		img_engel = new ImageIcon("image/engel.png").getImage();
		img_invincible1 = new ImageIcon("image/invincible1.gif").getImage();
		img_ball = new ImageIcon("image/ball.gif").getImage();
		img_bar = new ImageIcon("image/bar.png").getImage();
		img_base = new ImageIcon("image/base.png").getImage();
		img_hp = new ImageIcon("image/hp.png").getImage();
		img_powerup = new ImageIcon("image/powerup.png").getImage();

		img_enemy2 = new ImageIcon("image/enemy2.png").getImage();
		img_enemy3 = new ImageIcon("image/enemy3.png").getImage();
		img_enemy3_1 = new ImageIcon("image/enemy3_1.png").getImage();
		img_enemy4 = new ImageIcon("image/enemy4.png").getImage();
		img_enemy4_2 = new ImageIcon("image/enemy4_2.png").getImage();

		img_missile1 = new ImageIcon("image/missile1.png").getImage();
		img_missile2 = new ImageIcon("image/missile2.png").getImage();
		img_missile3 = new ImageIcon("image/missile3.png").getImage();
		img_missile4 = new ImageIcon("image/missile4.png").getImage();
		img_missile5 = new ImageIcon("image/missile5.png").getImage();

		img_buboss = new ImageIcon("image/buboss.png").getImage();
		img_boss1 = new ImageIcon("image/boss1.png").getImage();
		img_boss2 = new ImageIcon("image/boss2.png").getImage();

		img_background = new ImageIcon("image/background.png").getImage();

		img_invincible = new Image[4];
		for (int i = 0; i < img_invincible.length; i++) {
			img_invincible[i] = new ImageIcon("image/invincible/a_" + i
					+ ".png").getImage();
		}
		img_ce = new Image[5];
		for (int i = 0; i < img_ce.length; i++) {
			img_ce[i] = new ImageIcon("image/ce/item_e_" + i + ".png")
					.getImage();
		}
		img_be = new Image[31];
		for (int i = 0; i < img_be.length; i++) {
			img_be[i] = new ImageIcon("image/be/bd_" + i + ".png").getImage();
		}
		img_gage = new Image[5];
		for (int i = 0; i < img_gage.length; i++) {
			img_gage[i] = new ImageIcon("image/player_gage/gage_" + i + ".jpg")
					.getImage();
		}
		img_bomb = new Image[27];
		for (int i = 0; i < img_bomb.length; i++) {
			String filename = String.format("image/bomb/m%02d.png", i + 1);
			img_bomb[i] = new ImageIcon(filename).getImage();
		}
		img_charge = new Image[14];
		for (int i = 0; i < img_charge.length; i++) {
			String filename = String.format("image/charge/d%02d.png", i + 1);
			img_charge[i] = new ImageIcon(filename).getImage();
		}
		img_chargegage = new Image[3];
		for (int i = 0; i < img_chargegage.length; i++) {
			img_chargegage[i] = new ImageIcon("image/chargegage/charge_" + i
					+ ".png").getImage();
		}

	}

	// 타이머
	public Timer timer;

	// UI
	public Rectangle me_rect, base_rect;

	// 키관련
	public int key;// 눌린 키값
	public boolean bPressed = false;// 플레이어 움직임
	public boolean gun_Pressed = false;// 플레이어총

	public int gunCharge_attack = 1;// 기모으기 발사!_!
	public int gunCharge_if = 0;// 기모으기발동조건

	// 키상태 상수
	static final int K_LEFT = 0x00001;
	static final int K_RIGHT = 0x00010;
	static final int K_UP = 0x00100;
	static final int K_DOWN = 0x01000;
	static final int GUN = 0x10000;

	// 키상태변수
	int key_state = 0;
	int gan = 4;

	// 폭탄 리스트 관리 객체
	public List<Bomb> bombList = new ArrayList<Bomb>();

	// 기모으기 이미지 리스트 관리 객체
	public List<ImgMove> imgchargeList = new ArrayList<ImgMove>();

	// =====================================똥================================================
	// 똥크기 상수
	public final double DDONG_W = 30;// 다 만들고 위로 빼져
	public final double DDONG_H = 30;
	// 똥통
	public List<DDong> ddongList = new ArrayList<DDong>();
	// 똥 만드는 간격
	public final static int DDONG_MAKE_INTERVAL = 140;
	// 똥 만드는 간격을 관리하는 변수
	public int ddong_make_interval = DDONG_MAKE_INTERVAL;

	// 똥총 크기 상수
	public final int DDONGGUN_W = 15;
	public final int DDONGGUN_H = 15;
	// 똥총 통
	public List<DDongGun> ddonggunList = new ArrayList<DDongGun>();
	// 똥총 간격
	public final static int DDONG_GUN_MAKE_INTERVAL = 155;
	public int ddong_gun_make_interval = DDONG_GUN_MAKE_INTERVAL;
	// ---------------------------------------2-------------------------------------------
	// 똥2크기 상수
	public final int DDONG2_W = 40;// 다 만들고 위로 빼져
	public final int DDONG2_H = 40;
	// 똥2통
	public List<DDong2> ddong2List = new ArrayList<DDong2>();
	// 똥2 만드는 간격
	public final static int DDONG2_MAKE_INTERVAL = 80;
	// 똥2 만드는 간격을 관리하는 변수
	public int ddong2_make_interval = DDONG2_MAKE_INTERVAL;

	// 똥2총 크기 상수
	public final int DDONGGUN2_W = 15;
	public final int DDONGGUN2_H = 15;
	// 똥2총 통
	public List<DDong2Gun> ddong2gunList = new ArrayList<DDong2Gun>();
	// 똥2총 간격
	public final static int DDONG2_GUN_MAKE_INTERVAL = 15;
	public int ddong2_gun_make_interval = DDONG2_GUN_MAKE_INTERVAL;
	public int ddong2gun_interval_count = 0;
	// -------------------------------------3---------------------------------------------
	// 똥3크기 상수
	public final int DDONG3_W = 30;// 다 만들고 위로 빼져
	public final int DDONG3_H = 30;
	// 똥3통
	public List<DDong3> ddong3List = new ArrayList<DDong3>();
	// 똥3 만드는 간격
	public final static int DDONG3_MAKE_INTERVAL = 10;
	// 똥3 만드는 간격을 관리하는 변수
	public int ddong3_make_interval = DDONG3_MAKE_INTERVAL;

	// 똥3총 크기 상수
	public final int DDONGGUN3_W = 15;
	public final int DDONGGUN3_H = 15;
	// 똥3총 통
	public List<DDong3Gun> ddong3gunList = new ArrayList<DDong3Gun>();
	// 똥3총 간격
	public final static int DDONG3_GUN_MAKE_INTERVAL = 1;
	public int ddong3_gun_make_interval = DDONG3_GUN_MAKE_INTERVAL;
	public int ddong3_interval_count = 0;
	public int direction_count = 0;
	public int ddong3a_b_count = 0;
	public int ddong3_1a_b_count = 0;
	public int ddong3_start_count = 0;
	public int ddong3_gun_count = 0;
	public int ddong3_gun_start_count = 0;
	// ----------------------------------------3B---------------------------------
	// 똥3크기 상수
	public final int DDONG3B_W = 30;// 다 만들고 위로 빼져
	public final int DDONG3B_H = 30;
	// 똥3통
	public List<DDong3B> ddong3BList = new ArrayList<DDong3B>();
	// 똥3 만드는 간격
	public final static int DDONG3B_MAKE_INTERVAL = 10;
	// 똥3 만드는 간격을 관리하는 변수
	public int ddong3B_make_interval = DDONG3B_MAKE_INTERVAL;
	// ------------------------------------4----------------------------------
	// 똥크기 상수
	public final double DDONG4_W = 100;// 다 만들고 위로 빼져
	public final double DDONG4_H = 100;
	// 똥통
	public List<DDong4> ddong4List = new ArrayList<DDong4>();
	public List<DDong4> ddong4_2List = new ArrayList<DDong4>();
	// 똥 만드는 간격
	public final static int DDONG4_MAKE_INTERVAL = 1;
	// 똥 만드는 간격을 관리하는 변수
	public int ddong4_make_interval = DDONG4_MAKE_INTERVAL;
	public final static int DDONG4_2_MAKE_INTERVAL = 1;
	// 똥 만드는 간격을 관리하는 변수
	public int ddong4_2_make_interval = 0;
	// 똥4스피드
	public double ddong4_speed = 2;

	// 똥4총 크기 상수
	public final int DDONGGUN4_W = 35;
	public final int DDONGGUN4_H = 35;
	// 똥4총 통
	public List<DDong4Gun> ddong4gunList = new ArrayList<DDong4Gun>();
	public List<DDong4Gun> ddong4_2gunList = new ArrayList<DDong4Gun>();
	// 똥4총 간격
	public final static int DDONG4_GUN_MAKE_INTERVAL = 10;
	public int ddong4_gun_make_interval = DDONG4_GUN_MAKE_INTERVAL;
	// 똥4_2총 간격
	public final static int DDONG4_2_GUN_MAKE_INTERVAL = 10;
	public int ddong4_2_gun_make_interval = DDONG4_2_GUN_MAKE_INTERVAL;

	public int ddong4_start_count = 0;
	public int ddong4_interval_count = 0;
	public int ddong4_delay_count = 0;

	public int ddong4_gun_start_count = 0;
	public int ddong4_2_gun_start_count = 0;

	public int ddong4_gun_interval_count = 0;
	public int ddong4_2_gun_interval_count = 0;
	public int ddong4_gun_end_count = 0;
	public int ddong4_2_gun_end_count = 0;
	// ----------------------------------5----------------------------------
	// 똥5크기 상수
	public final double DDONG5_W = 252;
	public final double DDONG5_H = 261;
	// 똥5통
	public List<DDong5> ddong5List = new ArrayList<DDong5>();
	// 똥5 만드는 간격
	public final static int DDONG5_MAKE_INTERVAL = 1;
	// 똥5 만드는 간격을 관리하는 변수
	public int ddong5_make_interval = DDONG5_MAKE_INTERVAL;
	public int ddong5_start_count = 0;
	public int ddong5_interval_count = 0;

	public boolean move_stop_d5 = true;
	// 똥5총 크기 상수
	public final int DDONGGUN5_W = 20;
	public final int DDONGGUN5_H = 20;

	public final int DDONGGUN5_2_W = 37;
	public final int DDONGGUN5_2_H = 37;
	// 5총 통
	public List<DDong5Gun> ddong5_1gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_2gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_3gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_4gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_5gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_6gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_7gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_8gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_9gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_10gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_11gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_12gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_13gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_14gunList = new ArrayList<DDong5Gun>();
	public List<DDong5Gun> ddong5_15gunList = new ArrayList<DDong5Gun>();
	// 똥5총 간격
	public final static int DDONG5_1_GUN_MAKE_INTERVAL = 1;
	public int ddong5_1gun_make_interval = DDONG5_1_GUN_MAKE_INTERVAL;

	public int ddong5_1gun_start_count = 0;
	public int ddong5_1gun_interval_count = 0;

	public final static int DDONG5_2_GUN_MAKE_INTERVAL = 10;
	public int ddong5_2gun_make_interval = DDONG5_2_GUN_MAKE_INTERVAL;

	public int ddong5_2gun_start_count = 0;
	public int ddong5_2gun_interval_count = 0;
	// -------------------------6-------------------------
	// 똥6크기 상수
	public final double DDONG6_W = 124;
	public final double DDONG6_H = 282;
	// 똥6통
	public List<DDong6> ddong6List = new ArrayList<DDong6>();
	// 똥6 만드는 간격
	public final static int DDONG6_MAKE_INTERVAL = 1;
	// 똥6 만드는 간격을 관리하는 변수
	public int ddong6_make_interval = DDONG6_MAKE_INTERVAL;
	public int ddong6_interval_count = 0;

	// 똥6총 크기 상수
	public final int DDONGGUN6_W = 20;
	public final int DDONGGUN6_H = 20;

	public final int DDONGGUN6_2_W = 37;
	public final int DDONGGUN6_2_H = 37;

	public boolean move_stop_d6 = true;
	// 6총 통
	public List<DDong6Gun> ddong6_1gunList = new ArrayList<DDong6Gun>();
	public List<DDong6Gun> ddong6_2gunList = new ArrayList<DDong6Gun>();
	public List<DDong6Gun> ddong6_3gunList = new ArrayList<DDong6Gun>();
	public List<DDong6Gun> ddong6_4gunList = new ArrayList<DDong6Gun>();

	// 똥6총 간격
	public final static int DDONG6_1_GUN_MAKE_INTERVAL = 5;
	public int ddong6_1gun_make_interval = DDONG6_1_GUN_MAKE_INTERVAL;

	public int ddong6_1gun_start_count = 0;
	public int ddong6_1gun_interval_count = 0;

	public final static int DDONG6_2_GUN_MAKE_INTERVAL = 1;
	public int ddong6_2gun_make_interval = DDONG6_2_GUN_MAKE_INTERVAL;

	public int ddong6_2gun_start_count = 0;
	public int ddong6_2gun_interval_count = 0;
	// ------------------------------7-----------------------------
	// 똥7크기 상수
	public final double DDONG7_W = 397;
	public final double DDONG7_H = 436;
	// 똥7통
	public List<DDong7> ddong7List = new ArrayList<DDong7>();
	// 똥7 만드는 간격
	public final static int DDONG7_MAKE_INTERVAL = 1;
	// 똥7 만드는 간격을 관리하는 변수
	public int ddong7_make_interval = DDONG7_MAKE_INTERVAL;
	public int ddong7_start_count = 0;
	public int ddong7_interval_count = 0;

	// 똥7총 크기 상수
	public final int DDONGGUN7_W = 20;
	public final int DDONGGUN7_H = 20;

	public final int DDONGGUN7_2_W = 37;
	public final int DDONGGUN7_2_H = 37;

	public final int DDONGGUN7_3_W = 67;
	public final int DDONGGUN7_3_H = 67;
	// 7총 통
	public List<DDong7Gun> ddong7_1gunList = new ArrayList<DDong7Gun>();
	public List<DDong7Gun> ddong7_2gunList = new ArrayList<DDong7Gun>();
	public List<DDong7Gun> ddong7_3gunList = new ArrayList<DDong7Gun>();
	public List<DDong7Gun> ddong7_4gunList = new ArrayList<DDong7Gun>();
	public List<DDong7Gun> ddong7_5gunList = new ArrayList<DDong7Gun>();
	public List<DDong7Gun> ddong7_6gunList = new ArrayList<DDong7Gun>();
	public List<DDong7Gun> ddong7_7gunList = new ArrayList<DDong7Gun>();
	public List<DDong7Gun> ddong7_8gunList = new ArrayList<DDong7Gun>();
	public List<DDong7Gun> ddong7_9gunList = new ArrayList<DDong7Gun>();

	// 똥7총 간격
	public final static int DDONG7_1_GUN_MAKE_INTERVAL = 1;
	public int ddong7_1gun_make_interval = DDONG7_1_GUN_MAKE_INTERVAL;

	public int ddong7_1gun_start_count = 0;
	public int ddong7_1gun_interval_count = 0;

	public final static int DDONG7_2_GUN_MAKE_INTERVAL = 4;
	public int ddong7_2gun_make_interval = DDONG7_2_GUN_MAKE_INTERVAL;

	public int ddong7_2gun_start_count = 0;
	public int ddong7_2gun_interval_count = 0;

	public final static int DDONG7_3_GUN_MAKE_INTERVAL = 70;
	public int ddong7_3gun_make_interval = 0;

	public int ddong7_3gun_start_count = 0;
	public int ddong7_3gun_interval_count = 0;

	public final static int DDONG7_4_GUN_MAKE_INTERVAL = 2;
	public int ddong7_4gun_make_interval = DDONG7_4_GUN_MAKE_INTERVAL;

	public int ddong7_4gun_start_count = 0;
	public int ddong7_4gun_interval_count = 0;

	public List<ImgMove> imgbeList = new ArrayList<ImgMove>();
	// ======================================================================================

	// =======================================아이템===============================================
	// 목숨크기
	public final int Life_W = 30;
	public final int Life_H = 30;
	// 라이프통
	public List<Life> lifeList = new ArrayList<Life>();
	// 목숨 만드는 간격
	public final int LIFE_MAKE_INTERVAL = 200;
	// 목숨 만드는 간격을 관리하는 변수
	public int life_make_interval = LIFE_MAKE_INTERVAL;

	// 파워업크기
	public final int POWERUP_W = 30;
	public final int POWERUP_H = 30;
	// 파워업통
	List<ItemPowerUp> poweruplist = new ArrayList<ItemPowerUp>();

	// =====================================플레이====================================
	public double player_hp = 150;
	public boolean invincible = false;
	public int invincible_count = 0;
	public int player_status = 0;
	public int gage_x = 10;
	public List<ImgMove> invinciblelist = new ArrayList<ImgMove>();
	// ================================================================================

	// ========================================플레이어총=============================================
	// 총알 크기
	public final double GUN_W = 50;
	public final double GUN_H = 10;
	// 총알2 크기
	public final double GUN2_W = 20;
	public final double GUN2_H = 7;
	// 총알집
	public List<Gun> gunlist = new ArrayList<Gun>();
	public List<Gun> gun2list = new ArrayList<Gun>();
	public List<Gun> gun3list = new ArrayList<Gun>();
	public List<Gun> gun4list = new ArrayList<Gun>();

	// 총알 간격
	public final static int GUN_MAKE_TNTERVAL = 8;
	// 목숨 만드는 간격을 관리하는 변수
	public int gun_make_interval = GUN_MAKE_TNTERVAL;

	public static int GUN_SPEED = 20;

	public int gun_levelup_count = 0;
	// ======================================================================================

	// ===================================차지===================================================
	public final int GUNSUPER_MAKE_INTERVAL = 1;
	public int gunsuper_make_interval = GUNSUPER_MAKE_INTERVAL;
	public final int GUNCHARGE_W = GAMEPAN_W;
	public final int GUNCHARGE_H = 60;
	public List<GunCharge> gunChargelist = new ArrayList<GunCharge>();
	public List<ImgMove> imgceList = new ArrayList<ImgMove>();

	public int charge_start_count = 0;
	public int charge_gage = 0;
	public int chargegage_x = 800;
	// ======================================================================================

	// ==================================몬스터hp====================================================
	public final static double DDONG_HP = 4;
	public double ddong_hp = DDONG_HP;
	public final static double DDONG2_HP = 10;
	public double ddong2_hp = DDONG2_HP;
	public final static double DDONG3_HP = 1;
	public double ddong3_hp = DDONG3_HP;
	public final static double DDONG4_HP = 200;
	public double ddong4_hp = DDONG4_HP;
	public final static double DDONG4_2_HP = 200;
	public double ddong4_2_hp = DDONG4_2_HP;
	public final static double DDONG5_HP = 1000;
	public double ddong5_hp = DDONG5_HP;
	public final static double DDONG6_HP = 1500;
	public double ddong6_hp = DDONG6_HP;
	public final static double DDONG7_HP = 2000;
	public double ddong7_hp = DDONG7_HP;

	// ======================================================================================

	// =========================================무기damege===============================
	public final double GUN_DAMEGE = 2;
	public final static double GUN_CHARGE_DAMEGE = 1.5;
	// ======================================================================================
	public int score = 0;
	public int timepush = 0;
	public int flow_count = 0;
	public int flow_control_count = 0;

	public MyMain() {
		// TODO main
		super("슈팅게임");// title
		// 차지기모으기 타이머
		gunchargetimer = new Timer(800, gunChargeListener);
		// 차지 유지시간타이머
		gunTimer = new Timer(6500, gunListener);
		guncharge_remove_timer = new Timer(1, remove_listener);

		// 똥2 총 인터벌타이머
		ddong2guntimer = new Timer(1000, ddong2gunlistener);

		// 똥3타이머
		ddong3_starttimer = new Timer(1000, ddong3_startlistener);
		ddong3_intervaltimer = new Timer(5000, ddong3listener);

		// 똥3총타이머
		ddong3_start_guntimer = new Timer(500, ddong3_gun_startlistener);

		// 똥4타이머
		ddong4_starttimer = new Timer(2500, ddong4_startlistener);
		ddong4_intervaltimer = new Timer(30000, ddong4listener);
		ddong4_delay_timer = new Timer(600, ddong4_delaylistener);

		// 똥4총타이머
		ddong4gun_start_timer = new Timer(2000, ddong4gun_start_listener);
		ddong4gun_interval_timer = new Timer(500, ddong4gun_interval_listener);
		ddong4_gun_end_timer = new Timer(13500, ddong4_gun_end_listener);

		ddong4_2gun_start_timer = new Timer(2500, ddong4_2gun_start_listener);
		ddong4_2gun_interval_timer = new Timer(1000,
				ddong4_2gun_interval_listener);
		ddong4_2_gun_end_timer = new Timer(14500, ddong4_2_gun_end_listener);

		// 똥5타이머
		ddong5_stop_timer = new Timer(20000, ddong5_stop_listener);

		// 똥5총 타이머
		ddong5_1gun_starttimer = new Timer(3300, ddong5_1gun_start_listener);
		ddong5_1gun_intervaltimer = new Timer(1300, ddong5gun_intervallistener);
		ddong5_2gun_starttimer = new Timer(6300, ddong5_2gun_start_listener);
		ddong5_2_intervaltimer = new Timer(1000, ddong5_2_intervallistener);

		// 똥6총 타이머
		ddong6_1gun_starttimer = new Timer(3770, ddong6_1gun_start_listener);
		ddong6_1gun_intervaltimer = new Timer(13760,
				ddong6_1gun_interval_listener);

		ddong6_2gun_starttimer = new Timer(7600, ddong6_2gun_start_listener);
		ddong6_2gun_intervaltimer = new Timer(1500,
				ddong6_2gun_interval_listener);

		// 똥7 타이머
		ddong7_starttimer = new Timer(5000, ddong7_start_listener);
		// 똥7총 타이머
		ddong7_1gun_intervaltimer = new Timer(1500,
				ddong7_1gun_interval_listener);

		ddong7_1gun_starttimer = new Timer(3000, ddong7_1gun_start_listener);
		ddong7_2gun_intervaltimer = new Timer(1000,
				ddong7_2gun_interval_listener);

		ddong7_3gun_intervaltimer = new Timer(1500,
				ddong7_3gun_interval_listener);
		ddong7_4gun_intervaltimer = new Timer(1, ddong7_4gun_interval_listener);

		// 게임흐름제어
		flow_timer = new Timer(1000, flow_listener);
		flow_2_timer = new Timer(3000, flow_2_listener);

		initGame();// ui단 rect설정
		initGamePanel();// 게임 내 모든 이미지 구현
		initMouseEvent();// 이벤트
		initKeyEvent();
		startGame();// 게임 내의 모든 연산

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width / 10, 0);
		setResizable(false);

		pack();

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void startGame() {
		// 타이머구동객체
		ActionListener timer_proc = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 필요한 연산작업
				process();

				// 화면 재구성
				gamePanel.repaint();// paintComponent Call
			}
		};

		// 타이머 구동 시간(1/1000초)
		timer = new Timer(10, timer_proc);
		timer.start();

	}

	// ================================================================================================
	// =====================================프로세스==================================================
	// ===============================================================================================
	protected void process() {
		// TODO process
		// =====================================똥=================================================

		if (flow_control_count == 2) {
			flow_count = 2;
		}
		if (flow_control_count == 3) {
			flow_count = 3;
		}
		if (flow_control_count == 5) {
			flow_count = 4;
		}

		switch (flow_count) {
		case 0:
			break;
		case 1:
			move_back();// 배경스크롤 시키기
			gun();// 플레이어총
			charge();// 차지
			collision_me_energy();// 플레이어충돌
			life();// 회복아이템
			heeling();// 회복

			ddong();// 적1
			ddong2();// 적2
			ddong3();// 적3
			ddong4();// 적4
			break;
		case 2:
			move_back();
			gun();
			charge();
			collision_me_energy();
			life();
			heeling();

			move_ddong();
			move_ddong_gun();
			move_ddong2();
			move_ddong2_gun();
			move_ddong3();
			move_ddong3_gun();
			move_ddong4();
			move_ddong4_gun();
			move_ddong4_2_gun();
			ddong5();// 똥5
			break;
		case 3:
			move_back();// 배경스크롤 시키기
			gun();// 플레이어총
			charge();// 차지
			collision_me_energy();// 플레이어충돌
			life();// 회복
			heeling();

			ddong5();// 똥5
			ddong();// 똥1
			ddong2();// 똥2
			ddong3();// 똥3
			ddong4();// 똥4
			break;
		case 4:
			move_back();// 배경스크롤 시키기
			gun();// 플레이어총
			charge();// 차지
			collision_me_energy();// 플레이어충돌
			life();// 회복
			heeling();

			move_ddong();
			move_ddong_gun();
			move_ddong2();
			move_ddong2_gun();
			move_ddong3();
			move_ddong3_gun();
			move_ddong4();
			move_ddong4_gun();
			move_ddong4_2_gun();
			flow_timer.start();
			break;
		case 5:
			move_back();// 배경스크롤 시키기
			gun();// 플레이어총
			charge();// 차지
			collision_me_energy();// 플레이어충돌
			life();// 회복
			heeling();

			move_ddong();
			move_ddong_gun();
			move_ddong2();
			move_ddong2_gun();
			move_ddong3();
			move_ddong3_gun();
			move_ddong4();
			move_ddong4_gun();
			move_ddong4_2_gun();
			ddong6();// 똥6
			ddong7();// 똥7
			break;
		}
		// ====================================플레이어움직임==========================================
		if (bPressed)// 키가 눌려있으면
		{
			move_me();
		}
	}

	private void ddong() {
		// 똥 만들기
		if (ddong_make_interval == DDONG_MAKE_INTERVAL)
			make_ddong();
		ddong_make_interval--;
		if (ddong_make_interval < 0) {
			ddong_make_interval = DDONG_MAKE_INTERVAL;
		}
		// 똥을 이동시키기
		move_ddong();

		// 똥총 만들기
		if (ddong_gun_make_interval == DDONG_GUN_MAKE_INTERVAL)
			for (DDong ddong : ddongList)
				make_ddong_gun(ddong);
		ddong_gun_make_interval--;
		if (ddong_gun_make_interval < 0) {
			ddong_gun_make_interval = DDONG_GUN_MAKE_INTERVAL;
		}
		// 똥총 이동

		move_ddong_gun();
	}

	private void ddong2() {
		// 똥2 만들기
		if (ddong2_make_interval == DDONG2_MAKE_INTERVAL)
			make_ddong2();
		ddong2_make_interval--;
		if (ddong2_make_interval < 0) {
			ddong2_make_interval = DDONG2_MAKE_INTERVAL;
		}
		// 똥2 이동
		move_ddong2();

		// 똥2총 만들기
		if (ddong2_gun_make_interval == DDONG2_GUN_MAKE_INTERVAL)
			for (DDong2 ddong2 : ddong2List)
				make_ddong2_gun(ddong2);
		ddong2_gun_make_interval--;
		if (ddong2_gun_make_interval == 0) {
			ddong2_gun_make_interval = DDONG2_GUN_MAKE_INTERVAL;
			ddong2gun_interval_count++;
		}
		if (ddong2gun_interval_count == 3) {
			ddong2_gun_make_interval = 0;
			ddong2guntimer.start();
		}
		// 똥2총 이동
		move_ddong2_gun();
	}

	private void ddong3() {
		// 똥3 만들기
		ddong3_starttimer.start();
		if (ddong3_start_count == 1) {
			if (ddong3_make_interval == DDONG3_MAKE_INTERVAL) {
				if (direction_count == 0) {
					ddong3_1a_b_count = 0;
					if (ddong3a_b_count == 0) {
						make_ddong3A();
						ddong3a_b_count = 1;
					} else if (ddong3a_b_count == 1) {
						make_ddong3B();
					}
					ddong3_start_guntimer.start();

				} else if (direction_count == 1) {
					ddong3a_b_count = 0;
					if (ddong3_1a_b_count == 0) {
						make_ddong3_1A();
						ddong3_1a_b_count = 1;
					} else if (ddong3_1a_b_count == 1) {
						make_ddong3_1B();
					}
					ddong3_start_guntimer.start();
				}
			}
			ddong3_make_interval--;
			if (ddong3_make_interval == 0) {
				ddong3_make_interval = DDONG3_MAKE_INTERVAL;
				ddong3_interval_count++;
			}
			if (ddong3_interval_count >= 4) {
				ddong3_make_interval = 0;
				ddong3_intervaltimer.start();
			}

			move_ddong3();
		}
		// 똥3총 만들기
		if (ddong3_gun_start_count == 1) {
			if (ddong3_gun_make_interval == DDONG3_GUN_MAKE_INTERVAL) {
				make_ddong3_gun();
			}
			ddong3_gun_make_interval--;
			if (ddong3_gun_make_interval == 0) {
				ddong3_gun_make_interval = DDONG3_GUN_MAKE_INTERVAL;
				ddong3_gun_count++;
			}
			if (ddong3_gun_count >= 1) {
				ddong3_gun_make_interval = 0;

			}

			move_ddong3_gun();
		}

	}

	private void ddong4() {
		ddong4_starttimer.start();
		if (ddong4_start_count == 1) {
			if (ddong4_make_interval == DDONG4_MAKE_INTERVAL) {
				make_ddong4_0();
				make_ddong4_1();
			}
			ddong4_make_interval--;
			if (ddong4_make_interval == 0) {
				ddong4_delay_count++;
			}
			if (ddong4_delay_count >= 1) {
				ddong4_make_interval = 0;
				ddong4_delay_timer.start();

			} else if (ddong4_2_make_interval == DDONG4_2_MAKE_INTERVAL) {
				make_ddong4_2();
				ddong4_2gun_start_timer.start();
				ddong4gun_start_timer.start();

			}
			ddong4_make_interval--;
			if (ddong4_2_make_interval == 0) {
				ddong4_interval_count++;
			}
			if (ddong4_interval_count >= 1) {
				ddong4_2_make_interval = 0;
				ddong4_intervaltimer.start();
			}

			move_ddong4();

		}
		// 똥4총 만들기

		if (ddong4_gun_start_count == 1) {
			if (ddong4_gun_make_interval == DDONG4_GUN_MAKE_INTERVAL) {
				make_ddong4_gun();
			}
			ddong4_gun_make_interval--;
			if (ddong4_gun_make_interval == 0) {
				ddong4_gun_make_interval = DDONG4_GUN_MAKE_INTERVAL;
				ddong4_gun_interval_count++;
			}

			if (ddong4_gun_interval_count >= 7) {
				ddong4_gun_end_timer.start();
				ddong4_gun_make_interval = 0;
				ddong4gun_interval_timer.start();
			}
		}
		move_ddong4_gun();
		// -----------------------------
		if (ddong4_2_gun_start_count == 1) {
			if (ddong4_2_gun_make_interval == DDONG4_2_GUN_MAKE_INTERVAL) {
				make_ddong4_2gun();
				make_ddong4_2gun();
			}
			ddong4_2_gun_make_interval--;
			if (ddong4_2_gun_make_interval == 0) {
				ddong4_2_gun_make_interval = DDONG4_2_GUN_MAKE_INTERVAL;
				ddong4_2_gun_interval_count++;
			}

			if (ddong4_2_gun_interval_count >= 4) {
				ddong4_2_gun_end_timer.start();
				ddong4_2_gun_make_interval = 0;
				ddong4_2gun_interval_timer.start();
			}
		}
		move_ddong4_2_gun();

	}

	private void ddong5() {
		if (ddong5_make_interval == DDONG5_MAKE_INTERVAL) {
			make_ddong5();
			ddong5_1gun_starttimer.start();
			ddong5_2gun_starttimer.start();
		}
		ddong5_make_interval--;
		if (ddong5_make_interval == 0) {
			ddong5_make_interval = DDONG5_MAKE_INTERVAL;
			ddong5_interval_count++;
		}
		if (ddong5_interval_count >= 1) {
			ddong5_make_interval = 0;
		}
		move_ddong5();

		if (ddong5_1gun_start_count == 1) {
			if (ddong5_1gun_make_interval == DDONG5_1_GUN_MAKE_INTERVAL) {
				make_ddong5_1gun();
			}
			ddong5_1gun_make_interval--;
			if (ddong5_1gun_make_interval == 0) {
				ddong5_1gun_make_interval = DDONG5_1_GUN_MAKE_INTERVAL;
				ddong5_1gun_interval_count++;
			}
			if (ddong5_1gun_interval_count >= 240) {
				ddong5_1gun_make_interval = 0;
				ddong5_1gun_intervaltimer.start();
			}
			move_ddong5gun();
		}

		if (ddong5_2gun_start_count == 1) {
			if (ddong5_2gun_make_interval == DDONG5_2_GUN_MAKE_INTERVAL) {
				make_ddong5_2gun();
			}
			ddong5_2gun_make_interval--;
			if (ddong5_2gun_make_interval == 0) {
				ddong5_2gun_interval_count++;
				ddong5_2gun_make_interval = DDONG5_2_GUN_MAKE_INTERVAL;
			}
			if (ddong5_2gun_interval_count >= 1) {
				ddong5_2gun_make_interval = 0;
				ddong5_2_intervaltimer.start();
			}
			move_ddong5_2gun();
		}
	}

	private void ddong6() {
		if (ddong6_make_interval == DDONG6_MAKE_INTERVAL) {
			make_ddong6();
			ddong6_1gun_starttimer.start();
			ddong6_2gun_starttimer.start();

		}
		ddong6_make_interval--;
		if (ddong6_make_interval == 0) {
			ddong6_make_interval = DDONG6_MAKE_INTERVAL;
			ddong6_interval_count++;
		}
		if (ddong6_interval_count >= 1) {
			ddong6_make_interval = 0;
		}
		move_ddong6();

		if (ddong6_1gun_start_count == 1) {
			if (ddong6_1gun_make_interval == DDONG6_1_GUN_MAKE_INTERVAL) {
				make_ddong6gun_1();
				make_ddong6gun_2();
			}
			ddong6_1gun_make_interval--;
			if (ddong6_1gun_make_interval == 0) {
				ddong6_1gun_make_interval = DDONG6_1_GUN_MAKE_INTERVAL;
				ddong6_1gun_interval_count++;
			}
			if (ddong6_1gun_interval_count >= 10) {
				ddong6_1gun_make_interval = 0;
				ddong6_1gun_intervaltimer.start();
			}
			move_ddong6gun();
		}

		if (ddong6_2gun_start_count == 1) {
			if (ddong6_2gun_make_interval == DDONG6_2_GUN_MAKE_INTERVAL) {
				make_ddong6_1gun_1();
				make_ddong6_1gun_2();
			}
			ddong6_2gun_make_interval--;
			if (ddong6_2gun_make_interval == 0) {
				ddong6_2gun_make_interval = DDONG6_2_GUN_MAKE_INTERVAL;
				ddong6_2gun_interval_count++;
			}
			if (ddong6_2gun_interval_count >= 180) {
				ddong6_2gun_make_interval = 0;
				ddong6_2gun_intervaltimer.start();
			}
			move_ddong6_1gun();
		}
	}

	private void ddong7() {
		if (ddong7_start_count == 1) {
			if (ddong7_make_interval == DDONG7_MAKE_INTERVAL) {
				make_ddong7();
				ddong7_1gun_starttimer.start();
			}
			ddong7_make_interval--;
			if (ddong7_make_interval == 0) {
				ddong7_make_interval = DDONG7_MAKE_INTERVAL;
				ddong7_interval_count++;
			}
			if (ddong7_interval_count >= 1) {
				ddong7_make_interval = 0;
			}
			move_ddong7();
		}

		// ------------------------총----------------------------
		if (ddong7_1gun_start_count == 1) {
			if (ddong7_1gun_make_interval == DDONG7_1_GUN_MAKE_INTERVAL) {
				make_ddong7_1gun_1();
				make_ddong7_1gun_2();
				make_ddong7_1gun_1();
				make_ddong7_1gun_2();
			}
			ddong7_1gun_make_interval--;
			if (ddong7_1gun_make_interval == 0) {
				ddong7_1gun_make_interval = DDONG7_1_GUN_MAKE_INTERVAL;
				ddong7_1gun_interval_count++;
			}
			if (ddong7_1gun_interval_count >= 234) {
				ddong7_1gun_make_interval = 0;
			}
			move_ddong7_1gun();
			if (ddong7_2gun_make_interval == DDONG7_2_GUN_MAKE_INTERVAL) {
				make_ddong7_2gun_1();
				make_ddong7_2gun_2();
			}
			ddong7_2gun_make_interval--;
			if (ddong7_2gun_make_interval == 0) {
				ddong7_2gun_make_interval = DDONG7_2_GUN_MAKE_INTERVAL;
				ddong7_2gun_interval_count++;
			}
			if (ddong7_2gun_interval_count >= 122) {
				ddong7_2gun_make_interval = 0;
				ddong7_2gun_intervaltimer.start();
			}
			move_ddong7_2gun();
		}
		if (ddong7_3gun_make_interval == DDONG7_3_GUN_MAKE_INTERVAL) {
			make_ddong7_3gun();
		}
		ddong7_3gun_make_interval--;
		if (ddong7_3gun_make_interval == 0) {
			ddong7_3gun_make_interval = DDONG7_3_GUN_MAKE_INTERVAL;
			ddong7_3gun_interval_count++;
		}
		if (ddong7_3gun_interval_count >= 2) {
			ddong7_3gun_make_interval = 0;
			ddong7_3gun_intervaltimer.start();
		}
		move_ddong7_3gun();
		if (ddong7_4gun_make_interval == DDONG7_4_GUN_MAKE_INTERVAL) {
			make_ddong7_4gun();
			make_ddong7_4_1gun();
			make_ddong7_4_2gun();
			make_ddong7_4_3gun();
		}
		ddong7_4gun_make_interval--;
		if (ddong7_4gun_make_interval == 0) {
			ddong7_4gun_make_interval = DDONG7_4_GUN_MAKE_INTERVAL;
			ddong7_4gun_interval_count++;
		}
		if (ddong7_4gun_interval_count >= 400) {
			ddong7_4gun_make_interval = 0;
			ddong7_4gun_intervaltimer.start();
		}
		move_ddong7_4gun();
	}

	private void charge() {
		if (gunCharge_attack == gunCharge_if) {
			make_gunCharge();
			gunTimer.start();

		}
		remove_gunCharge();
		guncharge_intersects();

		if (charge_start_count >= 15) {
			charge_gage = 1;
		}
		if (charge_start_count >= 30) {
			charge_gage = 2;
		}
		if (charge_start_count >= 45) {
			charge_gage = 3;
		}
	}

	private void life() {
		// 목숨 만들기
		if (life_make_interval == LIFE_MAKE_INTERVAL)
			make_life();
		life_make_interval--;
		if (life_make_interval < 0) {
			life_make_interval = LIFE_MAKE_INTERVAL;
		}
		// 목숨을 이동시키기
		move_life();
	}

	private void gun() {
		if (gun_levelup_count == 0) {
			if (gun_Pressed) {
				if (gun_make_interval == GUN_MAKE_TNTERVAL)
					make_gun();
				gun_make_interval--;
				if (gun_make_interval < 0) {
					gun_make_interval = GUN_MAKE_TNTERVAL;
				}
			}
		}
		if (gun_levelup_count == 1) {
			if (gun_Pressed) {
				if (gun_make_interval == GUN_MAKE_TNTERVAL) {
					make_gun();
					make_gun2();

				}
				gun_make_interval--;
				if (gun_make_interval < 0) {
					gun_make_interval = GUN_MAKE_TNTERVAL;
				}
			}
		}
		if (gun_levelup_count == 2) {
			if (gun_Pressed) {
				if (gun_make_interval == GUN_MAKE_TNTERVAL) {
					make_gun();
					make_gun2();
					make_gun3();
				}
				gun_make_interval--;
				if (gun_make_interval < 0) {
					gun_make_interval = GUN_MAKE_TNTERVAL;
				}
			}
		}
		if (gun_levelup_count == 3) {
			if (gun_Pressed) {
				if (gun_make_interval == GUN_MAKE_TNTERVAL) {
					make_gun();
					make_gun2();
					make_gun3();
				}
				gun_make_interval--;
				if (gun_make_interval < 0) {
					gun_make_interval = GUN_MAKE_TNTERVAL;
				}
			}
		}
		move_gun();

		gun_intersects();
		move_item_powerup();
	}

	// =============================================================================================
	// ============================================메소드============================================
	// ==============================================================================================
	// TODO 메소드
	// ==========================================차지메소드=====================================
	Timer gunTimer;
	ActionListener gunListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			gunCharge_attack = 1;
			gunCharge_if = 0;
			charge_start_count = 0;
			charge_gage--;
			gunTimer.stop();
			gunChargelist.clear();
			imgchargeList.clear();
		}
	};

	Timer gunchargetimer;
	ActionListener gunChargeListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gunCharge_if = 2;

		}
	};

	private void make_gunCharge() {
		double y = me_rect.y - 12.5;
		double x = me_rect.x + me_rect.width;
		double speed = 0.01;
		Rectangle2D.Double pos = new Rectangle2D.Double(x, y, GUNCHARGE_W,
				GUNCHARGE_H);

		GunCharge gunsuper = new GunCharge(pos, speed);
		gunChargelist.add(gunsuper);

	}

	private void remove_gunCharge() {
		guncharge_remove_timer.start();

	}

	Timer guncharge_remove_timer;
	ActionListener remove_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (GunCharge guncharge : gunChargelist) {
				gunChargelist.remove(guncharge);
				return;
			}
			guncharge_remove_timer.stop();
		}
	};

	// =============================================================================================

	// ======================================================총메소드=======================================
	private void make_gun() {
		// 총 나가는 위치
		double y = me_rect.y;
		double x = me_rect.x + me_rect.width;
		// 총알 속도
		double speed = GUN_SPEED;

		Rectangle2D.Double pos = new Rectangle2D.Double(x, y, GUN_W, GUN_H);
		// 총알객체 생성
		Gun gun = new Gun(pos, speed);
		gunlist.add(gun);
	}

	private void make_gun2() {
		// 총 나가는 위치

		double x1 = me_rect.x + me_rect.width;
		double y1 = me_rect.y + 10;

		// 총알 속도
		double speed1 = GUN_SPEED;

		Rectangle2D.Double pos1 = new Rectangle2D.Double(x1, y1, GUN_W, GUN_H);
		// 총알객체 생성
		Gun gun1 = new Gun(pos1, speed1);
		gun2list.add(gun1);

		// 총 나가는 위치

		double y1_1 = me_rect.y - 10;
		double x1_1 = me_rect.x + me_rect.width;
		// 총알 속도
		double speed1_1 = GUN_SPEED;

		Rectangle2D.Double pos1_1 = new Rectangle2D.Double(x1_1, y1_1, GUN_W,
				GUN_H);
		// 총알객체 생성
		Gun gun1_1 = new Gun(pos1_1, speed1_1);
		gun2list.add(gun1_1);

	}

	private void make_gun3() {

		int y2 = me_rect.y + 25;
		int x2 = me_rect.x + me_rect.width;
		// 총알 속도
		int speed2 = GUN_SPEED;

		Rectangle2D.Double pos2 = new Rectangle2D.Double(x2, y2, GUN_W, GUN_H);
		// 총알객체 생성
		Gun gun2 = new Gun(pos2, speed2);
		gun3list.add(gun2);

		// 총 나가는 위치

		double y2_1 = me_rect.y - 25;
		double x2_1 = me_rect.x + me_rect.width;
		// 총알 속도
		double speed2_1 = GUN_SPEED;

		Rectangle2D.Double pos2_1 = new Rectangle2D.Double(x2_1, y2_1, GUN_W,
				GUN_H);
		// 총알객체 생성
		Gun gun2_1 = new Gun(pos2_1, speed2_1);
		gun3list.add(gun2_1);

	}

	private void move_gun() {
		for (Gun gun : gunlist) {
			gun.move();
			// 화면에서 벗어나면 제거
			if (gun.pos.x > GAMEPAN_W) {
				gunlist.remove(gun);
				return;
			}
		}
		for (Gun gun : gun2list) {
			gun.move();
			// 화면에서 벗어나면 제거
			if (gun.pos.x > GAMEPAN_W) {
				gun2list.remove(gun);
				return;
			}
		}
		for (Gun gun : gun3list) {
			gun.move();
			// 화면에서 벗어나면 제거
			if (gun.pos.x > GAMEPAN_W) {
				gun3list.remove(gun);
				return;
			}
		}

	}

	// -------------------------총 충돌메소드-------------------------------
	private void gun_intersects() {
		for (Gun gun : gunlist) {
			for (DDong ddong : ddongList) {
				if (gun.pos.intersects(ddong.pos)) {
					gunlist.remove(gun);
					charge_start_count++;
					ddong_hp -= GUN_DAMEGE;
					ddong_hp(ddong);
					return;
				}
			}

			for (DDong2 ddong2 : ddong2List) {
				if (gun.pos.intersects(ddong2.pos2)) {
					gunlist.remove(gun);
					charge_start_count++;
					ddong2_hp -= GUN_DAMEGE;
					ddong2_hp(ddong2);

					return;
				}
			}
			for (DDong3 ddong3 : ddong3List) {
				if (gun.pos.intersects(ddong3.pos3)) {
					gunlist.remove(gun);
					charge_start_count++;
					ddong3_hp -= GUN_DAMEGE;
					ddong3_hp(ddong3);

					return;
				}
			}
			for (DDong3B ddong3b : ddong3BList) {
				if (gun.pos.intersects(ddong3b.pos3B)) {
					gunlist.remove(gun);
					charge_start_count++;
					ddong3_hp -= GUN_DAMEGE;
					ddong3B_hp(ddong3b);

					return;
				}
			}
			for (DDong4 ddong4 : ddong4List) {
				if (gun.pos.intersects(ddong4.pos)) {
					gunlist.remove(gun);
					charge_start_count++;
					ddong4_hp -= GUN_DAMEGE;
					ddong4_hp(ddong4);

					return;
				}
			}
			for (DDong4 ddong4_2 : ddong4_2List) {
				if (gun.pos.intersects(ddong4_2.pos)) {
					gunlist.remove(gun);
					charge_start_count++;
					ddong4_2_hp -= GUN_DAMEGE;
					ddong4_2_hp(ddong4_2);

					return;
				}
			}
			if (move_stop_d5 == true) {
				for (DDong5 ddong5 : ddong5List) {
					if (gun.pos.intersects(ddong5.pos)) {
						gunlist.remove(gun);
						charge_start_count++;
						ddong5_hp -= GUN_DAMEGE;
						ddong5_hp(ddong5);

						return;
					}
				}
			}
			if (move_stop_d6 == true) {
				for (DDong6 ddong6 : ddong6List) {
					if (gun.pos.intersects(ddong6.pos)) {
						gunlist.remove(gun);
						charge_start_count++;
						ddong6_hp -= GUN_DAMEGE;
						ddong6_hp(ddong6);

						return;
					}
				}
			}
			for (DDong7 ddong7 : ddong7List) {
				if (gun.pos.intersects(ddong7.pos)) {
					gunlist.remove(gun);
					charge_start_count++;
					ddong7_hp -= GUN_DAMEGE;
					ddong7_hp(ddong7);

					return;
				}
			}

		}
		for (Gun gun : gun2list) {
			for (DDong ddong : ddongList) {
				if (gun.pos.intersects(ddong.pos)) {
					gun2list.remove(gun);
					charge_start_count++;
					ddong_hp -= GUN_DAMEGE;
					ddong_hp(ddong);
					return;
				}
			}

			for (DDong2 ddong2 : ddong2List) {
				if (gun.pos.intersects(ddong2.pos2)) {
					gun2list.remove(gun);
					charge_start_count++;
					ddong2_hp -= GUN_DAMEGE;
					ddong2_hp(ddong2);

					return;
				}
			}
			for (DDong3 ddong3 : ddong3List) {
				if (gun.pos.intersects(ddong3.pos3)) {
					gun2list.remove(gun);
					charge_start_count++;
					ddong3_hp -= GUN_DAMEGE;
					ddong3_hp(ddong3);

					return;
				}
			}
			for (DDong3B ddong3b : ddong3BList) {
				if (gun.pos.intersects(ddong3b.pos3B)) {
					gun2list.remove(gun);
					charge_start_count++;
					ddong3_hp -= GUN_DAMEGE;
					ddong3B_hp(ddong3b);

					return;
				}
			}
			for (DDong4 ddong4 : ddong4List) {
				if (gun.pos.intersects(ddong4.pos)) {
					gun2list.remove(gun);
					charge_start_count++;
					ddong4_hp -= GUN_DAMEGE;
					ddong4_hp(ddong4);

					return;
				}
			}
			for (DDong4 ddong4_2 : ddong4_2List) {
				if (gun.pos.intersects(ddong4_2.pos)) {
					gun2list.remove(gun);
					charge_start_count++;
					ddong4_2_hp -= GUN_DAMEGE;
					ddong4_2_hp(ddong4_2);

					return;
				}
			}
			if (move_stop_d5 == true) {
				for (DDong5 ddong5 : ddong5List) {
					if (gun.pos.intersects(ddong5.pos)) {
						gun2list.remove(gun);
						charge_start_count++;
						ddong5_hp -= GUN_DAMEGE;
						ddong5_hp(ddong5);

						return;
					}
				}
			}
			if (move_stop_d6 == true) {
				for (DDong6 ddong6 : ddong6List) {
					if (gun.pos.intersects(ddong6.pos)) {
						gun2list.remove(gun);
						charge_start_count++;
						ddong6_hp -= GUN_DAMEGE;
						ddong6_hp(ddong6);

						return;
					}
				}
			}
			for (DDong7 ddong7 : ddong7List) {
				if (gun.pos.intersects(ddong7.pos)) {
					gun2list.remove(gun);
					charge_start_count++;
					ddong7_hp -= GUN_DAMEGE;
					ddong7_hp(ddong7);

					return;
				}
			}

		}
		for (Gun gun : gun3list) {
			for (DDong ddong : ddongList) {
				if (gun.pos.intersects(ddong.pos)) {
					gun3list.remove(gun);
					charge_start_count++;
					ddong_hp -= GUN_DAMEGE;
					ddong_hp(ddong);
					return;
				}
			}

			for (DDong2 ddong2 : ddong2List) {
				if (gun.pos.intersects(ddong2.pos2)) {
					gun3list.remove(gun);
					charge_start_count++;
					ddong2_hp -= GUN_DAMEGE;
					ddong2_hp(ddong2);

					return;
				}
			}
			for (DDong3 ddong3 : ddong3List) {
				if (gun.pos.intersects(ddong3.pos3)) {
					gun3list.remove(gun);
					charge_start_count++;
					ddong3_hp -= GUN_DAMEGE;
					ddong3_hp(ddong3);

					return;
				}
			}
			for (DDong3B ddong3b : ddong3BList) {
				if (gun.pos.intersects(ddong3b.pos3B)) {
					gun3list.remove(gun);
					charge_start_count++;
					ddong3_hp -= GUN_DAMEGE;
					ddong3B_hp(ddong3b);

					return;
				}
			}
			for (DDong4 ddong4 : ddong4List) {
				if (gun.pos.intersects(ddong4.pos)) {
					gun3list.remove(gun);
					charge_start_count++;
					ddong4_hp -= GUN_DAMEGE;
					ddong4_hp(ddong4);

					return;
				}
			}
			for (DDong4 ddong4_2 : ddong4_2List) {
				if (gun.pos.intersects(ddong4_2.pos)) {
					gun3list.remove(gun);
					charge_start_count++;
					ddong4_2_hp -= GUN_DAMEGE;
					ddong4_2_hp(ddong4_2);

					return;
				}
			}
			if (move_stop_d5 == true) {
				for (DDong5 ddong5 : ddong5List) {
					if (gun.pos.intersects(ddong5.pos)) {
						gun3list.remove(gun);
						charge_start_count++;
						ddong5_hp -= GUN_DAMEGE;
						ddong5_hp(ddong5);

						return;
					}
				}
			}
			if (move_stop_d6 == true) {
				for (DDong6 ddong6 : ddong6List) {
					if (gun.pos.intersects(ddong6.pos)) {
						gun3list.remove(gun);
						charge_start_count++;
						ddong6_hp -= GUN_DAMEGE;
						ddong6_hp(ddong6);

						return;
					}
				}
			}
			for (DDong7 ddong7 : ddong7List) {
				if (gun.pos.intersects(ddong7.pos)) {
					gun3list.remove(gun);
					charge_start_count++;
					ddong7_hp -= GUN_DAMEGE;
					ddong7_hp(ddong7);

					return;
				}
			}
		}
	}

	// ==================================차지충돌메소드=================================
	private void guncharge_intersects() {
		for (GunCharge gunsuper : gunChargelist) {
			charge_image();
			for (DDong ddong : ddongList) {
				if (gunsuper.pos.intersects(ddong.pos)) {
					ce(ddong);
					ddong_hp -= GUN_CHARGE_DAMEGE;
					ddong_hp(ddong);
					return;
				}
			}
			for (DDong2 ddong2 : ddong2List) {
				if (gunsuper.pos.intersects(ddong2.pos2)) {
					ddong2_hp -= GUN_CHARGE_DAMEGE;
					ce2(ddong2);
					ddong2_hp(ddong2);
					return;
				}
			}
			for (DDong3 ddong3A : ddong3List) {
				if (gunsuper.pos.intersects(ddong3A.pos3)) {
					ddong3_hp -= GUN_CHARGE_DAMEGE;
					ce3a(ddong3A);
					ddong3_hp(ddong3A);
					return;
				}
			}
			for (DDong3B ddong3B : ddong3BList) {
				if (gunsuper.pos.intersects(ddong3B.pos3B)) {
					ddong3_hp -= GUN_CHARGE_DAMEGE;
					ce3b(ddong3B);
					ddong3B_hp(ddong3B);
					return;
				}
			}
			for (DDong4 ddong4 : ddong4List) {
				if (gunsuper.pos.intersects(ddong4.pos)) {
					ddong4_hp -= GUN_CHARGE_DAMEGE;
					ce4(ddong4);
					ddong4_hp(ddong4);
					return;
				}
			}
			for (DDong4 ddong4_2 : ddong4_2List) {
				if (gunsuper.pos.intersects(ddong4_2.pos)) {
					ddong4_2_hp -= GUN_CHARGE_DAMEGE;
					ce4_2(ddong4_2);
					ddong4_2_hp(ddong4_2);
					return;
				}
			}
			if (move_stop_d5 == true) {
				for (DDong5 ddong5 : ddong5List) {
					if (gunsuper.pos.intersects(ddong5.pos)) {
						ddong5_hp -= GUN_CHARGE_DAMEGE;
						ce5(ddong5);
						ddong5_hp(ddong5);
						return;
					}
				}
			}
			if (move_stop_d6 == true) {
				for (DDong6 ddong6 : ddong6List) {
					if (gunsuper.pos.intersects(ddong6.pos)) {
						ddong6_hp -= GUN_CHARGE_DAMEGE;
						ce6(ddong6);
						ddong6_hp(ddong6);
						return;
					}
				}
			}
			for (DDong7 ddong7 : ddong7List) {
				if (gunsuper.pos.intersects(ddong7.pos)) {
					ddong7_hp -= GUN_CHARGE_DAMEGE;
					ce7(ddong7);
					ddong7_hp(ddong7);
					return;
				}
			}

		}

	}

	// ===================================================================================================

	// =====================================몬스터hp메소드===================================
	// TODO 몬스터 hp메소드
	private void ddong_hp(DDong ddong) {
		if (ddong_hp <= 0) {
			explosion_ddong(ddong);// 몬스터 터지는 효과
			charge_start_count++;
			ddongList.remove(ddong);
			ddong_hp = DDONG_HP;
			score += 100;
		}
	}

	private void ddong2_hp(DDong2 ddong2) {
		if (ddong2_hp <= 0) {
			explosion_ddong2(ddong2);
			charge_start_count++;
			ddong2List.remove(ddong2);
			ddong2_hp = DDONG2_HP;
			// 여기에 return;달면 이미 리므브된 객체를 다시 리므브하므로 에러
			score += 200;
		}
	}

	private void ddong3_hp(DDong3 ddong3a) {
		if (ddong3_hp <= 0) {
			explosion_ddong3(ddong3a);
			charge_start_count++;
			ddong3List.remove(ddong3a);
			ddong3_hp = DDONG3_HP;
			score += 150;

			make_item_powerup(ddong3a);

		}
	}

	private void ddong3B_hp(DDong3B ddong3b) {
		if (ddong3_hp <= 0) {
			explosion_ddong3B(ddong3b);
			charge_start_count++;
			ddong3BList.remove(ddong3b);
			ddong3_hp = DDONG3_HP;
			score += 150;

		}

	}

	private void ddong4_hp(DDong4 ddong4) {
		if (ddong4_hp <= 0) {
			explosion_ddong4(ddong4);
			charge_start_count++;
			ddong4List.remove(ddong4);
			ddong4_hp = DDONG4_HP;
			score += 350;

		}

	}

	private void ddong4_2_hp(DDong4 ddong4_2) {
		if (ddong4_2_hp <= 0) {
			explosion_ddong4_2(ddong4_2);
			charge_start_count++;
			ddong4_2List.remove(ddong4_2);
			ddong4_2_hp = DDONG4_2_HP;
			score += 350;
		}
	}

	private void ddong5_hp(DDong5 ddong5) {
		if (ddong5_hp <= 0) {
			explosion_ddong5(ddong5);
			charge_start_count++;
			move_stop_d5 = false;
			score += 5000;
		}
	}

	private void ddong6_hp(DDong6 ddong6) {
		if (ddong6_hp <= 0) {
			ddong7_starttimer.start();
			explosion_ddong6(ddong6);
			charge_start_count++;
			move_stop_d6 = false;
			score += 10000;
		}
	}

	private void ddong7_hp(DDong7 ddong7) {
		if (ddong7_hp <= 0) {
			be();
			flow_2_timer.start();
			charge_start_count++;
			ddong7List.remove(ddong7);
			score += 20000;
		}
	}

	// ====================================================================================

	// ==============================================똥메소드==========================================
	private void make_ddong() {
		// 똥 위치 계산
		double x = GAMEPAN_W + DDONG_W;
		double y = ra.nextInt(510) + 50;

		double speed = 2;
		Rectangle2D.Double pos_double = new Rectangle2D.Double(x, y, DDONG_W,
				DDONG_H);
		// 똥 객체 생성
		DDong ddong = new DDong(pos_double, speed);
		// 만든 똥을 똥통에 추가
		ddongList.add(ddong);

	}

	private void move_ddong() {
		for (DDong ddong : ddongList) {
			ddong.move();
			// 화면 아래로 벗어나면 제거
			if ((ddong.pos.x < -DDONG_W) || (ddong.pos.y < -DDONG_W)
					|| (ddong.pos.y > GAMEPAN_H + DDONG_W)) {
				ddongList.remove(ddong);
				return;
			}
		}
	}

	private void make_ddong_gun(DDong ddong) {
		int x = (int) ddong.pos.x;
		int y = (int) ddong.pos.y;

		int speed = 6;

		Rectangle pos = new Rectangle(x, y, DDONGGUN_W, DDONGGUN_H);

		DDongGun ddonggun = new DDongGun(pos, speed);
		ddonggunList.add(ddonggun);
	}

	private void move_ddong_gun() {
		for (DDongGun ddonggun : ddonggunList) {
			ddonggun.move();
			if (ddonggun.pos.x < 0) {
				ddonggunList.remove(ddonggun);
				return;
			}
		}
	}

	// ---------------------------------------2-------------------------------------
	private void make_ddong2() {
		int x = GAMEPAN_W + DDONG2_W;
		int y = ra.nextInt(510) + 50;
		double speed = 2;

		Rectangle2D.Double pos2 = new Rectangle2D.Double(x, y, DDONG2_W,
				DDONG2_H);
		// 똥2 객체 생성
		DDong2 ddong2 = new DDong2(pos2, speed);
		// 만든 똥2를 똥2통에 추가
		ddong2List.add(ddong2);
	}

	private void move_ddong2() {
		for (DDong2 ddong2 : ddong2List) {
			ddong2.move();
			if ((ddong2.pos2.x < -DDONG2_W) || (ddong2.pos2.y < -DDONG2_W)
					|| (ddong2.pos2.y > GAMEPAN_H + DDONG2_W)) {
				ddong2List.remove(ddong2);
				return;
			}
			// return;//(초기화) 리턴을 달면 for문이 한 번 작동하고 끝, 그래서 하나가 없어지면 위의 if문에 의해
			// 다시 하나 생성
		}
	}

	private void make_ddong2_gun(DDong2 ddong2) {
		int x = (int) ddong2.pos2.x;
		int y = (int) ((int) ddong2.pos2.y + ddong2.pos2.width / 2);

		int speed = 7;

		Rectangle pos = new Rectangle(x, y, DDONGGUN2_W, DDONGGUN2_H);
		DDong2Gun ddong2gun = new DDong2Gun(pos, speed);

		ddong2gunList.add(ddong2gun);

	}

	private void move_ddong2_gun() {
		for (DDong2Gun ddong2gun : ddong2gunList) {
			ddong2gun.move();
			if (ddong2gun.pos.x < 0) {
				ddong2gunList.remove(ddong2gun);
				return;
			}
		}

	}

	Timer ddong2guntimer;
	ActionListener ddong2gunlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong2gun_interval_count = 0;
			ddong2_gun_make_interval = DDONG2_GUN_MAKE_INTERVAL;
			ddong2guntimer.stop();
		}
	};

	// ---------------------------------------3-----------------------------------
	private void make_ddong3A() {
		int x = GAMEPAN_W + DDONG2_W;
		int y = 75;

		int speed = 5;

		Rectangle pos3 = new Rectangle(x, y, DDONG3_W, DDONG3_H);
		DDong3 ddong3 = new DDong3(pos3, speed);

		ddong3List.add(ddong3);
	}

	private void make_ddong3B() {
		int x = GAMEPAN_W + DDONG2_W;
		int y = 75;

		int speed = 5;

		Rectangle pos3B = new Rectangle(x, y, DDONG3_W, DDONG3_H);
		DDong3B ddong3B = new DDong3B(pos3B, speed);
		ddong3BList.add(ddong3B);
	}

	private void make_ddong3_1A() {
		int x = GAMEPAN_W + DDONG2_W;
		int y = 500;

		int speed = 5;

		Rectangle pos3 = new Rectangle(x, y, DDONG3_W, DDONG3_H);
		DDong3 ddong3 = new DDong3(pos3, speed);

		ddong3List.add(ddong3);

	}

	private void make_ddong3_1B() {
		int x = GAMEPAN_W + DDONG2_W;
		int y = 500;

		int speed = 5;

		Rectangle pos3B = new Rectangle(x, y, DDONG3_W, DDONG3_H);
		DDong3B ddong3B = new DDong3B(pos3B, speed);
		ddong3BList.add(ddong3B);

	}

	private void move_ddong3() {
		int speed = 2;
		for (DDong3 ddong3 : ddong3List) {
			ddong3.move();
			if (ddong3.pos3.x < GAMEPAN_W - 100) {
				if (direction_count == 0) {
					ddong3.pos3.y += speed * 2;
				} else if (direction_count == 1) {
					ddong3.pos3.y -= speed * 2;
				}
			}
			if (ddong3.pos3.x < 0) {

				ddong3List.remove(ddong3);
				return;
			}
		}
		for (DDong3B ddong3b : ddong3BList) {
			ddong3b.move();
			if (ddong3b.pos3B.x < GAMEPAN_W - 100) {
				if (direction_count == 0) {
					ddong3b.pos3B.y += speed * 2;
				} else if (direction_count == 1) {
					ddong3b.pos3B.y -= speed * 2;
				}
			}
			if (ddong3b.pos3B.x < 250) {
				ddong3BList.remove(ddong3b);
				return;
			}

		}

	}

	Timer ddong3_start_guntimer;
	ActionListener ddong3_gun_startlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong3_gun_start_count = 1;
			ddong3_start_guntimer.stop();
		}
	};

	Timer ddong3_starttimer;
	ActionListener ddong3_startlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong3_start_count = 1;
			ddong3_starttimer.stop();
		}
	};

	Timer ddong3_intervaltimer;
	ActionListener ddong3listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ddong3_gun_start_count = 0;
			ddong3_gun_make_interval = DDONG3_GUN_MAKE_INTERVAL;
			ddong3_interval_count = 0;
			if (direction_count == 0) {
				direction_count = 1;
				ddong3_make_interval = DDONG3_MAKE_INTERVAL;
			} else if (direction_count == 1) {
				direction_count = 0;
				ddong3_make_interval = DDONG3_MAKE_INTERVAL;
			}

			ddong3_intervaltimer.stop();
		}
	};

	private void make_ddong3_gun() {

		for (DDong3 ddong3 : ddong3List) {
			int x = ddong3.pos3.x;
			int y = ddong3.pos3.y;

			int speed = 8;

			Rectangle pos = new Rectangle(x, y, DDONGGUN3_W, DDONGGUN3_H);
			DDong3Gun ddong3gun = new DDong3Gun(pos, speed);

			ddong3gunList.add(ddong3gun);
		}
		for (DDong3B ddong3b : ddong3BList) {
			int x = ddong3b.pos3B.x;
			int y = ddong3b.pos3B.y;

			int speed = 8;

			Rectangle pos = new Rectangle(x, y, DDONGGUN3_W, DDONGGUN3_H);
			DDong3Gun ddong3gun = new DDong3Gun(pos, speed);

			ddong3gunList.add(ddong3gun);
		}

	}

	private void move_ddong3_gun() {
		for (DDong3Gun ddong3gun : ddong3gunList) {
			ddong3gun.move();
			if ((ddong3gun.pos.x < 0) || (ddong3gun.pos.y < 0)
					|| (ddong3gun.pos.y > GAMEPAN_H)) {
				ddong3gunList.remove(ddong3gun);
				return;
			}

		}
	}

	// ---------------------------------4---------------------------------------------
	private void make_ddong4_0() {
		double x = GAMEPAN_W + DDONG4_W;
		double y = 70;
		Rectangle2D.Double pos1 = new Rectangle2D.Double(x, y, DDONG4_W,
				DDONG4_H);
		DDong4 ddong4 = new DDong4(pos1, ddong4_speed);
		ddong4List.add(ddong4);
	}

	private void make_ddong4_1() {
		double x = GAMEPAN_W + DDONG4_W;
		double y = 370;
		Rectangle2D.Double pos2 = new Rectangle2D.Double(x, y, DDONG4_W,
				DDONG4_H);
		DDong4 ddong4 = new DDong4(pos2, ddong4_speed);
		ddong4List.add(ddong4);
	}

	private void make_ddong4_2() {
		double x = GAMEPAN_W + DDONG4_W;
		double y = 220;
		Rectangle2D.Double pos3 = new Rectangle2D.Double(x, y, DDONG4_W,
				DDONG4_H);
		DDong4 ddong4 = new DDong4(pos3, ddong4_speed);
		ddong4_2List.add(ddong4);
	}

	private void move_ddong4() {
		for (DDong4 ddong4 : ddong4List) {
			ddong4.move();
			if (ddong4.pos.x < -DDONG4_W) {
				ddong4List.remove(ddong4);
				return;
			}
		}
		for (DDong4 ddong4_2 : ddong4_2List) {
			ddong4_2.move_2();

			if (ddong4_2.pos.x < -DDONG4_W) {
				ddong4_2List.remove(ddong4_2);
				return;
			}
		}
	}

	Timer ddong4_starttimer;
	ActionListener ddong4_startlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong4_start_count = 1;
			ddong4_starttimer.stop();
		}
	};
	Timer ddong4_intervaltimer;
	ActionListener ddong4listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong4_interval_count = 0;
			ddong4_make_interval = DDONG4_MAKE_INTERVAL;
			ddong4_intervaltimer.stop();
		}
	};
	Timer ddong4_delay_timer;
	ActionListener ddong4_delaylistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong4_delay_count = 0;
			ddong4_2_make_interval = DDONG4_2_MAKE_INTERVAL;
			ddong4_delay_timer.stop();
		}
	};

	private void make_ddong4_gun() {
		for (DDong4 ddong4 : ddong4List) {
			double x = ddong4.pos.x - DDONGGUN4_H;
			double y = ddong4.pos.y + DDONGGUN4_H;

			double speed = 7.5;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN4_W,
					DDONGGUN4_H);
			DDong4Gun ddong4gun = new DDong4Gun(pos, speed);

			ddong4gunList.add(ddong4gun);
		}
	}

	private void make_ddong4_2gun() {
		for (DDong4 ddong4 : ddong4_2List) {
			double x = ddong4.pos.x - DDONGGUN4_H;
			double y = ddong4.pos.y + DDONGGUN4_H;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN4_W,
					DDONGGUN4_H);
			DDong4Gun ddong4gun = new DDong4Gun(pos, speed);

			ddong4_2gunList.add(ddong4gun);
		}
	}

	private void move_ddong4_gun() {
		for (DDong4Gun ddong4gun : ddong4gunList) {
			ddong4gun.move();
			if ((ddong4gun.pos.x < 0) || (ddong4gun.pos.y < 0)
					|| (ddong4gun.pos.y > GAMEPAN_H)) {
				ddong4gunList.remove(ddong4gun);
				return;
			}
		}
	}

	private void move_ddong4_2_gun() {
		for (DDong4Gun ddong4_2gun : ddong4_2gunList) {
			ddong4_2gun.move_2();
			if ((ddong4_2gun.pos.x < 0) || (ddong4_2gun.pos.y < 0)
					|| (ddong4_2gun.pos.y > GAMEPAN_H)) {
				ddong4_2gunList.remove(ddong4_2gun);
				return;
			}
		}
	}

	Timer ddong4gun_start_timer;
	ActionListener ddong4gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong4_gun_start_count = 1;
			ddong4gun_start_timer.stop();
		}
	};
	Timer ddong4gun_interval_timer;
	ActionListener ddong4gun_interval_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong4_gun_interval_count = 0;
			ddong4_gun_make_interval = DDONG4_GUN_MAKE_INTERVAL;

			ddong4gun_interval_timer.stop();
		}
	};
	Timer ddong4_gun_end_timer;
	ActionListener ddong4_gun_end_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong4_gun_start_count = 0;
			ddong4_gun_end_timer.stop();
		}
	};
	Timer ddong4_2gun_start_timer;
	ActionListener ddong4_2gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong4_2_gun_start_count = 1;
			ddong4_2gun_start_timer.stop();
		}
	};
	Timer ddong4_2gun_interval_timer;
	ActionListener ddong4_2gun_interval_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong4_2_gun_interval_count = 0;
			ddong4_2_gun_make_interval = DDONG4_2_GUN_MAKE_INTERVAL;

			ddong4gun_interval_timer.stop();
		}
	};
	Timer ddong4_2_gun_end_timer;
	ActionListener ddong4_2_gun_end_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong4_2_gun_start_count = 0;
			ddong4_2_gun_end_timer.stop();
		}
	};

	// -----------------------------------------5------------------------------------
	private void make_ddong5() {
		double x = GAMEPAN_W + 140;
		double y = 200;

		double speed = 2;

		Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONG5_W,
				DDONG5_H);

		DDong5 ddong5 = new DDong5(pos, speed);

		ddong5List.add(ddong5);

	}

	private void move_ddong5() {
		for (DDong5 ddong5 : ddong5List) {
			if (move_stop_d5 == true) {
				ddong5.move();
			}
			if (move_stop_d5 == false) {
				ddong5.move1();
				if ((ddong5.pos.y < -DDONG5_H)) {
					ddong5List.clear();
					return;
				}
			}
			ddong5_stop_timer.start();// 타이머를 이용하여 적당히 등장시킨 후 다시 보낸다.
		}
	}

	Timer ddong5_stop_timer;
	ActionListener ddong5_stop_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			move_stop_d5 = false;

			ddong5_stop_timer.stop();

		}
	};

	// ---------------------------총--------------------------
	private void make_ddong5_1gun() {
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 28;
			double y = ddong5.pos.y + 145;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN5_W,
					DDONGGUN5_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_1gunList.add(ddong5gun);

		}
	}

	static int number = 24;

	private void move_ddong5gun() {
		for (int i = 0; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_1();
		}
		for (int i = 1; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_2();
		}
		for (int i = 2; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_3();
		}
		for (int i = 3; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_4();
		}
		for (int i = 4; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_5();
		}
		for (int i = 5; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_6();
		}
		for (int i = 6; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_7();
		}
		for (int i = 7; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_8();
		}
		for (int i = 8; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_9();
		}
		for (int i = 9; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_10();
		}
		for (int i = 10; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_11();
		}
		for (int i = 11; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_12();
		}
		for (int i = 12; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_13();
		}
		for (int i = 13; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_14();
		}
		for (int i = 14; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_15();
		}
		for (int i = 15; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_16();
		}
		for (int i = 16; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_17();
		}
		for (int i = 17; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_18();
		}
		for (int i = 18; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_19();
		}
		for (int i = 19; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_20();
		}
		for (int i = 20; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_21();
		}
		for (int i = 21; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_22();
		}
		for (int i = 22; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_23();
		}
		for (int i = 23; i < ddong5_1gunList.size(); i += number) {
			ddong5_1gunList.get(i).move_24();
		}
	}

	Timer ddong5_1gun_starttimer;
	ActionListener ddong5_1gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong5_1gun_start_count = 1;
			ddong5_1gun_starttimer.stop();
		}
	};

	Timer ddong5_1gun_intervaltimer;
	ActionListener ddong5gun_intervallistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong5_1gunList.clear();
			ddong5_1gun_interval_count = 0;
			ddong5_1gun_make_interval = DDONG5_1_GUN_MAKE_INTERVAL;
			ddong5_1gun_intervaltimer.stop();
		}
	};
	public static double ddong5_2gun_speed = 4.5;

	private void make_ddong5_2gun() {
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 5;
			double y = ddong5.pos.y + 190;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_2gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x - 20;
			double y = ddong5.pos.y + 120;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_3gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x;
			double y = ddong5.pos.y + 75;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_4gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 45;
			double y = ddong5.pos.y + 27;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_5gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 105;
			double y = ddong5.pos.y + 15;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_6gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 145;
			double y = ddong5.pos.y + 25;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_7gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 215;
			double y = ddong5.pos.y + 70;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_8gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 215;
			double y = ddong5.pos.y + 150;

			double speed = 6;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_9gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 187;
			double y = ddong5.pos.y + 185;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_10gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 155;
			double y = ddong5.pos.y + 205;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN5_2_W, DDONGGUN5_2_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_11gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 20;
			double y = ddong5.pos.y + 170;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN5_W,
					DDONGGUN5_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_12gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 220;
			double y = ddong5.pos.y + 125;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN5_W,
					DDONGGUN5_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_13gunList.add(ddong5gun);
		}
		for (DDong5 ddong5 : ddong5List) {
			double x = ddong5.pos.x + 42;
			double y = ddong5.pos.y + 143;

			double speed = ddong5_2gun_speed;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN5_W,
					DDONGGUN5_H);

			DDong5Gun ddong5gun = new DDong5Gun(pos, speed);

			ddong5_14gunList.add(ddong5gun);
		}

	}

	private void move_ddong5_2gun() {
		for (DDong5Gun ddong5_2gun : ddong5_2gunList) {
			ddong5_2gun.move2();
			if ((ddong5_2gun.pos.x < 0) || (ddong5_2gun.pos.y < 0)
					|| (ddong5_2gun.pos.y > GAMEPAN_H)) {
				ddong5_2gunList.remove(ddong5_2gun);
				return;
			}
		}
		for (DDong5Gun ddong5_3gun : ddong5_3gunList) {
			ddong5_3gun.move2();
			if ((ddong5_3gun.pos.x < 0) || (ddong5_3gun.pos.y < 0)
					|| (ddong5_3gun.pos.y > GAMEPAN_H)) {
				ddong5_3gunList.remove(ddong5_3gun);
				return;
			}
		}
		for (DDong5Gun ddong5_4gun : ddong5_4gunList) {
			ddong5_4gun.move2();
			if ((ddong5_4gun.pos.x < 0) || (ddong5_4gun.pos.y < 0)
					|| (ddong5_4gun.pos.y > GAMEPAN_H)) {
				ddong5_4gunList.remove(ddong5_4gun);
				return;
			}
		}
		for (DDong5Gun ddong5_5gun : ddong5_5gunList) {
			ddong5_5gun.move2();
			if ((ddong5_5gun.pos.x < 0) || (ddong5_5gun.pos.y < 0)
					|| (ddong5_5gun.pos.y > GAMEPAN_H)) {
				ddong5_5gunList.remove(ddong5_5gun);
				return;
			}
		}
		for (DDong5Gun ddong5_6gun : ddong5_6gunList) {
			ddong5_6gun.move2();
			if ((ddong5_6gun.pos.x < 0) || (ddong5_6gun.pos.y < 0)
					|| (ddong5_6gun.pos.y > GAMEPAN_H)) {
				ddong5_6gunList.remove(ddong5_6gun);
				return;
			}
		}
		for (DDong5Gun ddong5_7gun : ddong5_7gunList) {
			ddong5_7gun.move2();
			if ((ddong5_7gun.pos.x < 0) || (ddong5_7gun.pos.y < 0)
					|| (ddong5_7gun.pos.y > GAMEPAN_H)) {
				ddong5_7gunList.remove(ddong5_7gun);
				return;
			}
		}
		for (DDong5Gun ddong5_8gun : ddong5_8gunList) {
			ddong5_8gun.move2();
			if ((ddong5_8gun.pos.x < 0) || (ddong5_8gun.pos.y < 0)
					|| (ddong5_8gun.pos.y > GAMEPAN_H)) {
				ddong5_8gunList.remove(ddong5_8gun);
				return;
			}
		}
		for (DDong5Gun ddong5_9gun : ddong5_9gunList) {
			ddong5_9gun.move2();
			if ((ddong5_9gun.pos.x < 0) || (ddong5_9gun.pos.y < 0)
					|| (ddong5_9gun.pos.y > GAMEPAN_H)) {
				ddong5_9gunList.remove(ddong5_9gun);
				return;
			}
		}
		for (DDong5Gun ddong5_10gun : ddong5_10gunList) {
			ddong5_10gun.move2();
			if ((ddong5_10gun.pos.x < 0) || (ddong5_10gun.pos.y < 0)
					|| (ddong5_10gun.pos.y > GAMEPAN_H)) {
				ddong5_10gunList.remove(ddong5_10gun);
				return;
			}
		}
		for (DDong5Gun ddong5_11gun : ddong5_11gunList) {
			ddong5_11gun.move2();
			if ((ddong5_11gun.pos.x < 0) || (ddong5_11gun.pos.y < 0)
					|| (ddong5_11gun.pos.y > GAMEPAN_H)) {
				ddong5_11gunList.remove(ddong5_11gun);
				return;
			}
		}
		for (DDong5Gun ddong5_12gun : ddong5_12gunList) {
			ddong5_12gun.move2();
			if ((ddong5_12gun.pos.x < 0) || (ddong5_12gun.pos.y < 0)
					|| (ddong5_12gun.pos.y > GAMEPAN_H)) {
				ddong5_12gunList.remove(ddong5_12gun);
				return;
			}
		}
		for (DDong5Gun ddong5_13gun : ddong5_13gunList) {
			ddong5_13gun.move2();
			if ((ddong5_13gun.pos.x < 0) || (ddong5_13gun.pos.y < 0)
					|| (ddong5_13gun.pos.y > GAMEPAN_H)) {
				ddong5_13gunList.remove(ddong5_13gun);
				return;
			}
		}
		for (DDong5Gun ddong5_14gun : ddong5_14gunList) {
			ddong5_14gun.move2();
			if ((ddong5_14gun.pos.x < 0) || (ddong5_14gun.pos.y < 0)
					|| (ddong5_14gun.pos.y > GAMEPAN_H)) {
				ddong5_14gunList.remove(ddong5_14gun);
				return;
			}
		}
	}

	Timer ddong5_2gun_starttimer;
	ActionListener ddong5_2gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong5_2gun_start_count = 1;
			ddong5_2gun_starttimer.stop();
		}
	};
	Timer ddong5_2_intervaltimer;
	ActionListener ddong5_2_intervallistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong5_2gun_interval_count = 0;
			ddong5_2gun_make_interval = DDONG5_2_GUN_MAKE_INTERVAL;
			ddong5_2_intervaltimer.stop();
		}
	};

	// ------------------------------6---------------------------
	private void make_ddong6() {
		double x = GAMEPAN_W + DDONG6_W;
		double y = 190;

		double speed = 2;

		Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONG6_W,
				DDONG6_H);
		DDong6 ddong6 = new DDong6(pos, speed);

		ddong6List.add(ddong6);
	}

	private void move_ddong6() {
		for (DDong6 ddong6 : ddong6List) {
			if (move_stop_d6 == true) {
				ddong6.move();
			}
			if (move_stop_d6 == false) {
				ddong6.move1();
				if (ddong6.pos.x > GAMEPAN_W) {
					ddong6List.clear();
					return;
				}
			}

		}
	}

	private void make_ddong6gun_1() {
		for (DDong6 ddong6 : ddong6List) {
			double x = ddong6.pos.x + 30;
			double y = ddong6.pos.y;
			double speed = 6;
			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN6_2_W, DDONGGUN6_2_H);
			DDong6Gun ddong6gun = new DDong6Gun(pos, speed);

			ddong6_1gunList.add(ddong6gun);
		}
	}

	private void make_ddong6gun_2() {
		for (DDong6 ddong6 : ddong6List) {
			double x = ddong6.pos.x + 30;
			double y = ddong6.pos.y + 20;

			double speed = 6;
			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN6_2_W, DDONGGUN6_2_H);
			DDong6Gun ddong6gun = new DDong6Gun(pos, speed);

			ddong6_2gunList.add(ddong6gun);
		}
	}

	private void move_ddong6gun() {
		for (DDong6Gun ddong6_1gun : ddong6_1gunList) {
			ddong6_1gun.move3();
			if ((ddong6_1gun.pos.x < 0) || (ddong6_1gun.pos.y < 0)
					|| (ddong6_1gun.pos.y > GAMEPAN_H)) {
				ddong6_1gunList.remove(ddong6_1gun);
				return;
			}
		}
		for (DDong6Gun ddong6_2gun : ddong6_2gunList) {
			ddong6_2gun.move4();
			if ((ddong6_2gun.pos.x < 0) || (ddong6_2gun.pos.y < 0)
					|| (ddong6_2gun.pos.y > GAMEPAN_H)) {
				ddong6_2gunList.remove(ddong6_2gun);
				return;
			}
		}
	}

	Timer ddong6_1gun_intervaltimer;
	ActionListener ddong6_1gun_interval_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong6_1gunList.clear();
			ddong6_2gunList.clear();
			ddong6_1gun_interval_count = 0;
			ddong6_1gun_make_interval = DDONG6_1_GUN_MAKE_INTERVAL;
			ddong6_1gun_intervaltimer.stop();
		}
	};
	Timer ddong6_1gun_starttimer;
	ActionListener ddong6_1gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong6_1gun_start_count = 1;
			ddong6_1gun_starttimer.stop();
		}
	};

	private void make_ddong6_1gun_1() {

		for (DDong6 ddong6 : ddong6List) {
			double x = ddong6.pos.x;
			double y = ddong6.pos.y;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN6_W,
					DDONGGUN6_H);

			DDong6Gun ddong6gun = new DDong6Gun(pos, speed);

			ddong6_3gunList.add(ddong6gun);
		}
	}

	private void make_ddong6_1gun_2() {
		for (DDong6 ddong6 : ddong6List) {
			double x = ddong6.pos.x;
			double y = ddong6.pos.y;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN6_W,
					DDONGGUN6_H);

			DDong6Gun ddong6gun = new DDong6Gun(pos, speed);

			ddong6_4gunList.add(ddong6gun);
		}
	}

	public static int number_1 = 18;

	private void move_ddong6_1gun() {
		for (int i = 0; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_1();
		}
		for (int i = 1; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_2();
		}
		for (int i = 2; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_3();
		}
		for (int i = 3; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_4();
		}
		for (int i = 4; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_5();
		}
		for (int i = 5; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_6();
		}
		for (int i = 6; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_7();
		}
		for (int i = 7; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_8();
		}
		for (int i = 8; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_9();
		}
		for (int i = 9; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_10();
		}
		for (int i = 10; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_11();
		}
		for (int i = 11; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_12();
		}
		for (int i = 12; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_13();
		}
		for (int i = 13; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_14();
		}
		for (int i = 14; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_15();
		}
		for (int i = 15; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_16();
		}
		for (int i = 16; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_17();
		}
		for (int i = 17; i < ddong6_3gunList.size(); i += number_1) {
			ddong6_3gunList.get(i).move_18();
		}

		for (int i = 0; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_1();
		}
		for (int i = 1; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_2();
		}
		for (int i = 2; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_3();
		}
		for (int i = 3; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_4();
		}
		for (int i = 4; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_5();
		}
		for (int i = 5; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_6();
		}
		for (int i = 6; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_7();
		}
		for (int i = 7; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_8();
		}
		for (int i = 8; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_9();
		}
		for (int i = 9; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_10();
		}
		for (int i = 10; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_11();
		}
		for (int i = 11; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_12();
		}
		for (int i = 12; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_13();
		}
		for (int i = 13; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_14();
		}
		for (int i = 14; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_15();
		}
		for (int i = 15; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_16();
		}
		for (int i = 16; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_17();
		}
		for (int i = 17; i < ddong6_4gunList.size(); i += number_1) {
			ddong6_4gunList.get(i).move2_18();
		}
	}

	Timer ddong6_2gun_starttimer;
	ActionListener ddong6_2gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong6_2gun_start_count = 1;
			ddong6_2gun_starttimer.stop();
		}
	};
	Timer ddong6_2gun_intervaltimer;
	ActionListener ddong6_2gun_interval_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong6_3gunList.clear();
			ddong6_4gunList.clear();
			ddong6_2gun_interval_count = 0;
			ddong6_2gun_make_interval = DDONG6_2_GUN_MAKE_INTERVAL;
			ddong6_2gun_intervaltimer.stop();
		}
	};

	// -----------------------7-----------------------
	private void make_ddong7() {
		double x = GAMEPAN_W;
		double y = 100;

		double speed = 2;

		Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONG7_W,
				DDONG7_H);

		DDong7 ddong7 = new DDong7(pos, speed);

		ddong7List.add(ddong7);

	}

	private void move_ddong7() {
		for (DDong7 ddong7 : ddong7List) {
			ddong7.move();
		}

	}

	Timer ddong7_starttimer;
	ActionListener ddong7_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong7_start_count = 1;
			ddong7_starttimer.stop();
		}
	};

	private void make_ddong7_1gun_1() {
		for (DDong7 ddong7 : ddong7List) {
			double x = ddong7.pos.x + 230;
			double y = ddong7.pos.y + 210;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN7_W,
					DDONGGUN7_H);

			DDong7Gun ddong7gun = new DDong7Gun(pos, speed);

			ddong7_1gunList.add(ddong7gun);
		}
	}

	private void make_ddong7_1gun_2() {
		for (DDong7 ddong7 : ddong7List) {
			double x = ddong7.pos.x + 230;
			double y = ddong7.pos.y + 210;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN7_W,
					DDONGGUN7_H);

			DDong7Gun ddong7gun = new DDong7Gun(pos, speed);

			ddong7_2gunList.add(ddong7gun);
		}
	}

	private void move_ddong7_1gun() {
		for (int i = 0; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_1();
		}
		for (int i = 1; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_2();
		}
		for (int i = 2; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_3();
		}
		for (int i = 3; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_4();
		}
		for (int i = 4; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_5();
		}
		for (int i = 5; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_6();
		}
		for (int i = 6; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_7();
		}
		for (int i = 7; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_8();
		}
		for (int i = 8; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_9();
		}
		for (int i = 9; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_10();
		}
		for (int i = 10; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_11();
		}
		for (int i = 11; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_12();
		}
		for (int i = 12; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_13();
		}
		for (int i = 13; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_14();
		}
		for (int i = 14; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_15();
		}
		for (int i = 15; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_16();
		}
		for (int i = 16; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_17();
		}
		for (int i = 17; i < ddong7_1gunList.size(); i += number_1) {
			ddong7_1gunList.get(i).move_18();
		}

		for (int i = 0; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_1();
		}
		for (int i = 1; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_2();
		}
		for (int i = 2; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_3();
		}
		for (int i = 3; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_4();
		}
		for (int i = 4; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_5();
		}
		for (int i = 5; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_6();
		}
		for (int i = 6; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_7();
		}
		for (int i = 7; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_8();
		}
		for (int i = 8; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_9();
		}
		for (int i = 9; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_10();
		}
		for (int i = 10; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_11();
		}
		for (int i = 11; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_12();
		}
		for (int i = 12; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_13();
		}
		for (int i = 13; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_14();
		}
		for (int i = 14; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_15();
		}
		for (int i = 15; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_16();
		}
		for (int i = 16; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_17();
		}
		for (int i = 17; i < ddong7_2gunList.size(); i += number_1) {
			ddong7_2gunList.get(i).move2_18();
		}
	}

	Timer ddong7_1gun_intervaltimer;
	ActionListener ddong7_1gun_interval_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong7_1gunList.clear();
			ddong7_2gunList.clear();
			ddong7_1gun_interval_count = 0;
			ddong7_1gun_make_interval = DDONG7_1_GUN_MAKE_INTERVAL;
			ddong7_1gun_intervaltimer.stop();
		}
	};
	Timer ddong7_1gun_starttimer;
	ActionListener ddong7_1gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong7_1gun_start_count = 1;
			ddong7_1gun_starttimer.stop();
		}
	};

	private void make_ddong7_2gun_1() {
		for (DDong7 ddong7 : ddong7List) {
			double x = ddong7.pos.x + 230;
			double y = ddong7.pos.y + 210;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN7_2_W, DDONGGUN7_2_H);

			DDong7Gun ddong7gun = new DDong7Gun(pos, speed);

			ddong7_3gunList.add(ddong7gun);
		}
	}

	private void make_ddong7_2gun_2() {
		for (DDong7 ddong7 : ddong7List) {
			double x = ddong7.pos.x + 230;
			double y = ddong7.pos.y + 210;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN7_2_W, DDONGGUN7_2_H);

			DDong7Gun ddong7gun = new DDong7Gun(pos, speed);

			ddong7_4gunList.add(ddong7gun);
		}
	}

	private void move_ddong7_2gun() {
		for (int i = 0; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_1();
		}
		for (int i = 1; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_2();
		}
		for (int i = 2; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_3();
		}
		for (int i = 3; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_4();
		}
		for (int i = 4; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_5();
		}
		for (int i = 5; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_6();
		}
		for (int i = 6; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_7();
		}
		for (int i = 7; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_8();
		}
		for (int i = 8; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_9();
		}
		for (int i = 9; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_10();
		}
		for (int i = 10; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_11();
		}
		for (int i = 11; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_12();
		}
		for (int i = 12; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_13();
		}
		for (int i = 13; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_14();
		}
		for (int i = 14; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_15();
		}
		for (int i = 15; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_16();
		}
		for (int i = 16; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_17();
		}
		for (int i = 17; i < ddong7_3gunList.size(); i += number_1) {
			ddong7_3gunList.get(i).move_18();
		}

		for (int i = 0; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_1();
		}
		for (int i = 1; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_2();
		}
		for (int i = 2; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_3();
		}
		for (int i = 3; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_4();
		}
		for (int i = 4; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_5();
		}
		for (int i = 5; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_6();
		}
		for (int i = 6; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_7();
		}
		for (int i = 7; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_8();
		}
		for (int i = 8; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_9();
		}
		for (int i = 9; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_10();
		}
		for (int i = 10; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_11();
		}
		for (int i = 11; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_12();
		}
		for (int i = 12; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_13();
		}
		for (int i = 13; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_14();
		}
		for (int i = 14; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_15();
		}
		for (int i = 15; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_16();
		}
		for (int i = 16; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_17();
		}
		for (int i = 17; i < ddong7_4gunList.size(); i += number_1) {
			ddong7_4gunList.get(i).move2_18();
		}
	}

	Timer ddong7_2gun_intervaltimer;
	ActionListener ddong7_2gun_interval_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong7_1gunList.clear();
			ddong7_2gunList.clear();
			ddong7_3gunList.clear();
			ddong7_4gunList.clear();
			ddong7_2gun_interval_count = 0;
			ddong7_3gun_make_interval = DDONG7_3_GUN_MAKE_INTERVAL;

			ddong7_2gun_intervaltimer.stop();
		}
	};

	private void make_ddong7_3gun() {
		for (DDong7 ddong7 : ddong7List) {
			double x = ddong7.pos.x + 125;
			double y = ddong7.pos.y + 45;

			double speed = 2;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y,
					DDONGGUN7_3_W, DDONGGUN7_3_H);

			DDong7Gun ddong7gun = new DDong7Gun(pos, speed);

			ddong7_5gunList.add(ddong7gun);
		}
	}

	private void move_ddong7_3gun() {
		for (DDong7Gun ddong7_5gun : ddong7_5gunList) {
			ddong7_5gun.move3();
		}
	}

	Timer ddong7_3gun_starttimer;
	ActionListener ddong7_3gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong7_3gun_start_count = 1;
			ddong7_3gun_starttimer.stop();
		}
	};
	Timer ddong7_3gun_intervaltimer;
	ActionListener ddong7_3gun_interval_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong7_5gunList.clear();
			ddong7_1gun_interval_count = 0;
			ddong7_1gun_make_interval = DDONG7_1_GUN_MAKE_INTERVAL;
			ddong7_3gun_interval_count = 0;
			ddong7_2gun_make_interval = DDONG7_2_GUN_MAKE_INTERVAL;
			ddong7_3gun_intervaltimer.stop();
		}
	};

	private void make_ddong7_4gun() {
		for (DDong7Gun ddong7gun : ddong7_5gunList) {
			double x = ddong7gun.pos.x + 20;
			double y = ddong7gun.pos.y + 20;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN7_W,
					DDONGGUN7_H);

			DDong7Gun ddong7gun1 = new DDong7Gun(pos, speed);

			ddong7_6gunList.add(ddong7gun1);
		}
	}

	private void make_ddong7_4_1gun() {
		for (DDong7Gun ddong7gun : ddong7_5gunList) {
			double x = ddong7gun.pos.x + 20;
			double y = ddong7gun.pos.y + 25;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN7_W,
					DDONGGUN7_H);

			DDong7Gun ddong7_1gun = new DDong7Gun(pos, speed);

			ddong7_7gunList.add(ddong7_1gun);
		}
	}

	private void make_ddong7_4_2gun() {
		for (DDong7Gun ddong7gun : ddong7_5gunList) {
			double x = ddong7gun.pos.x + 20;
			double y = ddong7gun.pos.y + 20;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN7_W,
					DDONGGUN7_H);

			DDong7Gun ddong7_2gun = new DDong7Gun(pos, speed);

			ddong7_8gunList.add(ddong7_2gun);
		}
	}

	private void make_ddong7_4_3gun() {
		for (DDong7Gun ddong7gun : ddong7_5gunList) {
			double x = ddong7gun.pos.x + 20;
			double y = ddong7gun.pos.y + 20;

			double speed = 7;

			Rectangle2D.Double pos = new Rectangle2D.Double(x, y, DDONGGUN7_W,
					DDONGGUN7_H);

			DDong7Gun ddong7_3gun = new DDong7Gun(pos, speed);

			ddong7_9gunList.add(ddong7_3gun);
		}
	}

	public static int number_2 = 40;

	private void move_ddong7_4gun() {
		for (int i = 0; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_1();
		}
		for (int i = 1; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_2();
		}
		for (int i = 2; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_3();
		}
		for (int i = 3; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_4();
		}
		for (int i = 4; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_5();
		}
		for (int i = 5; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_6();
		}
		for (int i = 6; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_7();
		}
		for (int i = 7; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_8();
		}
		for (int i = 8; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_9();
		}
		for (int i = 9; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_10();
		}
		for (int i = 10; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_11();
		}
		for (int i = 11; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_12();
		}
		for (int i = 12; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_13();
		}
		for (int i = 13; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_14();
		}
		for (int i = 14; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_15();
		}
		for (int i = 15; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_16();
		}
		for (int i = 16; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_17();
		}
		for (int i = 17; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_18();
		}
		for (int i = 18; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_19();
		}
		for (int i = 19; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_20();
		}
		for (int i = 20; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_21();
		}
		for (int i = 21; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_22();
		}
		for (int i = 22; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_23();
		}
		for (int i = 23; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_24();
		}
		for (int i = 24; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_25();
		}
		for (int i = 25; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_26();
		}
		for (int i = 26; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_27();
		}
		for (int i = 27; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_28();
		}
		for (int i = 28; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_29();
		}
		for (int i = 29; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_30();
		}
		for (int i = 30; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_31();
		}
		for (int i = 31; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_32();
		}
		for (int i = 32; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_33();
		}
		for (int i = 33; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_34();
		}
		for (int i = 34; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_35();
		}
		for (int i = 35; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_36();
		}
		for (int i = 36; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_37();
		}
		for (int i = 37; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_38();
		}
		for (int i = 38; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_39();
		}
		for (int i = 39; i < ddong7_6gunList.size(); i += number_2) {
			ddong7_6gunList.get(i).move4_40();
		}

		for (int i = 0; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_1();
		}
		for (int i = 1; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_2();
		}
		for (int i = 2; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_3();
		}
		for (int i = 3; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_4();
		}
		for (int i = 4; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_5();
		}
		for (int i = 5; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_6();
		}
		for (int i = 6; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_7();
		}
		for (int i = 7; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_8();
		}
		for (int i = 8; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_9();
		}
		for (int i = 9; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_10();
		}
		for (int i = 10; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_11();
		}
		for (int i = 11; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_12();
		}
		for (int i = 12; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_13();
		}
		for (int i = 13; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_14();
		}
		for (int i = 14; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_15();
		}
		for (int i = 15; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_16();
		}
		for (int i = 16; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_17();
		}
		for (int i = 17; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_18();
		}
		for (int i = 18; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_19();
		}
		for (int i = 19; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_20();
		}
		for (int i = 20; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_21();
		}
		for (int i = 21; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_22();
		}
		for (int i = 22; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_23();
		}
		for (int i = 23; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_24();
		}
		for (int i = 24; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_25();
		}
		for (int i = 25; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_26();
		}
		for (int i = 26; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_27();
		}
		for (int i = 27; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_28();
		}
		for (int i = 28; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_29();
		}
		for (int i = 29; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_30();
		}
		for (int i = 30; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_31();
		}
		for (int i = 31; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_32();
		}
		for (int i = 32; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_33();
		}
		for (int i = 33; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_34();
		}
		for (int i = 34; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_35();
		}
		for (int i = 35; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_36();
		}
		for (int i = 36; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_37();
		}
		for (int i = 37; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_38();
		}
		for (int i = 38; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_39();
		}
		for (int i = 39; i < ddong7_7gunList.size(); i += number_2) {
			ddong7_7gunList.get(i).move4_1_40();
		}

		for (int i = 0; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_1();
		}
		for (int i = 1; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_2();
		}
		for (int i = 2; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_3();
		}
		for (int i = 3; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_4();
		}
		for (int i = 4; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_5();
		}
		for (int i = 5; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_6();
		}
		for (int i = 6; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_7();
		}
		for (int i = 7; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_8();
		}
		for (int i = 8; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_9();
		}
		for (int i = 9; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_10();
		}
		for (int i = 10; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_11();
		}
		for (int i = 11; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_12();
		}
		for (int i = 12; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_13();
		}
		for (int i = 13; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_14();
		}
		for (int i = 14; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_15();
		}
		for (int i = 15; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_16();
		}
		for (int i = 16; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_17();
		}
		for (int i = 17; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_18();
		}
		for (int i = 18; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_19();
		}
		for (int i = 19; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_20();
		}
		for (int i = 20; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_21();
		}
		for (int i = 21; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_22();
		}
		for (int i = 22; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_23();
		}
		for (int i = 23; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_24();
		}
		for (int i = 24; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_25();
		}
		for (int i = 25; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_26();
		}
		for (int i = 26; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_27();
		}
		for (int i = 27; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_28();
		}
		for (int i = 28; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_29();
		}
		for (int i = 29; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_30();
		}
		for (int i = 30; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_31();
		}
		for (int i = 31; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_32();
		}
		for (int i = 32; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_33();
		}
		for (int i = 33; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_34();
		}
		for (int i = 34; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_35();
		}
		for (int i = 35; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_36();
		}
		for (int i = 36; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_37();
		}
		for (int i = 37; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_38();
		}
		for (int i = 38; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_39();
		}
		for (int i = 39; i < ddong7_8gunList.size(); i += number_2) {
			ddong7_8gunList.get(i).move4_2_40();
		}

		for (int i = 0; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_1();
		}
		for (int i = 1; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_2();
		}
		for (int i = 2; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_3();
		}
		for (int i = 3; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_4();
		}
		for (int i = 4; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_5();
		}
		for (int i = 5; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_6();
		}
		for (int i = 6; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_7();
		}
		for (int i = 7; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_8();
		}
		for (int i = 8; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_9();
		}
		for (int i = 9; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_10();
		}
		for (int i = 10; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_11();
		}
		for (int i = 11; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_12();
		}
		for (int i = 12; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_13();
		}
		for (int i = 13; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_14();
		}
		for (int i = 14; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_15();
		}
		for (int i = 15; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_16();
		}
		for (int i = 16; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_17();
		}
		for (int i = 17; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_18();
		}
		for (int i = 18; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_19();
		}
		for (int i = 19; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_20();
		}
		for (int i = 20; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_21();
		}
		for (int i = 21; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_22();
		}
		for (int i = 22; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_23();
		}
		for (int i = 23; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_24();
		}
		for (int i = 24; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_25();
		}
		for (int i = 25; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_26();
		}
		for (int i = 26; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_27();
		}
		for (int i = 27; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_28();
		}
		for (int i = 28; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_29();
		}
		for (int i = 29; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_30();
		}
		for (int i = 30; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_31();
		}
		for (int i = 31; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_32();
		}
		for (int i = 32; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_33();
		}
		for (int i = 33; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_34();
		}
		for (int i = 34; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_35();
		}
		for (int i = 35; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_36();
		}
		for (int i = 36; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_37();
		}
		for (int i = 37; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_38();
		}
		for (int i = 38; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_39();
		}
		for (int i = 39; i < ddong7_9gunList.size(); i += number_2) {
			ddong7_9gunList.get(i).move4_3_40();
		}
	}

	Timer ddong7_4gun_starttimer;
	ActionListener ddong7_4gun_start_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			ddong7_4gun_start_count = 1;
			ddong7_4gun_starttimer.stop();
		}
	};
	Timer ddong7_4gun_intervaltimer;
	ActionListener ddong7_4gun_interval_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			ddong7_6gunList.clear();
			ddong7_7gunList.clear();
			ddong7_8gunList.clear();
			ddong7_9gunList.clear();
			ddong7_4gun_interval_count = 0;
			ddong7_4gun_make_interval = DDONG7_4_GUN_MAKE_INTERVAL;
			ddong7_4gun_intervaltimer.stop();
		}
	};

	// =============================================================================
	// =================================아이템메소드================================
	private void make_life() {
		// 목숨 투하 위치 계산
		int x = GAMEPAN_W + Life_W;
		int y = ra.nextInt(GAMEPAN_H) + Life_H / 2;
		// 목숨 떨어지는 속도
		int speed = ra.nextInt(1) + 8;

		Rectangle pos = new Rectangle(x, y, Life_W, Life_H);
		// 목숨 객체 생성
		Life life = new Life(pos, speed);
		// 만든 목숨을 목숨에 추가
		lifeList.add(life);

	}

	private void move_life() {
		for (Life life : lifeList) {
			life.move_down();

			// 화면 아래로 벗어나면 제거
			if (life.pos.x < 0) {
				// 목숨객체 생성
				// 목숨위치 계산
				lifeList.remove(life);
				return;// for문으로 돌리고 있기 때문에 리무브돼서 포문에서 하나가 사라지면 에러먹음
			}
		}
	}

	private void make_item_powerup(DDong3 ddong3a) {
		int x = ddong3a.pos3.x;
		int y = ddong3a.pos3.y;
		int speed = 1;

		Rectangle pos = new Rectangle(x, y, POWERUP_W, POWERUP_H);
		ItemPowerUp powerup = new ItemPowerUp(pos, speed);

		poweruplist.add(powerup);
	}

	private void move_item_powerup() {
		for (ItemPowerUp powerup : poweruplist) {
			powerup.move();
			if (powerup.pos.x < 10) {
				poweruplist.remove(powerup);
				return;
			}

		}
	}

	// ===============================이미지메소드=============================
	// TODO 이미지메소드
	// 폭발메소드
	private void explosion_ddong(DDong ddong) {
		// 폭발객체 생성 // 폭발위치 계산
		int x = (int) (ddong.pos.x - DDONG_W);
		int y = (int) (ddong.pos.y - img_bomb[0].getHeight(this) / 6);
		Bomb bomb = new Bomb(x, y); // 폭발리스트 관리객체
		bombList.add(bomb);

	}

	private void explosion_ddong2(DDong2 ddong2) {
		int x = (int) (ddong2.pos2.x - (int) DDONG_W);
		int y = (int) (ddong2.pos2.y - img_bomb[0].getHeight(this) / 6);
		Bomb bomb = new Bomb(x, y);
		bombList.add(bomb);

	}

	private void explosion_ddong3(DDong3 ddong3a) {
		int x = ddong3a.pos3.x - (int) DDONG_W;
		int y = ddong3a.pos3.y - img_bomb[0].getHeight(this) / 6;
		Bomb bomb = new Bomb(x, y);
		bombList.add(bomb);

	}

	private void explosion_ddong3B(DDong3B ddong3b) {
		int x = ddong3b.pos3B.x - (int) DDONG_W;
		int y = ddong3b.pos3B.y - img_bomb[0].getHeight(this) / 6;
		Bomb bomb = new Bomb(x, y);
		bombList.add(bomb);

	}

	private void explosion_ddong4(DDong4 ddong4) {
		double x = ddong4.pos.x + DDONG4_W / 6;
		double y = ddong4.pos.y + DDONG4_H / 2;
		Bomb bomb = new Bomb(x, y);
		bombList.add(bomb);

	}

	private void explosion_ddong4_2(DDong4 ddong4_2) {
		double x = ddong4_2.pos.x + DDONG4_W / 6;
		double y = ddong4_2.pos.y + DDONG4_H / 2;
		Bomb bomb = new Bomb(x, y);
		bombList.add(bomb);

	}

	private void explosion_ddong5(DDong5 ddong5) {
		double x = ddong5.pos.x + DDONG4_W / 6;
		double y = ddong5.pos.y + DDONG4_H / 2;
		Bomb bomb = new Bomb(x, y);
		bombList.add(bomb);

	}

	private void explosion_ddong6(DDong6 ddong6) {
		double x = ddong6.pos.x + DDONG4_W / 6;
		double y = ddong6.pos.y + DDONG4_H / 2;
		Bomb bomb = new Bomb(x, y);
		bombList.add(bomb);

	}

	// 차지충돌이미지
	private void ce7(DDong7 ddong7) {
		int x = (int) ddong7.pos.x;
		if (me_rect.y > (int) ddong7.pos.y) {
			int y = (int) ddong7.pos.y + (me_rect.y - (int) ddong7.pos.y)
					- (img_ce[0].getWidth(this) / 3);
			ImgMove ce = new ImgMove(x, y);
			imgceList.add(ce);
		}
		if (me_rect.y < (int) ddong7.pos.y) {
			int y = (int) ddong7.pos.y + ((int) ddong7.pos.y - me_rect.y)
					- (img_ce[0].getWidth(this) / 3);
			ImgMove ce = new ImgMove(x, y);
			imgceList.add(ce);
		}
	}

	private void ce6(DDong6 ddong6) {
		int x = (int) ddong6.pos.x;
		if (me_rect.y > (int) ddong6.pos.y) {
			int y = (int) ddong6.pos.y + (me_rect.y - (int) ddong6.pos.y)
					- (img_ce[0].getWidth(this) / 3);
			ImgMove ce = new ImgMove(x, y);
			imgceList.add(ce);
		}
		if (me_rect.y < (int) ddong6.pos.y) {
			int y = (int) ddong6.pos.y + ((int) ddong6.pos.y - me_rect.y)
					- (img_ce[0].getWidth(this) / 3);
			ImgMove ce = new ImgMove(x, y);
			imgceList.add(ce);
		}
	}

	private void ce5(DDong5 ddong5) {
		int x = (int) ddong5.pos.x;
		if (me_rect.y > (int) ddong5.pos.y) {
			int y = (int) ddong5.pos.y + (me_rect.y - (int) ddong5.pos.y)
					- (img_ce[0].getWidth(this) / 3);
			ImgMove ce = new ImgMove(x, y);
			imgceList.add(ce);
		}
		if (me_rect.y < (int) ddong5.pos.y) {
			int y = (int) ddong5.pos.y + ((int) ddong5.pos.y - me_rect.y)
					- (img_ce[0].getWidth(this) / 3);
			ImgMove ce = new ImgMove(x, y);
			imgceList.add(ce);
		}
	}

	private void ce4_2(DDong4 ddong4_2) {
		int x = (int) ddong4_2.pos.x;
		int y = (int) ddong4_2.pos.y;
		ImgMove ce = new ImgMove(x, y);
		imgceList.add(ce);
	}

	private void ce4(DDong4 ddong4) {
		int x = (int) ddong4.pos.x;
		int y = (int) ddong4.pos.y;
		ImgMove ce = new ImgMove(x, y);
		imgceList.add(ce);
	}

	private void ce3b(DDong3B ddong3b) {
		int x = (int) ddong3b.pos3B.x;
		int y = (int) ddong3b.pos3B.y;
		ImgMove ce = new ImgMove(x, y);
		imgceList.add(ce);
	}

	private void ce3a(DDong3 ddong3a) {
		int x = (int) ddong3a.pos3.x;
		int y = (int) ddong3a.pos3.y;
		ImgMove ce = new ImgMove(x, y);
		imgceList.add(ce);
	}

	private void ce2(DDong2 ddong2) {
		int x = (int) ddong2.pos2.x;
		int y = (int) ddong2.pos2.y;
		ImgMove ce = new ImgMove(x, y);
		imgceList.add(ce);
	}

	private void ce(DDong ddong) {
		int x = (int) ddong.pos.x;
		int y = (int) ddong.pos.y;
		ImgMove ce = new ImgMove(x, y);
		imgceList.add(ce);
	}

	// 차지이미지객체 생성
	private void charge_image() {
		int x = me_rect.x;
		int y = me_rect.y;
		ImgMove imgcharge = new ImgMove(x, y);
		imgchargeList.add(imgcharge);
	}

	// 플레이어무적이미지
	private void invincibla() {
		int x = me_rect.x;
		int y = me_rect.y;
		ImgMove imginvincible = new ImgMove(x, y);
		invinciblelist.add(imginvincible);
	}

	// 보스 폭발이미지
	private void be() {
		int x = 0;
		int y = -100;
		ImgMove be = new ImgMove(x, y);
		imgbeList.add(be);
	}

	// ==========================================================================

	// ==================================부가기능메소드===============================
	// 플레이어충돌이벤트메소드
	private void collision_me_energy() {
		// TODO collision
		if (invincible == false) {
			// -----------------똥-------------------
			for (DDong ddong : ddongList) {
				if (me_rect.intersects(ddong.pos)) {
					player_hp -= 5;
					ddongList.remove(ddong);
					explosion_ddong(ddong);
					timestop();
					return;
				}

			}
			for (DDongGun ddonggun : ddonggunList) {
				if (me_rect.intersects(ddonggun.pos)) {
					player_hp -= 5;
					ddonggunList.remove(ddonggun);
					timestop();
					return;
				}
			}
			// ----------------------똥2---------------------------------
			for (DDong2 ddong2 : ddong2List) {
				if (me_rect.intersects(ddong2.pos2)) {
					player_hp -= 5;
					ddong2List.remove(ddong2);
					explosion_ddong2(ddong2);
					timestop();
					return;

				}
			}
			for (DDong2Gun ddong2gun : ddong2gunList) {
				if (me_rect.intersects(ddong2gun.pos)) {
					player_hp -= 5;
					ddong2gunList.remove(ddong2gun);
					timestop();
					return;
				}
			}
			// 똥3
			for (DDong3 ddong3 : ddong3List) {
				if (me_rect.intersects(ddong3.pos3)) {
					player_hp -= 5;
					explosion_ddong3(ddong3);
					ddong3List.remove(ddong3);
					timestop();
					return;
				}
			}
			for (DDong3B ddong3b : ddong3BList) {
				if (me_rect.intersects(ddong3b.pos3B)) {
					player_hp -= 5;
					ddong3BList.remove(ddong3b);
					explosion_ddong3B(ddong3b);
					timestop();
					return;
				}
			}
			for (DDong3Gun ddong3gun : ddong3gunList) {
				if (me_rect.intersects(ddong3gun.pos)) {
					player_hp -= 5;
					ddong3gunList.remove(ddong3gun);
					timestop();
					return;
				}
			}
			// --------똥4-------
			for (DDong4 ddong4 : ddong4List) {
				if (me_rect.intersects(ddong4.pos)) {
					player_hp -= 5;
					explosion_ddong4(ddong4);
					ddong4List.remove(ddong4);
					timestop();
					return;
				}
			}
			for (DDong4 ddong4_2 : ddong4_2List) {
				if (me_rect.intersects(ddong4_2.pos)) {
					player_hp -= 5;
					ddong4_2List.remove(ddong4_2);
					explosion_ddong4_2(ddong4_2);
					timestop();
					return;
				}
			}
			for (DDong4Gun ddong4gun : ddong4gunList) {
				if (me_rect.intersects(ddong4gun.pos)) {
					player_hp -= 5;
					ddong4gunList.remove(ddong4gun);
					timestop();
					return;
				}
			}
			for (DDong4Gun ddong4_2gun : ddong4_2gunList) {
				if (me_rect.intersects(ddong4_2gun.pos)) {
					player_hp -= 5;
					ddong4_2gunList.remove(ddong4_2gun);
					timestop();
					return;
				}
			}
			// ------------똥5----------------
			for (DDong5 ddong5 : ddong5List) {
				if (me_rect.intersects(ddong5.pos)) {
					player_hp -= 5;
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_1gun : ddong5_1gunList) {
				if (me_rect.intersects(ddong5_1gun.pos)) {
					player_hp -= 5;
					ddong5_1gun.pos.x = GAMEPAN_W;
					ddong5_1gun.pos.y = GAMEPAN_H;
					timestop();
					return;
				}

			}
			for (DDong5Gun ddong5_2gun : ddong5_2gunList) {
				if (me_rect.intersects(ddong5_2gun.pos)) {
					player_hp -= 5;
					ddong5_2gunList.remove(ddong5_2gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_3gun : ddong5_3gunList) {
				if (me_rect.intersects(ddong5_3gun.pos)) {
					player_hp -= 5;
					ddong5_3gunList.remove(ddong5_3gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_4gun : ddong5_4gunList) {
				if (me_rect.intersects(ddong5_4gun.pos)) {
					player_hp -= 5;
					ddong5_4gunList.remove(ddong5_4gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_5gun : ddong5_5gunList) {
				if (me_rect.intersects(ddong5_5gun.pos)) {
					player_hp -= 5;
					ddong5_5gunList.remove(ddong5_5gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_6gun : ddong5_6gunList) {
				if (me_rect.intersects(ddong5_6gun.pos)) {
					player_hp -= 5;
					ddong5_6gunList.remove(ddong5_6gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_7gun : ddong5_7gunList) {
				if (me_rect.intersects(ddong5_7gun.pos)) {
					player_hp -= 5;
					ddong5_7gunList.remove(ddong5_7gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_8gun : ddong5_8gunList) {
				if (me_rect.intersects(ddong5_8gun.pos)) {
					player_hp -= 5;
					ddong5_8gunList.remove(ddong5_8gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_9gun : ddong5_9gunList) {
				if (me_rect.intersects(ddong5_9gun.pos)) {
					player_hp -= 5;
					ddong5_9gunList.remove(ddong5_9gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_10gun : ddong5_10gunList) {
				if (me_rect.intersects(ddong5_10gun.pos)) {
					player_hp -= 5;
					ddong5_10gunList.remove(ddong5_10gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_11gun : ddong5_11gunList) {
				if (me_rect.intersects(ddong5_11gun.pos)) {
					player_hp -= 5;
					ddong5_11gunList.remove(ddong5_11gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_12gun : ddong5_12gunList) {
				if (me_rect.intersects(ddong5_12gun.pos)) {
					player_hp -= 5;
					ddong5_12gunList.remove(ddong5_12gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_13gun : ddong5_13gunList) {
				if (me_rect.intersects(ddong5_13gun.pos)) {
					player_hp -= 5;
					ddong5_13gunList.remove(ddong5_13gun);
					timestop();
					return;
				}
			}
			for (DDong5Gun ddong5_14gun : ddong5_14gunList) {
				if (me_rect.intersects(ddong5_14gun.pos)) {
					player_hp -= 5;
					ddong5_14gunList.remove(ddong5_14gun);
					timestop();
					return;
				}
			}
			// -------------------똥6--------------------
			for (DDong6 ddong6 : ddong6List) {
				if (me_rect.intersects(ddong6.pos)) {
					player_hp -= 5;
					timestop();
					return;
				}
			}
			for (DDong6Gun ddong6_1gun : ddong6_1gunList) {
				if (me_rect.intersects(ddong6_1gun.pos)) {
					player_hp -= 5;
					ddong6_1gunList.remove(ddong6_1gun);
					timestop();
					return;
				}
			}
			for (DDong6Gun ddong6_2gun : ddong6_2gunList) {
				if (me_rect.intersects(ddong6_2gun.pos)) {
					player_hp -= 5;
					ddong6_2gunList.remove(ddong6_2gun);
					timestop();
					return;
				}
			}
			for (DDong6Gun ddong6_3gun : ddong6_3gunList) {
				if (me_rect.intersects(ddong6_3gun.pos)) {
					player_hp -= 2.5;
					ddong6_3gun.pos.x = GAMEPAN_W + 100;
					ddong6_3gun.pos.y = GAMEPAN_H + 100;
					timestop();
					return;
				}
			}
			for (DDong6Gun ddong6_4gun : ddong6_4gunList) {
				if (me_rect.intersects(ddong6_4gun.pos)) {
					player_hp -= 2.5;
					ddong6_4gun.pos.x = -100;
					ddong6_4gun.pos.y = -100;
					timestop();
					return;
				}
			}
			// ----------------------똥7-----------------------------
			for (DDong7 ddong7 : ddong7List) {
				if (me_rect.intersects(ddong7.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_1gun : ddong7_1gunList) {
				if (me_rect.intersects(ddong7_1gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_2gun : ddong7_2gunList) {
				if (me_rect.intersects(ddong7_2gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_3gun : ddong7_3gunList) {
				if (me_rect.intersects(ddong7_3gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_4gun : ddong7_4gunList) {
				if (me_rect.intersects(ddong7_4gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_5gun : ddong7_5gunList) {
				if (me_rect.intersects(ddong7_5gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_6gun : ddong7_6gunList) {
				if (me_rect.intersects(ddong7_6gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_7gun : ddong7_7gunList) {
				if (me_rect.intersects(ddong7_7gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_8gun : ddong7_8gunList) {
				if (me_rect.intersects(ddong7_8gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
			for (DDong7Gun ddong7_9gun : ddong7_9gunList) {
				if (me_rect.intersects(ddong7_9gun.pos)) {
					player_hp -= 2.5;
					timestop();
					return;
				}
			}
		}
	}

	private void timestop() {
		if (player_hp <= 0) {
			timer.stop();
			// 메시지 다이어로그
			JOptionPane.showMessageDialog(this, "다시해봐!!!!");
		}
	}

	private void heeling() {
		for (Life life : lifeList) {
			if (me_rect.intersects(life.pos)) {
				player_hp += 5;
				lifeList.remove(life);
				return;
			}
		}
		// ------------------------------아이템---------------------------------
		// 아이템과 플레이어가 충돌하면 건레벨카운트가 올라가고 총이 업그레이드됨
		// 카운트가 풀이되면 그 이후부터는 스코어점수가 올라감
		for (ItemPowerUp powerup : poweruplist) {
			if (me_rect.intersects(powerup.pos)) {
				poweruplist.remove(powerup);
				gun_levelup_count++;
				if (gun_levelup_count > 3) {
					gun_levelup_count = 3;
					score += 500;
				}
				return;
			}
		}
	}

	// ==========흐름제어타이머====================
	Timer flow_timer;
	ActionListener flow_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			flow_count = 5;
			flow_timer.stop();

		}
	};
	Timer flow_2_timer;
	ActionListener flow_2_listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			flow_count = 6;
			flow_2_timer.stop();

		}
	};

	// ===================================백그라운드이동메소드============================
	private void move_back() {
		offset_x += 1.5;
		if (offset_x > img_background.getWidth(this) - GAMEPAN_W) {
			offset_x = 0;
			flow_control_count++;
		}

	}

	// ===============================플레이어 이동 메소드======================
	private void move_me() {

		if ((key_state & K_LEFT) == K_LEFT && (key_state & K_UP) == K_UP) {

			me_rect.x -= gan;
			me_rect.y -= gan;

		} else if ((key_state & K_LEFT) == K_LEFT
				&& (key_state & K_DOWN) == K_DOWN) {

			me_rect.x -= gan;
			me_rect.y += gan;
		} else if ((key_state & K_RIGHT) == K_RIGHT
				&& (key_state & K_DOWN) == K_DOWN) {

			me_rect.x += gan;
			me_rect.y += gan;
		} else if ((key_state & K_RIGHT) == K_RIGHT
				&& (key_state & K_UP) == K_UP) {

			me_rect.x += gan;
			me_rect.y -= gan;
		} else if ((key_state & K_LEFT) == K_LEFT) {

			me_rect.x -= gan;
		} else if ((key_state & K_RIGHT) == K_RIGHT) {

			me_rect.x += gan;
		} else if ((key_state & K_UP) == K_UP) {

			me_rect.y -= gan;
		} else if ((key_state & K_DOWN) == K_DOWN) {

			me_rect.y += gan;
		}
	}

	private void initKeyEvent() {
		// TODO keyevent
		KeyAdapter k_listener = new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				key = e.getKeyCode();// 눌린키값

				if (key == KeyEvent.VK_LEFT) {
					key_state = key_state | K_LEFT;
				} else if (key == KeyEvent.VK_RIGHT) {
					key_state |= K_RIGHT;
				} else if (key == KeyEvent.VK_UP) {
					key_state |= K_UP;
				} else if (key == KeyEvent.VK_DOWN) {
					key_state |= K_DOWN;
				} else if (key == KeyEvent.VK_A) {
					key_state |= GUN;
				}

				if (key == 'R') {
					gun_levelup_count = 0;
					gunlist.clear();
					gunChargelist.clear();
					// 이전에 남아있는 똥 제거
					ddongList.clear();
					ddonggunList.clear();

					ddong2List.clear();
					ddong2gunList.clear();
					ddong2gun_interval_count = 0;

					ddong3List.clear();
					ddong3BList.clear();
					ddong3gunList.clear();
					ddong3_start_count = 0;
					ddong3_gun_start_count = 0;

					ddong4List.clear();
					ddong4_2List.clear();
					ddong4gunList.clear();
					ddong4_2gunList.clear();
					ddong4_starttimer.stop();
					ddong4_delay_count = 0;
					ddong4_start_count = 0;
					ddong4_gun_end_count = 0;
					ddong4_2_gun_end_count = 0;
					ddong4_gun_start_count = 0;
					ddong4_2_gun_start_count = 0;
					ddong4_gun_interval_count = 0;
					ddong4_2_gun_interval_count = 0;

					ddong5List.clear();
					ddong5_hp = DDONG5_HP;
					move_stop_d5 = true;

					ddong6List.clear();
					ddong6_hp = DDONG6_HP;
					move_stop_d6 = true;

					ddong7List.clear();
					ddong7_hp = DDONG7_HP;

					timepush = 0;
					lifeList.clear();
					poweruplist.clear();
					bombList.clear();
					flow_count = 0;
					key_state = 0;
				}

				if ((key == 'P')) {
					timer.stop();
					timepush++;
					if ((timepush % 2 == 0) && (key == 'P')) {

						timer.start();
					}
				}
				if ((key == 'O')) {
					player_status = 1;
					invincible = true;
					invincible_count++;
					if ((invincible_count % 2 == 0) && (key == 'O')) {
						player_status = 0;
						invincible = false;
					}
				}

				if ((key_state & GUN) == GUN) {
					// if (key == 'A') {// 총 눌린 정보
					gun_Pressed = true;
				}
				if (charge_gage >= 1) {
					if (key == 'S') {
						gunchargetimer.start();

						gunCharge_attack = 1;
					}
				}
				bPressed = true;// 쥔공 눌린정보
			}

			@Override
			public void keyReleased(KeyEvent e) {
				key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT) {
					key_state = key_state ^ K_LEFT;
				} else if (key == KeyEvent.VK_RIGHT) {
					key_state ^= K_RIGHT;
				} else if (key == KeyEvent.VK_UP) {
					key_state ^= K_UP;
				} else if (key == KeyEvent.VK_DOWN) {
					key_state ^= K_DOWN;
				} else if (key == 'A') {
					key_state ^= GUN;
				}

				bPressed = false;
				gun_Pressed = false;

				if (key == 'S') {
					gunchargetimer.stop();
					gunCharge_attack = 2;
				}
			}
		};

		// this(MyMain)에서 키가 눌리면 k_listener에게 처리를 위임하겠다.
		this.addKeyListener(k_listener);
	}

	private void initMouseEvent() {
		MouseAdapter mouse_listener = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				flow_count = 1;
			}
		};
		this.addMouseListener(mouse_listener);
	}

	private void initGame() {
		// 이렇게 바로 rect을 안 만들어주고 메소드를 이용해서 만들면
		// 후에 값을 넣어줄 때 가변성에 용이
		me_rect = new Rectangle(25, GAMEPAN_H / 2, 30, 15);
		base_rect = new Rectangle(0, 0, GAMEPAN_W, 50);
	}

	private void initGamePanel() {
		// TODO draw
		gamePanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {// Graphics그리기도구
				// Graphics : 그리기도구(펜,붓)
				// 이전화면 지우기
				// g.clearRect(0, 0, GAMEPAN_W, GAMEPAN_H);//배경그림이 덮어버림으로 필요 없음
				switch (flow_count) {
				case 0:
					g.drawImage(img_start, 0, 0, GAMEPAN_W, GAMEPAN_H, this);
					break;
				case 1:
					draw_background(g);
					draw_player(g);
					draw_bomb(g);
					draw_life(g);
					draw_powerup(g);
					draw_ce(g);

					draw_gun(g);
					draw_guncharge(g);

					draw_ddong(g);
					draw_ddonggun(g);
					draw_ddong2(g);
					draw_ddong2gun(g);
					draw_ddong3(g);
					draw_ddong3B(g);
					draw_ddong3gun(g);
					draw_ddong4(g);
					draw_ddong4_1(g);
					draw_ddong4gun(g);
					draw_ddong4_2gun(g);

					draw_ddong5(g);
					draw_ddong5gun(g);
					draw_ddong5_2gun(g);

					draw_ddong6(g);
					draw_ddong6gun(g);

					draw_ddong7(g);
					draw_ddong7gun(g);

					Draw_statusText(g);
					break;
				case 2:
					draw_background(g);
					draw_player(g);
					draw_bomb(g);
					draw_life(g);
					draw_powerup(g);
					draw_ce(g);

					draw_gun(g);
					draw_guncharge(g);

					draw_ddong(g);
					draw_ddonggun(g);
					draw_ddong2(g);
					draw_ddong2gun(g);
					draw_ddong3(g);
					draw_ddong3B(g);
					draw_ddong3gun(g);
					draw_ddong4(g);
					draw_ddong4_1(g);
					draw_ddong4gun(g);
					draw_ddong4_2gun(g);
					draw_ddong5(g);
					draw_ddong5gun(g);
					draw_ddong5_2gun(g);

					Draw_statusText(g);
					break;
				case 3:
					draw_background(g);
					draw_player(g);
					draw_bomb(g);
					draw_life(g);
					draw_powerup(g);
					draw_ce(g);

					draw_gun(g);
					draw_guncharge(g);

					draw_ddong(g);
					draw_ddonggun(g);
					draw_ddong2(g);
					draw_ddong2gun(g);
					draw_ddong3(g);
					draw_ddong3B(g);
					draw_ddong3gun(g);
					draw_ddong4(g);
					draw_ddong4_1(g);
					draw_ddong4gun(g);
					draw_ddong4_2gun(g);
					draw_ddong5(g);
					draw_ddong5gun(g);
					draw_ddong5_2gun(g);

					Draw_statusText(g);
					break;
				case 4:
					draw_background(g);
					draw_player(g);
					draw_bomb(g);
					draw_life(g);
					draw_powerup(g);
					draw_ce(g);

					draw_gun(g);
					draw_guncharge(g);

					draw_ddong(g);
					draw_ddonggun(g);
					draw_ddong2(g);
					draw_ddong2gun(g);
					draw_ddong3(g);
					draw_ddong3B(g);
					draw_ddong3gun(g);
					draw_ddong4(g);
					draw_ddong4_1(g);
					draw_ddong4gun(g);
					draw_ddong4_2gun(g);

					Draw_statusText(g);
					break;
				case 5:
					draw_background(g);
					draw_player(g);
					draw_bomb(g);
					draw_life(g);
					draw_powerup(g);
					draw_ce(g);
					draw_be(g);

					draw_gun(g);
					draw_guncharge(g);

					draw_ddong6(g);
					draw_ddong6gun(g);
					draw_ddong7(g);
					draw_ddong7gun(g);

					Draw_statusText(g);
					break;
				case 6:
					g.setColor(Color.white);
					g.fillRect(0, 0, GAMEPAN_W, GAMEPAN_H);
					g.setColor(Color.black);
					g.setFont(new Font("Defualt", Font.BOLD, 40));
					g.drawString("SCORE : " + score, 400, 280);
					g.drawString("THE END", 430, 50);
					g.drawString("수고했삼", 430, 450);
					break;
				}
			}

		};
		// 크기설정
		// 폭/높이 정보를 담는 자료형
		Dimension dimension = new Dimension(GAMEPAN_W, GAMEPAN_H);
		// 사전에 gamePanel크기를 설정(예약)
		gamePanel.setPreferredSize(dimension);

		// JFrame에 gamePanel 적재
		this.add(gamePanel);

	}

	protected void Draw_statusText(Graphics g) {
		// 에너지바 바닥 이미지
		g.drawImage(img_base, base_rect.x, base_rect.y, base_rect.width,
				base_rect.height, this);
		g.setColor(Color.white);
		g.setFont(new Font("Defualt", Font.BOLD, 20));// 폰트 설정을 합니다. 기본폰트, 굵게,
														// 사이즈 20
		g.drawString("SCORE : " + score, 470, 23);
		g.drawString("PLAYER-HP ", 10, 20);
		for (int i = 0; i < player_hp; i += 2) {
			g.drawImage(img_gage[0], gage_x + i, 23, this);
			if (player_hp <= 120) {
				g.drawImage(img_gage[1], gage_x + i, 23, this);
			}
			if (player_hp <= 90) {
				g.drawImage(img_gage[2], gage_x + i, 23, this);
			}
			if (player_hp <= 60) {
				g.drawImage(img_gage[3], gage_x + i, 23, this);
			}
			if (player_hp <= 30) {
				g.drawImage(img_gage[4], gage_x + i, 23, this);
			}
		}
		g.drawString("CHARGE " + charge_gage, 800, 20);
		if (charge_gage == 1) {
			g.drawImage(img_chargegage[0], chargegage_x, 23, this);
		}
		if (charge_gage == 2) {
			g.drawImage(img_chargegage[1], chargegage_x, 23, this);
		}
		if (charge_gage == 3) {
			g.drawImage(img_chargegage[2], chargegage_x, 23, this);

		}
	}

	int inv_index = 0;

	protected void draw_player(Graphics g) {
		// 나(사람)
		switch (player_status) {
		case 0:
			g.drawImage(img_engel, me_rect.x - 14, me_rect.y - 12,
					me_rect.width + 29, me_rect.height + 24, this);
			break;
		case 1:
			invincibla();
			if (invinciblelist.size() == 0) {
				return;
			}
			ImgMove imginvincible = invinciblelist.get(0);
			g.drawImage(img_invincible[imginvincible.inv_index],
					me_rect.x - 14, me_rect.y - 12, me_rect.width + 29,
					me_rect.height + 24, this);
			if (imginvincible.move_inv() == false) {
				invinciblelist.remove(imginvincible);
				return;
			}
			break;
		}
	}

	// ===========================================그리기================================================
	// 배경
	protected void draw_background(Graphics g) {
		g.drawImage(img_background, 0, 0, GAMEPAN_W, GAMEPAN_H, // 화면좌표
				(int) offset_x, 0, (int) offset_x + GAMEPAN_W, GAMEPAN_H,// 배경그림좌표
				null);

	}

	// 똥
	protected void draw_ddong(Graphics g) {
		for (DDong ddong : ddongList) {
			g.drawImage(img_ball, (int) ddong.pos.x, (int) ddong.pos.y,
					(int) ddong.pos.width, (int) ddong.pos.height, this);
		}
	}

	protected void draw_ddonggun(Graphics g) {
		for (DDongGun ddong6_4gun : ddonggunList) {
			g.drawImage(img_missile3, (int) ddong6_4gun.pos.x,
					(int) ddong6_4gun.pos.y, (int) ddong6_4gun.pos.width,
					(int) ddong6_4gun.pos.height, this);
		}
	}

	// 똥 2
	protected void draw_ddong2(Graphics g) {
		for (DDong2 ddong2 : ddong2List) {
			g.drawImage(img_enemy2, (int) ddong2.pos2.x, (int) ddong2.pos2.y,
					(int) ddong2.pos2.width, (int) ddong2.pos2.height, this);
		}
	}

	protected void draw_ddong2gun(Graphics g) {
		for (DDong2Gun ddong6_4gun : ddong2gunList) {
			g.drawImage(img_missile3, (int) ddong6_4gun.pos.x,
					(int) ddong6_4gun.pos.y, (int) ddong6_4gun.pos.width,
					(int) ddong6_4gun.pos.height, this);
		}
	}

	// 똥3
	protected void draw_ddong3(Graphics g) {
		for (DDong3 ddong3 : ddong3List) {
			g.drawImage(img_enemy3, (int) ddong3.pos3.x, (int) ddong3.pos3.y,
					(int) ddong3.pos3.width, (int) ddong3.pos3.height, this);
		}

	}

	// 똥3B
	protected void draw_ddong3B(Graphics g) {
		for (DDong3B ddong3b : ddong3BList) {
			g.drawImage(img_enemy3_1, (int) ddong3b.pos3B.x,
					(int) ddong3b.pos3B.y, (int) ddong3b.pos3B.width,
					(int) ddong3b.pos3B.height, this);
		}
	}

	protected void draw_ddong3gun(Graphics g) {
		for (DDong3Gun ddong6_4gun : ddong3gunList) {
			g.drawImage(img_missile3, (int) ddong6_4gun.pos.x,
					(int) ddong6_4gun.pos.y, (int) ddong6_4gun.pos.width,
					(int) ddong6_4gun.pos.height, this);
		}

	}

	// 똥4

	protected void draw_ddong4(Graphics g) {
		for (DDong4 ddong4 : ddong4List) {
			g.drawImage(img_enemy4, (int) ddong4.pos.x, (int) ddong4.pos.y,
					(int) ddong4.pos.width, (int) ddong4.pos.height, this);
		}

	}

	protected void draw_ddong4_1(Graphics g) {
		for (DDong4 ddong4_1 : ddong4_2List) {
			g.drawImage(img_enemy4_2, (int) ddong4_1.pos.x,
					(int) ddong4_1.pos.y, (int) ddong4_1.pos.width,
					(int) ddong4_1.pos.height, this);
		}

	}

	protected void draw_ddong4gun(Graphics g) {
		for (DDong4Gun ddong6_4gun : ddong4gunList) {
			g.drawImage(img_missile4, (int) ddong6_4gun.pos.x,
					(int) ddong6_4gun.pos.y, (int) ddong6_4gun.pos.width,
					(int) ddong6_4gun.pos.height, this);
		}
	}

	protected void draw_ddong4_2gun(Graphics g) {
		for (DDong4Gun ddong6_4gun : ddong4_2gunList) {
			g.drawImage(img_missile4, (int) ddong6_4gun.pos.x,
					(int) ddong6_4gun.pos.y, (int) ddong6_4gun.pos.width,
					(int) ddong6_4gun.pos.height, this);
		}
	}

	// 똥5

	protected void draw_ddong5(Graphics g) {
		for (DDong5 ddong5 : ddong5List) {
			g.drawImage(img_buboss, (int) ddong5.pos.x,
					(int) ddong5.pos.y - 10, (int) ddong5.pos.width + 15,
					(int) ddong5.pos.height + 20, this);
		}
	}

	protected void draw_ddong5gun(Graphics g) {

		for (DDong5Gun ddong5gun : ddong5_1gunList) {
			g.drawImage(img_missile3, (int) ddong5gun.pos.x,
					(int) ddong5gun.pos.y, (int) ddong5gun.pos.width,
					(int) ddong5gun.pos.height, this);

		}
	}

	protected void draw_ddong5_2gun(Graphics g) {
		for (DDong5Gun ddong5_2gun : ddong5_2gunList) {
			g.drawImage(img_missile4, (int) ddong5_2gun.pos.x,
					(int) ddong5_2gun.pos.y, (int) ddong5_2gun.pos.width,
					(int) ddong5_2gun.pos.height, this);
		}
		for (DDong5Gun ddong5_3gun : ddong5_3gunList) {
			g.drawImage(img_missile4, (int) ddong5_3gun.pos.x,
					(int) ddong5_3gun.pos.y, (int) ddong5_3gun.pos.width,
					(int) ddong5_3gun.pos.height, this);
		}
		for (DDong5Gun ddong5_4gun : ddong5_4gunList) {
			g.drawImage(img_missile4, (int) ddong5_4gun.pos.x,
					(int) ddong5_4gun.pos.y, (int) ddong5_4gun.pos.width,
					(int) ddong5_4gun.pos.height, this);
		}
		for (DDong5Gun ddong5_5gun : ddong5_5gunList) {
			g.drawImage(img_missile4, (int) ddong5_5gun.pos.x,
					(int) ddong5_5gun.pos.y, (int) ddong5_5gun.pos.width,
					(int) ddong5_5gun.pos.height, this);
		}
		for (DDong5Gun ddong5_6gun : ddong5_6gunList) {
			g.drawImage(img_missile4, (int) ddong5_6gun.pos.x,
					(int) ddong5_6gun.pos.y, (int) ddong5_6gun.pos.width,
					(int) ddong5_6gun.pos.height, this);
		}
		for (DDong5Gun ddong5_7gun : ddong5_7gunList) {
			g.drawImage(img_missile4, (int) ddong5_7gun.pos.x,
					(int) ddong5_7gun.pos.y, (int) ddong5_7gun.pos.width,
					(int) ddong5_7gun.pos.height, this);
		}
		for (DDong5Gun ddong5_8gun : ddong5_8gunList) {
			g.drawImage(img_missile4, (int) ddong5_8gun.pos.x,
					(int) ddong5_8gun.pos.y, (int) ddong5_8gun.pos.width,
					(int) ddong5_8gun.pos.height, this);
		}
		for (DDong5Gun ddong5_9gun : ddong5_9gunList) {
			g.drawImage(img_missile4, (int) ddong5_9gun.pos.x,
					(int) ddong5_9gun.pos.y, (int) ddong5_9gun.pos.width,
					(int) ddong5_9gun.pos.height, this);
		}
		for (DDong5Gun ddong5_10gun : ddong5_10gunList) {
			g.drawImage(img_missile4, (int) ddong5_10gun.pos.x,
					(int) ddong5_10gun.pos.y, (int) ddong5_10gun.pos.width,
					(int) ddong5_10gun.pos.height, this);
		}
		for (DDong5Gun ddong5_11gun : ddong5_11gunList) {
			g.drawImage(img_missile4, (int) ddong5_11gun.pos.x,
					(int) ddong5_11gun.pos.y, (int) ddong5_11gun.pos.width,
					(int) ddong5_11gun.pos.height, this);
		}
		for (DDong5Gun ddong5_12gun : ddong5_12gunList) {
			g.drawImage(img_missile3, (int) ddong5_12gun.pos.x,
					(int) ddong5_12gun.pos.y, (int) ddong5_12gun.pos.width,
					(int) ddong5_12gun.pos.height, this);
		}
		for (DDong5Gun ddong5_13gun : ddong5_13gunList) {
			g.drawImage(img_missile3, (int) ddong5_13gun.pos.x,
					(int) ddong5_13gun.pos.y, (int) ddong5_13gun.pos.width,
					(int) ddong5_13gun.pos.height, this);
		}
		for (DDong5Gun ddong5_14gun : ddong5_14gunList) {
			g.drawImage(img_missile3, (int) ddong5_14gun.pos.x,
					(int) ddong5_14gun.pos.y, (int) ddong5_14gun.pos.width,
					(int) ddong5_14gun.pos.height, this);
		}

	}

	// 똥6
	protected void draw_ddong6(Graphics g) {
		for (DDong6 ddong6 : ddong6List) {
			g.drawImage(img_boss1, (int) ddong6.pos.x, (int) ddong6.pos.y,
					(int) ddong6.pos.width + 20, (int) ddong6.pos.height + 20,
					this);
		}

	}

	protected void draw_ddong6gun(Graphics g) {
		for (DDong6Gun ddong6_1gun : ddong6_1gunList) {
			g.drawImage(img_missile4, (int) ddong6_1gun.pos.x,
					(int) ddong6_1gun.pos.y, (int) ddong6_1gun.pos.width,
					(int) ddong6_1gun.pos.height, this);
		}
		for (DDong6Gun ddong6_2gun : ddong6_2gunList) {
			g.drawImage(img_missile4, (int) ddong6_2gun.pos.x,
					(int) ddong6_2gun.pos.y, (int) ddong6_2gun.pos.width,
					(int) ddong6_2gun.pos.height, this);
		}
		for (DDong6Gun ddong6_3gun : ddong6_3gunList) {
			g.drawImage(img_missile3, (int) ddong6_3gun.pos.x,
					(int) ddong6_3gun.pos.y, (int) ddong6_3gun.pos.width,
					(int) ddong6_3gun.pos.height, this);
		}
		for (DDong6Gun ddong6_4gun : ddong6_4gunList) {
			g.drawImage(img_missile3, (int) ddong6_4gun.pos.x,
					(int) ddong6_4gun.pos.y, (int) ddong6_4gun.pos.width,
					(int) ddong6_4gun.pos.height, this);
		}
	}

	// 똥7
	protected void draw_ddong7(Graphics g) {
		for (DDong7 ddong7 : ddong7List) {
			g.drawImage(img_boss2, (int) ddong7.pos.x, (int) ddong7.pos.y,
					(int) ddong7.pos.width, (int) ddong7.pos.height, this);
		}
	}

	protected void draw_ddong7gun(Graphics g) {
		for (DDong7Gun ddong7_1gun : ddong7_1gunList) {
			g.drawImage(img_missile3, (int) ddong7_1gun.pos.x,
					(int) ddong7_1gun.pos.y, (int) ddong7_1gun.pos.width,
					(int) ddong7_1gun.pos.height, this);
		}
		for (DDong7Gun ddong7_1gun : ddong7_2gunList) {
			g.drawImage(img_missile3, (int) ddong7_1gun.pos.x,
					(int) ddong7_1gun.pos.y, (int) ddong7_1gun.pos.width,
					(int) ddong7_1gun.pos.height, this);
		}
		for (DDong7Gun ddong7_1gun : ddong7_3gunList) {
			g.drawImage(img_missile4, (int) ddong7_1gun.pos.x,
					(int) ddong7_1gun.pos.y, (int) ddong7_1gun.pos.width,
					(int) ddong7_1gun.pos.height, this);
		}
		for (DDong7Gun ddong7_1gun : ddong7_4gunList) {
			g.drawImage(img_missile4, (int) ddong7_1gun.pos.x,
					(int) ddong7_1gun.pos.y, (int) ddong7_1gun.pos.width,
					(int) ddong7_1gun.pos.height, this);
		}
		for (DDong7Gun ddong7_5gun : ddong7_5gunList) {
			g.drawImage(img_missile5, (int) ddong7_5gun.pos.x,
					(int) ddong7_5gun.pos.y, (int) ddong7_5gun.pos.width,
					(int) ddong7_5gun.pos.height, this);
		}
		for (DDong7Gun ddong7_6gun : ddong7_6gunList) {
			g.drawImage(img_missile3, (int) ddong7_6gun.pos.x,
					(int) ddong7_6gun.pos.y, (int) ddong7_6gun.pos.width,
					(int) ddong7_6gun.pos.height, this);
		}
		for (DDong7Gun ddong7_7gun : ddong7_7gunList) {
			g.drawImage(img_missile3, (int) ddong7_7gun.pos.x,
					(int) ddong7_7gun.pos.y, (int) ddong7_7gun.pos.width,
					(int) ddong7_7gun.pos.height, this);
		}
		for (DDong7Gun ddong7_8gun : ddong7_8gunList) {
			g.drawImage(img_missile3, (int) ddong7_8gun.pos.x,
					(int) ddong7_8gun.pos.y, (int) ddong7_8gun.pos.width,
					(int) ddong7_8gun.pos.height, this);
		}
		for (DDong7Gun ddong7_9gun : ddong7_9gunList) {
			g.drawImage(img_missile3, (int) ddong7_9gun.pos.x,
					(int) ddong7_9gun.pos.y, (int) ddong7_9gun.pos.width,
					(int) ddong7_9gun.pos.height, this);
		}

	}

	// 플레이어총
	protected void draw_gun(Graphics g) {
		for (Gun gun : gunlist) {
			g.drawImage(img_missile1, (int) gun.pos.x, (int) gun.pos.y,
					(int) gun.pos.width, (int) gun.pos.height, this);
		}
		for (Gun gun2 : gun2list) {
			g.drawImage(img_missile1, (int) gun2.pos.x, (int) gun2.pos.y,
					(int) gun2.pos.width, (int) gun2.pos.height, this);
		}
		for (Gun gun3 : gun3list) {
			g.drawImage(img_missile2, (int) gun3.pos.x, (int) gun3.pos.y,
					(int) gun3.pos.width, (int) gun3.pos.height, this);
		}
	}

	// 플레이어차지
	int index_charge;

	protected void draw_guncharge(Graphics g) {
		if (imgchargeList.size() == 0)
			return;
		ImgMove imgcharge = imgchargeList.get(0);
		g.drawImage(img_charge[imgcharge.index_charge], me_rect.x
				+ me_rect.width, me_rect.y - 129, this);

		if (imgcharge.move() == false) {

			// 인덱스 카운트를 설정해둔 move메소드를 이용하여 이미지를 모두 출력 후 리므부
			imgchargeList.remove(imgcharge);
			return;
		}
	}

	// 폭발
	int index;

	protected void draw_bomb(Graphics g) {
		for (Bomb bomb : bombList) {
			g.drawImage(img_bomb[bomb.index], (int) bomb.x, (int) bomb.y - 50,
					this);
			if (bomb.move() == false) {
				// 23장의 그림을 모두 출력
				bombList.remove(bomb);
				return;
			}
		}

		// 움직이는 png넣기
		/*
		 * g.drawImage(img_bomb[index], 0, 0, this);
		 * 
		 * index++; if (index > 24) index = 0;
		 */

	}

	double ce_index;

	protected void draw_ce(Graphics g) {
		for (ImgMove ce : imgceList) {
			g.drawImage(img_ce[ce.ce_index], (int) ce.x, (int) ce.y, this);
			if (ce.move_ce() == false) {
				// 23장의 그림을 모두 출력
				imgceList.remove(ce);
				return;
			}
		}
	}

	int be_index;

	protected void draw_be(Graphics g) {
		if (imgbeList.size() == 0) {
			return;
		}
		ImgMove be = imgbeList.get(0);

		g.drawImage(img_be[be.be_index], (int) be.x, (int) be.y, this);
		if (be.move_be() == false) {
			// 23장의 그림을 모두 출력
			imgbeList.remove(be);
			return;
		}

	}

	// ========================================아이템=======================================
	// hp
	protected void draw_life(Graphics g) {
		for (Life life : lifeList) {
			g.drawImage(img_hp, life.pos.x, life.pos.y, life.pos.width,
					life.pos.height, this);
		}
	}

	protected void draw_powerup(Graphics g) {
		for (ItemPowerUp powerup : poweruplist) {
			g.drawImage(img_powerup, powerup.pos.x, powerup.pos.y,
					powerup.pos.width, powerup.pos.height, this);
		}
	}

	public static void main(String[] args) {
		new MyMain();
		System.out.println("시작!!");

	}
}