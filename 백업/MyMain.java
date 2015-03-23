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

	// �������� ũ�⼳�����
	public static final int GAMEPAN_W = 1024;
	public static final int GAMEPAN_H = 576;
	// Ű�̺�Ʈ ���
	public static final int LEFT = KeyEvent.VK_LEFT;
	public static final int RIGHT = KeyEvent.VK_RIGHT;
	public static final int UP = KeyEvent.VK_UP;
	public static final int DOWN = KeyEvent.VK_DOWN;
	// ��׶��� ���
	public int offset_x = 0;
	// �̹��� �ʱ�ȭ
	public static Image img_ddong, img_bar, img_base, img_hp, img_engel,
			img_capture, img_yura, img_water, img_cosmos, img_background;
	public static Image[] img_bomb, img_charge;// �����̹���

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

	// Ÿ�̸�
	public Timer timer;
	public JPanel gamePanel;

	// ��(���)
	public Rectangle me_rect, life_rect, life_base_rect, hp_bar;

	// Ű����
	public boolean bPressed = false;// Ű ����?
	public int key;// ���� Ű��
	public boolean gun_Pressed = false;// �� ����?

	public boolean gunCharge = false;// �������
	public int gunCharge_attack = 1;// ������� �߻�!_!
	public int gunCharge_if = 0;// �ߵ�����

	// ��ź ����Ʈ ���� ��ü
	public List<Bomb> bombList = new ArrayList<Bomb>();

	// ������� �̹��� ����Ʈ ���� ��ü
	public List<ImgCharge> imgchargeList = new ArrayList<ImgCharge>();

	// -------------------------------------------------------��---------------------------------

	// ��ũ�� ���
	public final int DDONG_W = 30;// �� ����� ���� ����
	public final int DDONG_H = 30;
	// ����
	public List<DDong> ddongList = new ArrayList<DDong>();
	// �� ����� ����
	public final int DDONG_MAKE_INTERVAL = 70;
	// �� ����� ������ �����ϴ� ����
	public int ddong_make_interval = DDONG_MAKE_INTERVAL;
	// ----------------------------------------------��--------------------------------------

	// ------------------------------------------------������-------------------------------------
	// ���ũ��
	public final int Life_W = 30;
	public final int Life_H = 30;
	// ��������
	public List<Life> lifeList = new ArrayList<Life>();
	// ��� ����� ����
	public final int LIFE_MAKE_INTERVAL = 200;
	// ��� ����� ������ �����ϴ� ����
	public int life_make_interval = LIFE_MAKE_INTERVAL;

	// -----------------------------------------������---------------------------------------

	// -------------------------��----------------------------
	// �Ѿ� ũ��
	public final int GUN_W = 15;
	public final int GUN_H = 15;

	// �Ѿ���
	public List<Gun> gunlist = new ArrayList<Gun>();
	// �Ѿ� ����
	public final int GUN_MAKE_TNTERVAL = 3;
	// ��� ����� ������ �����ϴ� ����
	public int gun_make_interval = GUN_MAKE_TNTERVAL;

	// -------------------------��----------------------------
	// -----------------�������-----------------
	public final int GUNSUPER_MAKE_INTERVAL = 1;
	public int gunsuper_make_interval = GUNSUPER_MAKE_INTERVAL;
	public final int GUNCHARGE_W = 250;
	public final int GUNCHARGE_H = 80;
	public List<GunSuper> gunChargelist = new ArrayList<GunSuper>();
	// ---------------------------------------------------

	// -----------------------���� HP----------------------------
	public int normal_monster_hp = 2; // �⺻ ���� hp=2;

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
		super("�����ϱ�");// title
		gunchargetimer = new Timer(1000, gunChargeListener);
		gunTimer = new Timer(200, gunListener);

		// ���ӿ� ��� �� ���� �ʱ�ȭ
		initGame();

		// �������� ����
		initGamePanel();

		// Ű�̺�Ʈ �ʱ�ȭ
		initKeyEvent();

		// ���ӽ���
		startGame();

		// ��� ��ġ
		setLocation(200, 0);// ����ȭ���� ����
		setResizable(false);

		// ũ��
		pack();// �ڽ� �����츦 ���Ѵ�.
		// setSize(300, 300);//����ȵ� ������ �ʱ� ��

		// view
		setVisible(true);

		// �����ڵ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void startGame() {
		// TODO Auto-generated method stub
		// Ÿ�̸ӱ�����ü
		ActionListener timer_proc = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// �ʿ��� �����۾�
				// process();
				tr.start();

				// ȭ���� �ٽ� �׷���
				gamePanel.repaint();// paintComponent Call
			}
		};

		// Ÿ�̸� ���� �ð�(1/1000��)
		timer = new Timer(10, timer_proc);
		timer.start();

	}

	protected void process() {
		// ��潺ũ�� ��Ű��
		move_back();
		// -----------------------��,�����������---------------------------------
		// �� �����
		if (ddong_make_interval == DDONG_MAKE_INTERVAL)
			make_ddong();
		ddong_make_interval--;
		if (ddong_make_interval < 0) {
			ddong_make_interval = DDONG_MAKE_INTERVAL;
		}
		// ���� �̵���Ű��
		move_ddong();

		// ��� �����
		if (life_make_interval == LIFE_MAKE_INTERVAL)
			make_life();
		life_make_interval--;
		if (life_make_interval < 0) {
			life_make_interval = LIFE_MAKE_INTERVAL;
		}
		// ����� �̵���Ű��
		move_life();
		// -------------------------------------------------------------------------------------------------
		// �浹üũ

		// ---------------------�Ѿ� ��������-----------------------------
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
		// --------------------------------�������--------------------------

		if (gunCharge_attack == gunCharge_if) {
			make_gunCharge();
			gunTimer.start();
		}

		guncharge_intersects();
		// ------------------------------------------------------

		collision_me_energy();
		heeling();
		/*
		 * // �� ���� Ȯ�� setTitle("�� ����: " + ddongList.size());
		 */

		if (bPressed)// Ű�� ����������
		{
			move_me();
		}
	}

	MyMain mm;
	Thread tr = new Thread(mm);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// ��潺ũ�� ��Ű��
		while (true) {
			move_back();
			// -----------------------��,�����������---------------------------------
			// �� �����
			if (ddong_make_interval == DDONG_MAKE_INTERVAL)
				make_ddong();
			ddong_make_interval--;
			if (ddong_make_interval < 0) {
				ddong_make_interval = DDONG_MAKE_INTERVAL;
			}
			// ���� �̵���Ű��
			move_ddong();

			// ��� �����
			if (life_make_interval == LIFE_MAKE_INTERVAL)
				make_life();
			life_make_interval--;
			if (life_make_interval < 0) {
				life_make_interval = LIFE_MAKE_INTERVAL;
			}
			// ����� �̵���Ű��
			move_life();
			// -------------------------------------------------------------------------------------------------
			// �浹üũ

			// ---------------------�Ѿ� ��������-----------------------------
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
			// --------------------------------�������--------------------------

			if (gunCharge_attack == gunCharge_if) {
				make_gunCharge();
				gunTimer.start();
			}

			guncharge_intersects();
			// ------------------------------------------------------

			collision_me_energy();
			heeling();
			/*
			 * // �� ���� Ȯ�� setTitle("�� ����: " + ddongList.size());
			 */

			if (bPressed)// Ű�� ����������
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
						explosion(ddong);// ���� ������ ȿ��
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
		// �� ������ ��ġ
		int y = me_rect.y + me_rect.height / 2;
		int x = me_rect.x + me_rect.width;
		// �Ѿ� �ӵ�
		int speed = ra.nextInt(1) + 9;

		Rectangle pos = new Rectangle(x, y, GUN_W, GUN_H);
		// �Ѿ˰�ü ����
		Gun gun = new Gun(pos, speed);
		gunlist.add(gun);

	}

	private void move_gun() {
		for (Gun gun : gunlist) {
			gun.move_up();
			// ȭ�鿡�� ����� ����
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
						explosion(ddong);// ���� ������ ȿ��
						ddongList.remove(ddong);
						normal_monster_hp = 2;
					}

					return;
				}
			}
		}
	}

	private void make_ddong() {
		// �� ���� ��ġ ���

		int x = GAMEPAN_W + DDONG_W;
		int y = ra.nextInt(GAMEPAN_H) + DDONG_H / 2;
		// �� �������� �ӵ�
		int speed = 3;

		Rectangle pos = new Rectangle(x, y, DDONG_W, DDONG_H);
		// �� ��ü ����
		DDong ddong = new DDong(pos, speed);
		// ���� ���� ���뿡 �߰�
		ddongList.add(ddong);

	}

	private void move_ddong() {
		for (DDong ddong : ddongList) {
			ddong.move_down();
			// ȭ�� �Ʒ��� ����� ����
			if (ddong.pos.x < 0) {
				ddongList.remove(ddong);
				return;
			}
		}
	}

	private void make_life() {
		// ��� ���� ��ġ ���
		int x = GAMEPAN_W + Life_W;
		int y = ra.nextInt(GAMEPAN_H) + Life_H / 2;
		// ��� �������� �ӵ�
		int speed = ra.nextInt(1) + 8;

		Rectangle pos = new Rectangle(x, y, Life_W, Life_H);
		// ��� ��ü ����
		Life life = new Life(pos, speed);
		// ���� ����� ����� �߰�
		lifeList.add(life);

	}

	private void move_life() {
		for (Life life : lifeList) {
			life.move_down();

			// ȭ�� �Ʒ��� ����� ����
			if (life.pos.x < 0) {
				// �����ü ����
				// �����ġ ���
				lifeList.remove(life);
				return;// for������ ������ �ֱ� ������ ������ż� �������� �ϳ��� ������� ��������
			}
		}
	}

	private void explosion(DDong ddong) {
		// ���߰�ü ���� // ������ġ ���
		int x = ddong.pos.x - DDONG_W;
		int y = ddong.pos.y - img_bomb[0].getHeight(this) / 6;
		Bomb bomb = new Bomb(x, y); // ���߸���Ʈ ������ü
		bombList.add(bomb);

	}

	// ������ü ����
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
					// �޽��� ���̾�α�
					JOptionPane.showMessageDialog(this, "�ٽ��غ�!!!!");
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

	// Ű���� ���
	static final int K_LEFT = 0x00001;
	static final int K_RIGHT = 0x00010;
	static final int K_UP = 0x00100;
	static final int K_DOWN = 0x01000;
	static final int GUN = 0x10000;

	// Ű���º���
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

				key = e.getKeyCode();// ����Ű��

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
					// ������ �����ִ� �� ����
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
					// if (key == 'A') {// �� ���� ����
					gun_Pressed = true;
				}
				if (key == 'S') {
					if (gunCharge == false) {
						gunchargetimer.start();
						gunCharge = true;

					}
				}

				bPressed = true;// ��� ��������
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
					// gunCharge_attack �޽��� �ʱ�ȭ�� �����ֱ�
				}
			}
		};

		// this(MyMain)���� Ű�� ������ k_listener���� ó���� �����ϰڴ�.
		this.addKeyListener(k_listener);
	}

	private void initGame() {
		// �̷��� �ٷ� rect�� �� ������ְ� �޼ҵ带 �̿��ؼ� �����
		// �Ŀ� ���� �־��� �� �������� ����
		me_rect = new Rectangle(0, GAMEPAN_H / 2, 60, 40);
		life_rect = new Rectangle(100, GAMEPAN_H - 50, GAMEPAN_W - 100, 50);
		life_base_rect = new Rectangle(0, GAMEPAN_H - 50, GAMEPAN_W, 50);
		hp_bar = new Rectangle(0, GAMEPAN_H - 50, 100, 50);

	}

	private void initGamePanel() {
		// ��ü ������ ���ÿ� ������(paintComponent)
		gamePanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {// Graphics�׸��⵵��
				// Graphics : �׸��⵵��(��,��)
				// ����ȭ�� �����
				// g.clearRect(0, 0, GAMEPAN_W, GAMEPAN_H);//���׸��� ����������� �ʿ� ����
				draw_background(g);

				// �� ��üȭ �ϱ�
				draw_ddong(g);

				// �����̹��� �׸���
				draw_bomb(g);
				// ����̹��� �׸���
				draw_life(g);
				// �Ѿ� �׸���
				draw_gun(g);
				// ������� �׸���
				draw_guncharge(g);

				// �������� �ٴ� �̹���
				g.drawImage(img_base, life_base_rect.x, life_base_rect.y,
						life_base_rect.width, life_base_rect.height, this);
				// �������� �̹���
				g.drawImage(img_bar, life_rect.x, life_rect.y, life_rect.width,
						life_rect.height, this);
				// ��� �̹���
				g.drawImage(img_hp, hp_bar.x, hp_bar.y, hp_bar.width,
						hp_bar.height, this);
				// ��(���)
				g.drawImage(img_engel, me_rect.x, me_rect.y, me_rect.width,
						me_rect.height, this);

			}

		};
		// ũ�⼳��
		// ��/���� ������ ��� �ڷ���
		Dimension dimension = new Dimension(GAMEPAN_W, GAMEPAN_H);
		// ������ gamePanelũ�⸦ ����(����)
		gamePanel.setPreferredSize(dimension);

		// JFrame�� gamePanel ����
		this.add(gamePanel);

	}

	protected void draw_background(Graphics g) {
		g.drawImage(img_background, 0, 0, GAMEPAN_W, GAMEPAN_H, // ȭ����ǥ
				offset_x, 0, offset_x + GAMEPAN_W, GAMEPAN_H,// ���׸���ǥ
				null);

	}

	// ------------------------------����,��,��� �׸���--------------------------
	int index_charge;

	protected void draw_guncharge(Graphics g) {

		for (ImgCharge imgcharge : imgchargeList) {
			g.drawImage(img_charge[imgcharge.index_charge], imgcharge.x,
					imgcharge.y, this);
			if (imgcharge.move() == false) {// �ε��� ī��Ʈ�� �����ص� move�޼ҵ带 �̿��Ͽ� �̹�����
											// ��� ��� �� ���Ǻ�
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

	// �����̴� png�ֱ�
	int index;

	protected void draw_bomb(Graphics g) {
		for (Bomb bomb : bombList) {
			g.drawImage(img_bomb[bomb.index], bomb.x, bomb.y - 50, this);
			if (bomb.move() == false) {
				// 23���� �׸��� ��� ���
				bombList.remove(bomb);
				return;
			}
		}

		// �����̴� png�ֱ�
		/*
		 * g.drawImage(img_bomb[index], 0, 0, this);
		 * 
		 * index++; if (index > 24) index = 0;
		 */

	}

	protected void draw_ddong(Graphics g) {

		for (DDong ddong : ddongList) {
			// ���׸���

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