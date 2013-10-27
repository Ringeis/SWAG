
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Images {
	
	BufferedImage gimpySprite[] = new BufferedImage[3];
	BufferedImage scenery[] = new BufferedImage[2];
	BufferedImage enemy = null;
	
	public Images(){
		try{
            gimpySprite[0] = ImageIO.read(new File("src\\Joe.png"));
            gimpySprite[1] = ImageIO.read(new File("src\\Joe_2.png"));
            gimpySprite[2] = ImageIO.read(new File("src\\Joe_3.png"));
            scenery[1] = ImageIO.read(new File("src\\Background.jpg"));
            enemy = ImageIO.read(new File("src\\enemy.png"));
        }catch(IOException e){System.out.print("ERROR");}
	}
	
}
