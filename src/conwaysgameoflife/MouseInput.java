package conwaysgameoflife;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse Input In Progress
 * @author Zhewen
 *
 */
public class MouseInput extends MouseAdapter {
	private CellMatrix cellMatrix;
	
	public MouseInput(CellMatrix cellMatrix) {
		this.cellMatrix = cellMatrix;
	}
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / 16;
		int y = e.getY() / 16;
		cellMatrix.update(x, y);
		System.out.print(x + " " + y);
	}
}
