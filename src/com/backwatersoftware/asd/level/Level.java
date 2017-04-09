package com.backwatersoftware.asd.level;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.backwatersoftware.asd.Animations.Explosion;
import com.backwatersoftware.asd.Announces.FloatMessage;
import com.backwatersoftware.asd.entity.Entity;
import com.backwatersoftware.asd.entity.Drops.Drop;
import com.backwatersoftware.asd.entity.Obstacles.Obstacle;
import com.backwatersoftware.asd.entity.Particles.ParticleSpawner;
import com.backwatersoftware.asd.entity.mob.Ganja;
import com.backwatersoftware.asd.entity.mob.Minion;
import com.backwatersoftware.asd.entity.mob.Mob;
import com.backwatersoftware.asd.entity.mob.Player;
import com.backwatersoftware.asd.entity.mob.RandomMinion;
import com.backwatersoftware.asd.entity.projectile.Projectile;
import com.backwatersoftware.asd.graphics.Screen;
import com.backwatersoftware.asd.level.tile.Tile;
import com.backwatersoftware.physics.*;

public abstract class Level {

	private ArrayList<Tile> exitTiles = new ArrayList<Tile>(); // Tällä
																// matkustellaan
																// kenttien
																// välillä + tee
																// pari mappia
	private boolean paused;
	protected Random random;
	protected int width,height;
	protected int time = 0;
	protected int[] tiles;
	protected Gravity g;
	public List<Player> players = new ArrayList<Player>();
	public List<Mob> mobs = new ArrayList<Mob>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Explosion> expl = new ArrayList<Explosion>();
	public List<ParticleSpawner> ps = new ArrayList<ParticleSpawner>();
	public List<Obstacle> obs = new ArrayList<Obstacle>();
	public List<Drop> drops = new ArrayList<Drop>();
	public List<FloatMessage> messages = new ArrayList<FloatMessage>();
	public List<Entity> uEntities = new ArrayList<Entity>();
	protected int xScroll = 0;
	protected int yScroll = 0;
	protected Entity perspective;

	public Level(int width, int height) {
		width = width;
		height = height;
		tiles = new int[width * height];
		paused = false;
		random = new Random();
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
	}

	

	protected void generateLevel() {
	}

	protected void loadLevel(String path) {
	}

	/**
	 * Updates all entities in level
	 */
	public void update() {
		if (paused) {
			return;
		}
		updateTime();
		
		//Creep spawner
		/*
		if (time % (60*15) == 0) {
			SpawnCreatures(10);
		}*/
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) {
				players.remove(i);
				continue;
			}
		}
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).isRemoved()) {
				mobs.remove(i);
				continue;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) {
				projectiles.remove(i);
				continue;
			}
		}
		for (int i = 0; i < expl.size(); i++) {
			if (expl.get(i).isRemoved()) {
				expl.remove(i);
				continue;
			}
		}
		for (int i = 0; i < ps.size(); i++) {
			if (ps.get(i).isRemoved()) {
				ps.remove(i);
				continue;
			}
		}
		for (int i = 0; i < obs.size(); i++) {
			if (obs.get(i).isRemoved()) {
				obs.remove(i);
				continue;
			}
		}
		for (int i = 0; i < drops.size(); i++) {
			if (drops.get(i).isRemoved()) {
				drops.remove(i);
				continue;
			}
		}
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).isRemoved()) {
				messages.remove(i);
				continue;
			}
		}
		for (int i = 0; i < uEntities.size(); i++) {
			if (uEntities.get(i).isRemoved()) {
				uEntities.remove(i);
				continue;
			}
		}

		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < expl.size(); i++) {
			expl.get(i).update();
		}
		for (int i = 0; i < ps.size(); i++) {
			ps.get(i).update();
		}
		for (int i = 0; i < obs.size(); i++) {
			obs.get(i).update();
		}
		for (int i = 0; i < drops.size(); i++) {
			drops.get(i).update();
		}
		for (int i = 0; i < messages.size(); i++) {
			messages.get(i).update();
		}
		for (int i = 0; i < uEntities.size(); i++) {
			uEntities.get(i).update();
		}


	}

	private void updateTime() {
		if (time == 200000) {
			time = 0;
		}
		time++;
	}

	/**
	 * Tells all the entities to render themselves in the level. This is the top
	 * of all living beings Currently involves following lists: 1 Playerlist, 2
	 * MobList, 3 Explosionlist, 4 particlelist, 5 projectilelist
	 * 
	 * @param screen
	 * @param perspective
	 */
	public void render(Screen screen) {
		if (paused) {
			return;
		}
		
		if (perspective != null) {
			xScroll = (int) perspective.point().x() - screen.width / 2 + 16;
			yScroll = (int) perspective.point().y() - screen.height / 2 + 16 * 2;
		}
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < expl.size(); i++) {
			expl.get(i).render(screen);
		}
		for (int i = 0; i < ps.size(); i++) {
			ps.get(i).render(screen);
		}
		for (int i = 0; i < obs.size(); i++) {
			obs.get(i).render(screen);
		}
		for (int i = 0; i < drops.size(); i++) {
			drops.get(i).render(screen);
		}
		for (int i = 0; i < messages.size(); i++) {
			messages.get(i).render(screen);
		}
		for (int i = 0; i < uEntities.size(); i++) {
			uEntities.get(i).render(screen);
		}

		
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.voidTile;
		}
		return Tile.TileList[tiles[x + y * width]];
	}

	public void addPlayer(Player p) {
		p.init(this);
		players.add(p);
	}

	public void addProjectile(Projectile p) {
		p.init(this);
		projectiles.add(p);
	}

	public void addMob(Mob m) {
		m.init(this);
		mobs.add(m);
	}

	private void addExplosion(Explosion e) {
		e.init(this);
		expl.add(e);
	}

	private void addParticleSpawner(ParticleSpawner pspawner) {
		pspawner.init(this);
		ps.add(pspawner);
	}

	private void addObstacle(Obstacle e) {
		e.init(this);
		obs.add(e);

	}

	private void addDrop(Drop d) {
		d.init(this);
		drops.add(d);
	}

	private void addMessage(FloatMessage e) {
		e.init(this);
		messages.add(e);
		
	}
/**
 * Adds entity only if there's none with same content
 * @param e
 */
	public void addUnique(Entity e){
		if (e.isFloatMessage()) {
			for (int i = 0; i < messages.size(); i++) {
				if (e.equals(messages.get(i))) {
					return; 
				}
			}
			addMessage((FloatMessage) e);
			return;
		}
	}
	
	public void add(Entity e) {

		if (e.isProjectile()) {
			addProjectile((Projectile) e);
			return;
		}
		if (e.isMob()) {
			addMob((Mob) e);
			return;
		}
		if (e.isExplosion()) {
			addExplosion((Explosion) e);
			return;
		}
		if (e.isParticleSpawner()) {
			addParticleSpawner((ParticleSpawner) e);
			return;
		}
		if (e.isObstacle()) {
			addObstacle((Obstacle) e);
			return;
		}
		if (e.isDrop()) {
			addDrop((Drop) e);
			return;
		}
		if (e.isFloatMessage()) {
			addMessage((FloatMessage) e);
			return;
		}
		
		e.init(this);
		uEntities.add(e);
		
		System.out.println("Added " + e.toString() + " to the universal entity array");

	}

	public int time() {
		return time;
	}

	public void addGravity(Gravity g) {
		g = g;
	}

	public boolean contains(Player player) {
		if (players.isEmpty()) {
			return false;
		}
		return true;
	}

	public void pause() {
		paused = true;
	}

	public void unpause() {
		paused = false;
	}

	public boolean paused() {

		return paused;
	}

	public void SpawnCreatures(int i) {
		for (int j = 0; j < i; j++) {
			add(randomMinion(190 + random.nextInt(width * 16 - 200), random.nextInt(199 + height * 16 - 200)));
		}
	}

	private Entity randomMinion(int a, int b) {
		if (random.nextBoolean()) {
			try {
				return new RandomMinion(new Point(a,b,0), getHighestLevelPlayer().level());
			} catch (NoPlayersException e) {
				return new RandomMinion(new Point(a,b,0), 1);
			}
		}
		return new Ganja(new Point(a,b,0));
	}

	public Mob getNearestPlayer(Minion minion) {
		if (players.isEmpty()) {
			return null;
		}
		Player p = players.get(0);
		for (int i = 1; i < players.size(); i++) {
			double dist = minion.point().distance(p.point());
			Player p2 = players.get(i);
			if (minion.point().distance(p2.point()) < dist) {
				p = p2;
			}
		}
		return p;
	}

	public Player getNearestPlayer(Point point) {
		if (players.isEmpty()) {
			return null;
		}
		Player p = players.get(0);
		for (int i = 1; i < players.size(); i++) {
			double dist = point.distance(p.point());
			Player p2 = players.get(i);
			if (point.distance(p2.point()) < dist) {
				p = p2;
			}
		}
		return p;
	}

	public Player getHighestLevelPlayer() throws NoPlayersException {
		if (players.isEmpty()) {
			throw new NoPlayersException();
		}
		Player p = players.get(0);
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).level() > p.level()) {
				p = players.get(i);
			}
		}
		return p;
	}
	
	public void newMessageFrom(String message, Entity e, Color color, int life){
		add(new FloatMessage(e.point(), message, life, color));
	}

	public int xScroll() {
		// TODO Auto-generated method stub
		return xScroll;
	}
	public int yScroll() {
		// TODO Auto-generated method stub
		return yScroll;
	}

	public Object perspective() {
		return perspective;
	}

	public void setPerspective(Entity player) {
		perspective = player;
		
	}

	public void addScroll(int i, int j) {
		xScroll += i;
		yScroll += j;
		
	}

	/**
	 * returns the first mob it detects from square area where xt and yt are top left corner coordinates
	 * and sides are 16 wide
	 * @param xt
	 * @param yt
	 * @return Mob
	 */
	public Mob getMob(int xt, int yt) {
		for (int i = 0; i < mobs.size(); i++) {
			Mob t = mobs.get(i);
			if (t.point().x() < xt && xt < t.point().x() + t.XSIZE && t.point().y() < yt && yt < t.point().y() + t.YSIZE) {
				return t;
			}
		}
		for (int i = 0; i < players.size(); i++) {
			Mob t = players.get(i);
			if (t.point().x() < xt && xt < t.point().x() + t.XSIZE && t.point().y() < yt && yt < t.point().y() + t.YSIZE) {
				return t;
			}
		}
		return null;
	}
/**
 * Height as pixels
 * @return
 */
	public double height() {
		return height * 16;
	}
	/**
	 * Width as pixels
	 * @return
	 */
		public double width() {
			return width * 16;
		}

}
