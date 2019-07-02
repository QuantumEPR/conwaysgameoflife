package conwaysgameoflife;

import java.awt.Color;
import java.awt.Graphics;

/*
 * This class is the cell matrix
 */

/**
 * cellMatrix here has to be either a handler itself, or an single
 * object under a separate handler, for it handles all cells in the 
 * game. Cell life logic needs revamp before any other attempt.
 * Note: the correspondence between 
 * @author Zhewen
 *
 */

public class CellMatrix {
	private int height; // the height of the cell matrix
	private int width; // the width of the cell matrix
	private int[][] matrix; // the cell matrix
	
	public static final int CELL_SIZE = 16;
	
	//post: constructs a cell matrix from input
	public CellMatrix(int width, int height) {
		this.height = height / CELL_SIZE;
		this.width = width / CELL_SIZE;
		this.matrix = new int[this.height][this.width];
		
		// init with zeros
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.matrix[i][j] = 0;
			}
		}
		
		// Test purposes: a strip of cells like 000111000
		for (int i = 0; i < 3; i++) {
			this.matrix[30][10 + i] = 1;
		}
	}
	
	//post: apply the changes on the original matrix and transform it to a new matrix
	// 		ticks the matrix once
	public void tick() {
		int[][] newMatrix = new int[height][width];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				int lifeCount = getLifeNum(j, i);
				System.out.print(lifeCount);
				if (lifeCount <= 1 || lifeCount >= 4) {
					newMatrix[i][j] = 0;
				} else {
					newMatrix[i][j] = 1;
				}
			}
		}
		matrix = newMatrix;
	}
	
	//pre: takes in the x coordinate and y coordinate of a cell in the matrix
	//post: return a boolean indicating whether the cell is populated or not
	public boolean isPopulated(int x, int y) {
		return (getLifeNum(x, y) == 3);
	}	
	
	//pre: takes in the x coordinate and y coordinate of a cell in the matrix
	//post: return the life number around this particular cell
	public int getLifeNum(int x, int y) {
		int count = 0;
		
		/*to check whether the cell is dead or not, we just need to check the value in the
		matrix, if the value is 0 then it's dead, if the value is 1, then it lives.*/
		/*to check whether the cell is dead or not, we just need to check the value in the
		matrix, if the value is 0 then it's dead, if the value is 1, then it lives.*/
		
		// left
		if (x != 0) {
			count += matrix[y][x - 1];
		}
		
		//upper left corner
		if (x != 0 && y != 0) {
			count += matrix[y - 1][x - 1];
		}
		
		//lower left corner
		if (x != 0 && y != height - 1) {
			count += matrix[y + 1][x - 1];
		}
		
		//top
		if (y != 0) {
			count += matrix[y - 1][x];
		}
		
		//bottom
		if (y != height - 1) {
			count += matrix[y + 1][x];
		}
		
		//right
		if (x != width - 1) {
			count += matrix[y][x + 1];
		}
		
		//upper right corner
		if (x != width - 1 && y != 0) {
			count += matrix[y - 1][x + 1];
		}
		
		//lower right corner
		if (x != width - 1 && y != height - 1) {
			count += matrix[y + 1][x + 1];
		}
		
		return count;
		
		/**
		 * The following works the same as your implementation; 
		 * However the entire program is not working as intended.
		 */
//		for (int i = -1; i <= 1; i++) {
//			if (x + i >= 0 && x + i < height) {
//				for (int j = -1; j <= 1; j++) {
//					if (y + j >= 0 && y + j < width) {
//						count += matrix[x + i][y + j];
//					}
//				}
//			}
//		}
//		return count;
	}
	
	/**
	 * Populate/kill a cell at x, y
	 * @param x 
	 * @param y
	 */
	public void update(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			matrix[y][x] = (matrix[y][x] == 0) ? 1 : 0;
		}
	}
	
	//post: return the cell matrix
	public int[][] getMatrix() {
		return matrix;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			str.append(matrix[i] + "\n");
		}
		return str.toString();
	}
	
	/**
	 * Renders according cells
	 * @param g
	 */
	public void render(Graphics g) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				g.setColor(Color.yellow);
				if (matrix[i][j] == 1) {
					g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
	}
}
