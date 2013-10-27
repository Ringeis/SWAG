/**
 * @author Jonathan
 *
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class GamePanel extends JPanel implements KeyListener { //CHANGE NAME OF GAME TO "The Strange and Wondrous Adventures of Gimpy"
	
	Graphics2D g2;
	Images image = new Images();
	int toggle = 0;
	int[] gimpyPos = {0,210};
	int[][] enemyPos = new int[2][5]; 
	Timer timer;
	int frame = 0;
	ActionListener action = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			if(toggle == 0){ //for the first run through 
	            toggle = 1;
	        }
	        revalidate();
	        repaint();
		}
		
	};
	private BitSet keyBits = new BitSet(256);
	
	
	public GamePanel(){
		enemyPos[0][0] = 700;
		enemyPos[0][1] = 450;
		timer = new Timer(50, action);
        timer.setInitialDelay(3000);
        timer.start();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.white);
        addKeyListener(this);
        getInputMap().put(KeyStroke.getKeyStroke("P"),
                "Pause");
        getActionMap().put("Pause",
                 new AbstractAction() {

        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(timer.isRunning()){
        			timer.stop();
        		}else{
        			timer.setInitialDelay(0);
        			timer.restart();
        		}
        	}
        });
        getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),
                "Escape");
        getActionMap().put("Escape",
                 new AbstractAction() {

        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(950, 650);
	}
	
	private void movement(){
		int moveSpeed = 5;
		if(isKeyPressed(87)){ // w or up
			if(gimpyPos[1] >= 210){
				gimpyPos[1]-=moveSpeed;
			} 
		}
		if(isKeyPressed(65)){ // a or left
			if(gimpyPos[0] >= 0){
				gimpyPos[0]-=moveSpeed;
			} 
		} 
		if(isKeyPressed(68)){ // d or right
			if(gimpyPos[0] <= 905){
				gimpyPos[0]+=moveSpeed;
				if(frame == 2){frame = 0;}
				else {frame+=1;}					
			}
		} 
		if(isKeyPressed(83)){ // s or down
			if(gimpyPos[1] <= 545){
				gimpyPos[1]+=moveSpeed;
			} 
		}
	}
	
	public void paintComponent(Graphics g){        
        g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
        if(toggle == 0){
        	Font f = new Font("AR DARLING", Font.PLAIN, 42);
        	g2.setFont(f);
            g2.setColor(Color.BLACK);
	        try{
	        	image.scenery[0] = ImageIO.read(new File("src\\CoverScreen.jpg"));
	        }catch(IOException e){System.out.print("ERROR");}
	        g2.drawImage(image.scenery[0], -74, -90, 1024, 768, this);
	        g2.drawString("The Strange and Wondrous", 245, 300);
	        g2.drawString("Adventures of Gimpy",300, 350);
		}else if(toggle == 1){
			g2.drawImage(image.scenery[1], 0, 0, 950, 650, this);
        	g2.drawImage(image.gimpySprite[frame], gimpyPos[0], gimpyPos[1], 45, 105, this);
        	if((Math.abs(gimpyPos[0]-enemyPos[0][0])>=25) || (Math.abs(gimpyPos[1]-enemyPos[0][1])>=25)){ //Stop if too close to target!
        		findJoe();
        	}else{System.exit(0);} // GG
        	g2.drawImage(image.enemy, enemyPos[0][0], enemyPos[0][1], 50, 110, this);
        }
        movement();
        
	}
	
	private void findJoe(){
		if(gimpyPos[0] < enemyPos[0][0] && gimpyPos[1] < enemyPos[0][1]){ //If Joe is to the left and above the enemy, respectively.
			if(Math.abs(gimpyPos[0]-enemyPos[0][0]) >= Math.abs(gimpyPos[1]-enemyPos[0][1])){ //check which axis is a greater distance
				enemyPos[0][0]-=2;
			}else{
				enemyPos[0][1]-=2;
			}
		}if(gimpyPos[0] > enemyPos[0][0] && gimpyPos[1] <= enemyPos[0][1]){ //If Joe is to the right and above the enemy, respectively.
			if(Math.abs(gimpyPos[0]-enemyPos[0][0]) >= Math.abs(gimpyPos[1]-enemyPos[0][1])){ //check which axis is a greater distance
				enemyPos[0][0]+=2;
			}else{
				enemyPos[0][1]-=2;
			}
		}if(gimpyPos[0] <= enemyPos[0][0] && gimpyPos[1] >= enemyPos[0][1]){ //If Joe is to the left and below the enemy, respectively.
			if(Math.abs(gimpyPos[0]-enemyPos[0][0]) >= Math.abs(gimpyPos[1]-enemyPos[0][1])){ //check which axis is a greater distance
				enemyPos[0][0]-=2;
			}else{
				enemyPos[0][1]+=2;
			}
		}if(gimpyPos[0] >= enemyPos[0][0] && gimpyPos[1] > enemyPos[0][1]){ //If Joe is to the right and below the enemy, respectively.
			if(Math.abs(gimpyPos[0]-enemyPos[0][0]) >= Math.abs(gimpyPos[1]-enemyPos[0][1])){ //check which axis is a greater distance
				enemyPos[0][0]+=2;
			}else{
				enemyPos[0][1]+=2;
			}
		}
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
        keyBits.set(keyCode);   
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
        keyBits.clear(keyCode);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// dgaf
		
	}

	public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
	/**
	 * @param args
	 */
	

}
