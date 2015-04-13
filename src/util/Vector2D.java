package util;

import java.awt.Point;

public class Vector2D {
	private float x;
	private float y;
	
	public Vector2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Vector2D multiply(long deltaMilli) {
		return new Vector2D((deltaMilli*x)/1000.0f, (deltaMilli*y)/1000.0f);
	}
	
	public Point clamp() {
		return new Point(Math.round(x), Math.round(y));
	}

	public double getLength() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public void normalize() {
	    double length = getLength();
	 
	    if(length != 0){
	        x = (float) (x/length);
	        y = (float) (y/length);
	    }
	}
}
