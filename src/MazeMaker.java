import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//select a random cell to start
		Cell cell = maze.getCell(new Random().nextInt(width), new Random().nextInt(height));
		
		//call selectNextPath method with the randomly selected cell
		selectNextPath(cell);
		
		return maze;
	}

	private static void selectNextPath(Cell currentCell) {
		// mark current cell as visited
		currentCell.setBeenVisited(true);
		// check for unvisited neighbors
		ArrayList<Cell> unvisted = getUnvisitedNeighbors(currentCell);
		
		// if has unvisited neighbors,
		if(unvisted.size() > 0){
			int rand = randGen.nextInt(unvisted.size());
			uncheckedCells.push(unvisted.get(rand));
			removeWalls(currentCell, unvisted.get(rand));
			currentCell = unvisted.get(rand);
			currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
		}else{
			if(uncheckedCells.size() > 0){
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
			// select one at random.
			// push it to the stack
			// remove the wall between the two cells
			// make the new cell the current cell and mark it as visited
		
			//call the selectNextPath method with the current cell
			
		// if all neighbors are visited
			//if the stack is not empty
				// pop a cell from the stack
				// make that the current cell
		
				//call the selectNextPath method with the current cell
	}

	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() == c2.getX()){
			if(c1.getY() > c2.getY()){
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}else{
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		}
		if(c1.getY() == c2.getY()){
			if(c1.getX() > c2.getX()){
				c1.setWestWall(false);
				c2.setEastWall(false);
			}else{
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		int x = c.getX();
		int y = c.getY();
		if(x != 0){
			if(!maze.getCell((x-1), y).hasBeenVisited()){
				cells.add(maze.getCell((x-1), y));
			}
		}
		if(x != maze.getWidth() - 1){
			if(!maze.getCell((x+1), y).hasBeenVisited()){
				cells.add(maze.getCell((x+1), y));
			}
		}
		if(y != 0){
			if(!maze.getCell(x, (y-1)).hasBeenVisited()){
				cells.add(maze.getCell(x, (y-1)));
			}
		}
		if(y != maze.getHeight() - 1){
			if(!maze.getCell(x, (y+1)).hasBeenVisited()){
				cells.add(maze.getCell(x, (y+1)));
			}
		}
		return cells;
	}
}