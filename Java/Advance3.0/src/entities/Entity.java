package entities;


public abstract class Entity {
	
	public long xLocation; //pixels, not tiles. The location of the entity's top left coordinate.
	public long yLocation; //pixels, not tiles. The location of the entity's top left coordinate.
	public int xWidth;
	public int yWidth;
	
	
	public abstract void tick();
	
	/**
	 * Render this entity onto the buffer, where the buffer's top left pixel is specified by the
	 * screen coordinates.
	 * @param buffer
	 * @param screenX
	 * @param screenY
	 */
	public abstract void render(int[] buffer, long screenX, long screenY);
	
	
}
