package advance;

public class Bounds{
	public int bottom; //Note - bottom is the top of what the user sees. Bottom is the lower value, which means
	// higher up for a screen's display.
	public int top;
	public int left;
	public int right;

	
	
	public Bounds(int bottom, int top, int left, int right) {
		super();
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	public Bounds intersection(Bounds other){
		return new Bounds(
				max(other.bottom, this.bottom),
				min(other.top, this.top),
				max(other.left, this.left),
				min(other.right, this.right));
	}
	
	/**
	 * Subtract this vector from all vectors defining this rectangle.
	 * @param v
	 */
	public void minusVector(int x, int y){
		top = top-y;
		bottom = bottom-y;
		left = left-x;
		right = right-x;
	}
	
	/**
	 * Only return true if this rectangle has positive volume.
	 * A rectangle with top=bottom has no volume, and a rectangle with
	 * negative volume (top < bottom or left > right) has no volume. 
	 * @return
	 */
	public boolean hasVolume(){
		return (this.top > this.bottom) && (this.left < this.right);
	}
	
	
	
	
	/**
	 * Returns i when equal.
	 * @param i
	 * @param j
	 * @return
	 */
	public int min(int i, int j){
		if(i <= j){
			return i;
		}
		return j;
	}
	
	/**
	 * Returns i when equal.
	 * @param i
	 * @param j
	 * @return
	 */
	public int max(int i, int j){
		if(i < j){
			return j;
		}
		return i;
	}
	
	
	public String toString(){
		return "btm:" + bottom + "  top:" + top + "  lft:" + left + "  rht:" + right;
	}
	
	public boolean isPositive(){
		return (top >= 0) 
				&& (bottom >= 0)
				&& (left >= 0)
				&& (right >= 0);
	}
	
	
}
