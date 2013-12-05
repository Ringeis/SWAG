import java.awt.Graphics2D;

/**
 * 
 */

/**
 * @author Jonathan
 *
 */
public class Levels {

	int[][] enemyPos = new int[5][2]; 
	int[] joePos = {0,210}; //Change name to Gimpy!
	
	public void levelOne(int[] joePos, int[][] enemyPos){
		if((Math.abs(joePos[0]-enemyPos[0][0])>=25) || (Math.abs(joePos[1]-enemyPos[0][1])>=25)){ //Stop if too close to target!
    		findJoe(enemyPos[0], 0);
    		findJoe(enemyPos[1], 1);
    		findJoe(enemyPos[2], 2);
    	}else{
    		System.exit(0);
    	}
    }
	
	public void levelTwo(int[] joePos, int[][] enemyPos){
		if((Math.abs(joePos[0]-enemyPos[0][0])>=25) || (Math.abs(joePos[1]-enemyPos[0][1])>=25)){ //Stop if too close to target!
    		findJoe(enemyPos[0], 0);
    		findJoe(enemyPos[1], 1);
    		findJoe(enemyPos[2], 2);
    	}else{
    		System.exit(0);
    	}
	}
	
	public void levelThree(int[] joePos, int[][] enemyPos){
		if((Math.abs(joePos[0]-enemyPos[0][0])>=25) || (Math.abs(joePos[1]-enemyPos[0][1])>=25)){ //Stop if too close to target!
    		findJoe(enemyPos[0], 0);
    		findJoe(enemyPos[1], 1);
    		findJoe(enemyPos[2], 2);
    		findJoe(enemyPos[3], 3);
    	}else{
    		System.exit(0);
    	}
	}
	
	
	private void findJoe(int[] pos, int i){
		if(joePos[0] <= pos[0] && joePos[1] <= pos[1]){ //If Joe is to the left and above the enemy, respectively.
			if(Math.abs(joePos[0]-pos[0]) >= Math.abs(joePos[1]-pos[1])){ //check which axis is a greater distance
				enemyPos[i][0]-=3;
			}else{
				enemyPos[i][1]-=3;
			}
		}if(joePos[0] >= pos[0] && joePos[1] <= pos[1]){ //If Joe is to the right and above the enemy, respectively.
			if(Math.abs(joePos[0]-pos[0]) >= Math.abs(joePos[1]-pos[1])){ //check which axis is a greater distance
				enemyPos[i][0]+=3;
			}else{
				enemyPos[i][1]-=3;
			}
		}if(joePos[0] <= pos[0] && joePos[1] >= pos[1]){ //If Joe is to the left and below the enemy, respectively.
			if(Math.abs(joePos[0]-pos[0]) >= Math.abs(joePos[1]-pos[1])){ //check which axis is a greater distance
				enemyPos[i][0]-=3;
			}else{
				enemyPos[i][1]+=3;
			}
		}if(joePos[0] >= pos[0] && joePos[1] >= pos[1]){ //If Joe is to the right and below the enemy, respectively.
			if(Math.abs(joePos[0]-pos[0]) >= Math.abs(joePos[1]-pos[1])){ //check which axis is a greater distance
				enemyPos[i][0]+=3;
			}else{
				enemyPos[i][1]+=3;
			}
		}
	}
}
