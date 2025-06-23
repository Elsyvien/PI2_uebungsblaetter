package info2.blob;

public class Blob {
    
    public static final int CELL_STATE_FREE = Maze.DEFAULT_CELL_STATE;
    public static final int CELL_STATE_BLOB_FRESH = CELL_STATE_FREE + 1;
    public static final int CELL_STATE_BLOB_DEAD = CELL_STATE_FREE + 2; 
    
    public boolean infest(final Maze maze, final int x, final int y) {
        if (maze.isGoal(x, y) && maze.getCellState(x, y) == CELL_STATE_FREE) {
            maze.setCellState(x, y, CELL_STATE_BLOB_DEAD);
            System.out.println("Goal reached at (" + x + ", " + y + ")");
            return true;
        }

        if (maze.getCellState(x, y) == CELL_STATE_FREE) {
            maze.setCellState(x, y, CELL_STATE_BLOB_FRESH);
            System.out.println("Infesting cell at (" + x + ", " + y + ")");

            if (!maze.isWallLeft(x, y) && infest(maze, x - 1, y)) {
                return true;
            }
            if (!maze.isWallRight(x, y) && infest(maze, x + 1, y)) {
                return true;
            }
            if (!maze.isWallAbove(x, y) && infest(maze, x, y - 1)) {
                return true;
            }
            if (!maze.isWallBelow(x, y) && infest(maze, x, y + 1)) {
                return true;
            }
        }
        return false;
    }
}


