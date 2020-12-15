package simpledraw;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

/**
 * The tool to select, move and delete Shapes in the Drawing
 * @author Rémi Bastide
 * @version 1.0
 */

public class SelectionTool
	extends DrawingTool {
	private Shape mySelectedShape = null;
	private Point myLastPoint;
	private List<Shape> mySelectedShapes = new LinkedList<Shape>();

	public SelectionTool(DrawingPanel panel) {
		super(panel);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'g') {
			if (mySelectedShape != null) {
				myDrawing.addShapeGroup(new ShapeGroup(mySelectedShapes));
				System.out.println("Group");

				System.out.println(myDrawing.getShapeGroups().size());
			}
		}
		if (e.getKeyChar() == KeyEvent.VK_DELETE) {
			if (mySelectedShape != null) {
				myDrawing.deleteShape(mySelectedShape);
				myPanel.repaint();
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		Shape pickedShape = myDrawing.pickShapeAt(e.getPoint());
		myLastPoint = e.getPoint();
		if(e.getButton() == MouseEvent.BUTTON3) {
			System.out.println("Right click");
			if (pickedShape != null) {
				mySelectedShape = pickedShape;
				mySelectedShape.setSelected(true);
				if(!mySelectedShapes.contains(mySelectedShape)) {
					mySelectedShapes.add(mySelectedShape);
				}
				System.out.println(mySelectedShapes.size());
			} else {
				myDrawing.clearSelection();
				mySelectedShapes.clear();
				System.out.println(mySelectedShapes.size());
			}
			
		}
		

		if(e.getButton() == MouseEvent.BUTTON1) {
			System.out.println("Left click");
			if (mySelectedShape != null) {
				myDrawing.clearSelection();
			}
			mySelectedShape = pickedShape;
			if (mySelectedShape != null) {
				List<ShapeGroup> groups = myDrawing.getShapeGroups();
				for(ShapeGroup sg : groups) {
					List<Shape> groupedShapes = sg.getShapes();
					if(groupedShapes.contains(mySelectedShape)) {
						for(Shape s : groupedShapes) {
							s.setSelected(true);
						}
					}
				}
				if(!mySelectedShape.isSelected()) {
					mySelectedShape.setSelected(true);
				}
				myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
					MOVE_CURSOR));
			}
		}
		myPanel.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		Shape pickedShape = myPanel.myDrawing.pickShapeAt(e.getPoint());
		if (pickedShape != null) {
			myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
				HAND_CURSOR));
		} else {
			myPanel.setCursor(Cursor.getDefaultCursor());
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (mySelectedShape != null) {
			mySelectedShape.translateBy(
				e.getX() - myLastPoint.x,
				e.getY() - myLastPoint.y
				);
			myLastPoint = e.getPoint();
        		myPanel.repaint();
		}
	}

	void draw(Graphics2D g) {
	}

}
