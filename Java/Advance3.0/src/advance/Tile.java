package advance;


public abstract class Tile{
	public static int tileID;
	
	boolean solid;
	
	static boolean checkErrors = true;
	
	int[][] pixels;
	
	
	
	
	
	
	/**
	 * 
	 * @param bufferX - the x offset in the buffer. May be negative.
	 * @param bufferY - the y offset in the buffer. May be negative.
	 * @param pixelsPerRowInBuffer
	 * @param buffer
	 */
	public void render(Bounds localPixelBounds, 
			int pixelsPerRowInBuffer, 
			int bufferOffset, 
			int[] buffer){
		render(localPixelBounds, pixelsPerRowInBuffer, false, false, bufferOffset,  buffer);
	}
	
	
	public void render(
			Bounds localPixelBounds, 
			int pixelsPerRowInBuffer, 
			boolean flipX,
			boolean flipY,
			int bufferOffset, 
			int[] buffer){
		
		if(checkErrors){
			if(pixelsPerRowInBuffer < 0){
				throw new RuntimeException("tile tried to render with negative pixels per row in buffer");
			}
			if(!localPixelBounds.isPositive()){
				throw new RuntimeException("Tried to render a tile using local bounds " + localPixelBounds);
			}
			if(bufferOffset < 0 || bufferOffset > buffer.length){
				throw new RuntimeException("Tried to render a tile with an incorrect buffer offset:"
						+ "\n offset " + bufferOffset + " size " + buffer.length);
			}
			if((localPixelBounds.top - 1) * pixelsPerRowInBuffer + localPixelBounds.right - 1 + bufferOffset >= buffer.length){
				throw new RuntimeException("Tried to render a tile with numbers that don't add up:\n"
						+ "Local bounds: " + localPixelBounds + "\n"
						+ "buffer offset: " + bufferOffset + ", buffer size: " + buffer.length + 
						", pixels per row: " + pixelsPerRowInBuffer);
			}
		}
		
		
		int xInc = 0;
		int yInc = 0;
		for(int localY = localPixelBounds.bottom ; localY < localPixelBounds.top ; localY ++){
			for(int localX = localPixelBounds.left ; localX < localPixelBounds.right; localX ++){
				int localXIndex = localX;
				int localYIndex = localY;
				if(flipX)localXIndex = pixels[0].length - localX + 1;
				if(flipY)localYIndex = pixels.length - localY + 1;
				buffer[bufferOffset + yInc * pixelsPerRowInBuffer + xInc] = pixels[localXIndex][localYIndex];
				xInc ++;
			}
			yInc ++;
			xInc = 0;
		}
	}
	
	
	
}
