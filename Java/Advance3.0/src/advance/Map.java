package advance;

import java.awt.Point;
import java.util.HashMap;

/**
 * This class handles the map.
 * 
 * There are three coordinate systems to be aware of - pixel coordinates are fin-grid coordinates,
 *  and correspond to one element of the array displayed to the user. Tile coordinates are the next level,
 *  and consist of 8X8 pixel coordinates (unless that changes). Chunk coordinates are larger, and each chunk 
 *  consists of 8X8 tile coordinates.
 * @author dannyrivers
 *
 */
public class Map {
	
	HashMap<Integer, Chunk> loadedChunks;
	
	

	/**
	 * Render the map centered at (pixelX,pixelY) and with net width pixelWidth*2
	 * and height pixelHeight*2 into the buffer. There will be 4 pixels in the center
	 * of this area as long as pixel width and height are divisible by 2
	 * - the top-left of the pixel (pixelX, pixelY) is the center of these 4 pixels.
	 * 
	 * @param pixelX
	 * @param pixelY
	 * @param pixelWidth
	 * @param pixelHeight
	 * @param buffer
	 */
	public void render(long pixelX, long pixelY, int pixelWidth, int pixelHeight, int[] buffer){
		
		
		long left = pixelX - pixelWidth; // The left of the screen in absolute pixel coordinates
		long top = pixelY - pixelHeight; // the top of the screen (lowest value) in absolute pixel coordintaes
		
		int pixelsPerRowInBuffer = pixelWidth * 2;
		
		
		
		int tileXZero = (int)(left / 8); //the first tile coordinate that we need (leftmost)
		if(left < 0 && left%8 != 0) tileXZero --;
		int tileYZero = (int)(top / 8); //the first tile coordinate that we need (topmost = lowest value)
		if(top < 0 && top % 8 != 0) tileYZero --;
		
		int localTop = (int)(top%8); //the top of the screen in local coordinates, where local coordinates
		// place (0,0) at the top left of the first tile we need.
		if(localTop < 0) localTop += 8;
		int localLeft = (int)(left%8); //left of the screen in local coordinates.
		if(localLeft < 0) localLeft += 8;
		
		//The bounds of the screen in local pixel coordinates, where local coordinates place (0,0)
		// at the top left of the top left tile of interest.
		Bounds renderRectangle = new Bounds(
				localTop, 
				localTop + 2*pixelHeight, 
				localLeft, 
				localLeft + 2*pixelWidth);
		
		
		for(int tileX = tileXZero ; tileX < tileXZero + (pixelWidth*2)/8 + 1; tileX ++){
			// tileX is the global tile coordinate of the current tile.
			for(int tileY = tileYZero ; tileY < tileYZero + (pixelHeight * 2 / 8) + 1 ; tileY ++){
				// tileY is the global tile coordinate of the current tile.
				//tileBounds is the local pixel coordinates of the bounds of this tile.
				Bounds tileBounds = new Bounds(
						(tileY-tileYZero) * 8, 
						((tileY - tileYZero) + 1) * 8,
						(tileX-tileXZero) * 8, 
						((tileX-tileXZero) + 1) * 8);
				Bounds toRender = tileBounds.intersection(renderRectangle);
				if(toRender.hasVolume()){

					int numRowsFinished = toRender.bottom - renderRectangle.bottom; // bounds treat the 'bottom' as the lower value.
					// even though it's what the user will see at the top.
					int progressOnThisRow = toRender.left - renderRectangle.left;
					int bufferOffset = numRowsFinished * pixelsPerRowInBuffer + progressOnThisRow;
					
					//System.out.println("RowsFinished, progressThisRow" + numRowsFinished + "," + progressOnThisRow);
					//System.out.println(toRender + "\t" + renderRectangle);

					//To convert the intersection rectangle into the tile's local coordinates, 
					// subtract the vector that points to the tile's origin in local coordinates.
					// Bounds treat 'top' and 'bottom' as backwards, since they're mathematical.
					toRender.minusVector(tileBounds.left,  tileBounds.bottom);
					getTile(tileX, tileY).render(toRender, pixelsPerRowInBuffer, false, false, bufferOffset, buffer);
				}
			}
		}
	}
	
	
	public Tile getTile(long tileX, long tileY){
		if(tileX== 0 || tileY == 0){
			return new BasicTile(0);
		}
		return new BasicTile();
	}
	
	public Point getTileCoords(long pixelX, long pixelY){
		long tileLeft = pixelX / 8; //tile coords
		if(pixelX < 0) tileLeft --;
		long tileTop = pixelY / 8; //tile coords
		if(pixelY < 0) tileTop --;
		
		return new Point((int)tileLeft, (int)tileTop);
	}
}
