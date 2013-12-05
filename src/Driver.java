/**
 * @author Ringeis
 *
 */

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI(){
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new GamePanel());
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

}
