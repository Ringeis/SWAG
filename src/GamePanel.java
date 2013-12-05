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


public class GamePanel extends JPanel implements KeyListener { //CHANGE NAME OF GAME TO "The Spectacular and Wondrous Adventures of Gimpy"
	
	Graphics2D g2;
	Images image = new Images();
	int toggle = 0;
	Levels level = new Levels();
	int[] joePunch = {level.joePos[0]+45,level.joePos[1]+15};
	Timer timer;
	int frame = 0;
	Combat combat = new Combat();
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

		level.enemyPos[0][0] = 700;
		level.enemyPos[0][1] = 250;
		level.enemyPos[1][0] = 700;
		level.enemyPos[1][1] = 450;
		level.enemyPos[2][0] = 700;
		level.enemyPos[2][1] = 350;
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
			if(level.joePos[1] >= 210){
				level.joePos[1]-=moveSpeed;
				joePunch[1] = level.joePos[1];
			} 
		}
		if(isKeyPressed(65)){ // a or left
			if(level.joePos[0] >= 0){
				level.joePos[0]-=moveSpeed;
				joePunch[0] = level.joePos[0]+45;
			} 
		} 
		if(isKeyPressed(68)){ // d or right
			if(level.joePos[0] <= 905){
				level.joePos[0]+=moveSpeed;
				if(frame == 2){frame = 0;}
				else {frame+=1;}	
				joePunch[0] = level.joePos[0]+45;
			}
		} 
		if(isKeyPressed(83)){ // s or down
			if(level.joePos[1] <= 545){
				level.joePos[1]+=moveSpeed;
				joePunch[1] = level.joePos[1];
			} 
		}
		if(isKeyPressed(32)){
			int num;
			if(combat.hitRegister(joePunch, level.enemyPos)){
				combat.enemySpawner();
				level.enemyPos[combat.enemyID][0]=combat.spawnX;
				level.enemyPos[combat.enemyID][1]=combat.spawnY;
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
	        			 //The Spectacular and Wondrous 
	        				 //Adventures of Gimpy
		}else if(toggle == 1){
			g2.drawImage(image.scenery[1], 0, 0, 950, 650, this);
        	g2.drawImage(image.joeSprite[frame], level.joePos[0], level.joePos[1], 45, 105, this);
        	
        	level.levelOne(level.joePos, level.enemyPos); 
        	for(int i = 0; i < 3; i++){
        		g2.drawImage(image.enemy[i], level.enemyPos[i][0], level.enemyPos[i][1], 50, 110, this);
        	}
        }
        movement();
        
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

