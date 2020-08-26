package clock;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public static int HEIGHT = 600;
	public static int WIDTH = 800;
	public static int SCALE = 1;
	
	public static boolean isRunning = false;
	public static Thread thread;
	public static BufferedImage base;
	public static BufferStrategy bs;
	public static Graphics screen;
	public static Main main;
	public static Relogio relogio;
	public static Data data;
	
	public Main() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame = new JFrame("Relogio");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		base = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		relogio = new Relogio(23, 34, 30);
		data = new Data(25, 8, 2020, 2);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	public synchronized void stop() {
		isRunning = false; try {thread.join();} catch(InterruptedException e) { e.printStackTrace();}
	}
	
	public void update() {
		relogio.update();
	}
	
	public void render() {
		bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		screen = base.getGraphics();
		screen.setColor(new Color(150, 150, 150));
		screen.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		
		relogio.render(screen);
		
		screen = bs.getDrawGraphics();
		screen.drawImage(base, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		bs.show();
	}
	
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfUpdates = 1.0;
		double ns = 1000000000 / amountOfUpdates;
		double delta = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				update();
				render();
				delta--;
			}
		}
		stop();
	}
	
	public static void main(String[] args) {
		main = new Main();
		main.start();
	}
	
}
