package simpledraw;

import java.util.List;

public class ShapeGroup {
	
	private List<Shape> shapes;


	public ShapeGroup(List<Shape> shapes) {
		setShapes(shapes);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	
	
}
