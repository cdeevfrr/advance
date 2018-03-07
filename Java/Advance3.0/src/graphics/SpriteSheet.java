package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import advance.Bounds;

public class SpriteSheet {
	
	String path;
	
	BufferedImage image;
	

	public SpriteSheet(String path){
		this.path = path;
		
		image = null;
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(image == null){
			return;
		}
	}
	
	public int[] getSubimage(Bounds b){
		int width = b.right - b.left;
		int height = b.top - b.bottom;
		int[] result = image.getRGB(b.left, b.bottom , width, height, null, 0, width);
		return result;
	}
}
