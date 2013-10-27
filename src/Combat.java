/**
 * 
 */

/**
 * @author Ringeis
 *
 */
public class Combat {
	
	public Combat(){
		
	}
	
	public int hitCalcuation(int currentHP, int damReceived){
		return currentHP-=damReceived;
	}
}
