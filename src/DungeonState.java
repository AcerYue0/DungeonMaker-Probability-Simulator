public class DungeonState{
	private static int depth;
	private static int width;
	
	private static char position;
	private static int entranceState;
	private static RelicState relicState;
	private Tile[][] tile;
	
	public DungeonState(int Depth, int Width, char Position, RelicState RelicState, int EntranceState) {
		depth = Depth;
		width = Width;
		position = Position;
		relicState = RelicState;
		entranceState = EntranceState;
	}
	
	public static RelicState getRelicState() {
		return relicState;
	}

	public void setRelicState(RelicState RelicState) {
		relicState = RelicState;
	}

	public static int getDepth() {
		return depth;
	}
	public void setDepthX(int Depth) {
		depth = Depth;
	}
	public static int getWidth() {
		return width;
	}
	public void setWidthY(int Width) {
		width = Width;
	}
	public static char getPosition() {
		return position;
	}
	public void setPosition(char Position) {
		position = Position;
	}
	public static int getEntranceState() {
		return entranceState;
	}
	public static void setEntranceState(int EntranceState) {
		entranceState = EntranceState;
	}

	public Tile[][] getTile() {
		return tile;
	}

	public void setTile(Tile[][] tile) {
		this.tile = tile;
	}
	
}