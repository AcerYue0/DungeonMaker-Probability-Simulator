import java.util.Random;

import javax.swing.JTextField;

public class Simulation {
	
	static Random rnd = new Random(System.currentTimeMillis());
	private RelicState RelicState;
	private int bossEntranceX;
	private int bossEntranceY;

	private int hero;
	private int dungeon[][];
	private boolean isBackwardingWithRealMap = false;
	
	Simulation(int heroCount){
		this.dungeon = new int[DungeonState.getWidth()][DungeonState.getDepth()];
		this.hero = heroCount;
		setBossEntrance();
	}
	
	private void setBossEntrance() {
		switch(DungeonState.getPosition()) {
		case 'U':
			this.bossEntranceX = 2;
			break;
		case 'D':
			this.bossEntranceX = 1;
			break;
		}
		switch(DungeonState.getWidth()) {
		case 3:
			this.bossEntranceX = 1;
			break;
		case 5:
			this.bossEntranceX = 2;
			break;
		}
		this.bossEntranceY = DungeonState.getDepth() - 1;
		this.RelicState = DungeonState.getRelicState();
	}

	void startDungeonSimulating() {
		if(DungeonState.getEntranceState() == 1) {
			for(int i = 0 ; i < hero; i++){
		        NextBlock(-1, -1, bossEntranceX, 0);
		    }
		} else if(DungeonState.getEntranceState() == 3) {
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
			        NextBlock(-1, -1, DungeonState.getWidth() - 1, 0);
				}
		    }
		}
	}
	
	private void NextBlock(int fromX, int fromY, int toX, int toY) {
		//no relic 1, 1, 1, 2 (north, west, south, east)
		//fake map 3, 2, 3, 2 (north, west, south, east)
		//real map 2, 1, 2, 0 (north, west, south, east)
		int k;
		int NewX = toX;
	    int NewY = toY;
		switch(RelicState) {
		case NoRelic:
			k = rnd.nextInt(5);
			if(toX == bossEntranceX && toY == bossEntranceY) {
				dungeon[bossEntranceX][bossEntranceY]++;
		    } else if(toX == 0 && inRange(k, 0, 0)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == (DungeonState.getDepth() - 1) && inRange(k, 1, 1)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toX == (DungeonState.getWidth() - 1) && inRange(k, 2, 2)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == 0 && inRange(k, 3, 4)) {
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
		    } else if(toX == 0 && inRange(k, 0, 2)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == (DungeonState.getDepth() - 1) && inRange(k, 3, 4)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toX == (DungeonState.getWidth() - 1) && inRange(k, 5, 7)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == 0 && inRange(k, 8, 9)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else {
		    	if(inRange(k, 0, 2)) {
	                NewX--;
		    	} else if (inRange(k, 3, 4)) {
	                NewY++;
		    	} else if (inRange(k, 5, 7)) {
	                NewX++;
		    	} else if (inRange(k, 8, 9)) {
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
		    } else if(toX == 0 && inRange(k, 0, 1)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toY == (DungeonState.getDepth() - 1) && inRange(k, 2, 2)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else if(toX == (DungeonState.getWidth() - 1) && inRange(k, 3, 4)) {
		        NextBlock(fromX, fromY, toX, toY);
		    } else {
		    	if(inRange(k, 0, 1)) {
		    		if(fromX == (DungeonState.getWidth() - 1) && isBackwardingWithRealMap) {
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
		    	if (toY == bossEntranceY && (toX == 0 || toX == (DungeonState.getWidth() - 1)) && (fromX == toX - 1 || fromX == toX + 1)) {
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
		        	} else if(toX == (DungeonState.getWidth() - 1)){
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

	void replaceProbebilityOf(Tile[][] tile) {
		for(int i = 0; i < DungeonState.getWidth(); i++) {
			for(int j = 0; j < DungeonState.getDepth(); j++) {
				tile[i][j].setText(String.format("%7.3f", (double)dungeon[i][j] / hero));
			}
		}
	}
	
	private boolean inRange(int num, int min, int max) {
		return num <= max && num >= min;
	}
}