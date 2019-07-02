package conwaysgameoflife;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Scanner;

public class Game extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8535764634659333752L;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 16 * 9;
	
	private Thread thread;
	
	private boolean running = false;
	
	private CellMatrix cellMatrix;
	
	/**
	 * Create a Game instance with cellMatrix, eventListeners, and Window
	 */
	
	public Game() {
		cellMatrix = new CellMatrix(WIDTH, HEIGHT);
		this.addMouseListener(new MouseInput(cellMatrix));
		
		new Window(WIDTH, HEIGHT, "Conway's Game of Life", this);
	}
	
	/**
	 * Starts the game.
	 */
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * Stops the game.
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Game Loop Logic: currently unable to adjust fps
	 * Enough for testing purposes
	 */
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	/** 
	 * ticks the cellMatrix.
	 */
	private void tick() {
		cellMatrix.tick();
	}
	
	/**
	 * Renders Window and cellMatrix under a set BufferStrategy.
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		cellMatrix.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		new Game();
	}



}
