package advance;

/**
 * A chunk in the map.
 * Represents a collection of map tiles by their tile-data.
 * 
 * Chunks always store 64 tiles in an 8X8 grid.
 * 
 * 
 * 				  (0,-8)
 * 					|				|				|
 * 					|				|				|
 * 		|			|	(0,-1)		|				|
 * 		|			|				|				|
 * 		|			|				|				|
 * 	(-8,0)-------(0,0)------------(8,0)-----------(16,0)
 * 		|			|				|				|
 * 		|			|				|				|
 * 		|	(-1,0)	|	(0,0)		|	(1,0)		|
 * 					|				|				|
 * 					|				|				|
 * 				 (0,8)------------(8,8)----------(16,8)
 * @author dannyrivers
 *
 */
public class Chunk {
	public static final int bitsPerTile = 128;
	
	private int chunkX;
	private int chunkY;
	
	
	private Chunk(int chunkX, int chunkY, int gameID){
		this.chunkX = chunkX;
		this.chunkY = chunkY;
	}
	
	/**
	 * 
	 * @param tileX
	 * @param tileY
	 * @param gameID
	 * @return
	 *
	public Chunk generateChunk(int tileX, int tileY, int gameID){
		
	}
	
	/**
	 * 
	 * @param tileX
	 * @param tileY
	 * @param gameID
	 * @return
	 *
	public Chunk loadChunk(int tileX, int tileY, int gameID){
		
	}
	*/
	
	public boolean contains(int xTileCoord, int yTileCoord){
		int[] chunkCoords = chunkCoordinates(xTileCoord, yTileCoord);
		return chunkCoords[0] == this.chunkX && chunkCoords[1] == this.chunkY;
	}
	
	public static int[] tileCoords(int xChunkCoord, int yChunkCoord){
		return new int[]{xChunkCoord<<3, yChunkCoord << 3};
	}
	
	public static int[] chunkCoordinates(int xTileCoord, int yTileCoord){
		return new int[]{xTileCoord >> 3, yTileCoord >> 3};
	}
}
