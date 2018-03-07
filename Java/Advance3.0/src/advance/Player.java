package advance;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Mob;
import graphics.SpriteSheet;

public class Player extends Mob{
	
	InputHandler input;
	
	static String imageFilePath = "res/Images/doggo.jpg";
	static SpriteSheet images = new SpriteSheet(imageFilePath);
	
	
	int rightwardIndex = 0;
	int rightwardWalkIndex = 1;
	int awayIndex = 3;
	int towardIndex = 2;
	int playerBits = 16;
	

	public Player(InputHandler input){
		xLocation = 0;
		yLocation = 0;
		xWidth = 8;
		yWidth = 8;
		this.input = input;
	}
	
	public int slow = 1; // only allow movement once every 'slow' ticks. 
	public long moveTickCounter = 0;

	@Override
	public boolean move(boolean up, boolean down, boolean left, boolean right) {
		if(moveTickCounter > 1){
			return false;
		}
		boolean result = quickMove(up, down, left, right);
		if(result){
			moveTickCounter = slow;
		}
		return result;
	}
	
	private boolean quickMove(boolean up, boolean down, boolean left, boolean right){
		if(input.up.isPressed){ yLocation --; return true;}
		if(down){ yLocation ++; return true;}
		if(left){ xLocation --; return true;}
		if(right){ xLocation ++; return true;}
		return false;
	}
	
	public void move(){
		if(input.shift.isPressed){
			slow = 20;
		}
		else{
			slow = 1;
		}
		boolean moveSuccess = move(
				input.up.isPressed, 
				input.down.isPressed, 
				input.left.isPressed, 
				input.right.isPressed);
		
		if(moveSuccess){
			System.out.println(xLocation + "," + yLocation);
		}
	}
	
	/**
	 * Draw the player in the map centered at the top left of coordinate (pixelX, pixelY).
	 * @param pixels
	 * @param pixelX
	 * @param pixelY
	 */
	public void render(int[] pixels, int pixelX, int pixelY){
		int pixelsPerRowInBuffer = pixelX * 2;
		pixels[pixelY * pixelsPerRowInBuffer + pixelX] = 0x00ff0000;
		pixels[pixelY * pixelsPerRowInBuffer + pixelX - 1] = 0x00ff0000;
		pixels[(pixelY - 1) * pixelsPerRowInBuffer + pixelX] = 0x00ff0000;
		pixels[(pixelY - 1) * pixelsPerRowInBuffer + pixelX - 1] = 0x00ff0000;
	}
	
	
	public void tick(){
		move();
		moveTickCounter --;
	}

	@Override
	public void render(int[] buffer, long screenX, long screenY) {
		int pixelsPerRowInScreen = Main.WIDTH * Main.BITS;
		int pixelX = Main.WIDTH  * Main.BITS / 2;
		int pixelY = Main.HEIGHT * Main.BITS / 2;
		buffer[pixelY * pixelsPerRowInScreen + pixelX] = 0x00ff0000;
		buffer[pixelY * pixelsPerRowInScreen + pixelX - 1] = 0x00ff0000;
		buffer[(pixelY - 1) * pixelsPerRowInScreen + pixelX] = 0x00ff0000;
		buffer[(pixelY - 1) * pixelsPerRowInScreen + pixelX - 1] = 0x00ff0000;
	}

	
	
	
	

}
