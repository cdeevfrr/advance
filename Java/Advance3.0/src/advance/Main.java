package advance;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable  {
	
	private static final long serialVersionUID = 1L;
	
	public boolean showSecondReport = false;
	
	
	//width and height in game tiles (each tile is an 8X8 tile by default)
	public static final int WIDTH = 12;
	public static final int HEIGHT = 9;
	public static final int SCALE = 6;
	public static final int BITS = 8;
	
	public static final double Version = 2.0;
	public static final String NAME = "Advance" + Version;
	
	
	double ticksPerSecond = 60; // The game will perform ticks no faster than this rate. It may be slower.
	double nsPerTick = 1000000000D/ticksPerSecond; //The game will wait at least this long before doing the next tick.
			// It may wait longer if something else is taking a lot of time.
	
	int frames;
	int ticks;
	
	boolean running = false;
	boolean shouldRender = true;
	
	private BufferedImage image = new BufferedImage(WIDTH * BITS, HEIGHT * BITS, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	
	private JFrame frame;
	
	private Map map;
	private Player player;
	private InputHandler input;
	
	
	public Main(){
		this.setPreferredSize(new Dimension(WIDTH * BITS * SCALE, HEIGHT * BITS * SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		
		init();
		
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	public void init(){
		this.map = new Map();
		input = new InputHandler(this);
		player = new Player(input);
		this.requestFocus();
	}
	
	
	public synchronized void start(){
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop(){
		running = false;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		wipeTimingStats(false);
		
		while(running){
			while(System.nanoTime() - lastTime > nsPerTick){
				//shouldRender = true;
				tick();
				lastTime += nsPerTick;
			}
			
			
			try {
				Thread.sleep(2);
				shouldRender = true;
			} catch (InterruptedException e) {
				shouldRender = true;
				e.printStackTrace();
			}
			
			
			if(shouldRender){
				render();
				shouldRender = false;
			}
		}
	}
	
	public void wipeTimingStats(boolean showReport){
		if(showReport){
			System.out.println("frames:" + frames + ", ticks:" + ticks); 
			System.out.println(player.moveTickCounter);
		}
		frames = 0;
		ticks = 0;
	}
	
	public void tick(){
		
		player.tick();
		
		//Cleanup
		ticks++;
		if(ticks >= 60){
			wipeTimingStats(showSecondReport);
		}
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		//Update the image displayed to the user
		
		map.render(player.xLocation, player.yLocation, WIDTH * BITS /2, HEIGHT * BITS/2 , pixels);
		player.render(pixels, WIDTH * BITS / 2, HEIGHT * BITS/2);

		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); 
		
		g.dispose();
		bs.show();
		frames++;
	}
	
	
	public static void main(String[] args){
		new Main().start();
	}
	
	

}
