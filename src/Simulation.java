import java.util.Random;

import javax.swing.JTextField;

public class Simulation {
	
	static Random rnd = new Random(System.currentTimeMillis());
	private int entranceState;
	private RelicState RelicState;
	private int dungeonX;
	private int dungeonY;
	private int bossEntranceX;
	private int bossEntranceY;

	private int hero;
	private int dungeon[][];
	private boolean isBackwardingWithRealMap = false;
	
	Simulation(int entranceState, DungeonState dungeonState, int heroCount){
		this.entranceState = entranceState;
		this.dungeonX = dungeonState.getWidth();
		this.dungeonY = dungeonState.getDepth();
		this.dungeon = new int[dungeonX][dungeonY];
		this.hero = heroCount;
		setBossEntrance(dungeonState);
	}
	
	private void setBossEntrance(DungeonState dungeonState) {
		switch(dungeonState.getPosition()) {
		case 'U':
			this.bossEntranceX = 2;
			break;
		case 'D':
			this.bossEntranceX = 1;
			break;
		}
		switch(dungeonState.getWidth()) {
		case 3:
			this.bossEntranceX = 1;
			break;
		case 5:
			this.bossEntranceX = 2;
			break;
		}
		this.bossEntranceY = dungeonY - 1;
		this.RelicState = dungeonState.getRelicState();
	}

	void startDungeonSimulating() {
		if(entranceState == 1) {
			for(int i = 0 ; i < hero; i++){
		        NextBlock(-1, -1, bossEntranceX, 0);
		    }
		} else if(entranceState == 3) {
			for(int i = 0 ; i < hero; i++){
				int s = rnd.nextInt(3) * 2;
				switch(s) {
				case 0:
			        NextBlock(-1, -1, 0, 0);
			        break;
				case 2:
			        NextBlock(-1, -1, bossEntranceX, 0);
			        break;
				case 4:
			        NextBlock(-1, -1, dungeonX - 1, 0);
				}
		    }
		}
	}
	
	private void NextBlock(int fromX, int fromY, int toX, int toY) {
		//no relic 1, 1, 1, 2 (right, forward, left, backward)
		//fake map 1, 1, 1, 7 (right, forward, left, backward)
		//real map 2, 1, 2, 0 (right, forward, left, backward)
		int k;
		int NewX = toX;
	    int NewY = toY;
		switch(RelicState) {
		case NoRelic:
			k = rnd.nextInt(5);
			if(toX == bossEntranceX && toY == bossEntranceY) {
				dungeon[bossEntranceX][bossEntranceY]++;
		    } else if(toX == 0 && k >= 0 && k <= 0) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == (dungeonY - 1) && k >= 1 && k <= 1) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toX == (dungeonX - 1) && k >= 2 && k <= 2) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == 0 && k >= 3 && k <= 4) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else {
		    	if(inRange(k, 0, 0)) {
	                NewX--;
		    	} else if (inRange(k, 1, 1)) {
	                NewY++;
		    	} else if (inRange(k, 2, 2)) {
	                NewX++;
		    	} else if (inRange(k, 3, 4)) {
	                NewY--;
		    	}
		        if(NewX == fromX && NewY == fromY) {
		            NextBlock(fromX, fromY, toX, toY);
		        } else {
		        	dungeon[toX][toY]++;
		            NextBlock(toX, toY, NewX, NewY);
		        }
		    }
			break;
		case FakeMap:
			k = rnd.nextInt(10);
			if(toX == bossEntranceX && toY == bossEntranceY) {
				dungeon[bossEntranceX][bossEntranceY]++;
		    } else if(toX == 0 && k >= 0 && k <= 0) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == (dungeonY - 1) && k >= 1 && k <= 1) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toX == (dungeonX - 1) && k >= 2 && k <= 2) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == 0 && k >= 3 && k <= 9) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else {
		    	if(inRange(k, 0, 0)) {
	                NewX--;
		    	} else if (inRange(k, 1, 1)) {
	                NewY++;
		    	} else if (inRange(k, 2, 2)) {
	                NewX++;
		    	} else if (inRange(k, 3, 9)) {
	                NewY--;
		    	}
		        if(NewX == fromX && NewY == fromY) {
		            NextBlock(fromX, fromY, toX, toY);
		        } else {
		        	dungeon[toX][toY]++;
		            NextBlock(toX, toY, NewX, NewY);
		        }
		    }
			break;
		case RealMap:
			k = rnd.nextInt(5);
			if(toX == bossEntranceX && toY == bossEntranceY) {
				dungeon[bossEntranceX][bossEntranceY]++;
		    } else if(toX == 0 && k >= 0 && k <= 1) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == (dungeonY - 1) && k >= 2 && k <= 2) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toX == (dungeonX - 1) && k >= 3 && k <= 4) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else {
		    	if(inRange(k, 0, 1)) {
		    		if(fromX == (dungeonX - 1) && isBackwardingWithRealMap) {
		    			isBackwardingWithRealMap = false;
		    		}
	                NewX--;
		    	} else if (inRange(k, 2, 2)) {
		    		if(NewY == 0) {
		    			isBackwardingWithRealMap = false;
		    		}
		    		NewY = isBackwardingWithRealMap ? NewY - 1 : NewY + 1;
		    	} else if (inRange(k, 3, 4)) {
		    		if(fromX == 0 && isBackwardingWithRealMap) {
		    			isBackwardingWithRealMap = false;
		    		}
	                NewX++;
		    	}
		    	if (toY == bossEntranceY && (toX == 0 || toX == (dungeonX - 1)) && (fromX == toX - 1 || fromX == toX + 1)) {
	        		dungeon[toX][toY]++;
	        		dungeon[toX][toY - 1]++;
	        		isBackwardingWithRealMap = true;
		    		if(toX == 0) {
		        		int b = rnd.nextInt(2);
		        		switch(b){
		        		case 0:
				        	NextBlock(0, toY - 1, toX, toY - 2);
		        			break;
		        		case 1:
		        			isBackwardingWithRealMap = false;
				        	NextBlock(0, toY - 1, toX + 1, toY - 1);
		        			break;
		        		}
		        	} else if(toX == (dungeonX - 1)){
		        		dungeon[toX][toY]++;
		        		dungeon[toX][toY - 1]++;
		        		int b = rnd.nextInt(2);
		        		switch(b){
		        		case 0:
				        	NextBlock(toX, toY - 1, toX, toY - 2);
		        			break;
		        		case 1:
		        			isBackwardingWithRealMap = false;
				        	NextBlock(toX, toY - 1, toX - 1, toY - 1);
		        			break;
		        		}
		        	}
			    } else if(NewX == fromX && NewY == fromY) {
		            NextBlock(fromX, fromY, toX, toY);
		        } else {
			        dungeon[toX][toY]++;
			        NextBlock(toX, toY, NewX, NewY);
		        }
		    }
			break;
		default:
			throw new Error("Not Reached");	
		}
	}

	void replaceProbebilityOf(JTextField[][] normalRoom) {
		for(int i = 0; i < dungeonX; i++) {
			for(int j = 0; j < dungeonY; j++) {
				normalRoom[i][j].setText(String.format("%.3f", (double)dungeon[i][j] / hero));
			}
		}
	}
	
	private boolean inRange(int num, int min, int max) {
		return num <= max && num >= min;
	}
}
