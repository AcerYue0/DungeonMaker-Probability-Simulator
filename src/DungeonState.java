public class DungeonState {
	private int depth;
	private int width;
	private char position;
	private RelicState relicState; 
	
	public DungeonState(int depth, int width, char position, RelicState relicState) {
		this.depth = depth;
		this.width = width;
		this.position = position;
		this.relicState = relicState;
	}
	
	public RelicState getRelicState() {
		return relicState;
	}

	public void setRelicState(RelicState relicState) {
		this.relicState = relicState;
	}

	public int getDepth() {
		return depth;
	}
	public void setDepthX(int depth) {
		this.depth = depth;
	}
	public int getWidth() {
		return width;
	}
	public void setWidthY(int width) {
		this.width = width;
	}
	public char getPosition() {
		return position;
	}
	public void setPosition(char position) {
		this.position = position;
	}
	
}
