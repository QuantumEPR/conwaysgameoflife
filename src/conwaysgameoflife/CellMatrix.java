package conwaysgameoflife;


/*
 * This class is the cell matrix
 */

public class CellMatrix {
	private int height; // the height of the cell matrix
	private int width; // the width of the cell matrix
	private int[][] matrix; // the cell matrix
	
	//post: constructs a cell matrix from input
	public CellMatrix(int height, int width, int[][] matrix ) {
		this.height = height;
		this.width = width;
		this.matrix = matrix;
	}
	
	//post: apply the changes on the original matrix and transform it to a new matrix
	public void nextMatrix() {
		int[][] newMatrix = new int[height][width];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				int lifeCount = getLifeNum(j, i);
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
	}
	
	//post: returns the height of the cell matrix
	public int getHeight() {
		return height;
	}
	
	//post: returns the width of the cell matrix
	public int getWidth() {
		return width;
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
}
