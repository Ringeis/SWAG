
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Images {
	
	BufferedImage joeSprite[] = new BufferedImage[3];
	BufferedImage scenery[] = new BufferedImage[2];
	BufferedImage enemy[] = new BufferedImage[3];
	
	public Images(){
		try{
            joeSprite[0] = ImageIO.read(new File("src\\Joe.png"));
            joeSprite[1] = ImageIO.read(new File("src\\Joe_2.png"));
            joeSprite[2] = ImageIO.read(new File("src\\Joe_3.png"));
            scenery[1] = ImageIO.read(new File("src\\Background.jpg"));
            enemy[0] = ImageIO.read(new File("src\\enemy.png"));
            enemy[1] = ImageIO.read(new File("src\\enemy.png"));
            enemy[2] = ImageIO.read(new File("src\\enemy.png"));
        }catch(IOException e){System.out.print("ERROR");}
	}
	
}
