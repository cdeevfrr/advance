package entities;

public abstract class Mob extends Entity{
	
	public abstract boolean move(boolean up, boolean down, boolean left, boolean right);
	
	public int facingDirection;
	public boolean isMoving;
	
	public static int DOWN = 0x00001;
	public static int UP = 0x01001;
	public static int LEFT = 0x10001;
	public static int RIGHT = 0x11001;
	
	public abstract void render(Screen screen);
}
