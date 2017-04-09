package com.backwatersoftware.asd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.backwatersoftware.physics.*;
import com.backwatersoftware.asd.GUI.*;
import com.backwatersoftware.asd.entity.Drops.LevelUp;
import com.backwatersoftware.asd.entity.Obstacles.Tree;
import com.backwatersoftware.asd.entity.Particles.ParticleStream;
import com.backwatersoftware.asd.entity.mob.Hayman;
import com.backwatersoftware.asd.entity.mob.Player;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.input.Keyboard;
import com.backwatersoftware.asd.input.Mouse;
import com.backwatersoftware.asd.level.Level;
import com.backwatersoftware.asd.level.SpawnLevel;

public class Game extends Canvas implements Runnable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static StringBuffer LOG = new StringBuffer();

	public static int width = 400;
	public static int height = width / 16 * 10;
	public static int scale = 3;
	public static Random random = new Random();
	public static int TICKRATE = 60;
	public static int TIME;

	public boolean started;
	private long timer;
	private int tickrate = 0;
	private int fps = 0;
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	private Level level;
	private Level currentLevel;
	private Player player;
	private Mouse mouse;
	private HUD HUD;

	int lastpause;

	public int time;

	private Screen screen;
	private BufferedImage startupImage;
	private BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) this.image.getRaster()
			.getDataBuffer()).getData();

	private Keyboard key;
	private Keyboard key2;
	
	public static int PLAY = 0;
	public static int PAUSE = 1;
	public static int MENU = 2;
	public static int STATE = 1;

	public Game() {
		
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		this.started = false;
		this.screen = new Screen(width, height);
		this.frame = new JFrame();
		this.key = new Keyboard();
		this.key2 = new Keyboard();

		try {
			this.startupImage = ImageIO.read(Game.class
					.getResource("/textures/StartupBeta.png"));
		} catch (IOException e) {
			System.out.println("Shit");
		}
		this.key.WASD = true;
		this.key2.WASD = false;
		this.mouse = new Mouse();

		addKeyListener(this.key);
		addKeyListener(this.key2);
		addMouseListener(this.mouse);
		addMouseMotionListener(this.mouse);

	}

	public synchronized void start() {
		this.running = true;
		this.thread = new Thread(this, "Display");
		LOG("Starting thread ");
		this.thread.start();
	}

	public static void LOG(String string) {
		Game.LOG.append(string + "\n");
	}

	public synchronized void stop() {
		Game.LOG.append("Exiting the game\n");
		System.out.println(Game.LOG.toString());

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("GameLog.txt"));
			String[] rivit = Game.LOG.toString().split("\n");
			for (int i = 0; i < rivit.length; i++) {
				bw.write(rivit[i]);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e1) {
			System.out.println("EVERYTHING IS FUCKED");
		}


		System.exit(0);
		this.running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			return;
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		this.timer = System.currentTimeMillis();

		// this.soitin = new MusaSoitin();

		requestFocus();

		NewGame();
		
		while (this.running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				render();
				frames++;
			}
			while (delta >= 1) {
				tick();

				ticks++;
				delta--;
			}

			if (System.currentTimeMillis() - this.timer > 1000) {
				this.timer += 1000;
				this.fps = frames;
				this.tickrate = ticks;
				frames = 0;
				ticks = 0;
			}
		}

		stop();
	}

	public void tick() {
		if(!this.started){
			NewGame();
		}
		Game.TIME++;
		if(Game.TIME > Game.TIME*60*60*24){
			Game.TIME = 0;
		}
		
		

		//Edgepan
		if(this.level != null && this.level.perspective() == null){
			if(Mouse.getX() < 10 || this.key2.left){
				currentLevel().addScroll(-2,0);
			}
			if(Mouse.getX() > Game.width*scale-10 || this.key2.right){
				currentLevel().addScroll(2,0);
			}
			if(Mouse.getY() < 10 || this.key2.up){
				currentLevel().addScroll(0,-2);
			}
			if(Mouse.getY() > Game.height*scale-10 || this.key2.down){
				currentLevel().addScroll(0,2);
			}
		}

		
		//Reset clock
		if(this.time == this.tickrate * 60 * 60 * 24){
			this.time = 0;
		}
		this.time++;
		
		

		this.key.update();
		this.key2.update();

		if(key.P){
			pause();
		}
		
		if (currentLevel() != null) {
			currentLevel().update();
			if(this.HUD != null){
				this.HUD.update();
			}
		}

	}

	private void pause() {
		if(TIME - lastpause < 20){return;}
		if(STATE == PAUSE){
			STATE = PLAY;
		}else{
			STATE = PAUSE;
		}
		lastpause = TIME;
	}

	private void NewGame() {

		this.started = true;
		this.level = new SpawnLevel("/textures/Grass.png");
		setCurrentLevel(this.level);

		//(double x, double y, Color col, int intensity, double dir, int length, double xforce, double yforce, double scatter)


		currentLevel().SpawnCreatures(100);
		this.level.add(new Tree(new Point(100, 150, 0)));
		if (this.player != null) {
			this.player.remove();
		}
		this.player = new Player(new Point(100, 100, 0), this.key, "Vesa");
		this.HUD = new HUD(0, (int)(this.height * 0.8), Game.width, (int)(this.height * 0.2), this.player, new Color(20, 10, 0));
		currentLevel().setPerspective(this.player);
		this.level.addPlayer(this.player);
		this.setCurrentLevel(this.level);
		this.level.unpause();

	}
	

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}

		
		Graphics g = bs.getDrawGraphics();
		this.screen.clear();

		if(STATE == PAUSE){
			screen.addText(new Point(150, 150, 0), "PAUSED", false);
		}

		if (currentLevel() != null && STATE == PLAY) {
			currentLevel().render(this.screen);
			this.HUD.render(this.screen);
		}
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.PINK);
		g.fillRect(0, 0, 80, 21);
		g.setColor(Color.BLACK);
		g.drawString("tick :" + this.tickrate, 1, 10);
		g.drawString("fps :" + this.fps, 1, 20);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		BufferedImage image = null;
		try {
			image = ImageIO
					.read(Game.class.getResource("/textures/cursor.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor c = toolkit.createCustomCursor(image, new java.awt.Point(game.frame.getX(), game.frame.getY()), "img");

		game.frame.setCursor(c);
		game.frame.setResizable(false);
		game.frame.setTitle("VesAdventure");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

	public static int GetWindowWidth() {
		return (width * scale);
	}

	public static int GetWindowHeight() {
		return (height * scale);
	}

	public Level currentLevel() {
		return this.currentLevel;
	}

	private void setCurrentLevel(Level level) {
		if (this.currentLevel == level) {
			return;
		}
		this.currentLevel = level;
	}



}
