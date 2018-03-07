package advance;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{
	
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key shift = new Key();
	
	public Main m;
	
	public class Key{
		public boolean isPressed = false;
		public void toggle(boolean isPressed){
			this.isPressed = isPressed;
		}
	}
	
	public InputHandler(Main m){
		this.m = m;
		m.addKeyListener(this);
	}
	
	
	

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
		if(e.getKeyCode() == KeyEvent.VK_R )m.shouldRender = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	public void toggleKey(int keyCode, boolean pressed){
		if(keyCode == KeyEvent.VK_W)up.toggle(pressed);
		if(keyCode == KeyEvent.VK_S)down.toggle(pressed);
		if(keyCode == KeyEvent.VK_A)left.toggle(pressed);
		if(keyCode == KeyEvent.VK_D)right.toggle(pressed);
		if(keyCode == KeyEvent.VK_SHIFT)shift.toggle(pressed);
		
	}
}
