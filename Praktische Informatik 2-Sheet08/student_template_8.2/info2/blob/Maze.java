package info2.blob;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import info2.blob.gui.MazeListener;

/**
 * This class represents the maze in which the blob is spawned.
 * It provides several methods e.g. to check for walls. Every cell
 * within the maze can have a cell state (encoded as an int value).
 * The cell state is 0 by default, which means that the cell is empty.
 * Specific blob related values are defined in the class Blob.
 * 
 * @author Alexander Conzelmann
 * @author Sebastian Otte
 */
public class Maze {

    public static final int DEFAULT_CELL_STATE = 0;
    
    private int rows;
    private int cols;
    private int lastCol;
    private int lastRow;

    private int goalX;
    private int goalY;
    
    private int[][] cells;
    
    private boolean[][] horizontalWalls;
    private boolean[][] verticalWalls;

    private Set<MazeListener> listeners = new HashSet<>();
    
    private int[][] accessMap;
    private int accessCtr;
    
    /**
     * Returns the x value of the goal position.
     */
    public int goalX() {
        return this.goalX;
    }

    /**
     * Returns the y value of the goal position.
     */
    public int goalY() {
        return this.goalY;
    }

    /**
     * Returns the number of rows in the maze.
     */
    public int getRows() {
        return this.rows;
    }
    
    /**
     * Returns the number of columns in the maze.
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * The checks whether the given position is the goal.
     * @param x The x value of the position of interest.
     * @param y The y value of the position of interest.
     * @return true if the goal is at (x,y), false otherwise.
     */
    public boolean isGoal(final int x, final int y) {
        return (this.goalX == x) && (this.goalY == y);
    }
    
    /**
     * Return the cell state of the given cell.
     * @param x The x value of the position of interest.
     * @param y The y value of the position of interest.
     * @return The int value representing the cell state.
     */
    public int getCellState(final int x, final int y) {
        return this.cells[y][x];
        
    }
    /**
     * Sets the cell state of the given cell. This methods also
     * caused a notification of possible listeners.
     * @param x The x value of the position of interest.
     * @param y The y value of the position of interest.
     * @param state The new cell state.
     */
    public void setCellState(final int x, final int y, final int state) {
        this.cells[y][x] = state;
        this.accessCtr++;
        if (this.accessMap[y][x] == 0) {
            this.accessMap[y][x] = this.accessCtr;
        }
        this.informUpdate();
    }
    
    /**
     * Checks whether there is a wall left of the given cell.
     * @param x The x value of the position of interest.
     * @param y The y value of the position of interest.
     * @return True if the is a wall left of (x, y), false otherwise. 
     */
    public boolean isWallLeft(final int x, final int y) {
        if (x == 0) return true;
        return this.horizontalWalls[y][x - 1];
    }

    /**
     * Checks whether there is a wall right of the given cell.
     * @param x The x value of the position of interest.
     * @param y The y value of the position of interest.
     * @return True if the is a wall right of (x, y), false otherwise. 
     */
    public boolean isWallRight(final int x, final int y) {
        if (x == lastCol) return true;
        return this.horizontalWalls[y][x];
    }


    /**
     * Checks whether there is a wall above the given cell.
     * @param x The x value of the position of interest.
     * @param y The y value of the position of interest.
     * @return True if the is a wall above (x, y), false otherwise. 
     */
    public boolean isWallAbove(final int x, final int y) {
        if (y == 0) return true;
        return this.verticalWalls[y - 1][x];
    }

    /**
     * Checks whether there is a wall below the given cell.
     * @param x The x value of the position of interest.
     * @param y The y value of the position of interest.
     * @return True if the is a wall below (x, y), false otherwise. 
     */
    public boolean isWallBelow(final int x, final int y) {
        if (y == lastRow) return true;
        return this.verticalWalls[y][x];
    }
    
    /**
     * Registers a MazeListener. MazeListeners will be informed
     * every time the state of the maze (it's cells) changes.
     * @param listener Instance of MazeListener.
     */
    public void registerListener(final MazeListener listener) {
        this.listeners.add(listener);
    }
    
    /**
     * Removes a given MazeListener from the list of intersted listeners.
     * @param listener Instance of MazeListener.
     */
    public void unregisterListener(final MazeListener listener) {
        this.listeners.remove(listener);
    }
    
    /**
     * Broadcasts an update to all listeners. This method will invoked
     * automatically whenever setCellState is used.
     */
    public void informUpdate() {
        for (MazeListener listener : this.listeners) {
            listener.onUpdate(this);
        }
    }

    /**
     * Return this current access value for the given cell. This methods
     * is mostly used for internal checks and GUI stuff and is not really
     * interesting for solving the blob exercise.
     * @param x The x value of the position of interest.
     * @param y The y value of the position of interest.
     * @return The access ctr value for (x, y).
     */
    public int getAccessValue(final int x, final int y) {
        return this.accessMap[y][x];
    }
    
    /**
     * Creates a empty maze with given dimension (cols x rows).
     * @param cols Number of columns in the maze.
     * @param rows Number of rows in the maze.
     */
    private Maze(final int cols, final int rows) {
        
        this.goalX = -1;
        this.goalY = -1;
        this.rows = rows;
        this.cols = cols;
        this.lastRow = rows - 1;
        this.lastCol = cols - 1;
        this.horizontalWalls = new boolean[rows][cols - 1];
        this.verticalWalls = new boolean[rows - 1][cols];
        this.accessMap = new int[rows][cols];
        this.cells = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < (cols - 1)) {
                    this.horizontalWalls[i][j] = true;
                }
                if (i < (rows - 1)) {
                    this.verticalWalls[i][j] = true;
                }
            }
        }
    }
    
    /**
     * This method creates a random maze with a "secret algorithm",
     * which ensures that the maze has a nice structure and that the
     * goal is reachable. The algorithm starts generating the maze 
     * at a given starting position. 
     * @param rnd Instance of Random. 
     * @param cols Number of columns in the maze.
     * @param rows Number of rows in the maze.
     * @param startX The x value of the starting position. 
     * @param startY The y value of the starting position.
     * @param goalSteps Number of steps (since the start) after
     *                  which the goal is positioned.
     * @return The freshly initialized.
     */
    public static Maze generateRandomMaze(
        final Random rnd,
        final int cols,
        final int rows,
        final int startX,
        final int startY,
        final int goalSteps
    ) {
        final Maze maze = new Maze(cols, rows);
        // Deobfuscation of the "MAGIC CODE"
        boolean[][] visited = new boolean[rows][cols];
        int[][] stack = new int[rows * cols][];
        int top = 0;
        int[] startCell = { startY, startX };
        visited[startCell[0]][startCell[1]] = true;
        stack[top++] = startCell;
        int steps = 0;

        while (top > 0) {
            int[] currentCell = stack[--top];
            if (steps == goalSteps) {
                maze.goalY = currentCell[0];
                maze.goalX = currentCell[1];
            }
            steps++;

            int[][] neighbors = new int[4][];
            int neighborCount = 0;

            // Nachbarn bestimmen (oben, unten, links, rechts)
            if (currentCell[0] - 1 >= 0 && !visited[currentCell[0] - 1][currentCell[1]]) {
                neighbors[neighborCount++] = new int[] { currentCell[0] - 1, currentCell[1] };
            }
            if (currentCell[0] + 1 < rows && !visited[currentCell[0] + 1][currentCell[1]]) {
                neighbors[neighborCount++] = new int[] { currentCell[0] + 1, currentCell[1] };
            }
            if (currentCell[1] - 1 >= 0 && !visited[currentCell[0]][currentCell[1] - 1]) {
                neighbors[neighborCount++] = new int[] { currentCell[0], currentCell[1] - 1 };
            }
            if (currentCell[1] + 1 < cols && !visited[currentCell[0]][currentCell[1] + 1]) {
                neighbors[neighborCount++] = new int[] { currentCell[0], currentCell[1] + 1 };
            }

            // Falls es unbesuchte Nachbarn gibt, wähle per Zufall einen aus
            if (neighborCount > 0) {
                stack[top++] = currentCell; 
                int r = rnd.nextInt(neighborCount);
                int[] nextCell = neighbors[r];
                visited[nextCell[0]][nextCell[1]] = true;
                stack[top++] = nextCell;

                // Entferne Mauer zwischen currentCell und nextCell
                if (currentCell[0] == nextCell[0]) {
                    maze.horizontalWalls[currentCell[0]][Math.min(currentCell[1], nextCell[1])] = false;
                } else {
                    maze.verticalWalls[Math.min(currentCell[0], nextCell[0])][currentCell[1]] = false;
                }
            }
        }
        return maze;
    }
}