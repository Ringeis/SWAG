import java.util.Random;

/**
 * @author Ringeis
 *
 */
public class Combat {
	
	int enemyID;
	int spawnX;
	int spawnY;
	Random rand = new Random(System.currentTimeMillis());;
	
	public Combat(){
		
	}
	
	public boolean hitRegister(int[] joeHit, int[][] enemy){ 
		for(int i = 0; i < 3; i++){
			if((Math.abs(enemy[i][0] - joeHit[0]) <= 25) && (Math.abs(enemy[i][1]- joeHit[1]) <= 10)){
				enemyID = i;
				return true;
			}
		}
		return false;
	}
	
	public void enemySpawner(){ //Spawns killed enemies outside the border of the game
		spawnX = rand.nextInt(1100) - 50;
		if(spawnX < 0){
			spawnY = rand.nextInt(440) + 210;
		}else if(spawnX > 950){
			spawnY = rand.nextInt(440) + 210;
		}else{spawnY = 650;}
	}
}
