package advance;

import java.awt.Color;

public class BasicTile extends Tile{
	static{
		tileID = 0;
	}
	
	
	
	public BasicTile(){
		this(0xff00ff00);
	}
	
	public BasicTile(int background){
		pixels = new int[8][8];
		for(int i = 0; i < pixels.length ; i ++){
			for(int j = 0; j < pixels.length ; j ++){
				pixels[i][j] = background;
			}
		}
		
		int secondary =  new Color(background).darker().getRGB();
		pixels[3][1] = secondary;
		pixels[3][2] = secondary;
		pixels[4][3] = secondary;
		pixels[5][3] = secondary;
		pixels[1][6] = secondary;
		
		//pixels[7][7] = 0;
	}
	
	
	
	
	
}
