package ReversiCode;

import java.util.Vector;

public class Point {
	private int x;
	private int y;
	private Vector<Point>flowDirection;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.flowDirection = new Vector<>();
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
		
		this.flowDirection = new Vector<>();
		// Copy all the directions vector with new allocations.
	    for (int i = 0; i < p.flowDirection.size(); ++i) {
	        Point pointToInsert = new Point(p.flowDirection.elementAt(i));
	        flowDirection.addElement(pointToInsert);
	    }
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {

		this.y = y;
	}

	public boolean isEqual(Point p) {
	    return (x == p.getX() && y == p.getY());
	}
	
	void insertFlowPoint(Point p) {
	    for (int i = 0; i < flowDirection.size(); ++i)
	        if (flowDirection.elementAt(i).isEqual(p))
	            return;
	    flowDirection.addElement(p);
	}
	
	public Vector<Point> getDirVector() {
		return flowDirection;
	}

	public String toString() {
	    return "(" + (x + 1) + ',' + (y + 1) + ")";
	}
	
	
}
