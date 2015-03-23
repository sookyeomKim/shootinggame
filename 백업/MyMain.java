package mymain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import myutil.Gun;
import myutil.GunSuper;
import myutil.ImgCharge;
import myutil.Life;

public class MyMain extends JFrame implements Runnable {
	public Random ra = new Random();

	// 게임판의 크기설정상수
	public static final int GAMEPAN_W = 1024;
	public static final int GAMEPAN_H = 576;
	// 키이벤트 상수
	public static final int LEFT = KeyEvent.VK_LEFT;
	public static final int RIGHT = KeyEvent.VK_RIGHT;
	public static final int UP = KeyEvent.VK_UP;
	public static final int DOWN = KeyEvent.VK_DOWN;
	// 백그라운드 상수
	public int offset_x = 0;
	// 이미지 초기화
	public static Image img_ddong, img_bar, img_base, img_hp, img_engel,
			img_capture, img_yura, img_water, img_cosmos, img_background;
	public static Image[] img_bomb, img_charge;// 폭발이미지

	static {
		img_engel = new ImageIcon("image/engel.png").getImage();
		img_ddong = new ImageIcon("image/ddong.png").getImage();
		img_bar = new ImageIcon("image/bar.png").getImage();
		img_base = new ImageIcon("image/base.png").getImage();
		img_hp = new ImageIcon("image/hp.png").getImage();
		img_capture = new ImageIcon("image/capture.png").getImage();
		img_yura = new ImageIcon("image/yura.png").getImage();
		img_water = new ImageIcon("image/water.png").getImage();
		img_cosmos = new ImageIcon("image/cosmos.png").getImage();
		img_background = new ImageIcon("image/background.png").getImage();

		img_bomb = new Image[27];
		for (int i = 0; i < img_bomb.length; i++) {
			String filename = String.format("image/bomb/m%02d.png", i + 1);
			img_bomb[i] = new ImageIcon(filename).getImage();
		}
		img_charge = new Image[3];
		for (int i = 0; i < img_charge.length; i++) {
			String filename = String.format("image/charge/a%02d.png", i + 1);
			img_charge[i] = new ImageIcon(filename).getImage();
		}
	}

	// 타이머
	public Timer timer;
	public JPanel gamePanel;

	// 나(사람)
	public Rectangle me_rect, life_rect, life_base_rect, hp_bar;

	// 키관련
	public boolean bPressed = false;// 키 눌림?
	public int key;// 눌린 키값
	public boolean gun_Pressed = false;// 총 눌림?

	public boolean gunCharge = false;// 기모으기
	public int gunCharge_attack = 1;// 기모으기 발사!_!
	public int gunCharge_if = 0;// 발동조건

	// 폭탄 리스트 관리 객체
	public List<Bomb> bombList = new ArrayList<Bomb>();

	// 기모으기 이미지 리스트 관리 객체
	public List<ImgCharge> imgchargeList = new ArrayList<ImgCharge>();

	// -------------------------------------------------------똥---------------------------------

	// 똥크기 상수
	public final int DDONG_W = 30;// 다 만들고 위로 빼져
	public final int DDONG_H = 30;
	// 똥통
	public List<DDong> ddongList = new ArrayList<DDong>();
	// 똥 만드는 간격
	public final int DDONG_MAKE_INTERVAL = 70;
	// 똥 만드는 간격을 관리하는 변수
	public int ddong_make_interval = DDONG_MAKE_INTERVAL;
	// ----------------------------------------------똥--------------------------------------

	// ------------------------------------------------라이프-------------------------------------
	// 목숨크기
	public final int Life_W = 30;
	public final int Life_H = 30;
	// 라이프통
	public List<Life> lifeList = new ArrayList<Life>();
	// 목숨 만드는 간격
	public final int LIFE_MAKE_INTERVAL = 200;
	// 목숨 만드는 간격을 관리하는 변수
	public int life_make_interval = LIFE_MAKE_INTERVAL;

	// -----------------------------------------라이프---------------------------------------

	// -------------------------총----------------------------
	// 총알 크기
	public final int GUN_W = 15;
	public final int GUN_H = 15;

	// 총알집
	public List<Gun> gunlist = new ArrayList<Gun>();
	// 총알 간격
	public final int GUN_MAKE_TNTERVAL = 3;
	// 목숨 만드는 간격을 관리하는 변수
	public int gun_make_interval = GUN_MAKE_TNTERVAL;

	// -------------------------총----------------------------
	// -----------------기모으기-----------------
	public final int GUNSUPER_MAKE_INTERVAL = 1;
	public int gunsuper_make_interval = GUNSUPER_MAKE_INTERVAL;
	public final int GUNCHARGE_W = 250;
	public final int GUNCHARGE_H = 80;
	public List<GunSuper> gunChargelist = new ArrayList<GunSuper>();
	// ---------------------------------------------------

	// -----------------------몬스터 HP----------------------------
	public int normal_monster_hp = 2; // 기본 몬스터 hp=2;

	Timer gunTimer;
	ActionListener gunListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gunCharge_if = 0;
			gunTimer.stop();
			gunChargelist.clear();
			imgchargeList.clear();
		}
	};

	Timer gunchargetimer;
	ActionListener gunChargeListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gunCharge_if = 2;
		}
	};

	public MyMain() {
		super("똥피하기");// title
		gunchargetimer = new Timer(1000, gunChargeListener);
		gunTimer = new Timer(200, gunListener);

		// 게임에 사용 될 변수 초기화
		initGame();

		// 게임판을 생성
		initGamePanel();

		// 키이벤트 초기화
		initKeyEvent();

		// 게임시작
		startGame();

		// 어디에 배치
		setLocation(200, 0);// 바탕화면을 기준
		setResizable(false);

		// 크기
		pack();// 자식 윈도우를 감싼다.
		// setSize(300, 300);//쓰면안됨 이유는 필기 ㄱ

		// view
		setVisible(true);

		// 종료코드
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void startGame() {
		// TODO Auto-generated method stub
		// 타이머구동객체
		ActionListener timer_proc = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 필요한 연산작업
				// process();
				tr.start();

				// 화면을 다시 그려라
				gamePanel.repaint();// paintComponent Call
			}
		};

		// 타이머 구동 시간(1/1000초)
		timer = new Timer(10, timer_proc);
		timer.start();

	}

	protected void process() {
		// 배경스크롤 시키기
		move_back();
		// -----------------------똥,목숨생성라인---------------------------------
		// 똥 만들기
		if (ddong_make_interval == DDONG_MAKE_INTERVAL)
			make_ddong();
		ddong_make_interval--;
		if (ddong_make_interval < 0) {
			ddong_make_interval = DDONG_MAKE_INTERVAL;
		}
		// 똥을 이동시키기
		move_ddong();

		// 목숨 만들기
		if (life_make_interval == LIFE_MAKE_INTERVAL)
			make_life();
		life_make_interval--;
		if (life_make_interval < 0) {
			life_make_interval = LIFE_MAKE_INTERVAL;
		}
		// 목숨을 이동시키기
		move_life();
		// -------------------------------------------------------------------------------------------------
		// 충돌체크

		// ---------------------총알 생성라인-----------------------------
		if (gun_Pressed) {
			if (gun_make_interval == GUN_MAKE_TNTERVAL)
				make_gun();
			gun_make_interval--;
			if (gun_make_interval < 0) {
				gun_make_interval = GUN_MAKE_TNTERVAL;
			}
		}

		move_gun();

		gun_intersects();
		// -------------------------------------------------------
		// --------------------------------기모으기--------------------------

		if (gunCharge_attack == gunCharge_if) {
			make_gunCharge();
			gunTimer.start();
		}

		guncharge_intersects();
		// ------------------------------------------------------

		collision_me_energy();
		heeling();
		/*
		 * // 똥 갯수 확인 setTitle("똥 갯수: " + ddongList.size());
		 */

		if (bPressed)// 키가 눌려있으면
		{
			move_me();
		}
	}

	MyMain mm;
	Thread tr = new Thread(mm);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 배경스크롤 시키기
		while (true) {
			move_back();
			// -----------------------똥,목숨생성라인---------------------------------
			// 똥 만들기
			if (ddong_make_interval == DDONG_MAKE_INTERVAL)
				make_ddong();
			ddong_make_interval--;
			if (ddong_make_interval < 0) {
				ddong_make_interval = DDONG_MAKE_INTERVAL;
			}
			// 똥을 이동시키기
			move_ddong();

			// 목숨 만들기
			if (life_make_interval == LIFE_MAKE_INTERVAL)
				make_life();
			life_make_interval--;
			if (life_make_interval < 0) {
				life_make_interval = LIFE_MAKE_INTERVAL;
			}
			// 목숨을 이동시키기
			move_life();
			// -------------------------------------------------------------------------------------------------
			// 충돌체크

			// ---------------------총알 생성라인-----------------------------
			if (gun_Pressed) {
				if (gun_make_interval == GUN_MAKE_TNTERVAL)
					make_gun();
				gun_make_interval--;
				if (gun_make_interval < 0) {
					gun_make_interval = GUN_MAKE_TNTERVAL;
				}
			}

			move_gun();

			gun_intersects();
			// -------------------------------------------------------
			// --------------------------------기모으기--------------------------

			if (gunCharge_attack == gunCharge_if) {
				make_gunCharge();
				gunTimer.start();
			}

			guncharge_intersects();
			// ------------------------------------------------------

			collision_me_energy();
			heeling();
			/*
			 * // 똥 갯수 확인 setTitle("똥 갯수: " + ddongList.size());
			 */

			if (bPressed)// 키가 눌려있으면
			{
				move_me();
			}
		}
	}

	private void move_back() {
		offset_x++;
		if (offset_x > img_background.getWidth(this) - GAMEPAN_W) {
			offset_x = 0;
		}

	}

	private void guncharge_intersects() {
		for (GunSuper gunsuper : gunChargelist) {
			charge(gunsuper);
			for (DDong ddong : ddongList) {
				if (gunsuper.pos.intersects(ddong.pos)) {
					normal_monster_hp -= 2;
					if (normal_monster_hp < 0) {
						explosion(ddong);// 몬스터 터지는 효과
						ddongList.remove(ddong);
						normal_monster_hp = 2;
					}

					return;
				}
			}
		}

	}

	private void make_gunCharge() {
		int y = me_rect.y - GUNCHARGE_H / 4;
		int x = me_rect.x + me_rect.width;

		int speed = 1;// me_rect.x+me_rect.width;

		Rectangle pos = new Rectangle(x, y, GUNCHARGE_W, GUNCHARGE_H);

		GunSuper gunsuper = new GunSuper(pos, speed);
		gunChargelist.add(gunsuper);

	}

	private void make_gun() {
		// 총 나가는 위치
		int y = me_rect.y + me_rect.height / 2;
		int x = me_rect.x + me_rect.width;
		// 총알 속도
		int speed = ra.nextInt(1) + 9;

		Rectangle pos = new Rectangle(x, y, GUN_W, GUN_H);
		// 총알객체 생성
		Gun gun = new Gun(pos, speed);
		gunlist.add(gun);

	}

	private void move_gun() {
		for (Gun gun : gunlist) {
			gun.move_up();
			// 화면에서 벗어나면 제거
			if (gun.pos.x > GAMEPAN_W) {
				gunlist.remove(gun);
				return;
			}
		}

	}

	private void gun_intersects() {
		for (Gun gun : gunlist) {
			for (DDong ddong : ddongList) {
				if (gun.pos.intersects(ddong.pos)) {
					gunlist.remove(gun);
					normal_monster_hp -= 1;
					if (normal_monster_hp < 0) {
						explosion(ddong);// 몬스터 터지는 효과
						ddongList.remove(ddong);
						normal_monster_hp = 2;
					}

					return;
				}
			}
		}
	}

	private void make_ddong() {
		// 똥 투하 위치 계산

		int x = GAMEPAN_W + DDONG_W;
		int y = ra.nextInt(GAMEPAN_H) + DDONG_H / 2;
		// 똥 떨어지는 속도
		int speed = 3;

		Rectangle pos = new Rectangle(x, y, DDONG_W, DDONG_H);
		// 똥 객체 생성
		DDong ddong = new DDong(pos, speed);
		// 만든 똥을 똥통에 추가
		ddongList.add(ddong);

	}

	private void move_ddong() {
		for (DDong ddong : ddongList) {
			ddong.move_down();
			// 화면 아래로 벗어나면 제거
			if (ddong.pos.x < 0) {
				ddongList.remove(ddong);
				return;
			}
		}
	}

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

	private void explosion(DDong ddong) {
		// 폭발객체 생성 // 폭발위치 계산
		int x = ddong.pos.x - DDONG_W;
		int y = ddong.pos.y - img_bomb[0].getHeight(this) / 6;
		Bomb bomb = new Bomb(x, y); // 폭발리스트 관리객체
		bombList.add(bomb);

	}

	// 차지객체 생성
	private void charge(GunSuper gunsuper) {
		int x = gunsuper.pos.x;
		int y = gunsuper.pos.y;
		ImgCharge imgcharge = new ImgCharge(x, y);
		imgchargeList.add(imgcharge);

	}

	private void collision_me_energy() {
		for (DDong ddong : ddongList) {
			if (me_rect.intersects(ddong.pos)) {
				life_rect.width -= 50;
				ddongList.remove(ddong);
				if (life_rect.width <= 0) {
					timer.stop();
					// 메시지 다이어로그
					JOptionPane.showMessageDialog(this, "다시해봐!!!!");
				}
				return;
			}
		}

	}

	private void heeling() {
		for (Life life : lifeList) {
			if (me_rect.intersects(life.pos)) {
				life_rect.width += 50;
				if (life_rect.width >= life_base_rect.width - hp_bar.width) {
					life_rect.width = life_base_rect.width - hp_bar.width;
				}
				lifeList.remove(life);
				return;
			}
		}
	}

	// 키상태 상수
	static final int K_LEFT = 0x00001;
	static final int K_RIGHT = 0x00010;
	static final int K_UP = 0x00100;
	static final int K_DOWN = 0x01000;
	static final int GUN = 0x10000;

	// 키상태변수
	int key_state = 0;
	int gan = 3;

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

				if (key == 'P') {
					// 이전에 남아있는 똥 제거
					ddongList.clear();
					lifeList.clear();
					bombList.clear();
					gunlist.clear();
					gunChargelist.clear();
					timer.start();
					key_state = 0;
					life_rect.width += GAMEPAN_W - 80;

				}
				if ((key_state & GUN) == GUN) {
					// if (key == 'A') {// 총 눌린 정보
					gun_Pressed = true;
				}
				if (key == 'S') {
					if (gunCharge == false) {
						gunchargetimer.start();
						gunCharge = true;

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
					gunCharge = false;
					// gunCharge_attack 펄스값 초기화만 시켜주기
				}
			}
		};

		// this(MyMain)에서 키가 눌리면 k_listener에게 처리를 위임하겠다.
		this.addKeyListener(k_listener);
	}

	private void initGame() {
		// 이렇게 바로 rect을 안 만들어주고 메소드를 이용해서 만들면
		// 후에 값을 넣어줄 때 가변성에 용이
		me_rect = new Rectangle(0, GAMEPAN_H / 2, 60, 40);
		life_rect = new Rectangle(100, GAMEPAN_H - 50, GAMEPAN_W - 100, 50);
		life_base_rect = new Rectangle(0, GAMEPAN_H - 50, GAMEPAN_W, 50);
		hp_bar = new Rectangle(0, GAMEPAN_H - 50, 100, 50);

	}

	private void initGamePanel() {
		// 객체 생성과 동시에 재정의(paintComponent)
		gamePanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {// Graphics그리기도구
				// Graphics : 그리기도구(펜,붓)
				// 이전화면 지우기
				// g.clearRect(0, 0, GAMEPAN_W, GAMEPAN_H);//배경그림이 덮어버림으로 필요 없음
				draw_background(g);

				// 똥 실체화 하기
				draw_ddong(g);

				// 폭발이미지 그리기
				draw_bomb(g);
				// 목숨이미지 그리기
				draw_life(g);
				// 총알 그리기
				draw_gun(g);
				// 기모으기 그리기
				draw_guncharge(g);

				// 에너지바 바닥 이미지
				g.drawImage(img_base, life_base_rect.x, life_base_rect.y,
						life_base_rect.width, life_base_rect.height, this);
				// 에너지바 이미지
				g.drawImage(img_bar, life_rect.x, life_rect.y, life_rect.width,
						life_rect.height, this);
				// 목숨 이미지
				g.drawImage(img_hp, hp_bar.x, hp_bar.y, hp_bar.width,
						hp_bar.height, this);
				// 나(사람)
				g.drawImage(img_engel, me_rect.x, me_rect.y, me_rect.width,
						me_rect.height, this);

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

	protected void draw_background(Graphics g) {
		g.drawImage(img_background, 0, 0, GAMEPAN_W, GAMEPAN_H, // 화면좌표
				offset_x, 0, offset_x + GAMEPAN_W, GAMEPAN_H,// 배경그림좌표
				null);

	}

	// ------------------------------폭발,똥,목숨 그리기--------------------------
	int index_charge;

	protected void draw_guncharge(Graphics g) {

		for (ImgCharge imgcharge : imgchargeList) {
			g.drawImage(img_charge[imgcharge.index_charge], imgcharge.x,
					imgcharge.y, this);
			if (imgcharge.move() == false) {// 인덱스 카운트를 설정해둔 move메소드를 이용하여 이미지를
											// 모두 출력 후 리므부
				imgchargeList.remove(imgcharge);
				return;
			}
			/*
			 * if (imgcharge.move() == false) { imgchargeList.remove(imgcharge);
			 * return; }
			 */
		}
	}

	protected void draw_gun(Graphics g) {
		for (Gun Gun : gunlist) {
			g.setColor(Color.white);
			g.fillRect(Gun.pos.x, Gun.pos.y, Gun.pos.width, Gun.pos.height);
		}
	}

	// 움직이는 png넣기
	int index;

	protected void draw_bomb(Graphics g) {
		for (Bomb bomb : bombList) {
			g.drawImage(img_bomb[bomb.index], bomb.x, bomb.y - 50, this);
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

	protected void draw_ddong(Graphics g) {

		for (DDong ddong : ddongList) {
			// 원그리기

			g.fillOval(ddong.pos.x, ddong.pos.y, ddong.pos.width,
					ddong.pos.height);

			/*
			 * g.drawImage(img_water, ddong.pos.x, ddong.pos.y, ddong.pos.width,
			 * ddong.pos.height, this);
			 */
		}
	}

	protected void draw_life(Graphics g) {
		for (Life life : lifeList) {
			/*
			 * g.drawImage(img_hp, life.pos.x, life.pos.y, life.pos.width,
			 * life.pos.height, this);
			 */
			g.setColor(Color.red);
			g.fillOval(life.pos.x, life.pos.y, life.pos.width, life.pos.height);
		}
	}

	public static void main(String[] args) {
		new MyMain();
	}

}